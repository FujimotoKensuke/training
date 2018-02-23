package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.IndexService;

/**
 * 初期(ログイン)画面のアクションビーンクラスです。
 */
@Named
@RequestScoped
public class IndexActionBean {

	@Inject
	private IndexService indexService;
	/**
     * ログイン認証を行います。認証成功でメインメニュー画面へ遷移します。
     * @return 遷移先名称
     * @throws Exception
     */
	public String execute() throws Exception{

		return indexService.execute();

	}

	/**
     * ユーザー登録画面へ遷移します。
     * @return 遷移先名称
     * @throws Exception
     */
	public String goUserCreate() throws Exception{

		return "/userCreate.xhtml?faces-redirect=true";
	}
}
