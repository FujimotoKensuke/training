package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entity.BattleRecord;
import entity.DetailBattleRecord;

/**
 * ゲームメイン画面のビーンクラスです。
 */
@Named
@ViewScoped
public class GameBean implements Serializable {

	/**
	 * 対戦日時です。
	 */
	private String battleDate;

	/**
	 * 勝敗結果です。
	 */
	private String battleResult;

	/**
	 * 対戦相手名です。
	 */
	private String opponentName;

	/**
	 * 現在のラウンド数です。
	 */
	private int round;

	/**
	 * 現在のラウンドでの勝敗結果です。
	 */
	private String battleResultOfRound;

	/**
	 * ユーザーの手札です。
	 */
	private String[] userCard;

	/**
	 * ユーザーの手札です。
	 */
	private String userCardInfo;

	/**
	 * 対戦相手の手札です。
	 */
	private String[] opponentCard;

	/**
	 * 対戦相手の手札です。
	 */
	private String opponentCardInfo;

	/**
	 * ボードのカード情報です。
	 */
	private String[] bordInfo;

	/**
	 * ボードのカード情報です。
	 */
	private String bordCardInfo;

	/**
	 * 現在のベット局面です。
	 */
	private String betRound;

	/**
	 * ユーザーの成立役です。
	 */
	private String userHand;

	/**
	 * 対戦相手の成立役です。
	 */
	private String opponentHand;

	/**
	 * 対戦相手の成立役情報です。
	 */
	private String opponentHandInfo;

	/**
	 * 変動したポイントです。
	 */
	private String changePoint;

	/**
	 * 対戦相手のブラインドです。
	 */
	private String opponentBlind;

	/**
	 * 相手の現在の所持ポイントです。
	 */
	private int opponentPoint;

	/**
	 * 現在のポットポイントです。
	 */
	private int potPoint;

	/**
	 * 対戦相手の選択したアクションです。
	 */
	private String opponentAction;

	/**
	 * ユーザーの選択したアクションです。
	 */
	private String userAction;

	/**
	 * ユーザーの状況またはユーザーの成立役です。
	 */
	private String userSituation;

	/**
	 * ユーザーのブラインドです。
	 */
	private String userBlind;

	/**
	 * ベットボタンの活性状態です。
	 */
	private boolean betBtnDisabled;

	/**
	 * ベットバーの最小値です。
	 */
	private int betMin;

	/**
	 * ユーザーのベットしたポイントです。
	 */
	private int userBetPoint;

	/**
	 * 対戦相手のベットしたポイントです。
	 */
	private int opponentBetPoint;

	/**
	 * レイズボタンの活性状態です。
	 */
	private boolean raiseBtnDisabled;

	/**
	 * レイズバーの最小値です。
	 */
	private int raiseMin;

	/**
	 * ユーザーのレイズしたポイントです。
	 */
	private int userRaisePoint;

	/**
	 * 対戦相手のレイズしたポイントです。
	 */
	private int opponentRaisePoint;

	/**
	 * コールボタンの活性状態です。
	 */
	private boolean callBtnDisabled;

	/**
	 * チェックボタンの活性状態です。
	 */
	private boolean checkBtnDisabled;

	/**
	 * フォールドボタンの活性状態です。
	 */
	private boolean foldBtnDisabled;
	/**
	 * ユーザーの現在の所持ポイントです。
	 */
	private int userPoint;

	/**
	 * ユーザーの現在の合計ベットポイントです。
	 */
	private int userTotalBetPoint;

	/**
	 * 対戦相手の現在の合計ベットポイントです。
	 */
	private int opponentTotalBetPoint;

	/**
	 * ユーザーの現在の成立役です。
	 */
	private String userCurrentHand;

	/**
	 * 現在ユーザーのアクション選択ターンか否かです。
	 */
	private Boolean userTurn;

	/**
	 * 現在のユーザーのベットラウンド内での合計ベットポイントです。
	 */
	private int currentUserBetPoint;

	/**
	 * 現在の対戦相手のベットラウンド内での合計ベットポイントです。
	 */
	private int currentOpponentBetPoint;

	/**
	 * ユーザーのアクションのメッセージです。
	 */
	private String userActionMsg;

	/**
	 * 対戦相手のアクションのメッセージです。
	 */
	private String opponentActionMsg;

	/**
	 * ユーザーのオールイン時のフラグです。
	 */
	private Boolean userAllInFlag;

