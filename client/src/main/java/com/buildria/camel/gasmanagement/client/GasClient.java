package com.buildria.camel.gasmanagement.client;

import com.buildria.camel.gasmanagement.core.model.GasAlert;
import com.buildria.camel.gasmanagement.core.transformer.GasAlertTransformer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.dataset.DataSetSupport;
import org.apache.camel.main.Main;

public class GasClient {

    private static final int DEFAULT_PORT = 9999;

    private static final int DATA_MAX = 1000;
    
    private final int port;

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length == 0) {
            port = DEFAULT_PORT;
        } else {
            port = Integer.parseInt(args[0]);
        }

        GasClient client = new GasClient(port);
        client.boot();
    }

    public GasClient(int port) {
        this.port = port;
    }

    private void boot() throws Exception {
        Main main = new Main();
        main.enableHangupSupport();

        GasDataSet gasDateSet = new GasDataSet(DATA_MAX);
        main.bind("gasDataSet", gasDateSet);
        GasAlertTransformer gasAlertTransformer = new GasAlertTransformer();
        main.bind("gasAlertTransformer", gasAlertTransformer);
        
        main.addRouteBuilder(new MyRouteBuilder(port));
        main.run();
    }

    private static class MyRouteBuilder extends RouteBuilder {

        private final int port;

        public MyRouteBuilder(int port) {
            this.port = port;
        }        
        
        @Override
        public void configure() throws Exception {
            from("dataset:gasDataSet?produceDelay=5000").beanRef("gasAlertTransformer", "toMessage").log("$simple{body}")
                    .to("netty:tcp://127.0.0.1:" + port + "?sync=false&textline=true&delimiter=LINE&encoding=ISO-8859-1");
        }

    }

    private static class GasDataSet extends DataSetSupport {

        private static final String DATEFORMAT = "yyyy/MM/dd HH:mm:ss";
        
        private static final int TYPE_MAX = 99;

        private static final int TENSION_MAX = 999;
        
        public GasDataSet(int size) {
            super(size);
        }
        
        @Override
        protected Object createMessageBody(long messageIndex) {
            String id = String.valueOf(messageIndex);
            
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
            String dateValue = sdf.format(date);
            
            Random rand = new Random(System.currentTimeMillis());
            String type = String.format("%02d", rand.nextInt(TYPE_MAX));
            
            int tension = rand.nextInt(TENSION_MAX);
            
            return new GasAlert(id, dateValue, type, tension);
        }
        
    }
}
