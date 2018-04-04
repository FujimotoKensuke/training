package service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import common.TexasHoldemEnum.action;

/**
 * チェックボタン処理を行うサービスクラスです。
 */
@RequestScoped
public class CheckBtnServiceImpl implements CheckBtnService {

    @Inject
    private GameBean gb;

    @Inject
    private GameMainService gameMainService;

    @Override
    public void execute() {

        check();
        gameMainService.allBtnNotUsable();

    }

    /**
     * チェックボタン押下時の動作を設定します。
     */
    public void check() {

        gb.setUserAction(action.CHECK.toString());
        gb.setUserActionMsg(gb.getUserAction());
        gb.setUserTurn(false);
        gb.setUserSituation("");

    }

}
