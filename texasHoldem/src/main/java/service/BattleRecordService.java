package service;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public interface BattleRecordService {

	/**
	 * 初期表示処理
	 * @return
	 */
	public void execute();

	/**
	 * DBの詳細情報設定処理
	 * @return
	 */
	public void setDetail();

}
