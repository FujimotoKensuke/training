package service;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.UserBean;
import common.TexasHoldemConstants;
import db.UserTableDb;
import util.MessageUtil;

/**
 * ログイン処理を行うサービスクラスです。
 */
@RequestScoped
public class IndexServiceImpl implements IndexService {

	@Inject
	private UserBean ub;

	@EJB
	private UserTableDb db;

	/**
	 * ログイン認証を行います。成功でメインメニュー画面に遷移します。
	 */
	@Override
	public String execute() {

		return login();

	}

	/**
	 * ログイン認証を行います。成功でメインメニュー画面に遷移します。
	 * @return String
	 */
	private String login() {

		try {

			db.loginSelect(ub);

		} catch (Exception e) {

			MessageUtil.facesMessage(TexasHoldemConstants.LOGIN_ERROR_MSG);
			return "";

		}

		return "/menu.xhtml?faces-redirect=true";

	}

}
