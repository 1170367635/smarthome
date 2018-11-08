package com.briup.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 储存温度、湿度、co2、光照强度这几类数据
 * @author zhijie
 * @created 2018年9月20日 上午9:25:27
 */
public class Environment implements Serializable {
	/**
	 * 串行化版本ID
	 */
	private static final long serialVersionUID = 6006461328629531624L;
	/**环境类型*/
	private String name;
	/**发送端ID*/
	private Integer srcId;
	/**树莓派ID*/
	private Integer desId;
	/**设备id*/
	private Integer devId;
	/**传感器id*/
	private Integer sensorAddress;
	/**传感器个数*/
	private Integer counter;
	/**指令3表示接受，16表示接受*/
	private Integer cmd;
	/**环境数据*/
	private Double data;
	/**是否接收成功1表示成功，0表示没有*/
	private Integer status;
	/**接收数据时间*/
	private Timestamp date;
	
	/** 无参构造器*/
	public Environment() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造器
	 * @param name
	 * @param srcId
	 * @param dstId
	 * @param devId
	 * @param sensorAddress
	 * @param count
	 * @param cmd
	 * @param data
	 * @param status
	 * @param date
	 */
	public Environment(String name, Integer srcId, Integer desId, Integer devId, Integer sensorAddress,
			Integer counter, Integer cmd, Double data, Integer status, Timestamp date) {
		super();
		this.name = name;
		this.srcId = srcId;
		this.desId = desId;
		this.devId = devId;
		this.sensorAddress = sensorAddress;
		this.counter = counter;
		this.cmd = cmd;
		this.data = data;
		this.status = status;
		this.date = date;
	}
	/**
	 * 取出name
	 * @return name 环境类型
	 */
	public String getName() {
		return name;
	}
	/**
	 * 传入name
	 * @param 环境类型
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Integer getSrcId() {
		return srcId;
	}

	public void setSrcId(Integer srcId) {
		this.srcId = srcId;
	}

	public Integer getDesId() {
		return desId;
	}

	public void setDesId(Integer desId) {
		this.desId = desId;
	}

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public Integer getSensorAddress() {
		return sensorAddress;
	}

	public void setSensorAddress(Integer sensorAddress) {
		this.sensorAddress = sensorAddress;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Integer getCmd() {
		return cmd;
	}

	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Environment [name=" + name + ", srcId=" + srcId + ", desId=" + desId + ", devId="
				+ devId + ", sensorAddress=" + sensorAddress + ", counter=" + counter + ", cmd=" + cmd + ", data=" + data
				+ ", status=" + status + ", date=" + date + "]"+"\n";
	}
	
	
}
