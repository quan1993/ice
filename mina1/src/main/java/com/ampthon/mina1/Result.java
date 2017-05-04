package com.ampthon.mina1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5995388322839213637L;
	private List<Object> objs;
	private String resultType;
	
	public Result(){
		
	}
	public Result(String resultType) {
		super();
		this.resultType = resultType;
		objs = new ArrayList<Object>();
	}
	public List<Object> getObjs() {
		return objs;
	}
	public void setObjs(List<Object> objs) {
		this.objs = objs;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	public String toString(){
		return "resultType=" + resultType + "; List<Object>=" + objs.toString();
	}
	
}
