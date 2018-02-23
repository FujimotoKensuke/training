package service;

import javax.enterprise.context.RequestScoped;

/**
 * フォールドボタン処理のインターフェースです。
 */
@RequestScoped
public interface FoldBtnService {

	/**
	 * フォールドアクション処理
	 */
	public void execute();

}
