package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="BattleRecord.findAll", query="SELECT u FROM BattleRecord u")
@Table(name = "battlerecord")
public class BattleRecord implements Serializable{

	/**
	 * ID
	 */
	@Id
	private Integer id;

	/**
	 * ユーザーID
	 */
	@Column(length = 10, nullable = false)
	private String userId;

	/**
	 * 対戦日時
	 */
	@Column(nullable = false)
	private String battleDate;

	/**
	 * 対戦相手名
	 */
	@Column(length = 10, nullable = false)
	private String opponentName;

	/**
	 * 勝敗結果
	 */
	@Column(length = 10, nullable = false)
	private String battleResult;

	//コンストラクタ
	public BattleRecord() {
	}

	//コンストラクタ
	public BattleRecord(Integer id,String userId, String battleDate, String opponentName, String battleResult) {
		this.id = id;
		this.userId = userId;
		this.battleDate = battleDate;
		this.opponentName = opponentName;
		this.battleResult = battleResult;
	}

	public String getBattleDate() {
		return battleDate;
	}

	public void setBattleDate(String battleDate) {
		this.battleDate = battleDate;
	}

	public String getOpponentName() {
		return opponentName;
	}

	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}

	public String getBattleResult() {
		return battleResult;
	}

	public void setBattleResult(String battleResult) {
		this.battleResult = battleResult;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
