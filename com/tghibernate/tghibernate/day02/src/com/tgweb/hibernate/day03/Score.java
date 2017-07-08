package com.tgweb.hibernate.day03;

/*
 * 没有ID，作为值(Value)映射
 * 
 */
public class Score {

	private Integer chinese;
	private Integer math;
	private Integer english;

	public Integer getChinese() {
		return chinese;
	}

	public void setChinese(Integer chinese) {
		this.chinese = chinese;
	}

	public Integer getMath() {
		return math;
	}

	public void setMath(Integer math) {
		this.math = math;
	}

	public Integer getEnglish() {
		return english;
	}

	public void setEnglish(Integer english) {
		this.english = english;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Score [chinese=").append(chinese).append(", math=").append(math).append(", english=")
				.append(english).append("]");
		return builder.toString();
	}

	public Score(Integer chinese, Integer math, Integer english) {
		super();
		this.chinese = chinese;
		this.math = math;
		this.english = english;
	}

	public Score() {
	}

}
