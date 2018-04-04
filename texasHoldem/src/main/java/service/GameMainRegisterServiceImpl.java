package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import beans.GameBean;
import beans.UserBean;
import db.BattleRecordDb;
import db.DetailBattleRecordDb;
import entity.BattleRecord;
import entity.DetailBattleRecord;

@RequestScoped
public class GameMainRegisterServiceImpl implements GameMainRegisterService {

    @Inject
    private GameBean gb;

    @Inject
    private UserBean ub;

    @EJB
    private BattleRecordDb battleRecordDb;

    @EJB
    private DetailBattleRecordDb detailBattleRecordDb;

    private BattleRecord battleRecord;

    private DetailBattleRecord detailBattleRecord;

    @Override
    public void execute() {
        registarBattleRecord();
        registarDetailBattleRecord();
    }

    @Override
    public void createBattleRecordTableInfo() {

        createBattleRecordInfo();

    }

    @Override
    public void createDetailBattleRecordTableInfo() {

        createDetailBattleRecordInfo();

    }

    /**
     * DBのバトルレコードテーブルに戦績データを新規登録します。
     */
    private void registarBattleRecord() {

        battleRecordDb.create(gb.getBattleRecord());

    }

    /**
     * DBのバトルレコードテーブルに戦績詳細データを新規登録します。
     */
    private void registarDetailBattleRecord() {

        for (DetailBattleRecord dbr : gb.getDetailBattleRecordList()) {

            detailBattleRecordDb.create(dbr);
        }

    }

    /**
     * BattleRecordテーブルの設定
     */
    private void createBattleRecordInfo() {

        battleRecord = new BattleRecord(0, ub.getUserId(), gb.getBattleDate(), gb.getOpponentName(), gb.getBattleResult());
        gb.setBattleRecord(battleRecord);

    }

    /**
     * DetailBattleRecordテーブルの設定
     */
    private void createDetailBattleRecordInfo() {

        try {

            detailBattleRecord = new DetailBattleRecord(0, ub.getUserId(), gb.getBattleDate(),
                    gb.getOpponentName(), gb.getRound(), gb.getUserCardInfo(), gb.getOpponentCardInfo(), gb.getBordCardInfo(),
                    gb.getBetRound(), gb.getUserHand(), gb.getOpponentHandInfo(), gb.getChangePoint());

            List<DetailBattleRecord> list = new ArrayList<>(Arrays.asList(detailBattleRecord));

            if (gb.getDetailBattleRecordList() == null) {
                gb.setDetailBattleRecordList(list);
            } else {
                List<DetailBattleRecord> list2 = new ArrayList<>(gb.getDetailBattleRecordList());

                list2.add(detailBattleRecord);
                gb.setDetailBattleRecordList(list2);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
