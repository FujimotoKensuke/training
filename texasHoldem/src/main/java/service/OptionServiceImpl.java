package service;

import beans.UserBean;
import common.TexasHoldemConstants;
import db.UserTableDb;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import util.MessageUtil;

@RequestScoped
public class OptionServiceImpl implements OptionService {

    @Inject
    private UserBean ub;

    @EJB
    private UserTableDb db;

    @Override
    public void execute() {
        updateUserNameAndComments();
    }

    @Override
    public void init() {
        ub.setVersion(db.versionSelect(ub));
    }

    /**
     * ユーザー名とコメントを更新します。
     */
    private void updateUserNameAndComments() {

        if (db.versionSelect(ub) != ub.getVersion()) {
            throw new OptimisticLockException(TexasHoldemConstants.UPDATE_MISS_MSG);
        }
        db.updateUserNameAndComments(ub);
        MessageUtil.facesMessage(TexasHoldemConstants.UPDATE_MSG);
    }

}
