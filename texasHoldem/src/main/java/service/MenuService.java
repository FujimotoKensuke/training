package service;

import javax.enterprise.context.RequestScoped;

/**
 * ログアウト処理のインターフェースです。
 */
@RequestScoped
public interface MenuService {

	/**
	 * ログアウトします。その後ログイン画面に遷移します。
	 * @return String
	 */
	public String execute();
}
