package service;

import javax.enterprise.context.RequestScoped;

/**
 * 各アクション処理のインターフェースです。
 */
@RequestScoped
public interface GameMainService {

	/**
	 * ゲーム進行処理
	 */
	public void execute();

	/**
	 * ベット局面を進行させる処理
	 */
	public void betRoundStep();

}
