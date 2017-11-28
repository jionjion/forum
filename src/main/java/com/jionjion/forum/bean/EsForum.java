package com.jionjion.forum.bean;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author JionJion
 *	搜索文本类
 */
@Document(indexName = "forum" , type="forum")
public class EsForum implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String title;
	
	private String summary;
	
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EsForum(String titile, String summary, String content) {
		super();
		this.title = titile;
		this.summary = summary;
		this.content = content;
	}

	public EsForum() {
		super();
	}

	@Override
	public String toString() {
		return "EsForum [id=" + id + ", title=" + title + ", summary=" + summary + ", content=" + content + "]";
	}
}
