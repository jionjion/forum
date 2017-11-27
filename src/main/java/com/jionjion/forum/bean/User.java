package com.jionjion.forum.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author JionJion
 *	用户模型类
 */
@Entity
public class User {
	
	/**用户ID*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**用户名*/
	private String username;
	/**用户密码*/
	private String password;
	/**用户邮箱*/
	private String email;
	/**联系电话*/
	private String telephone;

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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", telephone=" + telephone + "]";
	}
}
