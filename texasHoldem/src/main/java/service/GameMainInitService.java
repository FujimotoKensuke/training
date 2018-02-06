package service;

import javax.enterprise.context.RequestScoped;

/**
 * ゲームメイン画面の初期表示処理のインターフェースです。
 */
@RequestScoped
public interface GameMainInitService {

	/**
	 * 初期表示処理
	 */
	public void execute();
}
