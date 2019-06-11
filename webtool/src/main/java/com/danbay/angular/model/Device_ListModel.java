package com.danbay.angular.model;

import java.io.Serializable;

/**
 * Created by 卡特 on 2017-05-24. Copyright © 2016 －2017 旦倍科技
 * 设备表实体类
 */
public class Device_ListModel  implements Serializable {

	private	Long	id	=0L;
	private	java.time.LocalDateTime	create_time	=java.time.LocalDateTime.now();
	private	java.time.LocalDateTime	update_time	=java.time.LocalDateTime.now();
	private	boolean	delete_state	=false;
	private	String	device_no	="";
	private	String	sn_id	="";

	public Device_ListModel(){}

	public void setId(Long	id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setCreate_time(java.time.LocalDateTime	create_time){
		this.create_time = create_time;
	}

	public java.time.LocalDateTime getCreate_time(){
		return create_time;
	}

	public void setUpdate_time(java.time.LocalDateTime	update_time){
		this.update_time = update_time;
	}

	public java.time.LocalDateTime getUpdate_time(){
		return update_time;
	}

	public void setDelete_state(boolean	delete_state){
		this.delete_state = delete_state;
	}

	public boolean getDelete_state(){
		return delete_state;
	}

	public void setDevice_no(String	device_no){
		this.device_no = device_no;
	}

	public String getDevice_no(){
		return device_no;
	}

	public void setSn_id(String	sn_id){
		this.sn_id = sn_id;
	}

	public String getSn_id(){
		return sn_id;
	}

	@Override
	public String toString() {
		return "Device_ListModel{"+
				" ,id=" +id +
				" ,create_time=" +create_time +
				" ,update_time=" +update_time +
				" ,delete_state=" +delete_state +
				" ,device_no=" +device_no +
				" ,sn_id=" +sn_id +
				"};";
	}

}