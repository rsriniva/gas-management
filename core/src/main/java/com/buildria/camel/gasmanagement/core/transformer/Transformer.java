package com.buildria.camel.gasmanagement.core.transformer;

/**
 * メッセージとモデルを変換するインタフェース
 *
 * @author sogabe
 * @param <M> モデル
 */
public interface Transformer<M> {

    /**
     * モデルをメッセージに変換します。
     * 
     * @param model モデル
     * @return メッセージ
     * @throws TransformException 変換例外 
     */
    String toMessage(M model) throws TransformException;

    /**
     * メッセージをモデルに変換します。
     * 
     * @param message　メッセージ
     * @return モデル
     * @throws TransformException　変換例外 
     */
    M toModel(String message) throws TransformException;
}
