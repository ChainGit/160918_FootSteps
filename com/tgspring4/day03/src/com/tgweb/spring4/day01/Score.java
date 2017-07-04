package com.tgweb.spring4.day01;

public class Score {

	private Integer id;
	private Integer yuwen;
	private Integer shuxue;
	private Integer yinyu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYuwen() {
		return yuwen;
	}

	public void setYuwen(Integer yuwen) {
		this.yuwen = yuwen;
	}

	public Integer getShuxue() {
		return shuxue;
	}

	public void setShuxue(Integer shuxue) {
		this.shuxue = shuxue;
	}

	public Integer getYinyu() {
		return yinyu;
	}

	public void setYinyu(Integer yinyu) {
		this.yinyu = yinyu;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Score [id=").append(id).append(", yuwen=").append(yuwen).append(", shuxue=").append(shuxue)
				.append(", yinyu=").append(yinyu).append("]");
		return builder.toString();
	}

	public Score() {
	}

}
