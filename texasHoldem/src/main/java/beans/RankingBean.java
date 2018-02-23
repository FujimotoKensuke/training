package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RankingBean {
    
    private String searchUserId;
    
    private int totalChangePointRanking;
    
    private int NumberOfMatchRanking;
    
    private int winningRanking;

    public int getTotalChangePointRanking() {
        return totalChangePointRanking;
    }

    public void setTotalChangePointRanking(int totalChangePointRanking) {
        this.totalChangePointRanking = totalChangePointRanking;
    }

    public int getNumberOfMatchRanking() {
        return NumberOfMatchRanking;
    }

    public void setNumberOfMatchRanking(int NumberOfMatchRanking) {
        this.NumberOfMatchRanking = NumberOfMatchRanking;
    }

    public int getWinningRanking() {
        return winningRanking;
    }

    public void setWinningRanking(int winningRanking) {
        this.winningRanking = winningRanking;
    }

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }
    
    
}
