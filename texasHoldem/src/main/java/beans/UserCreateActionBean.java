package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.UserCreateService;

/**
 * ユーザー登録画面のアクションビーンクラスです。
 */
@Named
@RequestScoped
public class UserCreateActionBean {

	@Inject
	private UserCreateService userCreateService;

	/**
     * ユーザー登録を行います。登録完了後メインメニュー画面へ遷移します。
     * @return 遷移先名称
     * @throws Exception
     */
	public String execute() throws Exception{

		return userCreateService.execute();

	}
}
