package com.qushida.po;

public class Notice {
	public Notice() {
		super();
	}
	public Notice(int id, String name, String content, String times) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.times = times;
	}
	private int id;
	private String name;
	private String content;
	private String times;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", name=" + name + ", content=" + content + ", times=" + times + "]";
	}

}
