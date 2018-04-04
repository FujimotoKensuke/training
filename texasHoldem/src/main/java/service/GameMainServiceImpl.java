package service;

import beans.GameBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;
import common.TexasHoldemEnum.battleResult;
import common.TexasHoldemEnum.betRound;
import common.TexasHoldemEnum.blind;
import util.JavascriptFromJavaUtil;

/**
 * 各アクション処理を行うサービスクラスです。
 */
@RequestScoped
public class GameMainServiceImpl implements GameMainService {

    @Inject
    private GameBean gb;

    @Inject
    private GameMainHandJudgeService gameMainHandJudgeService;

    @Inject
    private GameMainRegisterService gameMainRegisterService;

    @Inject
    private GameMainTimerService gameMainTimerService;

    @Override
    public void execute() {

        // 対戦相手がCPUかどうかの分岐処理
        if (TexasHoldemConstants.CPU_NAME.equals(gb.getOpponentName())) {

            betRoundBranchingProcess();

        } else {

        }

    }

    @Override
    public void betRoundStep() {

        betRoundStepDefault();

    }

    @Override
    public void allBtnNotUsable() {
        allBtnFalse();
    }

    @Override
    public void resultDialogShow(String dialogScript) {
        JavascriptFromJavaUtil.execute(dialogScript);
    }

    @Override
    public void timerScriptExecute() {
        JavascriptFromJavaUtil.execute(TexasHoldemConstants.TIMER_SCRIPT);
    }

    /**
     * 各ベットラウンドの分岐処理です。
     */
    private void betRoundBranchingProcess() {

        switch (gb.getBetRound()) {

            case "プリフロップ":

                preFlop();
                break;

            case "フロップ":
                flopAndTurnAndRiver();
                gb.setUserHand(gameMainHandJudgeService.execute(ArrayUtils.addAll(gb.getUserCard(), gb.getBordInfo()[0], gb.getBordInfo()[1], gb.getBordInfo()[2])));
                gb.setOpponentHandInfo(gameMainHandJudgeService.execute(ArrayUtils.addAll(gb.getOpponentCard(), gb.getBordInfo()[0], gb.getBordInfo()[1], gb.getBordInfo()[2])));
                break;
            case "ターン":
                flopAndTurnAndRiver();
                gb.setUserHand(gameMainHandJudgeService.execute(ArrayUtils.addAll(gb.getUserCard(), gb.getBordInfo()[0], gb.getBordInfo()[1], gb.getBordInfo()[2], gb.getBordInfo()[3])));
                gb.setOpponentHandInfo(gameMainHandJudgeService.execute(ArrayUtils.addAll(gb.getOpponentCard(), gb.getBordInfo()[0], gb.getBordInfo()[1], gb.getBordInfo()[2], gb.getBordInfo()[3])));
                break;
            case "リバー":

                flopAndTurnAndRiver();
                gb.setUserHand(gameMainHandJudgeService.execute(ArrayUtils.addAll(gb.getUserCard(), gb.getBordInfo())));
                gb.setOpponentHandInfo(gameMainHandJudgeService.execute(ArrayUtils.addAll(gb.getOpponentCard(), gb.getBordInfo())));
                break;

            case "ショーダウン":

                showdown();
                break;
        }
    }

    /**
     * プリフロップ時の処理です。
     */
    private void preFlop() {

        /*
		 *  ユーザーのブラインドによって分岐
		 *  分岐条件：ユーザーがSBでさらにアクションがベットの場合
         */
        if (blind.SB.toString().equals(gb.getUserBlind()) && action.BET.toString().equals(gb.getUserAction())) {

            caseOfSbBtnSet();

            // プリフロップ時にユーザーがオールインだった場合の分岐処理
            if (gb.getOpponentActionMsg().equals(TexasHoldemConstants.ALL_IN + gb.getOpponentAction())) {
                gb.setOpponentAllInFlag(true);
            }

        } else {

            preFlopUserActoinBranchingProcess();
        }

    }

