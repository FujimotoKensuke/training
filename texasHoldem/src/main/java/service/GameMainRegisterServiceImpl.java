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

		for(DetailBattleRecord dbr:gb.getDetailBattleRecordList()) {

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
					gb.getOpponentName(),gb.getRound(), gb.getUserCardInfo(), gb.getOpponentCardInfo(), gb.getBordCardInfo(),
					gb.getBetRound(), gb.getUserHand(), gb.getOpponentHandInfo(), gb.getChangePoint());
			System.out.println(null + ub.getUserId()+gb.getBattleDate()+
					gb.getOpponentName()+gb.getRound()+ gb.getUserCardInfo()+ gb.getOpponentCardInfo()+ gb.getBordCardInfo()+
					gb.getBetRound()+ gb.getUserHand()+ gb.getOpponentHandInfo()+ gb.getChangePoint());

			List<DetailBattleRecord> list = new ArrayList<DetailBattleRecord>(Arrays.asList(detailBattleRecord));

			System.out.println(list);

			if(gb.getDetailBattleRecordList() == null) {
				gb.setDetailBattleRecordList(list);
			}else {
				List<DetailBattleRecord> list2 = new ArrayList<DetailBattleRecord>(gb.getDetailBattleRecordList());

				list2.add(detailBattleRecord);
				gb.setDetailBattleRecordList(list2);
			}
			for(DetailBattleRecord dbr:gb.getDetailBattleRecordList()) {
				System.out.println(dbr.getId());
				System.out.println(dbr.getUserId());
				System.out.println(dbr.getBattleDate());
				System.out.println(dbr.getOpponentName());
				System.out.println(dbr.getRound());
				System.out.println(dbr.getUserCard());
				System.out.println(dbr.getOpponentCard());
				System.out.println(dbr.getBordInfo());
				System.out.println(dbr.getLastBetRound());
				System.out.println(dbr.getUserHand());
				System.out.println(dbr.getOpponentHand());
				System.out.println(dbr.getChangePoint());
			}



		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
}
