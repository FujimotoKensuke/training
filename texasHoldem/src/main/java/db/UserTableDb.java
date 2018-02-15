package db;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.UserBean;
import entity.Usertable;

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
         *  DBのユーザーテーブルにコメントを追加します。
         * @param userId
         * @param comments 
         */
        public void updateComeents(String userId, String comments){
            em.createQuery("UPDATE Usertable u SET u.comments = '" + comments + "' WHERE u.userId = '" + userId  + "'").executeUpdate();
            
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

}
