package com.buildria.camel.gasmanagement.core.transformer;

import com.buildria.camel.gasmanagement.core.model.GasAlert;

public class GasAlertTransformer implements Transformer<GasAlert> {

    @Override
    public String toMessage(GasAlert model) throws TransformException {
        if (model == null) {
            throw new IllegalArgumentException("モデル未設定");
        }

        StringBuilder msg = new StringBuilder();
        msg.append(model.getDate());
        msg.append(model.getType());

        return msg.toString();
    }

    @Override
    public GasAlert toModel(String message) throws TransformException {
        if (message == null) {
            throw new IllegalArgumentException("メッセージ未設定");
        }

        GasAlert model = new GasAlert();
        model.setDate(message.substring(0, GasAlert.DATE_LENGTH));
        model.setType(message.substring(GasAlert.DATE_LENGTH, GasAlert.TYPE_LENGTH));

        return model;
    }

}
