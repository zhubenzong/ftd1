package org.ftd.common.pojo;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGridResult implements Serializable{
	//总记录数
	private long total;
	
	//内容列表
	private List<?> rows;

	public EasyUIDataGridResult(){}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}

	public EasyUIDataGridResult(Long total, List<?> rows) {
		this.total = total.intValue();
		this.rows = rows;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
