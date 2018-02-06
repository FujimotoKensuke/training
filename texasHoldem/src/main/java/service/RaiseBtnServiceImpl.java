package service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;

/**
 * レイズボタン処理を行うサービスクラスです。
 */
@RequestScoped
public class RaiseBtnServiceImpl implements RaiseBtnService {

	@Inject
	private GameBean gb;

	@Override
	public void execute() {

		raise();

	}

	/**
	 * レイズボタン押下時の動作を設定します。
	 */
	public void raise() {

		// ユーザー情報の設定
		gb.setUserAction(action.RAISE.toString());
		gb.setUserActionMsg(gb.getUserAction() + (gb.getUserRaisePoint() + gb.getCurrentUserBetPoint()));
		gb.setUserTurn(false);
		gb.setUserSituation("");

		// ユーザーの所持ポイントの変動
		gb.setUserPoint(gb.getUserPoint() - gb.getUserRaisePoint());

		// ユーザーの現ラウンドでの合計ベットポイントの変動
		gb.setUserTotalBetPoint(gb.getUserTotalBetPoint() + gb.getUserRaisePoint());

		// ポット情報の設定
		gb.setPotPoint(gb.getPotPoint() + gb.getUserRaisePoint());

		// オールイン時(全ユーザーポイントをレイズした場合)の処理です。
		if(gb.getUserPoint() <= 0) {
			gb.setUserActionMsg(TexasHoldemConstants.ALL_IN + gb.getUserAction());
			gb.setUserAllInFlag(true);
		}

		// ユーザーの現在のベット局面での合計ベットポイントの設定とレイズポイントのクリア
		gb.setCurrentUserBetPoint(gb.getUserRaisePoint() + gb.getCurrentUserBetPoint());
		gb.setUserRaisePoint(0);

	}

}
