package com.buildria.camel.gasmanagement.core.model;

import java.io.Serializable;

/**
 * アラートモデル
 *
 * @author sogabe
 */
public class GasAlert implements Serializable {

    /**
     * 発生年月日時分秒の長さ。
     */
    public static final int DATE_LENGTH = 19;
    
    /**
     * 種別の長さ。
     */
    public static final int TYPE_LENGTH = 2;
    
    /**
     * ID
     */
    private transient String id;

    /**
     * 発生年月日時分秒(yyyy-MM-dd HH:mm:ss) 19桁
     */
    private String date;

    /**
     * 種別 1桁
     */
    private String type;

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
     */
    public GasAlert(String date, String type) {
        this(null, date, type);
    }

    /**
     * コンストラクタ
     *
     * @param id ID
     * @param date 発生年月日
     * @param type 種別
     */
    public GasAlert(String id, String date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    @Override
    public String toString() {
        return "GasAlert{" + "id=" + id + ", date=" + date + ", type=" + type + '}';
    }
    
}
