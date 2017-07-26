package com.tgweb.hibernate.day03;

/*
 * 有ID，作为Entity
 * 
 */
public class Student {

	private Long id;
	private String name;
	private Integer age;
	private Score score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=").append(id).append(", name=").append(name).append(", age=").append(age)
				.append(", score=").append(score).append("]");
		return builder.toString();
	}

	public Student(String name, Integer age, Score score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public Student() {
	}

}
