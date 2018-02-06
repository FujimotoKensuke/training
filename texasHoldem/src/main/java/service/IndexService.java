package service;

import javax.enterprise.context.RequestScoped;

/**
 * ログイン処理のインターフェースです。
 */
@RequestScoped
public interface IndexService {

	/**
	 * ログイン処理
	 * @return String
	 */
	public String execute();

}
