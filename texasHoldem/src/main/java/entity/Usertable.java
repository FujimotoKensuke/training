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
@NamedQuery(name = "Usertable.findAll", query = "SELECT u FROM Usertable u")
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

    @Column(length = 10, nullable = false)
    private String comments;

    @Column(nullable = false)
    private Integer deleteFlg;

    @Column(nullable = false)
    private Integer DisplayFlg;

    /**
     * コンストラクタ
     */
    public Usertable() {
    }

    /**
     * Usertableのコンストラクタ
     *
     * @param userId
     * @param userName
     * @param password
     * @param comments
     * @param deleteFlg
     * @param DisplayFlg
     */
    public Usertable(String userId, String userName, String password, String comments, Integer deleteFlg, Integer DisplayFlg) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.comments = comments;
        this.deleteFlg = deleteFlg;
        this.DisplayFlg = DisplayFlg;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Integer deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public Integer getDisplayFlg() {
        return DisplayFlg;
    }

    public void setDisplayFlg(Integer DisplayFlg) {
        this.DisplayFlg = DisplayFlg;
    }

}
