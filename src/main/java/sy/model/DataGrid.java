package sy.model;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {
	
	private Integer total;
	private List rows = new ArrayList();
	 
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	

}
