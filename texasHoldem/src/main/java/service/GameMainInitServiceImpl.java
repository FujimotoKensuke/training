package service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.CardBean;
import beans.GameBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;
import common.TexasHoldemEnum.betRound;
import common.TexasHoldemEnum.blind;
import common.TexasHoldemEnum.cardNumber;
import common.TexasHoldemEnum.suit;

/**
 * ゲームメイン画面の初期表示処理を行うサービスクラスです。
 */
@RequestScoped
public class GameMainInitServiceImpl implements GameMainInitService {

    @Inject
    GameBean gb;

    @Inject
    GameMainHandJudgeService gameMainHandJudgeService;

    /**
     * トランプの数字の列挙型クラスの配列です。
     */
    private cardNumber[] cardnum = cardNumber.values();

    /**
     * トランプ全52種類を格納する配列です。
     */
    private CardBean[] allCard = new CardBean[TexasHoldemConstants.ALL_CARD_NUMBER];

    // 配列の初期化
    private String[] userCard = new String[2];

    private String[] opponentCard = new String[2];

    private String[] boardCard = new String[5];

    @Override
    public void execute() {

        setInit();

    }

    /**
     * ゲーム開始時の初期表示を設定します。
     */
    private void setInit() {

        // 対戦相手情報をセットします。
        gb.setOpponentName(TexasHoldemConstants.CPU_NAME);

        blindDecide();
        cardShuffle();

    }

    /**
     * トランプをシャッフルして配ります。
     */
    private void cardShuffle() {

        storedCard();
        shuffle();

        StringBuffer sb = new StringBuffer();

        // 1枚目と2枚目をユーザーに配ります。
        for (int i = 0; i < userCard.length; i++) {
            userCard[i] = allCard[i].toString();
            sb.append(allCard[i].imgString());
        }
        gb.setUserCardInfo(sb.toString());
        sb = new StringBuffer();

        // 3枚目と4枚目を対戦相手に配ります。
        for (int i = 0; i < opponentCard.length; i++) {
            opponentCard[i] = allCard[i + 2].toString();
            sb.append(allCard[i + 2].imgString());
        }
        gb.setOpponentCardInfo(sb.toString());
        sb = new StringBuffer();

        // 5～9枚目をボードに配ります。
        for (int i = 0; i < boardCard.length; i++) {
            boardCard[i] = allCard[i + 4].toString();
            sb.append(allCard[i + 4].imgString());
        }
        gb.setBordCardInfo(sb.toString());

        // シャッフルしたトランプをセットします。
        gb.setUserCard(userCard);
        gb.setUserHand(gameMainHandJudgeService.execute(gb.getUserCard()));
        gb.setOpponentCard(opponentCard);
        gb.setBordInfo(boardCard);
    }

    /**
     * SBかBBかを決定し、それに伴う初期表示処理をします。
     */
    private void blindDecide() {

        if ((gb.getRound() == 0) && (TexasHoldemConstants.CPU_NAME.equals(gb.getOpponentName()))) {
            // ゲーム開始時かつCPU対戦の場合
            gb.setUserBlind(blind.SB.toString());
            gb.setOpponentBlind(blind.BB.toString());

            // ラウンド数をセットします。
            gb.setRound(1);

            // 現在日時を取得してゲーム日時にセットします。
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            gb.setBattleDate(sdf.format(date));

            blindSbInitSetFirst();
        } else if ((gb.getRound() < 10) && (TexasHoldemConstants.CPU_NAME.equals(gb.getOpponentName()))) {
            // ラウンドが1～9以下かつCPU対戦の場合
            changeBlind();

            // ラウンド数を1つ進めます
            gb.setRound(gb.getRound() + 1);

        } else if ((gb.getRound() == 0) && (!TexasHoldemConstants.CPU_NAME.equals(gb.getOpponentName()))) {
            //ゲーム開始時かつCPU対戦じゃない場合
            randomSelectBlind();

            // ラウンド数をセットします。
            gb.setRound(1);

            // 現在日時を取得してゲーム日時にセットします。
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d HH:mm");
            gb.setBattleDate(sdf.format(date));

        } else if ((gb.getRound() < 10) && (!TexasHoldemConstants.CPU_NAME.equals(gb.getOpponentName()))) {
            // ラウンドが1～9以下かつCPU対戦CPU対戦じゃない場合
            changeBlind();

            // ラウンド数を1つ進めます
            gb.setRound(gb.getRound() + 1);

        } else {
            // ラウンドが10以上の場合
        }

    }

