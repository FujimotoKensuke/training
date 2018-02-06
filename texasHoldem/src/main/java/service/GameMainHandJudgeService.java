package service;

import javax.enterprise.context.RequestScoped;

/**
 * ゲームメイン画面のポーカーの役判定のインターフェースです。
 */
@RequestScoped
public interface GameMainHandJudgeService {

	/**
	 * ポーカーの役判定処理
	 */
	public String execute(String[] card);

	/**
	 * ポーカーの役の強さ判定処理
	 */
	public int handStrengthJudgeExecute(String myHand);

}
