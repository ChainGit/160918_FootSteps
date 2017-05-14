package com.chapter7;

public class OneBean {
	String[] columnName;
	String[][] tableRecord;

	public OneBean() {
		// TODO Auto-generated constructor stub
		columnName = new String[1];
		tableRecord = new String[1][1];
	}

	public String[] getColumnName() {
		return columnName;
	}

	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}

	public String[][] getTableRecord() {
		return tableRecord;
	}

	public void setTableRecord(String[][] tableRecord) {
		this.tableRecord = tableRecord;
	}

}
