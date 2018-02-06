package service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;
import common.TexasHoldemEnum.battleResult;

@RequestScoped
public class GameMainTimerServiceImpl implements GameMainTimerService,Serializable{

	@Inject
	private GameBean gb;

	@Inject
	private GameMainService gameMainService;

	@Inject
	private GameMainInitService gameMainInitService;

	@Inject
	GameMainHandJudgeService gameMainHandJudgeService;

	@Inject
	private GameMainRegisterService gameMainRegisterService;

	@Override
	public void execute() {

		betRoundBranching();

	}

	/**
	 * ゲームを進行させます。
	 */
	private void betRoundBranching() {

		if(gb.getUserFoldFlag() || gb.getOpponentFoldFlag()){

			showdown();

		}else if(action.FOLD.toString().equals(gb.getOpponentAction())) {

			foldSelect();

		}else {

			switch(gb.getBetRound()) {

			case "プリフロップ":

				preFlop();
				break;

			case "フロップ":
			case "ターン":
			case "リバー":

				flopAndTurnAndRiver();
				break;

			case "ショーダウン":

				showdown();
				break;
			}

		}

	}

	/**
	 * プリフロップ時の処理です。
	 */
	private void preFlop() {

		if(gb.getUserAllInFlag() || gb.getOpponentAllInFlag()) {

			gameMainService.betRoundStep();

		}else {
			// 分岐条件：ユーザー(レイズ)で対戦相手(コール)またはユーザー(チェック)で対戦相手(コール)またはユーザー(コール)で対戦相手(チェック)
			if((action.RAISE.toString().equals(gb.getUserAction())
					&& action.CALL.toString().equals(gb.getOpponentAction()))
					|| (action.CHECK.toString().equals(gb.getUserAction())
							&& action.CALL.toString().equals(gb.getOpponentAction()))
					|| (action.CALL.toString().equals(gb.getUserAction())
							&& action.CHECK.toString().equals(gb.getOpponentAction()))) {

				gameMainService.betRoundStep();
			}

		}

	}

	/**
	 * フロップ、ターン、リバー時の処理です。
	 */
	private void flopAndTurnAndRiver() {


		/*
		 *  分岐条件：ユーザー(ベット)で対戦相手(コール)またはユーザー(コール)で対戦相手(ベット)
		 *  		またはユーザー(レイズ)で対戦相手(コール)またはユーザー(コール)で対戦相手(レイズ)
		 *  		またはユーザー(チェック)で対戦相手(チェック)
		 */
		if((action.BET.toString().equals(gb.getUserAction())
				&& action.CALL.toString().equals(gb.getOpponentAction()))
				|| (action.CALL.toString().equals(gb.getUserAction())
						&& action.BET.toString().equals(gb.getOpponentAction()))
				|| (action.RAISE.toString().equals(gb.getUserAction())
						&& action.CALL.toString().equals(gb.getOpponentAction()))
				|| (action.CALL.toString().equals(gb.getUserAction())
						&& action.RAISE.toString().equals(gb.getOpponentAction()))
				||(action.CHECK.toString().equals(gb.getUserAction())
						&& action.CHECK.toString().equals(gb.getOpponentAction()))) {

			gameMainService.betRoundStep();
		}


	}

	/**
	 * ショーダウン時の処理です。
	 * 次のラウンドに進みます。
	 */
	private void showdown() {

		if(battleResult.WIN.toString().equals(gb.getBattleResultOfRound())) {

			//勝った時の処理
			String changePoint;
			if(gb.getOpponentFoldFlag()) {
				changePoint = "+" + (gb.getOpponentBetPoint()) + "(" + (gb.getUserPoint() + gb.getOpponentBetPoint()) + ")";
			}else {
				changePoint = "+" + (gb.getPotPoint() / 2) + "(" + (gb.getUserPoint() + gb.getPotPoint()) + ")";
			}

			gb.setChangePoint(changePoint);


			gb.setUserPoint(gb.getUserPoint() + gb.getPotPoint());
			gb.setPotPoint(0);

			gameMainRegisterService.createDetailBattleRecordTableInfo();



			if(gb.getOpponentPoint() <= 1) {
				gb.setBattleResultOfRound("");
				gb.setBattleResult(battleResult.WIN.toString());
//				gameMainRegisterService.createBattleRecordTableInfo();

			}else {
				// 初期表示処理
				gameMainInitService.execute();
				execute();
			}

		}else if(battleResult.LOSE.toString().equals(gb.getBattleResultOfRound())) {

			//負けた時の処理
			String changePoint;
			if(gb.getUserFoldFlag()) {
				changePoint = "-" + (gb.getUserBetPoint()) + "(" + (gb.getUserPoint()) + ")";
			}else {
				changePoint = "-" + (gb.getPotPoint() / 2) + "(" + (gb.getUserPoint()) + ")";
			}

			gb.setChangePoint(changePoint);


			gb.setOpponentPoint(gb.getOpponentPoint() + gb.getPotPoint());
			gb.setPotPoint(0);

			gameMainRegisterService.createDetailBattleRecordTableInfo();

			if(gb.getUserPoint() <= 1) {
				gb.setBattleResultOfRound("");
				gb.setBattleResult(battleResult.LOSE.toString());
//				gameMainRegisterService.createBattleRecordTableInfo();


			}else {
				// 初期表示処理
				gameMainInitService.execute();
				execute();
			}

		}else {
			//引き分け時の処理
			int potPonitDraw =  gb.getPotPoint() / 2;
			gb.setUserPoint(gb.getUserPoint() + potPonitDraw);
			gb.setOpponentPoint(gb.getOpponentPoint() + potPonitDraw);
			gb.setPotPoint(0);

			String changePoint = "+0(" + (gb.getUserPoint()) + ")";
			gb.setChangePoint(changePoint);

			gameMainRegisterService.createDetailBattleRecordTableInfo();

			// 初期表示処理
			gameMainInitService.execute();
			execute();

		}
//		System.out.println(gb.getDetailBattleRecordList().size());

	}

	/**
	 * フォールド時の処理です。
	 */
	private void foldSelect() {

		if(gb.getRound() < TexasHoldemConstants.ROUND_MAX_NUMBER) {

			gb.setBattleResultOfRound(battleResult.WIN.toString());

		}else {
			gb.setBattleResult(battleResult.WIN.toString());

		}
		gb.setOpponentFoldFlag(true);
	}

}
