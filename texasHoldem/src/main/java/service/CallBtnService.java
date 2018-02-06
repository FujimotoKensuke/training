package service;

import javax.enterprise.context.RequestScoped;

/**
 * コールボタン処理のインターフェースです。
 */
@RequestScoped
public interface CallBtnService {

	/**
	 * コールアクション処理
	 */
	public void execute();

}
