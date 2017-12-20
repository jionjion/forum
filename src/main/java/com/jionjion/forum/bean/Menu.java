package com.jionjion.forum.bean;

/**
 * 	菜单的选择
 * @author JionJion
 */
public class Menu {

	/** 菜单名*/
	private String name;
	
	/** 链接*/
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
}
