package service;

import javax.enterprise.context.RequestScoped;

/**
 * DBにユーザーを登録するインターフェースです。
 */
@RequestScoped
public interface UserCreateService {

	/**
	 * DBのusertableにユーザー登録し、ログインしてメインメニュー画面へ遷移します。
	 * @return String
	 */
	public String execute();
}
