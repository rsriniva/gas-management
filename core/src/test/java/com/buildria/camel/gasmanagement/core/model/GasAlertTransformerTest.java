package com.buildria.camel.gasmanagement.core.model;

import com.buildria.camel.gasmanagement.core.transformer.GasAlertTransformer;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for GasAlertTransformer
 * 
 * @author Seiji Sogabe
 */
public class GasAlertTransformerTest {
    
    private final Logger log = LoggerFactory.getLogger(GasAlertTransformerTest.class);
    
    private final GasAlertTransformer target = new GasAlertTransformer();
    
    @Rule
    public MethodRule watchman = new TestWatchman() {

        @Override
        public void starting(FrameworkMethod method) {
            log.info("=== {} starting ...", method.getName());
        }

        @Override
        public void finished(FrameworkMethod method) {
            log.info("=== {} finished.", method.getName());
        }
    
    };   
    
    /**
     * Test of toMessage method, of class GasAlert.
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testToMessage() throws Exception {
        GasAlert instance = new GasAlert("2014/10/18 12:00:00", "99", 90);
        String expected = "2014/10/18 12:00:0099090";
        assertThat(target.toMessage(instance), is(expected));
    }

    /**
     * Test of toModel method, of class GasAlert.
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testToModel() throws Exception {
        String message = "2014/10/18 12:00:0099090";
        GasAlert result = target.toModel(message);
        
        assertThat(result.getDate(), is("2014/10/18 12:00:00"));
        assertThat(result.getType(), is("99"));
        assertThat(result.getTension(), is(90));
    }
    
}
