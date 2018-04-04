package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.BetBtnService;
import service.CallBtnService;
import service.CheckBtnService;
import service.FoldBtnService;
import service.GameMainInitService;
import service.GameMainRegisterService;
import service.GameMainService;
import service.GameMainTimerService;
import service.RaiseBtnService;

/**
 * ゲームメイン画面のアクションビーンクラスです。
 */
@Named
@RequestScoped
public class GameMainActionBean {

    @Inject
    private GameMainInitService gameMainInitService;

    @Inject
    private GameMainService gameMainService;

    @Inject
    private BetBtnService betBtnService;

    @Inject
    private RaiseBtnService raiseBtnService;

    @Inject
    private CallBtnService callBtnService;

    @Inject
    private CheckBtnService checkBtnService;

    @Inject
    private FoldBtnService foldBtnService;

    @Inject
    private GameMainTimerService gameMainTimerService;

    @Inject
    private GameMainRegisterService gameMainRegisterService;

    /**
     * 初期表示処理です。
     *
     * @throws Exception
     */
    public void init() throws Exception {

        gameMainInitService.execute();

    }

    /**
     * ベットボタン押下時の処理です。
     *
     * @throws Exception
     */
    public void betAction() throws Exception {

        betBtnService.execute();
        gameMainService.timerScriptExecute();

    }

    /**
     * レイズボタン押下時の処理です。
     *
     * @throws Exception
     */
    public void raiseAction() throws Exception {

        raiseBtnService.execute();
        gameMainService.timerScriptExecute();

    }

    /**
     * コールボタン押下時の処理です。
     *
     * @throws Exception
     */
    public void callAction() throws Exception {

        callBtnService.execute();
        gameMainService.timerScriptExecute();
    }

    /**
     * チェックボタン押下時の処理です。
     *
     * @throws Exception
     */
    public void checkAction() throws Exception {

        checkBtnService.execute();
        gameMainService.timerScriptExecute();
    }

    /**
     * フォールドボタン押下時の処理です。
     *
     * @throws Exception
     */
    public void foldAction() throws Exception {

        foldBtnService.execute();
        gameMainService.execute();

    }

    /**
     * モーダルダイアログを閉じます。
     *
     * @throws Exception
     */
    public void closeDialog() throws Exception {

        gameMainInitService.execute();
        gameMainService.execute();

    }

    /**
     * ベット局面を進行させるタイマーです。
     *
     * @throws Exception
     */
    public void stepBetRound() throws Exception {
        gameMainTimerService.execute();
    }

    /**
     * 局面を進行時のタイマーです。
     *
     * @throws Exception
     */
    public void stepRound() throws Exception {
        gameMainService.execute();
    }

    /**
     * ゲーム終了時のメソッドです。
     *
     * @return
     * @throws Exception
     */
    public String gameFinish() throws Exception {

        gameMainRegisterService.execute();
        return "/menu.xhtml?faces-redirect=true";
    }

}
