package service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum.action;
import common.TexasHoldemEnum.battleResult;

/**
 * フォールドボタン処理を行うサービスクラスです。
 */
@RequestScoped
public class FoldBtnServiceImpl implements FoldBtnService {

    @Inject
    private GameBean gb;

    @Inject
    private GameMainService gameMainService;

    @Override
    public void execute() {

        fold();

    }

    /**
     * フォールドボタン押下時の動作を設定します。
     */
    public void fold() {

        gb.setUserAction(action.FOLD.toString());
        gb.setUserActionMsg(gb.getUserAction());
        gb.setUserSituation("");
        gb.setUserFoldFlag(true);
        gb.setBattleResultOfRound(battleResult.LOSE.toString());
        gameMainService.resultDialogShow(battleResult.LOSE.roundDialogScriptToString());

    }

}
