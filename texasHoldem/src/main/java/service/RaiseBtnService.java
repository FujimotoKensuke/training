package service;

import javax.enterprise.context.RequestScoped;

/**
 * レイズボタン処理のインターフェースです。
 */
@RequestScoped
public interface RaiseBtnService {

	/**
	 * レイズアクション処理
	 */
	public void execute();

}
