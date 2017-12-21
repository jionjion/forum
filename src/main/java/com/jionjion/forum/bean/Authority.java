package com.jionjion.forum.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
/**
 * 	安全配置的对象
 * @author JionJion
 */
@Entity
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	/**主键*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**权限名称*/
	@Column(nullable=false)
	private String name;
	
	/**获得权限*/
	@Override
	public String getAuthority() {
		return name;
	}

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
	
	

}
