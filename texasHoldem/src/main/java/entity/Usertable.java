package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * DBのユーザーテーブルのエンティティクラスです。
 *
 */
@Entity
@NamedQuery(name="Usertable.findAll", query="SELECT u FROM Usertable u")
@Table(schema = "texasholdem")
public class Usertable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 10, nullable = true)
	private String userId;

	@Column(length = 20, nullable = false)
	private String password;

	@Column(length = 10, nullable = false)
	private String userName;

	public Usertable() {
	}

	public Usertable(String userId,String userName,String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}