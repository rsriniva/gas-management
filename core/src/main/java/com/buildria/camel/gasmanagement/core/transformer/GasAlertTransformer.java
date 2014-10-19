package com.buildria.camel.gasmanagement.core.transformer;

import com.buildria.camel.gasmanagement.core.model.GasAlert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GasAlertモデルと電文の変換を行うクラス
 * 
 * @author Seiji Sogabe
 */
public class GasAlertTransformer implements Transformer<GasAlert>{
   
    private static final Logger LOG = LoggerFactory.getLogger(GasAlertTransformer.class);
    
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
