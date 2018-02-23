package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.RankingService;

/**
 * ランキング画面のアクションビーンクラスです。
 */
@Named
@RequestScoped
public class RankingActionBean {
    
    @Inject
    private RankingService rankingService;
    
    /**
     * ランキング画面の初期表示処理です。
     * @throws Exception 
     */
    public void init() throws Exception{
        rankingService.execute();
    }
    
    /**
     * ランキングを総変動ポイント表示にします。
     * @throws Exception 
     * @return 
     */
    public void totalChangePointRankingShow() throws Exception{
        rankingService.totalChangePointRankingShow();
//        return "/ranking.xhtml?faces-redirect=true";
    }
    
    /**
     * ランキングを試合数表示にします。
     * @throws Exception 
     * @return 
     */
    public void numberOfMatchRankingShow() throws Exception{
        rankingService.numberOfMatchRankingShow();
//        return "/ranking.xhtml?faces-redirect=true";
    }
    
    /**
     * ランキングを勝率表示にします。
     * @throws Exception 
     * @return 
     */
    public void winningRankingShow() throws Exception{
        rankingService.winningRankingShow();
//        return "/ranking.xhtml?faces-redirect=true";
    }
    
    /**
     * ユーザーIDからランキング表示したいユーザーを検索します。
     * @throws Exception 
     */
    public void search() throws Exception{
        rankingService.search();
    }
    
    /**
     * 昇順に並べ替えます。
     * @throws Exception 
     */
    public void ascendingSort() throws Exception{
        rankingService.ascendingSort();
    }
    
    /**
     * 降順に並べ替えます。
     * @throws Exception 
     */
    public void descendingSort() throws Exception{
        rankingService.descendingSort();
    }
    
    /**
     * メニュー画面に遷移します。
     * @throws Exception
     * @return 
     */
    public String goBack() throws Exception{
        
        return "/menu.xhtml?faces-redirect=true";
    }
    
}
