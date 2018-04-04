package service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;
import common.TexasHoldemEnum.battleResult;
import util.JavascriptFromJavaUtil;

@RequestScoped
public class GameMainTimerServiceImpl implements GameMainTimerService, Serializable {

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

        if (gb.getUserFoldFlag() || gb.getOpponentFoldFlag()) {

            showdown();

        } else if (action.FOLD.toString().equals(gb.getOpponentAction())) {

            foldSelect();

        } else {

            switch (gb.getBetRound()) {

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

        if (gb.getUserAllInFlag() || gb.getOpponentAllInFlag()) {

            gameMainService.betRoundStep();

        } else {
            // 分岐条件：ユーザー(レイズ)で対戦相手(コール)またはユーザー(チェック)で対戦相手(コール)またはユーザー(コール)で対戦相手(チェック)
            if ((action.RAISE.toString().equals(gb.getUserAction())
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
        if ((action.BET.toString().equals(gb.getUserAction())
                && action.CALL.toString().equals(gb.getOpponentAction()))
                || (action.CALL.toString().equals(gb.getUserAction())
                && action.BET.toString().equals(gb.getOpponentAction()))
                || (action.RAISE.toString().equals(gb.getUserAction())
                && action.CALL.toString().equals(gb.getOpponentAction()))
                || (action.CALL.toString().equals(gb.getUserAction())
                && action.RAISE.toString().equals(gb.getOpponentAction()))
                || (action.CHECK.toString().equals(gb.getUserAction())
                && action.CHECK.toString().equals(gb.getOpponentAction()))) {

            gameMainService.betRoundStep();
        }

    }

    /**
     * ショーダウン時の処理です。 次のラウンドに進みます。
     */
    private void showdown() {

        if (battleResult.WIN.toString().equals(gb.getBattleResultOfRound())) {

            //勝った時の処理
            String changePoint;
            if (gb.getOpponentFoldFlag()) {
                changePoint = "+" + (gb.getOpponentTotalBetPoint()) + "(" + (gb.getUserPoint() + gb.getPotPoint()) + ")";
            } else {
                changePoint = "+" + (gb.getPotPoint() / 2) + "(" + (gb.getUserPoint() + gb.getPotPoint()) + ")";
            }
            System.out.println(changePoint);
            gb.setChangePoint(changePoint);

            gb.setUserPoint(gb.getUserPoint() + gb.getPotPoint());
            gb.setPotPoint(0);

            gameMainRegisterService.createDetailBattleRecordTableInfo();

            // 相手のポイントが1以下になった場合(ゲーム続行不可)
            if (gb.getOpponentPoint() <= 1) {
                gb.setBattleResultOfRound("");
                gb.setBattleResult(battleResult.WIN.toString());

                gameMainRegisterService.createBattleRecordTableInfo();
                JavascriptFromJavaUtil.execute(TexasHoldemConstants.TIMER_STOP_SCRIPT + battleResult.WIN.gameDialogScriptToString());

            }

        } else if (battleResult.LOSE.toString().equals(gb.getBattleResultOfRound())) {

            //負けた時の処理
            String changePoint;
            if (gb.getUserFoldFlag() || (gb.getUserAllInFlag() && gb.getOpponentAllInFlag() == false)) {
                changePoint = "-" + (gb.getUserTotalBetPoint()) + "(" + (gb.getUserPoint()) + ")";
            } else {
                changePoint = "-" + (gb.getPotPoint() / 2) + "(" + (gb.getUserPoint()) + ")";
            }

            gb.setChangePoint(changePoint);

            gb.setOpponentPoint(gb.getOpponentPoint() + gb.getPotPoint());
            gb.setPotPoint(0);

            gameMainRegisterService.createDetailBattleRecordTableInfo();

            // ユーザーポイントが1以下になった場合(ゲーム続行不可)
            if (gb.getUserPoint() <= 1) {
                gb.setBattleResultOfRound("");
                gb.setBattleResult(battleResult.LOSE.toString());

                gameMainRegisterService.createBattleRecordTableInfo();
                JavascriptFromJavaUtil.execute(TexasHoldemConstants.TIMER_STOP_SCRIPT + battleResult.LOSE.gameDialogScriptToString());

            }

        } else {
            //引き分け時の処理
            int potPonitDraw = gb.getPotPoint() / 2;
            gb.setUserPoint(gb.getUserPoint() + potPonitDraw);
            gb.setOpponentPoint(gb.getOpponentPoint() + potPonitDraw);
            gb.setPotPoint(0);

            String changePoint = "+0(" + (gb.getUserPoint()) + ")";
            gb.setChangePoint(changePoint);

            gameMainRegisterService.createDetailBattleRecordTableInfo();

        }

        if (gb.getRound() == TexasHoldemConstants.ROUND_MAX_NUMBER) {
            if (gb.getUserPoint() - gb.getOpponentPoint() == 0) {
                gb.setBattleResult(battleResult.DRAW.toString());
                gameMainRegisterService.createBattleRecordTableInfo();
                JavascriptFromJavaUtil.execute(TexasHoldemConstants.TIMER_STOP_SCRIPT + battleResult.DRAW.gameDialogScriptToString());

            } else if (gb.getUserPoint() - gb.getOpponentPoint() > 0) {
                gb.setBattleResult(battleResult.WIN.toString());
                gameMainRegisterService.createBattleRecordTableInfo();
                JavascriptFromJavaUtil.execute(TexasHoldemConstants.TIMER_STOP_SCRIPT + battleResult.WIN.gameDialogScriptToString());
            } else {
                gb.setBattleResult(battleResult.LOSE.toString());
                gameMainRegisterService.createBattleRecordTableInfo();
                JavascriptFromJavaUtil.execute(TexasHoldemConstants.TIMER_STOP_SCRIPT + battleResult.LOSE.gameDialogScriptToString());
            }
        } else {
            // 初期表示処理
            gameMainInitService.execute();
            execute();
        }

    }

    /**
     * 対戦相手のフォールド時の処理です。
     */
    private void foldSelect() {

        gb.setBattleResultOfRound(battleResult.WIN.toString());
        gameMainService.resultDialogShow(battleResult.WIN.roundDialogScriptToString());
        gb.setOpponentFoldFlag(true);
    }

}
