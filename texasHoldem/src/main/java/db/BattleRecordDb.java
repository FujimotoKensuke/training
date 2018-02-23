package db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.BattleRecord;

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
    @SuppressWarnings("unchecked")
    public List<BattleRecord> selectAll(String userId) {
        
        return em.createQuery("SELECT u FROM BattleRecord u WHERE u.userId = '" + userId + "'").getResultList();
    }
    
    /**
     * DBのバトルレコードテーブルからユーザーIDと対戦日時をキーにしてBattleRecordを取得します。
     * @param userId
     * @param battleDate
     * @return
     */
    public BattleRecord selectBattlrResult(String userId,String battleDate) {
        
        return (BattleRecord) em.createQuery("SELECT u FROM BattleRecord u "
                + "WHERE u.userId = '" + userId + "' AND u.battleDate ='" + battleDate + "'").getSingleResult();
    }
}
