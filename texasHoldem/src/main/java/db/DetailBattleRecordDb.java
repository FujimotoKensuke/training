package db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.DetailBattleRecord;

/**
 * 詳細バトルレコードテーブルのDB接続クラスです。
 *
 */
@Stateless
public class DetailBattleRecordDb {

	@PersistenceContext
	private EntityManager em2;

	/**
	 * DBのバトルレコードテーブルに戦績データを新規登録します。
	 * @param detailBattleRecord
	 */
	public void create(DetailBattleRecord detailBattleRecord) {
		em2.persist(detailBattleRecord);
	}

	/**
	 * DBの詳細バトルレコードテーブルからユーザーとバトル日時をキーにして全データを取得します。
	 * @param userId,battleDate
	 */
	@SuppressWarnings("unchecked")
	public List<DetailBattleRecord> select(String userId,String battleDate) {

		return em2.createQuery("SELECT u FROM DetailBattleRecord u "
				+ "WHERE u.userId = '" + userId + "' AND u.battleDate ='" + battleDate + "'").getResultList();
	}
}
