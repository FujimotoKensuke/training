package db;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.UserBean;
import entity.Usertable;
import java.util.List;

/**
 * ユーザーテーブルのDB接続クラスです。
 *
 */
@Stateless
public class UserTableDb {

	@PersistenceContext
	private EntityManager em;

	/**
	 * DBのユーザーテーブルにユーザーを新規登録します。
	 * @param user
	 */
	public void create(Usertable user) {
		em.persist(user);
	}
        
         /**
         *  DBのユーザーテーブルのユーザー名を更新します。
         * @param ub
         */
        public void updateUserName(UserBean ub){
            em.createQuery("UPDATE Usertable u SET u.userName = :userName"
                    + " WHERE u.userId = :userId",Usertable.class)
                    .setParameter("userName", ub.getUserName())
                    .setParameter("userId", ub.getUserId())
                    .executeUpdate();
            
        }
        
        /**
         *  DBのユーザーテーブルのコメントを更新します。
         * @param ub
         */
        public void updateComments(UserBean ub){
            em.createQuery("UPDATE Usertable u SET u.comments = :comments"
                    + " WHERE u.userId = :userId",Usertable.class)
                    .setParameter("comments", ub.getComments())
                    .setParameter("userId", ub.getUserId())
                    .executeUpdate();
            
        }
        
	/**
	 * DBのユーザーテーブルからユーザーIDとパスワードで、登録済みユーザーを検索してユーザー名を取得します。
	 * @param ub
	 */
	public void loginSelect(UserBean ub) {

		Usertable user = em.createQuery("select u from Usertable u"
				+ " where u.userId = :userId"
				+ " AND u.password = :password", Usertable.class)
				.setParameter("userId", ub.getUserId())
				.setParameter("password", ub.getPassword())
				.getSingleResult();

		ub.setUserName(user.getUserName());

	}
        
        /**
         * DBのユーザーテーブルから全データのリストを取得します。
         * @return 
         */
        public List<Object[]> getUserIdAndUserNameAndComments(){
            return em.createQuery("SELECT u.userId,u.userName,u.comments FROM Usertable u").getResultList();
        }

}
