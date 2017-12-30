package com.jionjion.forum.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author JionJion
 *	用户模型类
 */
@Entity
public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	/**用户ID*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**用户名*/
	@NotEmpty(message="用户名不能为空!")
	@Size(min=2,max=20)
	@Column(nullable=false,length=30)	//字段设置
	private String username;
	
	/**用户密码*/
	@NotEmpty(message="密码不能为空!")
	@Size(min=2,max=20)
	@Column(nullable=false,length=30)	//字段设置	
	private String password;
	
	/**用户邮箱*/
	private String email;
	
	/**联系电话*/
	private String telephone;
	
	/**头像信息*/
	private String headImage;
	
	/**用户权限关系*/
	@ManyToMany(cascade=CascadeType.DETACH , fetch=FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))	
	private List<Authority> authorities;
	

	public Long getId() {
		return id;
	}

	public User() {
		super();
	}

	public User(Long id, String username, String password, String email, String telephone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", telephone=" + telephone + ", headImage=" + headImage + "]";
	}

	/** 获取权限信息*/
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//将自定义的权限对象转为SimpleGrantedAuthority
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		//遍历该对象内的权限信息,并转化
		for(GrantedAuthority authority:this.authorities) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}
 		return simpleGrantedAuthorities;
	}
	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	/** 是否账号没有过期*/
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/** 是否账号没有被锁*/
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/** 是否账号被没有被冻结*/
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/** 是否账号可用*/
	@Override
	public boolean isEnabled() {
		return true;
	}
}