package beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import entity.BattleRecord;
import entity.DetailBattleRecord;

/**
 * ユーザーのビーンクラスです。
 */
@Named
@SessionScoped
public class UserBean implements Serializable{

	/**
	 * ユーザーのIDです
	 */
	@Size(max=10,min=1,message = "ユーザーIDは1文字以上10文字以下で入力して下さい。")
	private String userId;

	/**
	 * ユーザーの名前です
	 */
	@Size(max=10,min=1,message = "ユーザー名は1文字以上10文字以下で入力して下さい。")
	private String userName;

	/**
	 * ユーザーのパスワードです
	 */
	@Size(max=20,min=8,message = "パスワードは8文字以上20文字以下で入力して下さい。")
	@Pattern(regexp = "[A-Za-z0-9]+",message = "パスワードに使用できない文字が含まれています。")
	private String password;

	/**
	 * 戦績レコードのリストです。
	 */
	private List<BattleRecord> battleRecord;

	/**
	 * 戦績詳細レコードのリストです。
	 */
	private List<DetailBattleRecord> detailBattleRecord;

	/**
	 * 戦績の勝率です。
	 */
	private int winning;

	/**
	 * 対戦相手の名前です。
	 */
	private String opponentName;

	/**
	 * 勝敗結果です。
	 */
	private String battleResult;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String string) {
		this.userId = string;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BattleRecord> getBattleRecord() {
		return battleRecord;
	}

	public void setBattleRecord(List<BattleRecord> battleRecord) {
		this.battleRecord = battleRecord;
	}

	public int getWinning() {
		return winning;
	}

	public void setWinning(int winning) {
		this.winning = winning;
	}

	public List<DetailBattleRecord> getDetailBattleRecord() {
		return detailBattleRecord;
	}

	public void setDetailBattleRecord(List<DetailBattleRecord> detailBattleRecord) {
		this.detailBattleRecord = detailBattleRecord;
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
}

