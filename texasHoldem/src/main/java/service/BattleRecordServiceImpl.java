package service;

import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.UserBean;
import common.TexasHoldemEnum.battleResult;
import db.BattleRecordDb;
import db.DetailBattleRecordDb;
import entity.BattleRecord;

@RequestScoped
public class BattleRecordServiceImpl implements BattleRecordService {

	@Inject
	private UserBean ub;

	@EJB
	private BattleRecordDb battleRecordDb;

	@EJB
	private DetailBattleRecordDb detailBattleRecordDb;

	@Override
	public void execute() {
		init();
	}

	@Override
	public void setDetail() {
		detailDialogSet();
	}

	/**
	 * 初期表示処理です。
	 */
	private void init() {

		ub.setBattleRecord(battleRecordDb.selectAll(ub.getUserId()));
		winningSet();

	}

	/**
	 * 詳細ダイアログ表示情報を設定します。
	 */
	private void detailDialogSet() {

		FacesContext fc=FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String unit=params.get("unit");

		ub.setDetailBattleRecord(detailBattleRecordDb.select(ub.getUserId(), unit));
		ub.setOpponentName(battleRecordDb.selectBattleResult(ub.getUserId(), unit).getOpponentName());
		ub.setBattleResult(battleRecordDb.selectBattleResult(ub.getUserId(), unit).getBattleResult());

	}

	/**
	 * 勝率を算出します。
	 */
	private void winningSet() {

		double winCount = 0;

		for(BattleRecord br:ub.getBattleRecord()) {


			if(battleResult.WIN.toString().equals(br.getBattleResult())) {

				winCount++;
			}
		}

		if(winCount == 0) {
			ub.setWinning(0);
		}else {
			double d = (winCount / ub.getBattleRecord().size()) * 100;
			ub.setWinning((int)(Math.round(d)));
		}

	}
}
