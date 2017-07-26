package com.tgweb.hibernate.day01;

import java.sql.Date;

/**
 * 
 * Hibernate是一个低侵入式框架
 * 
 * 持久化类和数据表的映射(.hbm.xml)
 * 
 * @author Chain
 *
 */
public class News {

	private Long id;
	private String title;
	private String author;
	private Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("News [id=").append(id).append(", title=").append(title).append(", author=").append(author)
				.append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}

	public News(String title, String author, Date createDate) {
		super();
		this.title = title;
		this.author = author;
		this.createDate = createDate;
	}

	public News() {
	}

}