	/**
	 * 対戦相手のオールイン時のフラグです。
	 */
	private Boolean opponentAllInFlag;

	/**
	 * ユーザーのフォールドのフラグです。
	 */
	private Boolean userFoldFlag;

	/**
	 * 対戦相手のフォールドのフラグです。
	 */
	private Boolean opponentFoldFlag;

	/**
	 * DetailBattleRecordクラスのリストです。
	 */
	private List<DetailBattleRecord> detailBattleRecordList;

	/**
	 * BattleRecordクラスです。
	 */
	private BattleRecord battleRecord;

	public String getBattleDate() {
		return battleDate;
	}

	public void setBattleDate(String battleDate) {
		this.battleDate = battleDate;
	}

	public String getBattleResult() {
		return battleResult;
	}

	public void setBattleResult(String battleResult) {
		this.battleResult = battleResult;
	}

	public String getOpponentName() {
		return opponentName;
	}

	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getBattleResultOfRound() {
		return battleResultOfRound;
	}

	public void setBattleResultOfRound(String battleResultOfRound) {
		this.battleResultOfRound = battleResultOfRound;
	}

	public String[] getUserCard() {
		return userCard;
	}

	public void setUserCard(String[] userCard) {
		this.userCard = userCard;
	}

	public String[] getOpponentCard() {
		return opponentCard;
	}

	public void setOpponentCard(String[] opponentCard) {
		this.opponentCard = opponentCard;
	}

	public String[] getBordInfo() {
		return bordInfo;
	}

	public void setBordInfo(String[] bordInfo) {
		this.bordInfo = bordInfo;
	}

	public String getBetRound() {
		return betRound;
	}

