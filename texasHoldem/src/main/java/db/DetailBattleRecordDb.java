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
    private EntityManager em;

    /**
     * DBのバトルレコードテーブルに戦績データを新規登録します。
     *
     * @param detailBattleRecord
     */
    public void create(DetailBattleRecord detailBattleRecord) {
        em.persist(detailBattleRecord);
    }

    /**
     * DBの詳細バトルレコードテーブルからユーザーIdとバトル日時をキーにして全データを取得します。
     * @param userId
     * @param battleDate
     * @return
     */
    public List<DetailBattleRecord> select(String userId, String battleDate) {

        return em.createQuery("SELECT u FROM DetailBattleRecord u "
                + "WHERE u.userId = :userId"
                + " AND u.battleDate = :battleDate", DetailBattleRecord.class)
                .setParameter("userId", userId)
                .setParameter("battleDate", battleDate)
                .getResultList();
    }

    /**
     * DBの詳細バトルレコードテーブルからユーザーIdをキーにして全データを取得します。
     * @param userId
     * @return
     */
    public List<Object[]> selectUserHandAndChangePoint(String userId) {

        return em.createQuery("SELECT u.userHand,u.changePoint FROM DetailBattleRecord u WHERE u.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }
}
