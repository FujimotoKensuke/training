package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.BattleRecordService;
import util.DialogUtil;

/**
 * 戦績表示画面のアクションビーンクラスです。
 */
@Named
@RequestScoped
public class BattleRecordActionBean {

	@Inject
	BattleRecordService battleRecordService;

	/**
	 * 初期表示処理です。
	 */
	public void init() throws Exception{

		battleRecordService.execute();
		System.out.println("呼ばれたよ");

	}

	/**
     * モーダルダイアログを表示します。
     * @return
     * @throws Exception
     */
	public void showDialog() throws Exception{

		battleRecordService.setDetail();
		DialogUtil.modalDialogShow("detailBattleRecordDialog.xhtml",700);

	}

	/**
     * モーダルダイアログを閉じます。
     * @return
     * @throws Exception
     */
	public void closeDialog() throws Exception{

		DialogUtil.modalDialogClose();

	}

	/**
	 * 戻るボタンのメソッドです。メイン画面に遷移します。
	 * @return
	 * @throws Exception
	 */
	public String backBottun() throws Exception {
		return "/menu.xhtml?faces-redirect=true";
	}
}