    /**
     * フロップ、ターン、リバー時の処理です。
     */
    private void flopAndTurnAndRiver() {

        /*
		 *  ユーザーのブラインドによって分岐
		 *  分岐条件：ユーザーがBBでさらにアクションを一度も選択していない場合
         */
        if (blind.BB.toString().equals(gb.getUserBlind()) && gb.getUserAction().equals("")) {

            gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);
            gb.setUserTurn(true);
            caseOfBbBtnSet();

        } else {

            defaultUserActoinBranchingProcess();
        }

    }

    /**
     * ショーダウン時の処理です。
     */
    private void showdown() {

        gb.setUserSituation(gb.getUserHand());
        gb.setOpponentHandInfo(gameMainHandJudgeService.execute(ArrayUtils.addAll(gb.getOpponentCard(), gb.getBordInfo())));
        gb.setOpponentHand(gb.getOpponentHandInfo());

        // 役を判定して勝敗を決定する処理です。
//        if (gb.getRound() < TexasHoldemConstants.ROUND_MAX_NUMBER) {
        if (gameMainHandJudgeService.handStrengthJudgeExecute(gb.getUserHand())
                - gameMainHandJudgeService.handStrengthJudgeExecute(gb.getOpponentHand()) == 0) {

            // 引き分け
            gb.setBattleResultOfRound(battleResult.DRAW.toString());
            JavascriptFromJavaUtil.execute(battleResult.DRAW.roundDialogScriptToString());

        } else if (gameMainHandJudgeService.handStrengthJudgeExecute(gb.getUserHand())
                - gameMainHandJudgeService.handStrengthJudgeExecute(gb.getOpponentHand()) > 0) {

            // 勝ち
            gb.setBattleResultOfRound(battleResult.WIN.toString());

            // ユーザーと相手がお互いにオールイン時に勝利した場合の処理です。ゲームの勝敗が決定します。
            if (gb.getUserAllInFlag() && gb.getOpponentAllInFlag()) {
                gb.setBattleResult(battleResult.WIN.toString());
                gb.setBattleResultOfRound("");
                String changePoint = "+" + (gb.getPotPoint() / 2) + "(" + (gb.getUserPoint() + gb.getPotPoint()) + ")";
                gb.setChangePoint(changePoint);
                registerCreate();
                JavascriptFromJavaUtil.execute(battleResult.WIN.gameDialogScriptToString());
            } else {
                JavascriptFromJavaUtil.execute(battleResult.WIN.roundDialogScriptToString());
            }

        } else {

            // 負け
            gb.setBattleResultOfRound(battleResult.LOSE.toString());

            // ユーザーと相手がお互いにオールイン時に敗北した場合の処理です。ゲームの勝敗が決定します。
            if (gb.getUserAllInFlag() && gb.getOpponentAllInFlag()) {
                gb.setBattleResult(battleResult.LOSE.toString());
                gb.setBattleResultOfRound("");
                String changePoint = "-" + (gb.getPotPoint() / 2) + "(" + (gb.getUserPoint()) + ")";
                gb.setChangePoint(changePoint);
                registerCreate();
                JavascriptFromJavaUtil.execute(battleResult.LOSE.gameDialogScriptToString());
            } else {
                JavascriptFromJavaUtil.execute(battleResult.LOSE.roundDialogScriptToString());
            }
        }
    }

    /**
     * プリフロップ時の各ボタンアクション選択時の分岐処理です。
     */
    private void preFlopUserActoinBranchingProcess() {

        allBtnFalse();

        // プリフロップ時にユーザーがオールインだった場合の分岐処理
        if (gb.getUserActionMsg().equals(TexasHoldemConstants.ALL_IN + gb.getUserAction())) {
            gb.setUserAllInFlag(true);
        }

        switch (gb.getUserAction()) {

            case "ベット":

                // ユーザーのオールインフラグが立っていた場合の分岐処理
                if (gb.getUserAllInFlag()) {

                    opponentCall();
                    gameMainTimerService.execute();

                } else {

                    // CPUのアクション及び情報設定
                    opponentCall();
                    caseOfCallBtnSet();
                }

                break;

            case "レイズ":

                // ランダム生成
                int ran = (int) (Math.random() * 10);

                // ユーザーのオールインフラグが立っていた場合の分岐処理
                if (gb.getUserAllInFlag()) {
                    // 確率分岐(10%でフォールド、90%でコール)
                    if (ran == 0) {
                        opponentFold();

                    } else {
                        opponentCall();
                    }
                } else {
                    // 確率分岐(10%でフォールド、90%でコール)
                    if (ran == 0) {
                        opponentFold();

                    } else {

                        opponentCall();
                        gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);
                        caseOfCallBtnSet();
                    }
                }

                break;

            case "コール":

                // ユーザーのオールインフラグが立っていた場合の分岐処理
                if (gb.getUserAllInFlag()) {

                } else {
                    // CPUのアクション及び情報設定
                    opponentCheck();
                }

                break;

            case "チェック":

                // 局面を進行させる
                //			betRoundStepDefault();
                break;

        }
    }

    /**
     * フロップ、ターン、リバー時の各ボタンアクション選択時のCPU対戦の分岐処理です。
     */
    private void defaultUserActoinBranchingProcess() {

        allBtnFalse();

        switch (gb.getUserAction()) {

            case "":

                // ランダム生成
                int ran = (int) (Math.random() * 10);

                // 確率分岐(10%でベット、90%でチェック)
                if (ran == 0) {
                    // ベットの場合
                    opponentBet(TexasHoldemConstants.CPU_BET_POINT);
                    gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);
                    // ボタンの活性
                    caseOfBetBtnSet();
                } else {
                    // チェックの場合
                    opponentCheck();
                    gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);

                    // ボタンの活性
                    caseOfCheckBtnSet();
                }

                break;

            case "ベット":

                // ランダム生成
                int betRan = (int) (Math.random() * 10);

                // CPUアクションの確率分岐(10%でフォールド、20%でレイズ、70%でコール)
                if (betRan == 0) {
                    // フォールドの場合
                    opponentFold();

                } else if (betRan == 1 || betRan == 2) {
                    // レイズの場合
                    opponentRaise();
                    gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);
                    // ボタンの活性
                    caseOfRaiseBtnSet();

                } else {
                    // コールの場合
                    opponentCall();

                    // 局面を進行させる
                    //				betRoundStepDefault();
                }

                break;

            case "レイズ":

                // ランダム生成
                int raiseRan = (int) (Math.random() * 10);

                // 確率分岐(10%でフォールド、90%でコール)
                if (raiseRan == 0) {

                    opponentFold();

                } else {
                    // コールの場合
                    opponentCall();

                }
                break;

            case "コール":

                // 局面を進行させる
                //			betRoundStepDefault();
                break;

            case "チェック":

                // ランダム生成
                int checkRan = (int) (Math.random() * 10);

                // 確率分岐(10%でベット、90%でチェック)
                if (checkRan == 0) {

                    opponentBet(TexasHoldemConstants.CPU_BET_POINT);
                    gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);
                    // ボタンの活性
                    caseOfBetBtnSet();
                } else {

                    // チェック
                    opponentCheck();

                    // 局面を進行させる
                    //				betRoundStepDefault();
                }
                break;

        }
    }

    /**
     * ベット局面を進行させる処理
     */
    private void betRoundStepDefault() {

        // ユーザーか対戦相手がオールイン時の分岐処理です。
        if (gb.getUserAllInFlag() || gb.getOpponentAllInFlag()) {

            gb.setBetRound(betRound.SHOWDOWN.toString());

        } else {
            // 局面を進行させる
            stepBetRoundBranching();
        }

        // 局面進行時の初期処理
        betRoundStepInit();

        betRoundBranchingProcess();
    }

    /**
     * ベット局面を進行させる際の分岐処理
     */
    private void stepBetRoundBranching() {

        //script
        if (betRound.PREFLOP.toString().equals(gb.getBetRound())) {
            // プリフロップの場合
            gb.setBetRound(betRound.FLOP.toString());
        } else if (betRound.FLOP.toString().equals(gb.getBetRound())) {
            // フロップの場合
            gb.setBetRound(betRound.TURN.toString());
        } else if (betRound.TURN.toString().equals(gb.getBetRound())) {
            // ターンの場合
            gb.setBetRound(betRound.RIVER.toString());
        } else {
            // リバーの場合
            gb.setBetRound(betRound.SHOWDOWN.toString());
        }

    }

    /**
     * 局面進行時の初期処理をします。
     */
    private void betRoundStepInit() {

        gb.setUserAction("");
        gb.setUserActionMsg("");
        gb.setOpponentAction("");
        gb.setOpponentActionMsg("");
        gb.setCurrentUserBetPoint(0);
        gb.setCurrentOpponentBetPoint(0);

    }

    /**
     * 全ボタンアクションを非活性にします。
     */
    private void allBtnFalse() {

        gb.setBetBtnDisabled(true);
        gb.setRaiseBtnDisabled(true);
        gb.setCallBtnDisabled(true);
        gb.setCheckBtnDisabled(true);
        gb.setFoldBtnDisabled(true);
    }

    /**
     * ベット局面進行時にユーザーがSB時だった場合の全ボタンアクションの活性を設定します。
     */
    private void caseOfSbBtnSet() {

        gb.setBetBtnDisabled(true);
        gb.setRaiseBtnDisabled(false);
        gb.setCallBtnDisabled(false);
        gb.setCheckBtnDisabled(true);
        gb.setFoldBtnDisabled(false);
    }

    /**
     * ベット局面進行時にユーザーがBB時だった場合の全ボタンアクションの活性を設定します。
     */
    private void caseOfBbBtnSet() {

        gb.setBetBtnDisabled(false);
        gb.setRaiseBtnDisabled(true);
        gb.setCallBtnDisabled(true);
        gb.setCheckBtnDisabled(false);
        gb.setFoldBtnDisabled(false);
    }

    /**
     * 対戦相手のアクションがベットだった場合の全ボタンアクションの活性を設定します。
     */
    private void caseOfBetBtnSet() {

        gb.setBetBtnDisabled(true);
        gb.setRaiseBtnDisabled(false);

        // 既にベットしているかどうかでレイズするポイントの分岐処理
        if (gb.getCurrentUserBetPoint() == 0) {
            gb.setRaiseMin(gb.getCurrentOpponentBetPoint() * 2);
        } else {
            gb.setRaiseMin(gb.getCurrentOpponentBetPoint() * 2 - gb.getCurrentUserBetPoint());
        }
        gb.setCallBtnDisabled(false);
        gb.setCheckBtnDisabled(true);
        gb.setFoldBtnDisabled(false);
    }

    /**
     * 対戦相手のアクションがレイズだった場合の全ボタンアクションの活性を設定します。
     */
    private void caseOfRaiseBtnSet() {

        gb.setBetBtnDisabled(true);
        gb.setRaiseBtnDisabled(false);

        // 既にベットしているかどうかでレイズするポイントの分岐処理
        if (gb.getCurrentUserBetPoint() == 0) {
            gb.setRaiseMin(gb.getCurrentOpponentBetPoint() * 2);
        } else {
            gb.setRaiseMin(gb.getCurrentOpponentBetPoint() * 2 - gb.getCurrentUserBetPoint());
        }
        gb.setCallBtnDisabled(false);
        gb.setCheckBtnDisabled(true);
        gb.setFoldBtnDisabled(false);
    }

    /**
     * 対戦相手のアクションがチェックだった場合の全ボタンアクションの活性を設定します。
     */
    private void caseOfCheckBtnSet() {

        gb.setBetBtnDisabled(false);
        gb.setRaiseBtnDisabled(true);
        gb.setCallBtnDisabled(true);
        gb.setCheckBtnDisabled(false);
        gb.setFoldBtnDisabled(false);
    }

    /**
     * 対戦相手のアクションがコールだった場合の全ボタンアクションの活性を設定します。
     */
    private void caseOfCallBtnSet() {

        gb.setBetBtnDisabled(true);
        gb.setRaiseBtnDisabled(false);

        // 既にベットしているかどうかでレイズするポイントの分岐処理
        if (gb.getCurrentUserBetPoint() == 0) {
            gb.setRaiseMin(gb.getCurrentOpponentBetPoint() * 2);
        } else {
            gb.setRaiseMin(gb.getCurrentOpponentBetPoint() * 2 - gb.getCurrentUserBetPoint());
        }
        gb.setCallBtnDisabled(true);
        gb.setCheckBtnDisabled(false);
        gb.setFoldBtnDisabled(false);
    }

    /**
     * 対戦相手がベットアクション選択時の動作を設定します。
     */
    private void opponentBet(int betPoint) {

        // 対戦相手情報の設定
        gb.setOpponentAction(action.BET.toString());
        gb.setOpponentActionMsg(gb.getOpponentAction() + betPoint);

        // 対戦相手の所持ポイントの変動と合計ベットポイントの変動
        gb.setOpponentPoint(gb.getOpponentPoint() - betPoint);
        gb.setOpponentTotalBetPoint(gb.getOpponentTotalBetPoint() + betPoint);

        // 対戦相手の現在のベット局面での合計ベットポイントの設定
        gb.setCurrentOpponentBetPoint(betPoint);

        // オールイン時(全対戦相手ポイントをベットした場合)の処理です。
        if (gb.getOpponentPoint() <= 0) {
            gb.setOpponentActionMsg(TexasHoldemConstants.ALL_IN + gb.getOpponentAction());
            gb.setOpponentAllInFlag(true);
        }

        // ユーザー情報の設定
        gb.setUserTurn(true);
        gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);

        // ポット情報の設定
        gb.setPotPoint(gb.getPotPoint() + betPoint);
    }

    /**
     * 対戦相手がレイズアクション選択時の動作を設定します。
     */
    public void opponentRaise() {

        // レイズするポイント(最低レイズポイントでレイズする)
        int raiseCpuPoint;

        // 既にベットしているかどうかでレイズするポイントの分岐処理
        if (gb.getCurrentOpponentBetPoint() == 0) {
            raiseCpuPoint = gb.getCurrentUserBetPoint() * 2;
        } else {
            raiseCpuPoint = gb.getCurrentUserBetPoint() * 2 - gb.getCurrentOpponentBetPoint();
        }

        // オールイン時(全対戦相手ポイントをレイズした場合)の処理です。
        if ((gb.getOpponentPoint() - raiseCpuPoint) <= 0) {
            raiseCpuPoint = gb.getOpponentPoint();
            gb.setOpponentAllInFlag(true);
        }

        // 対戦相手情報の設定
        gb.setOpponentAction(action.RAISE.toString());
        gb.setOpponentActionMsg(gb.getOpponentAction() + (raiseCpuPoint + gb.getCurrentOpponentBetPoint()));

        // 対戦相手の所持ポイントの変動と合計ベットポイントの変動
        gb.setOpponentPoint(gb.getOpponentPoint() - raiseCpuPoint);
        gb.setOpponentTotalBetPoint(gb.getOpponentTotalBetPoint() + raiseCpuPoint);

        // 対戦相手の現在のベット局面での合計ベットポイントの設定
        gb.setCurrentOpponentBetPoint(gb.getCurrentOpponentBetPoint() + raiseCpuPoint);

        // ユーザー情報の設定
        gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);
        gb.setUserTurn(true);
        gb.setUserBetPoint(0);

        if (gb.getOpponentAllInFlag()) {
            gb.setOpponentActionMsg(TexasHoldemConstants.ALL_IN + gb.getOpponentAction());
        }

        // ポット情報の設定
        gb.setPotPoint(gb.getPotPoint() + raiseCpuPoint);

    }

    /**
     * 対戦相手がコールアクション選択時の動作を設定します。
     */
    private void opponentCall() {

        // 対戦相手情報の設定
        gb.setOpponentAction(action.CALL.toString());

        // コールする際に追加するベットポイント数
        int callPoint = gb.getCurrentUserBetPoint() - gb.getCurrentOpponentBetPoint();

        if (gb.getOpponentPoint() - callPoint <= 0) {

            // 現在の対戦相手の所持ポイントよりもコールしたポイントが多い場合(オールイン時)
            gb.setOpponentAllInFlag(true);
            gb.setOpponentActionMsg(TexasHoldemConstants.ALL_IN + gb.getOpponentAction());
            gb.setCurrentOpponentBetPoint(gb.getOpponentPoint() + gb.getCurrentOpponentBetPoint());

            gb.setOpponentTotalBetPoint(gb.getOpponentTotalBetPoint() + gb.getCurrentOpponentBetPoint());

            // ポット情報の設定
            gb.setPotPoint(gb.getPotPoint() + gb.getOpponentPoint());
            gb.setOpponentPoint(0);
        } else {

            // 対戦相手情報の設定
            gb.setOpponentActionMsg(gb.getOpponentAction() + (callPoint + gb.getCurrentOpponentBetPoint()));
            gb.setCurrentOpponentBetPoint(callPoint + gb.getCurrentOpponentBetPoint());
            gb.setOpponentPoint(gb.getOpponentPoint() - callPoint);
            gb.setOpponentTotalBetPoint(gb.getOpponentTotalBetPoint() + callPoint);
            gb.setUserTurn(true);
            //				gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);

            // ポット情報の設定
            gb.setPotPoint(gb.getPotPoint() + callPoint);

        }
    }

    /**
     * 対戦相手がチェックアクション選択時の動作を設定します。
     */
    private void opponentCheck() {

        // 対戦相手情報の設定
        gb.setOpponentAction(action.CHECK.toString());
        gb.setOpponentActionMsg(gb.getOpponentAction());

    }

    /**
     * 対戦相手がフォールドアクション選択時の動作を設定します。
     */
    private void opponentFold() {

        gb.setOpponentAction(action.FOLD.toString());
        gb.setOpponentActionMsg(gb.getOpponentAction());

    }

    /**
     * 各レコード情報を作成します。
     */
    private void registerCreate() {
        gameMainRegisterService.createDetailBattleRecordTableInfo();
        gameMainRegisterService.createBattleRecordTableInfo();
    }

}
