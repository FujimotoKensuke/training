package service;

import beans.RankingBean;
import beans.UserBean;
import beans.UserRankBean;
import common.TexasHoldemEnum.battleResult;
import java.util.HashMap;
import javax.enterprise.context.RequestScoped;
import common.TexasHoldemEnum.hand;
import common.TexasHoldemEnum.rankingType;
import db.BattleRecordDb;
import db.DetailBattleRecordDb;
import db.UserTableDb;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * ランキングのサービスクラスです。
 */
@RequestScoped
public class RankingServiceImpl implements RankingService {

    @Inject
    private UserBean ub;

    @Inject
    private RankingBean rb;

    @EJB
    private UserTableDb userTableDb;

    @EJB
    private BattleRecordDb battleRecordDb;

    @EJB
    private DetailBattleRecordDb detailBattleRecordDb;

    @Override
    public void execute() {
        init();
    }

    @Override
    public void totalChangePointRankingShow() {
        totalChangePointRankingSort();
    }

    @Override
    public void numberOfMatchRankingShow() {
        numberOfMatchRankingSort();
    }

    @Override
    public void winningRankingShow() {
        winningRankingSort();
    }

    @Override
    public void search() {
        searchOnUserId();
    }

    @Override
    public void ascendingSort() {
        ascendingSortExecute();
    }

    @Override
    public void descendingSort() {
        descendingSortExecute();
    }

    /**
     * 初期表示処理です。
     */
    private void init() {

        rb.setShowUserName(ub.getUserName());
        listCreate();
        totalChangePointRankingSort();
        rb.setShowRanking(rankReturn());
        rb.setShowRankingType(rankingType.TOTALCHANGEPOINT.toString());
    }

