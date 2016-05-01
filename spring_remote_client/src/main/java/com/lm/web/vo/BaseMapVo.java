package com.lm.web.vo;
import java.util.HashMap;
import java.util.Map;

public class BaseMapVo extends BaseVo{
	private static final long serialVersionUID = 1L;
	
	private Map<String , Object> data;

	public Map<String , Object> getData() {
		return data;
	}

	public void setData(Map<String , Object> data) {
		this.data = data;
	}
	
	public void addData(String key,Object value){
		if(this.data==null){
			this.data = new HashMap<String,Object>();			
		}
		this.data.put(key, value);
	}
	
}
