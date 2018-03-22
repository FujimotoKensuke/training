package service;

import javax.enterprise.context.RequestScoped;

/**
 * ランキングのインターフェースです。
 */
@RequestScoped
public interface RankingService {
    
    /**
     * 初期表示処理です。
     */
    public void execute();
    
    /**
     * ランキングを総変動ポイント表示にします。
     */
    public void totalChangePointRankingShow();
    
    /**
     * ランキングを試合数表示にします。
     */
    public void numberOfMatchRankingShow();
    
    /**
     * ランキングを勝率表示にします。
     */
    public void winningRankingShow();
    
    /**
     * ユーザーIDからランキング表示したいユーザーを検索します。
     */
    public void search();
    
    /**
     * 昇順に並べ替えます。
     */
    public void ascendingSort();
    
    /**
     * 降順に並べ替えます。
     */
    public void descendingSort();
    
}