	public void setBetRound(String betRound) {
		this.betRound = betRound;
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

	public String getOpponentBlind() {
		return opponentBlind;
	}

	public void setOpponentBlind(String opponentBlind) {
		this.opponentBlind = opponentBlind;
	}

	public int getOpponentPoint() {
		return opponentPoint;
	}

	public void setOpponentPoint(int opponentPoint) {
		this.opponentPoint = opponentPoint;
	}

	public int getPotPoint() {
		return potPoint;
	}

	public void setPotPoint(int potPoint) {
		this.potPoint = potPoint;
	}

	public String getOpponentAction() {
		return opponentAction;
	}

	public void setOpponentAction(String opponentAction) {
		this.opponentAction = opponentAction;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public String getUserSituation() {
		return userSituation;
	}

	public void setUserSituation(String userSituation) {
		this.userSituation = userSituation;
	}

	public String getUserBlind() {
		return userBlind;
	}

	public void setUserBlind(String userBlind) {
		this.userBlind = userBlind;
	}

	public boolean isBetBtnDisabled() {
		return betBtnDisabled;
	}

	public void setBetBtnDisabled(boolean betBtnDisabled) {
		this.betBtnDisabled = betBtnDisabled;
	}

	public int getBetMin() {
		return betMin;
	}

	public void setBetMin(int betMin) {
		this.betMin = betMin;
	}

	public int getUserBetPoint() {
		return userBetPoint;
	}

	public void setUserBetPoint(int userBetPoint) {
		this.userBetPoint = userBetPoint;
	}

	public int getOpponentBetPoint() {
		return opponentBetPoint;
	}

	public void setOpponentBetPoint(int opponentBetPoint) {
		this.opponentBetPoint = opponentBetPoint;
	}

	public boolean isRaiseBtnDisabled() {
		return raiseBtnDisabled;
	}

	public void setRaiseBtnDisabled(boolean raiseBtnDisabled) {
		this.raiseBtnDisabled = raiseBtnDisabled;
	}

	public int getRaiseMin() {
		return raiseMin;
	}

	public void setRaiseMin(int raiseMin) {
		this.raiseMin = raiseMin;
	}

	public int getUserRaisePoint() {
		return userRaisePoint;
	}

	public void setUserRaisePoint(int userRaisePoint) {
		this.userRaisePoint = userRaisePoint;
	}

	public int getOpponentRaisePoint() {
		return opponentRaisePoint;
	}

	public void setOpponentRaisePoint(int opponentRaisePoint) {
		this.opponentRaisePoint = opponentRaisePoint;
	}

	public boolean isCallBtnDisabled() {
		return callBtnDisabled;
	}

	public void setCallBtnDisabled(boolean callBtnDisabled) {
		this.callBtnDisabled = callBtnDisabled;
	}

	public boolean isCheckBtnDisabled() {
		return checkBtnDisabled;
	}

	public void setCheckBtnDisabled(boolean checkBtnDisabled) {
		this.checkBtnDisabled = checkBtnDisabled;
	}

	public boolean isFoldBtnDisabled() {
		return foldBtnDisabled;
	}

	public void setFoldBtnDisabled(boolean foldBtnDisabled) {
		this.foldBtnDisabled = foldBtnDisabled;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public int getUserTotalBetPoint() {
		return userTotalBetPoint;
	}

	public void setUserTotalBetPoint(int userTotalBetPoint) {
		this.userTotalBetPoint = userTotalBetPoint;
	}

	public int getOpponentTotalBetPoint() {
		return opponentTotalBetPoint;
	}

	public void setOpponentTotalBetPoint(int opponentTotalBetPoint) {
		this.opponentTotalBetPoint = opponentTotalBetPoint;
	}

	public String getUserCurrentHand() {
		return userCurrentHand;
	}

	public void setUserCurrentHand(String userCurrentHand) {
		this.userCurrentHand = userCurrentHand;
	}

	public Boolean getUserTurn() {
		return userTurn;
	}

	public void setUserTurn(Boolean userTurn) {
		this.userTurn = userTurn;
	}

	public int getCurrentUserBetPoint() {
		return currentUserBetPoint;
	}

	public void setCurrentUserBetPoint(int currentUserBetPoint) {
		this.currentUserBetPoint = currentUserBetPoint;
	}

	public int getCurrentOpponentBetPoint() {
		return currentOpponentBetPoint;
	}

	public void setCurrentOpponentBetPoint(int currentOpponentBetPoint) {
		this.currentOpponentBetPoint = currentOpponentBetPoint;
	}

	public String getUserActionMsg() {
		return userActionMsg;
	}

	public void setUserActionMsg(String userActionMsg) {
		this.userActionMsg = userActionMsg;
	}

	public String getOpponentActionMsg() {
		return opponentActionMsg;
	}

	public void setOpponentActionMsg(String opponentActionMsg) {
		this.opponentActionMsg = opponentActionMsg;
	}

	public Boolean getUserAllInFlag() {
		return userAllInFlag;
	}

	public void setUserAllInFlag(Boolean userAllInFlag) {
		this.userAllInFlag = userAllInFlag;
	}

	public Boolean getOpponentAllInFlag() {
		return opponentAllInFlag;
	}

	public void setOpponentAllInFlag(Boolean opponentAllInFlag) {
		this.opponentAllInFlag = opponentAllInFlag;
	}

	public Boolean getUserFoldFlag() {
		return userFoldFlag;
	}

	public void setUserFoldFlag(Boolean userFoldFlag) {
		this.userFoldFlag = userFoldFlag;
	}

	public Boolean getOpponentFoldFlag() {
		return opponentFoldFlag;
	}

	public void setOpponentFoldFlag(Boolean opponentFoldFlag) {
		this.opponentFoldFlag = opponentFoldFlag;
	}

	public String getUserCardInfo() {
		return userCardInfo;
	}

	public void setUserCardInfo(String userCardInfo) {
		this.userCardInfo = userCardInfo;
	}

	public String getOpponentCardInfo() {
		return opponentCardInfo;
	}

	public void setOpponentCardInfo(String opponentCardInfo) {
		this.opponentCardInfo = opponentCardInfo;
	}

	public String getBordCardInfo() {
		return bordCardInfo;
	}

	public void setBordCardInfo(String bordCardInfo) {
		this.bordCardInfo = bordCardInfo;
	}

	public List<DetailBattleRecord> getDetailBattleRecordList() {
		return detailBattleRecordList;
	}

	public void setDetailBattleRecordList(List<DetailBattleRecord> detailBattleRecordList) {
		this.detailBattleRecordList = detailBattleRecordList;
	}

	public String getOpponentHandInfo() {
		return opponentHandInfo;
	}

	public void setOpponentHandInfo(String opponentHandInfo) {
		this.opponentHandInfo = opponentHandInfo;
	}

	public BattleRecord getBattleRecord() {
		return battleRecord;
	}

	public void setBattleRecord(BattleRecord battleRecord) {
		this.battleRecord = battleRecord;
	}

}
