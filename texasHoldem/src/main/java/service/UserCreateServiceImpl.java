package service;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.UserBean;
import common.TexasHoldemConstants;
import common.TexasHoldemEnum;
import db.UserTableDb;
import entity.Usertable;
import util.MessageUtil;

/**
 * DBにユーザーを登録するサービスクラスです。
 */
@RequestScoped
public class UserCreateServiceImpl implements UserCreateService {

    @Inject
    private UserBean ub;

    @EJB
    private UserTableDb db;

    /**
     * DBのusertableにユーザー登録し、ログインしてメインメニュー画面へ遷移します。
     *
     * @return String
     */
    @Override
    public String execute() {

        return userCreateAndLogin();

    }

    /**
     * DBのusertableにユーザーID、ユーザー名、パスワードを登録します。 その後ログインしてメインメニュー画面に遷移します。
     *
     * @return
     */
    private String userCreateAndLogin() {

        Usertable user = new Usertable(ub.getUserId(), ub.getUserName(), ub.getPassword(),
                 null, TexasHoldemEnum.deleteFlg.OFF.getNumber(), TexasHoldemEnum.displyFlg.ON.getNumber(), 0);

        try {

            db.create(user);
            db.loginSelect(ub);
            return "/menu.xhtml?faces-redirect=true";

        } catch (Exception e) {

            MessageUtil.facesMessage(TexasHoldemConstants.CREATE_ERROR_MSG);
            return "";

        }

    }

}
