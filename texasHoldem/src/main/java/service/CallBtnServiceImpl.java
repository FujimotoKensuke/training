package service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;

/**
 * コールボタン処理を行うサービスクラスです。
 */
@RequestScoped
public class CallBtnServiceImpl implements CallBtnService {

    @Inject
    private GameBean gb;

    @Inject
    private GameMainService gameMainService;

    @Override
    public void execute() {

        call();
        gameMainService.allBtnNotUsable();
    }

    /**
     * コールボタン押下時の動作を設定します。
     */
    private void call() {

        // ユーザー情報の設定
        gb.setUserAction(action.CALL.toString());

        // コールする際に追加するベットポイント数
        int callPoint = gb.getCurrentOpponentBetPoint() - gb.getCurrentUserBetPoint();

        // 現在のユーザーの所持ポイントよりもコールしたポイントが多い場合(オールイン時)
        if (gb.getUserPoint() - callPoint <= 0) {

            gb.setUserActionMsg(TexasHoldemConstants.ALL_IN + gb.getUserAction());
            gb.setUserAllInFlag(true);
            gb.setCurrentUserBetPoint(gb.getUserPoint() + gb.getCurrentUserBetPoint());
            gb.setUserTotalBetPoint(gb.getUserTotalBetPoint() + gb.getCurrentUserBetPoint());

            // ポット情報の設定
            gb.setPotPoint(gb.getPotPoint() + gb.getUserPoint());
            gb.setUserPoint(0);

        } else {

            // ユーザー情報の設定
            gb.setUserActionMsg(gb.getUserAction() + (callPoint + gb.getCurrentUserBetPoint()));
            gb.setCurrentUserBetPoint(callPoint + gb.getCurrentUserBetPoint());
            gb.setUserPoint(gb.getUserPoint() - callPoint);
            gb.setUserTotalBetPoint(gb.getUserTotalBetPoint() + callPoint);
            gb.setUserTurn(false);
            gb.setUserSituation("");

            // ポット情報の設定
            gb.setPotPoint(gb.getPotPoint() + callPoint);

        }

    }

}
