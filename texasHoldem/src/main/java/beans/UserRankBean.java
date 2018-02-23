package beans;

/**
 * ユーザー個人のランキングに使用する情報です。
 */
public class UserRankBean {

    /**
     * ユーザーのIDです。
     */
    private String userId;
    
    /**
     * ユーザーの名前です。
     */
    private String userName;

    /**
     * 現在までの総変動ポイントです。
     */
    private int totalChangePoint;

    /**
     * 現在までの試合数です。
     */
    private int numberOfMatch;

    /**
     * 現在の勝率です。
     */
    private int winning;

    /**
     * 現在までの1番多い成立役です。
     */
    private String mostHand;

    /**
     * ユーザーの設定したコメントです。
     */
    private String comments;

    /**
     * コンストラクタ
     */
    public UserRankBean(){}
    
    /**
     * コンストラクタ
     * @param userId
     * @param userName
     * @param totalChangePoint
     * @param numberOfMatch
     * @param winning
     * @param mostHand
     * @param comments 
     */
    public UserRankBean(String userId,String userName, int totalChangePoint, int numberOfMatch,
             int winning, String mostHand, String comments) {

        this.userId = userId;
        this.userName = userName;
        this.totalChangePoint = totalChangePoint;
        this.numberOfMatch = numberOfMatch;
        this.winning = winning;
        this.mostHand = mostHand;
        this.comments = comments;

    }

    public String getUserId() {
        return userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public int getTotalChangePoint() {
        return totalChangePoint;
    }

    public int getNumberOfMatch() {
        return numberOfMatch;
    }

    public int getWinning() {
        return winning;
    }

    public String getMostHand() {
        return mostHand;
    }

    public String getComments() {
        return comments;
    }

}
