package service;

import java.awt.event.WindowAdapter;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * ログアウト処理のサービスクラスです。
 */
@RequestScoped
public class MenuServiceImpl extends WindowAdapter implements MenuService {

	/**
	 * ログアウトします。その後ログイン画面に遷移します。
	 * @return String
	 */
	@Override
	public String execute() {

		return logout();

	}

	/**
	 * ログアウトします。その後ログイン画面に遷移します。
	 * @return String
	 */
	private String logout() {

		try {

			getServlet().invalidateSession();
			HttpServletRequest request = getRequest();
			request.logout();

		} catch (ServletException e) {
		}

		return "/index.xhtml?faces-redirect=true ";

	}

	/**
	 * サーブレット環境を取得します。
	 * @return ExternalContext
	 */
	private ExternalContext getServlet() {

		return FacesContext.getCurrentInstance().getExternalContext();

	}

	/**
	 * リクエストオブジェクトを取得します。
	 * @return HttpServletRequest
	 */
	private HttpServletRequest getRequest() {

		return (HttpServletRequest) getServlet().getRequest();

	}
}