    /**
     * 総変動ポイントでソートします。
     */
    private void totalChangePointRankingSort() {
        rb.setShowRankingType(rankingType.TOTALCHANGEPOINT.toString());
        rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getTotalChangePoint).reversed()).collect(Collectors.toList()));
        rb.setShowRanking(rankReturn());
    }

    /**
     * 試合数でソートします。
     */
    private void numberOfMatchRankingSort() {
        rb.setShowRankingType(rankingType.NUMBEROFMATCH.toString());
        rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getNumberOfMatch).reversed()).collect(Collectors.toList()));
        rb.setShowRanking(rankReturn());
    }

    /**
     * 勝率でソートします。
     */
    private void winningRankingSort() {
        rb.setShowRankingType(rankingType.WINNING.toString());
        rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getWinning).reversed()).collect(Collectors.toList()));
        rb.setShowRanking(rankReturn());
    }

    /**
     * ユーザーIdで検索します。
     */
    private void searchOnUserId() {
        rb.setShowUserName(rb.getRankingList().stream().filter(o -> o.getUserId().equals(rb.getSearchUserId())).findFirst().get().getUserName());
        rb.setShowRanking(rankReturn());
    }

    /**
     * 現在のランキングを昇順にソートします。
     */
    private void ascendingSortExecute() {

        switch (rankingType.toRankingType(rb.getShowRankingType())) {
            case TOTALCHANGEPOINT:
                rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getTotalChangePoint).reversed()).collect(Collectors.toList()));
                break;
            case NUMBEROFMATCH:
                rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getNumberOfMatch).reversed()).collect(Collectors.toList()));
                break;
            case WINNING:
                rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getWinning).reversed()).collect(Collectors.toList()));
                break;

        }
    }

    /**
     * 現在のランキングを降順にソートします。
     */
    private void descendingSortExecute() {

        switch (rankingType.toRankingType(rb.getShowRankingType())) {
            case TOTALCHANGEPOINT:
                rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getTotalChangePoint)).collect(Collectors.toList()));
                break;
            case NUMBEROFMATCH:
                rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getNumberOfMatch)).collect(Collectors.toList()));
                break;
            case WINNING:
                rb.setRankingList(rb.getRankingList().stream().sorted(comparing(UserRankBean::getWinning)).collect(Collectors.toList()));
                break;

        }
    }

    /**
     * ランキングの順位を返します。
     *
     * @return
     */
    private int rankReturn() {
        int rankNumber = 0;
        if (rb.getSearchUserId() == null) {
            for (int i = 0; i < rb.getRankingList().size(); i++) {
                if (rb.getRankingList().get(i).getUserId().equals(ub.getUserId())) {
                    rankNumber = i + 1;
                }
            }

        } else {
            for (int i = 0; i < rb.getRankingList().size(); i++) {
                if (rb.getRankingList().get(i).getUserId().equals((rb.getSearchUserId()))) {
                    rankNumber = i + 1;
                }
            }
        }
        return rankNumber;
    }

    /**
     * ランキングで表示するクラスのリストを作成します。
     */
    private void listCreate() {

        List<UserRankBean> userRankBeanList = new ArrayList<>();
        List<Object[]> userIdAndUserNameAndCommentsList = userTableDb.getUserIdAndUserNameAndComments();

        for (int i = 0; i < userIdAndUserNameAndCommentsList.size(); i++) {

            List<String> battleResultList = battleRecordDb.selectBattleResult(userIdAndUserNameAndCommentsList.get(i)[0].toString());
            List<Object[]> userHandAndChangePointList = detailBattleRecordDb.selectUserHandAndChangePoint(userIdAndUserNameAndCommentsList.get(i)[0].toString());
            ArrayList<String> userHandList = new ArrayList<>();
            ArrayList<String> changePointList = new ArrayList<>();

            for (Object[] o : userHandAndChangePointList) {
                userHandList.add(o[0].toString());
                changePointList.add(o[1].toString());
            }
            UserRankBean userRankBean = new UserRankBean(
                    userIdAndUserNameAndCommentsList.get(i)[0].toString(),
                    userIdAndUserNameAndCommentsList.get(i)[1].toString(),
                    changePointCalculate(changePointList),
                    battleResultList.size(),
                    winningCalculate(battleResultList),
                    handCount(userHandList),
                    null
            );

            //コメントがNULLではない場合
            if (userIdAndUserNameAndCommentsList.get(i)[2] != null) {
                userRankBean.setComments(userIdAndUserNameAndCommentsList.get(i)[2].toString());
            }

            userRankBeanList.add(userRankBean);
        }
        rb.setRankingList(userRankBeanList);
    }

    /**
     * 勝率を算出します。
     */
    private int winningCalculate(List<String> list) {

        double winCount = 0;

        // もしリストが空だった場合
        if (list.isEmpty()) {
            return 0;
        }

        for (String s : list) {
            if (battleResult.WIN.toString().equals(s)) {
                winCount++;
            }
        }

        if (winCount == 0) {
            return 0;
        } else {
            double d = (winCount / list.size()) * 100;
            return (int) (Math.round(d));
        }

    }

    /**
     * 成立役をカウントして1番多い役を返します。
     *
     * @param list
     * @return
     */
    private String handCount(List<String> list) {

        // 成立役を数える入れ物を用意
        HashMap<String, Integer> handCount = handMapCreate();

        //成立役を数える
        list.forEach((key) -> {
            handCount.put(key, handCount.get(key) + 1);
        });

        List<Entry<String, Integer>> list_entries = new ArrayList<>(handCount.entrySet());

        // 比較関数Comparatorを使用してMap.Entryの値を比較する（降順）
        Collections.sort(list_entries, (Entry<String, Integer> obj1, Entry<String, Integer> obj2) -> obj2.getValue().compareTo(obj1.getValue()));
        if (list_entries.get(0).getValue() == 0) {
            return "";
        } else {
            return list_entries.get(0).getKey();
        }

    }

    /**
     * 成立役をカウントするためのHashMapを作成します。
     *
     * @return
     */
    private HashMap<String, Integer> handMapCreate() {

        HashMap<String, Integer> handCount = new HashMap<>();
        handCount.put(hand.HIGHCARD.toString(), 0);
        handCount.put(hand.ONEPAIR.toString(), 0);
        handCount.put(hand.TWOPAIR.toString(), 0);
        handCount.put(hand.THREECARDS.toString(), 0);
        handCount.put(hand.STRAIGHT.toString(), 0);
        handCount.put(hand.FLASH.toString(), 0);
        handCount.put(hand.HULLHOUSE.toString(), 0);
        handCount.put(hand.FOURCARD.toString(), 0);
        handCount.put(hand.STRAIGHTFLASH.toString(), 0);
        handCount.put(hand.ROYALSTRAIGHTFLASH.toString(), 0);

        return handCount;
    }

    /**
     * 総変動ポイントを算出します。
     *
     * @param list
     * @return
     */
    private int changePointCalculate(List<String> list) {
        int total = 0;
        Pattern p = Pattern.compile("(^[-\\+]\\d+)(?=\\D+)");
        total = list.stream().map((s) -> {
            Matcher m = p.matcher(s);
            return m;
        }).filter((m) -> (m.find())).map((m) -> Integer.parseInt(m.group(0))).reduce(total, Integer::sum);

        return total;
    }
}
