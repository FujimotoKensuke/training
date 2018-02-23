package service;

import beans.UserBean;
import db.UserTableDb;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class OptionServiceImpl implements OptionService{
    
    @Inject
    private UserBean ub;
    
    @EJB
    private UserTableDb db;
    
    @Override
    public void execute() {
        updateUserNameAndComments();
    }
    
    /**
     * ユーザー名とコメントを更新します。
     */
    private void updateUserNameAndComments(){
        
        db.updateUserName(ub);
        db.updateComments(ub);
    }
    
}
