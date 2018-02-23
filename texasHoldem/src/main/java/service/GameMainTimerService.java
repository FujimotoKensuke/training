package service;

import javax.enterprise.context.RequestScoped;

/**
 * ゲームメイン画面の定期的に行われる処理のインターフェースです。
 */
@RequestScoped
public interface GameMainTimerService {

		/**
		 * タイマーで実行される処理
		 */
		public void execute();

}
