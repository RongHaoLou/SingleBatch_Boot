package com.indusfo.spc.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义响应结构 isOk():默认响应状态码为200
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONObject implements Serializable {
	private static final long serialVersionUID = 1L;

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务状态
	/*
	 * 200 成功 400 参数错误
	 */
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;


	/**
	 * yujn 返回的记录数 例：查询操作时，用于封装存储过程的L_COUNTS.以此来回显页面总条数
	 */
	// wanghui 为null时不返回
	@JsonInclude(Include.NON_NULL)
	private Integer lCounts;


	public Boolean isOk() {
		return this.status == 200;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getlCounts() {
		return lCounts;
	}

	public void setlCounts(Integer lCounts) {
		this.lCounts = lCounts;
	}

	public JSONObject() {

	}

	/**
	 * 构造函数,默认,状态码200,数据null JSONObject(200,msg,null)
	 *
	 * @param msg
	 * @author 王辉
	 *
	 * @Version 修改时间:2018年10月16日,下午4:31:50
	 */
	public JSONObject(String msg) {
		this.status = 200;
		this.msg = msg;
	}

	/**
	 * 构造函数,默认：状态码200，响应参数OK JSONObject(200,OK,数据)
	 *
	 * @param data
	 */
	public JSONObject(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	/**
	 * 构造函数,默认：状态码200
	 *
	 * @param msg
	 * @param data
	 * @author 王辉
	 *
	 * @Version 修改时间:2018年10月19日,下午2:29:11
	 */
	public JSONObject(String msg, Integer data) {
		this.status = 200;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * yujn 构造函数,默认：状态码200，响应参数OK，返回记录数lCounts JSONObject(200,OK,数据)
	 *
	 * @param data
	 */
	public JSONObject(Object data, Integer lCounts) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
		this.lCounts = lCounts;
	}

	/**
	 * 构造函数 JSONObject(状态码,响应消息,数据)
	 *
	 * @param status
	 * @param msg
	 * @param data
	 */
	public JSONObject(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	/***
	 * 构造函数 JSONObject(响应消息,数据,影响行数)
	 *  
	 * @author xuz
	 * @date 2018/11/23 1:01 PM
	 * @param [msg, data, lCounts]
	 * @return 
	 */
	public JSONObject(String msg, Object data, Integer lCounts) {
		this.status = 200;
		this.msg = msg;
		this.data = data;
		this.lCounts = lCounts;
	}


	/**
	 * 静态方法 return一个JSONObject(状态码,响应消息,数据)
	 * 
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	public static JSONObject build(Integer status, String msg, Object data) {
		return new JSONObject(status, msg, data);
	}

	/**
	 * 静态方法,默认：状态码200，响应参数OK return一个JSONObject(200,OK,null)
	 * 
	 * @return
	 */
	public static JSONObject oK() {
		return new JSONObject(200, "OK", null);
	}

	/**
	 * 静态方法,默认：状态码200，响应参数msg return一个JSONObject(200,响应参数,null)
	 * 
	 * @param data
	 * @return
	 */
	public static JSONObject oK(String msg) {
		return new JSONObject(msg);
	}

	/**
	 * 静态方法,默认：状态码200，响应参数OK return一个JSONObject(200,OK,数据)
	 * 
	 * @param data
	 * @return
	 */
	public static JSONObject oK(Object data) {
		return new JSONObject(data);
	}

	/**
	 * 静态方法,默认：状态码200
	 * 
	 * @param msg
	 * @param data
	 * @return
	 * @author 王辉
	 * 
	 * @Version 修改时间:2018年10月19日,下午2:30:16
	 */
	public static JSONObject oK(String msg, Integer data) {
		return new JSONObject(msg, data);
	}

	/**
	 * yujn 静态方法,默认：状态码200，响应参数OK return一个JSONObject(200,OK,数据,记录数)
	 * 
	 * @param data
	 * @return
	 */
	public static JSONObject oK(Object data, Integer lCounts) {
		return new JSONObject(data, lCounts);
	}

	/***
	 * 静态方法,默认：状态码200，响应参数msg return一个JSONObject(200,响应消息,数据,影响行数)
	 *  
	 * @author xuz
	 * @date 2018/11/23 1:05 PM
	 * @param [msg, data, lCounts]
	 * @return com.indusfo.spc.vo.JSONObject
	 */
	public static JSONObject oK(String msg, Object data, Integer lCounts) {
		return new JSONObject(msg, data, lCounts);
	}

	/**
	 * 静态方法 return一个JSONObject(状态码,响应消息,null)
	 * 
	 * @param status
	 * @param msg
	 * @return
	 */
	public static JSONObject build(Integer status, String msg) {
		return new JSONObject(status, msg, null);
	}



	/**
	 * 实例方法 为失败JSONObject赋值
	 * @param
	 * @param msg
	 */
	public void defeated(String msg) {
		this.data = null;
		this.status = 403;
		this.msg = msg;
	}

	/**
	 * 实例方法 为失败JSONObject赋值
	 * @param
	 * @param msg
	 */
	public void defeated(String msg, Object data) {
		this.data = data;
		this.status = 403;
		this.msg = msg;
	}
	/**
	 * 实例方法 为成功JSONObject赋值
	 * @param msg
	 * @param data
	 */
	public void victroy(String msg, Object data){
		this.data = data;
		this.status = 200;
		this.msg = msg;
	}


	/**
	 * 将json结果集转化为JSONObject对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            JSONObject中的object类型
	 * @return
	 */
	public static JSONObject formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, JSONObject.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 没有object对象的转化
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject format(String json) {
		try {
			return MAPPER.readValue(json, JSONObject.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object是集合转化
	 * 
	 * @param jsonData
	 *            json数据
	 * @param clazz
	 *            集合中的类型
	 * @return
	 */
	public static JSONObject formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return "JSONObject{" +
				"status=" + status +
				", msg='" + msg + '\'' +
				", data=" + data +
				", lCounts=" + lCounts +
				'}';
	}
}
