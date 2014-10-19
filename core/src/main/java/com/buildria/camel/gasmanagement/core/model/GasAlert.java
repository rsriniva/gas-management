package com.buildria.camel.gasmanagement.core.model;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * アラートモデル
 *
 * @author sogabe
 */
public class GasAlert implements Serializable {

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
    
}
