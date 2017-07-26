package com.tgweb.ssh.entity;

public class Book {

	private Long id;
	private String isbn;
	private String name;
	private Double price;
	private Integer stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=").append(id).append(", isbn=").append(isbn).append(", name=").append(name)
				.append(", price=").append(price).append(", stock=").append(stock).append("]");
		return builder.toString();
	}

	public Book(String isbn, String name, Double price, Integer stock) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

}
