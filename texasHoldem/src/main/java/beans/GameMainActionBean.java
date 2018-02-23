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
import util.DialogUtil;

/**
 * ゲームメイン画面のアクションビーンクラスです。
 */
@Named
@RequestScoped
public class GameMainActionBean{

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
	 */
	public void init() throws Exception{

		gameMainInitService.execute();

	}

	/**
	 * ベットボタン押下時の処理です。
	 */
	public void betAction() throws Exception{

		betBtnService.execute();
		gameMainService.execute();


	}

	/**
	 * レイズボタン押下時の処理です。
	 */
	public void raiseAction() throws Exception{

		raiseBtnService.execute();
		gameMainService.execute();

	}

	/**
	 * コールボタン押下時の処理です。
	 */
	public void callAction() throws Exception{

		callBtnService.execute();
		gameMainService.execute();

	}

	/**
	 * チェックボタン押下時の処理です。
	 */
	public void checkAction() throws Exception{

		checkBtnService.execute();
		gameMainService.execute();

	}

	/**
	 * フォールドボタン押下時の処理です。
	 */
	public void foldAction() throws Exception{

		foldBtnService.execute();
		gameMainService.execute();

	}

	/**
	 * モーダルダイアログを閉じます。
	 * @return
	 * @throws Exception
	 */
	public void closeDialog() throws Exception{

		DialogUtil.modalDialogClose();
		gameMainInitService.execute();
		gameMainService.execute();

	}

	/**
	 * ベット局面を進行させるタイマーです。
	 * @return
	 * @throws Exception
	 */
	public void stepBetRound() throws Exception{
		System.out.println("タイマー1");
		gameMainTimerService.execute();
	}

	/**
	 * 局面を進行時のタイマーです。
	 * @return
	 * @throws Exception
	 */
	public void stepRound() throws Exception{
		System.out.println("タイマー2");
		gameMainService.execute();
	}

	public String gameFinish() throws Exception{

		gameMainRegisterService.execute();
		return "/menu.xhtml?faces-redirect=true";
	}

}
