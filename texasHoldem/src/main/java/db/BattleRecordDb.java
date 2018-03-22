package db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.BattleRecord;
import java.util.ArrayList;

/**
 * バトルレコードテーブルのDB接続クラスです。
 *
 */
@Stateless
public class BattleRecordDb {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * DBのバトルレコードテーブルに戦績データを新規登録します。
     * @param battleRecord
     */
    public void create(BattleRecord battleRecord) {
        em.persist(battleRecord);
    }
    
    /**
     * DBのバトルレコードテーブルからユーザーIDをキーにして全データを取得します。
     * @param userId
     * @return
     */
    public List<BattleRecord> selectAll(String userId) {
        
        return em.createQuery("SELECT u FROM BattleRecord u WHERE u.userId = :userId",BattleRecord.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    
    /**
     * DBのバトルレコードテーブルからユーザーIDをキーにして勝敗結果データのリストを取得します。
     * @param userId
     * @return
     */
    public List<String> selectBattleResult(String userId) {
        
        return em.createQuery("SELECT u.battleResult FROM BattleRecord u WHERE u.userId = :userId",String.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    
    /**
     * DBのバトルレコードテーブルからユーザーIDと対戦日時をキーにしてBattleRecordを取得します。
     * @param userId
     * @param battleDate
     * @return
     */
    public BattleRecord selectBattleResult(String userId,String battleDate) {
        
        return (BattleRecord) em.createQuery("SELECT u FROM BattleRecord u "
                + "WHERE u.userId = :userId"
                + " AND u.battleDate = :battleDate",BattleRecord.class)
                .setParameter("userId", userId)
                .setParameter("battleDate", battleDate)
                .getSingleResult();
    }
}
