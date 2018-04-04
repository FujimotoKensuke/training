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
     *
     * @throws java.lang.Exception
     */
    public void init() throws Exception {

        battleRecordService.execute();

    }

    /**
     * モーダルダイアログを表示します。
     *
     * @throws Exception
     */
    public void showDialog() throws Exception {

        battleRecordService.setDetail();
        DialogUtil.modalDialogShow("detailBattleRecordDialog.xhtml", 700);

    }

    /**
     * モーダルダイアログを閉じます。
     *
     * @throws Exception
     */
    public void closeDialog() throws Exception {

        DialogUtil.modalDialogClose();

    }

    /**
     * 戻るボタンのメソッドです。メイン画面に遷移します。
     *
     * @return
     * @throws Exception
     */
    public String backBottun() throws Exception {
        return "/menu.xhtml?faces-redirect=true";
    }
}
