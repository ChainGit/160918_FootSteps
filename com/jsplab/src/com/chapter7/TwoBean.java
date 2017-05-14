package com.chapter7;

public class TwoBean {

	String[] columnName;

	String[][] tableRecord = null;

	public TwoBean() {

		tableRecord = new String[1][1];
		columnName = new String[1];
	}

	public void setTableRecord(String[][] s) {
		tableRecord = s;

	}

	public String[][] getTableRecord() {
		return tableRecord;

	}

	public void setColumnName(String[] s) {
		columnName = s;

	}

	public String[] getColumnName() {

		return columnName;

	}

}