    /**
     * ユーザーのブラインドがSBかつ1ラウンドだった場合の初期表示処理です。
     */
    private void blindSbInitSetFirst() {

        gb.setUserPoint(TexasHoldemConstants.FIRST_POINT);
        gb.setOpponentPoint(TexasHoldemConstants.FIRST_POINT);

        blindSbInitSet();
    }

    /**
     * ユーザーのブラインドがSBになった場合の初期表示処理です。
     */
    private void blindSbInitSetDefault() {

        blindSbInitSet();
    }

    /**
     * ユーザーのブラインドがSBだった場合の初期表示処理です。
     */
    private void blindSbInitSet() {

        // ラウンド局面をプリフロップにします。
        gb.setBetRound(betRound.PREFLOP.toString());

        // ユーザー情報をセットします。
        gb.setUserTurn(true);
        gb.setUserPoint(gb.getUserPoint() - TexasHoldemConstants.FIRST_SB_BETPOINT);
        gb.setUserBetPoint(TexasHoldemConstants.FIRST_SB_BETPOINT);
        gb.setUserAction(action.BET.toString());
        gb.setUserActionMsg(gb.getUserAction() + gb.getUserBetPoint());
        gb.setUserTotalBetPoint(TexasHoldemConstants.FIRST_SB_BETPOINT);
        gb.setUserSituation(TexasHoldemConstants.YOUR_TURN);
        gb.setCurrentUserBetPoint(gb.getUserBetPoint());
//		gb.setUserHand("");

        // CPU情報をセットします。
        gb.setOpponentAction(action.BET.toString());
        gb.setOpponentPoint(gb.getOpponentPoint() - TexasHoldemConstants.FIRST_BB_BETPOINT);
        gb.setOpponentBetPoint(TexasHoldemConstants.FIRST_BB_BETPOINT);
        gb.setOpponentActionMsg(gb.getOpponentAction() + gb.getOpponentBetPoint());
        gb.setOpponentTotalBetPoint(TexasHoldemConstants.FIRST_BB_BETPOINT);
        gb.setCurrentOpponentBetPoint(gb.getOpponentBetPoint());
        gb.setOpponentHand("");

        // ユーザーアクションボタンの設定をセットします。
        gb.setRaiseMin(gb.getOpponentBetPoint() * 2 - 1);
        gb.setBetBtnDisabled(true);
        gb.setCheckBtnDisabled(true);

        // ポット情報をセットします。
        gb.setPotPoint(gb.getCurrentUserBetPoint() + gb.getCurrentOpponentBetPoint());

        // 処理済みの値をリセットします。
        gb.setUserBetPoint(0);
        gb.setOpponentBetPoint(0);
        gb.setBattleResultOfRound("");
        gb.setUserFoldFlag(false);
        gb.setOpponentFoldFlag(false);
        gb.setUserAllInFlag(false);
        gb.setOpponentAllInFlag(false);

        // オールイン時(全対戦相手ポイントをベットした場合)の処理です。
        if (gb.getOpponentPoint() <= 0) {
            gb.setOpponentActionMsg(TexasHoldemConstants.ALL_IN + gb.getOpponentAction());
        }

    }

    /**
     * ユーザーのブラインドがBBかつ1ラウンド目だった場合の初期表示処理です。
     */
    private void blindBbInitSetFirst() {

        gb.setUserPoint(TexasHoldemConstants.FIRST_POINT);
        gb.setOpponentPoint(TexasHoldemConstants.FIRST_POINT);

        blindBbInitSet();
    }

    /**
     * ユーザーのブラインドがBBになった場合の初期表示処理です。
     */
    private void blindBbInitSetDefault() {

        blindBbInitSet();
    }

