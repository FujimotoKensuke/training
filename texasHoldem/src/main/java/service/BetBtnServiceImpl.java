package service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;

/**
 * ベットボタン処理を行うサービスクラスです。
 */
@RequestScoped
public class BetBtnServiceImpl implements BetBtnService {

	@Inject
	private GameBean gb;

	@Override
	public void execute() {

		bet();

	}

	/**
	 * ベットボタン押下時の動作を設定します。
	 */
	private void bet() {

		// ユーザーアクション情報の設定
		gb.setUserAction(action.BET.toString());
		gb.setUserActionMsg(gb.getUserAction() + gb.getUserBetPoint());
		gb.setUserTurn(false);
		gb.setUserSituation("");


		// ユーザーの所持ポイントの変動
		gb.setUserPoint(gb.getUserPoint() - gb.getUserBetPoint());

		// ユーザーの現ラウンドでの合計ベットポイントの変動
		gb.setUserTotalBetPoint(gb.getUserTotalBetPoint() + gb.getUserBetPoint());

		// ユーザーの現在のベット局面での合計ベットポイントの設定とベットポイントのクリア
		gb.setCurrentUserBetPoint(gb.getUserBetPoint());
		gb.setUserBetPoint(0);

		// ポット情報の設定
		gb.setPotPoint(gb.getPotPoint() + gb.getCurrentUserBetPoint());

		// オールイン時(全ユーザーポイントをベットした場合)の処理です。
		if(gb.getUserPoint() <= 0) {
			gb.setUserActionMsg(TexasHoldemConstants.ALL_IN + gb.getUserAction());
			gb.setUserAllInFlag(true);
		}

	}

}
