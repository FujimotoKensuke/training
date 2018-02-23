package beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * ランキングのビーンクラスです。
 */
@Named
@ViewScoped
public class RankingBean implements Serializable {

    /**
     *ランキング画面のトップに表示するユーザーの名前です。
     */
    private String showUserName;
    
    /**
     * 検索するユーザーIDです。
     */
    private String searchUserId;
    
    /**
     * ランキング画面のトップに表示する順位です。
     */
    private Integer showRanking;
    
    /**
     * ランキング画面に表示するランキングの種類です。
     */
    private String showRankingType;
    
    /**
     * ランキングで表示するクラスのリストです。
     */
    private List<UserRankBean> rankingList;
    
    public String getShowUserName() {
        return showUserName;
    }

    public void setShowUserName(String showUserName) {
        this.showUserName = showUserName;
    }
    
    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }
    
    public Integer getShowRanking() {
        return showRanking;
    }

    public void setShowRanking(Integer showRanking) {
        this.showRanking = showRanking;
    }
    
    public List<UserRankBean> getRankingList() {
        return rankingList;
    }

    public void setRankingList(List<UserRankBean> rankingList) {
        this.rankingList = rankingList;
    }

    public String getShowRankingType() {
        return showRankingType;
    }

    public void setShowRankingType(String showRankingType) {
        this.showRankingType = showRankingType;
    }
}
