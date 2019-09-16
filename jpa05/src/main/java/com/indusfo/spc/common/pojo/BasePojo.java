package com.indusfo.spc.common.pojo;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/***
 * pojo基类：用于扩展分页功能滴，你离分页只差一个继承哦
 * 
 * @author xuz
 * @date 2018/11/22 2:18 PM
 */

@MappedSuperclass
public class BasePojo implements Serializable {

	private static final long serialVersionUID = 5762265332554874563L;
	/**
	 * 模糊查询接受字段
	 */
	private String  dim;
	private Integer pagesize;
	private Integer pageindex;
	private Integer lN;
	/** 高级查询条件 */
	private String strWhere;

	public String getStrWhere() {
		return strWhere;
	}

	public void setStrWhere(String strWhere) {
		this.strWhere = strWhere;
	}

	public Integer getlN() {
		return lN;
	}

	public void setlN(Integer lN) {
		this.lN = lN;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	@Override
	public String toString() {
		return "BasePojo [pagesize=" + pagesize + ", pageindex=" + pageindex + ", lN=" + lN + ", strWhere=" + strWhere
				+ "]";
	}
}