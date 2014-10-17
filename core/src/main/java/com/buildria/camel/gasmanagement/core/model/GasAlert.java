package com.buildria.camel.gasmanagement.core.model;

import com.buildria.camel.gasmanagement.core.transformer.TransformException;
import com.buildria.camel.gasmanagement.core.transformer.Transformer;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アラートモデル
 *
 * @author sogabe
 */
public class GasAlert implements Transformer<GasAlert>, Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(GasAlert.class);
    
    /**
     * ID
     */
    private String id;

    /**
     * 発生年月日時分秒(yyyy/MM/dd HH:mm:ss) 19桁
     */
    private String date;

    /**
     * 種別 1桁
     */
    private String type;
    
    /**
     * ガス圧(pa) 3 桁
     */
    private int tension;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTension() {
        return tension;
    }

    public void setTension(int tension) {
        this.tension = tension;
    }
    
    /**
     * コンストラクタ
     */
    public GasAlert() {
        //
    }

    /**
     * コンストラクタ
     *
     * @param date 発生年月日
     * @param type 種別
     * @param tension ガス圧
     */
    public GasAlert(String date, String type, int tension) {
        this(null, date, type, tension);
    }

    /**
     * コンストラクタ
     *
     * @param id ID
     * @param date 発生年月日
     * @param type 種別
     * @param tension ガス圧
     */
    public GasAlert(String id, String date, String type, int tension) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.tension = tension;
    }

    @Override
    public String toString() {
        return "GasAlert{" + "id=" + id + ", date=" + date + ", type=" + type + ", tension=" + tension + '}';
    }

    @Override
    public String toMessage(GasAlert model) throws TransformException {
        if (model == null) {
            throw new IllegalArgumentException("モデル未設定");
        }

        StringBuilder msg = new StringBuilder();
        msg.append(model.getDate());
        msg.append(model.getType());
        msg.append(String.format("%03d", model.getTension()));

        return msg.toString();
    }

    @Override
    public GasAlert toModel(String message) throws TransformException {
        if (message == null) {
            throw new IllegalArgumentException("メッセージ未設定");
        }
        LOG.debug("メッセージ: [{}] サイズ: [{}]", new Object[] { message, message.length() });
        
        GasAlert model = new GasAlert();
        model.setDate(message.substring(0, 19));
        model.setType(message.substring(19, 21));
        model.setTension(Integer.parseInt(message.substring(21, 24)));

        return model;
    }
    
}
