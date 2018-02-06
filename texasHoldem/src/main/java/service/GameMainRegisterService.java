package service;

import javax.enterprise.context.RequestScoped;

/**
 * ゲームメイン画面のDB登録に関わるインターフェースです。
 */
@RequestScoped
public interface GameMainRegisterService {

	/**
	 * DB登録処理
	 */
	public void execute();

	/**
	 * 戦績データを格納する処理
	 */
	public void createBattleRecordTableInfo();

	/**
	 * 戦績の詳細データを格納する処理
	 */
	public void createDetailBattleRecordTableInfo();
}
