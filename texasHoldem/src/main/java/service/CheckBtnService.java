package service;

import javax.enterprise.context.RequestScoped;

/**
 * チェックボタン処理のインターフェースです。
 */
@RequestScoped
public interface CheckBtnService {

	/**
	 * チェックアクション処理
	 */
	public void execute();

}
