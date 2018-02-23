package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="DetailBattleRecord.findAll", query="SELECT u FROM DetailBattleRecord u")
@Table(name = "detailbattlerecord")
public class DetailBattleRecord implements Serializable{

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * ユーザーID
	 */
	@Column(length = 10, nullable = false)
	private String userId;

	/**
	 * 対戦日時
	 */
	@Column(length = 20,nullable = false)
	private String battleDate;

	/**
	 * 対戦相手名
	 */
	@Column(length = 10, nullable = false)
	private String opponentName;

	/**
	 * ラウンド
	 */
	@Column(nullable = false)
	private Integer round;

	/**
	 * 自札
	 */
	@Column(length = 10, nullable = false)
	private String userCard;

	/**
	 * 相手札
	 */
	@Column(length = 10, nullable = false)
	private String opponentCard;

	/**
	 * ボード情報
	 */
	@Column(length = 30, nullable = false)
	private String bordInfo;

	/**
	 * 最終局面
	 */
	@Column(length = 10, nullable = false)
	private String lastBetRound;

	/**
	 * ユーザーの成立役
	 */
	@Column(length = 20, nullable = false)
	private String userHand;

	/**
	 * 対戦相手の成立役
	 */
	@Column(length = 20, nullable = false)
	private String opponentHand;

	/**
	 * 変動ポイント
	 */
	@Column(length = 20, nullable = false)
	private String changePoint;

	//コンストラクタ
	public DetailBattleRecord() {
	}

	//コンストラクタ
	public DetailBattleRecord(Integer id,String userId, String battleDate, String opponentName,
			Integer round, String userCard, String opponentCard, String bordInfo,
			String lastBetRound, String userHand, String opponentHand, String changePoint) {

		this.id = id;
		this.userId = userId;
		this.battleDate = battleDate;
		this.opponentName = opponentName;
		this.round = round;
		this.userCard = userCard;
		this.opponentCard = opponentCard;
		this.bordInfo = bordInfo;
		this.lastBetRound = lastBetRound;
		this.userHand = userHand;
		this.opponentHand = opponentHand;
		this.changePoint = changePoint;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getOpponentCard() {
		return opponentCard;
	}

	public void setOpponentCard(String opponentCard) {
		this.opponentCard = opponentCard;
	}

	public String getBordInfo() {
		return bordInfo;
	}

	public void setBordInfo(String bordInfo) {
		this.bordInfo = bordInfo;
	}

	public String getLastBetRound() {
		return lastBetRound;
	}

	public void setLastBetRound(String lastBetRound) {
		this.lastBetRound = lastBetRound;
	}

	public String getUserHand() {
		return userHand;
	}

	public void setUserHand(String userHand) {
		this.userHand = userHand;
	}

	public String getOpponentHand() {
		return opponentHand;
	}

	public void setOpponentHand(String opponentHand) {
		this.opponentHand = opponentHand;
	}

	public String getChangePoint() {
		return changePoint;
	}

	public void setChangePoint(String changePoint) {
		this.changePoint = changePoint;
	}
}