    /**
     * ユーザーのブラインドがBBだった場合の初期表示処理です。
     */
    private void blindBbInitSet() {

        // ラウンド局面をプリフロップにします。
        gb.setBetRound(betRound.PREFLOP.toString());

        // ユーザー情報をセットします。
        gb.setUserTurn(false);
        gb.setUserPoint(gb.getUserPoint() - TexasHoldemConstants.FIRST_BB_BETPOINT);
        gb.setUserBetPoint(TexasHoldemConstants.FIRST_BB_BETPOINT);
        gb.setUserAction(action.BET.toString());
        gb.setUserActionMsg(gb.getUserAction() + gb.getUserBetPoint());
        gb.setUserTotalBetPoint(TexasHoldemConstants.FIRST_BB_BETPOINT);
        gb.setUserSituation("");
        gb.setCurrentUserBetPoint(gb.getUserBetPoint());
//		gb.setUserHand("");

        // CPU情報をセットします。
        gb.setOpponentPoint(gb.getOpponentPoint() - TexasHoldemConstants.FIRST_SB_BETPOINT);
        gb.setOpponentAction(action.BET.toString());
        gb.setOpponentBetPoint(TexasHoldemConstants.FIRST_SB_BETPOINT);
        gb.setOpponentActionMsg(gb.getOpponentAction() + gb.getOpponentBetPoint());
        gb.setOpponentTotalBetPoint(TexasHoldemConstants.FIRST_SB_BETPOINT);
        gb.setCurrentOpponentBetPoint(gb.getOpponentBetPoint());
        gb.setOpponentHand("");

        // ユーザーアクションボタンの設定をセットします。
        gb.setRaiseMin(gb.getOpponentBetPoint() * 2 - 1);
        allBtnFalse();

        // ポット情報をセットします。
        gb.setPotPoint(gb.getCurrentUserBetPoint() + gb.getCurrentOpponentBetPoint());

        // 処理済みの値をリセットします。
        gb.setUserBetPoint(0);
        gb.setOpponentBetPoint(0);
        gb.setBattleResultOfRound("");
        gb.setUserFoldFlag(false);
        gb.setOpponentFoldFlag(false);
        gb.setUserAllInFlag(false);
        gb.setOpponentAllInFlag(false);

        // オールイン時(全ユーザーポイントをベットした場合)の処理です。
        if (gb.getUserPoint() <= 0) {
            gb.setUserActionMsg(TexasHoldemConstants.ALL_IN + gb.getUserAction());
        }
    }

    /**
     * トランプ全52種類を格納します。
     */
    private void storedCard() {
        for (int i = 0; i < TexasHoldemConstants.CARD_TYPE_NUMBER; i++) {
            int suitCount = 0;
            for (suit suit : suit.values()) {
                allCard[i + TexasHoldemConstants.CARD_TYPE_NUMBER * suitCount] = new CardBean(cardnum[i], suit);
                suitCount++;
            }
        }
    }

    /**
     * トランプをシャッフルします。
     */
    private void shuffle() {
        List<CardBean> list = Arrays.asList(allCard);
        Collections.shuffle(list);
        allCard = (CardBean[]) list.toArray(new CardBean[list.size()]);
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
     * 現在のブラインドポジションを変更して初期化します。
     */
    private void changeBlind() {

        if (blind.SB.toString().equals(gb.getUserBlind())) {
            gb.setUserBlind(blind.BB.toString());
            gb.setOpponentBlind(blind.SB.toString());

            blindBbInitSetDefault();
        } else {
            gb.setUserBlind(blind.SB.toString());
            gb.setOpponentBlind(blind.BB.toString());

            blindSbInitSetDefault();
        }
    }

    /**
     * ブラインドポジションをランダムで決定します。
     */
    private void randomSelectBlind() {

        int ran = (int) (Math.random() * 2);

        if (ran == 0) {
            gb.setUserBlind(blind.BB.toString());
            gb.setOpponentBlind(blind.SB.toString());

            blindBbInitSetFirst();
        } else {
            gb.setUserBlind(blind.SB.toString());
            gb.setOpponentBlind(blind.BB.toString());

            blindSbInitSetFirst();
        }
    }
}
