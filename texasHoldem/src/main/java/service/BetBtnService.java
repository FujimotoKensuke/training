package service;

import javax.enterprise.context.RequestScoped;

/**
 * ベットボタン処理のインターフェースです。
 */
@RequestScoped
public interface BetBtnService {

	/**
	 * ベットアクション処理
	 */
	public void execute();

}
