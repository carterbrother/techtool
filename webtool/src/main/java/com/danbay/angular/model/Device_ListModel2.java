package com.danbay.angular.model;

import java.io.Serializable;

/**
 * Created by carter on 2017/5/24. Copyright © 2016 －2017 旦倍科技
 * 设备实体
 */
public class Device_ListModel2 implements Serializable {

    private Long  id = 0L;
    private java.time.LocalDateTime  create_time= java.time.LocalDateTime.now();
    private java.time.LocalDateTime  update_time= java.time.LocalDateTime.now();
    private boolean         delete_state=false;
    private String          device_no="";
    private String          sn_id="";
    
    public Device_ListModel2(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.time.LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(java.time.LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public java.time.LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(java.time.LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public boolean isDelete_state() {
        return delete_state;
    }

    public void setDelete_state(boolean delete_state) {
        this.delete_state = delete_state;
    }

    @Override
    public String toString() {
        return "Device_ListModel2{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", delete_state=" + delete_state +
                '}';
    }
}
