package service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.enterprise.context.RequestScoped;

import common.TexasHoldemEnum.hand;

/**
 * ゲームメイン画面のポーカーの役判定を行うサービスクラスです。
 */
@RequestScoped
public class GameMainHandJudgeServiceImpl implements GameMainHandJudgeService {

	private Boolean royalFlag;

	@Override
	public String execute(String[] card) {

		return handJudge(card);
	}

	@Override
	public int handStrengthJudgeExecute(String myHand) {

		return handStrengthJudge(myHand);
	}

	/**
	 * 役を判定します。
	 * @param card
	 * @return
	 */
	private String handJudge(String[] card) {

		if(straightAndRoyalFlashJudge(card) && royalFlag) {
			return hand.ROYALSTRAIGHTFLASH.toString();
		}else if(straightAndRoyalFlashJudge(card)) {
			return hand.STRAIGHTFLASH.toString();
		}else if(fourCardJudge(card)) {
			return hand.FOURCARD.toString();
		}else if(hullHouseJudge(card)) {
			return hand.HULLHOUSE.toString();
		}else if(flashJudge(card)) {
			return hand.FLASH.toString();
		}else if(straightJudge(card)) {
			return hand.STRAIGHT.toString();
		}else if(threeCardJudge(card)) {
			return hand.THREECARDS.toString();
		}else if(twoPairJudge(card)) {
			return hand.TWOPAIR.toString();
		}else if(onePairJudge(card)) {
			return hand.ONEPAIR.toString();
		}else {
			return hand.HIGHCARD.toString();
		}

	}

	/**
	 * 役に応じた強さのランクを返します。
	 * @param myHand
	 * @return
	 */
	private int handStrengthJudge(String myHand) {

		if(hand.ROYALSTRAIGHTFLASH.toString().equals(myHand)) {
			return hand.ROYALSTRAIGHTFLASH.handRankReturn();
		}else if(hand.STRAIGHTFLASH.toString().equals(myHand)) {
			return hand.STRAIGHTFLASH.handRankReturn();
		}else if(hand.FOURCARD.toString().equals(myHand)) {
			return hand.FOURCARD.handRankReturn();
		}else if(hand.HULLHOUSE.toString().equals(myHand)) {
			return hand.HULLHOUSE.handRankReturn();
		}else if(hand.FLASH.toString().equals(myHand)) {
			return hand.FLASH.handRankReturn();
		}else if(hand.STRAIGHT.toString().equals(myHand)) {
			return hand.STRAIGHT.handRankReturn();
		}else if(hand.THREECARDS.toString().equals(myHand)) {
			return hand.THREECARDS.handRankReturn();
		}else if(hand.TWOPAIR.toString().equals(myHand)) {
			return hand.TWOPAIR.handRankReturn();
		}else if(hand.ONEPAIR.toString().equals(myHand)) {
			return hand.ONEPAIR.handRankReturn();
		}else {
			return hand.HIGHCARD.handRankReturn();
		}


	}

	/**
	 * フォーカードを判定します。
	 * @return
	 */
	private Boolean fourCardJudge(String[] card) {

		return pearJudgeSample(card,4);
	}

	/**
	 * スリーカードを判定します。
	 * @return
	 */
	private Boolean threeCardJudge(String[] card) {

		return pearJudgeSample(card,3);
	}

	/**
	 * ツーペアを判定します。
	 * @return
	 */
	private Boolean onePairJudge(String[] card) {

		return pearJudgeSample(card,2);
	}
	/**
	 * フォーカード、スリーカード、ワンペアを判定します。
	 * @param targetCards
	 * @param sameCount
	 * @return
	 */
	private Boolean pearJudgeSample(String[] targetCards,int sameCount) {

		String[] number = numberChange(targetCards);

		HashMap<String, Integer> numbers = numbersCreate();

		for( int i=0; i<number.length; i++ ){

			numbers.put(String.valueOf(number[i]).toString(), numbers.get(String.valueOf(number[i])) + 1);
		}

		//カウントし終わった数字を検証していって、sameCount枚以上同じ数字があったらtrueをリターン
		for( Integer i=1; i<=13; i++ ){

			if( numbers.get(i.toString()) >= sameCount ){
				return true;
			}
		}

		//ここまで到達で対象の役は成立していない
		return false;

	}

	/**
	 * フラッシュを判定します。
	 * @param targetCards
	 * @return
	 */
	private Boolean flashJudge(String[] targetCards) {

		String[] suit  = suitChange(targetCards);

		//マークを数える入れ物を用意
		HashMap<String, Integer> suits = suitsCreate();

		//引数として渡されたカードのマークをカウントしていく
		for( int i=0; i<suit.length; i++ ){

			suits.put(suit[i], suits.get(suit[i]) + 1);
		}

		//カウントし終わったマークを検証していって、5枚同じマークがあったらtrueをリターン
		for(Integer value : suits.values()){
			if( value >= 5 ){
				return true;
			}
		}

		//ここまで到達でフラッシュは成立していない
		return false;
	}

	/**
	 * ツーペアを判定します。
	 * @param targetCards
	 * @return
	 */
	private Boolean twoPairJudge(String[] targetCards) {

		String[] number = numberChange(targetCards);

		//数字を数える入れ物を用意
		HashMap<String, Integer> numbers = numbersCreate();

		//引数として渡されたカードの数字をカウントしていく
		for( int i=0; i<number.length; i++ ){

			numbers.put(number[i], numbers.get(number[i]) + 1);
		}

		//「同じ数字が○枚」がいくつあったのかを数える入れ物を用意
		HashMap<String, Integer> sameCount = new HashMap<String, Integer>();
		sameCount.put("0", 0);
		sameCount.put("1", 0);
		sameCount.put("2", 0);
		sameCount.put("3", 0);
		sameCount.put("4", 0);


		//カウントした数字を集計
		for(Integer k = 1; k <= 13; k++ ){

			sameCount.put(numbers.get(String.valueOf(k)).toString(),sameCount.get(numbers.get(k.toString()).toString()) + 1);
		}

		//「同じ数字が2枚」が2つあったらツーペア判定
		if( sameCount.get("2") >= 2 ){
			return true;
		}

		//ここまで到達でツーペアは成立していない
		return false;
	}

	/**
	 * フルハウスを判定します。
	 * @param targetCards
	 * @return
	 */
	private Boolean hullHouseJudge(String[] targetCards) {

		String[] number = numberChange(targetCards);

		//数字を数える入れ物を用意
		HashMap<String, Integer> numbers = numbersCreate();

		//引数として渡されたカードの数字をカウントしていく
		for( int i=0; i<number.length; i++ ){

			numbers.put(number[i], numbers.get(number[i]) + 1);
		}

		//「同じ数字が○枚」がいくつあったのかを数える入れ物を用意
		HashMap<String, Integer> sameCount = new HashMap<String, Integer>();
		sameCount.put("0", 0);
		sameCount.put("1", 0);
		sameCount.put("2", 0);
		sameCount.put("3", 0);
		sameCount.put("4", 0);

		//カウントした数字を集計
		for(Integer k = 1; k <= 13; k++ ){

			sameCount.put(numbers.get(String.valueOf(k)).toString(),sameCount.get(numbers.get(k.toString()).toString()) + 1);
		}

		//同じ数字が3枚が1組以上と、同じ数字が2枚が1組以上あったらフルハウス成立
		if( sameCount.get("3") >= 1 && sameCount.get("2") >= 1 ){
			return true;
		}

		//同じ数字が3枚が2組以上あったらフルハウス成立
		if( sameCount.get("3")>=2 ){
			return true;
		}

		//ここまで到達でツーペアは成立していない
		return false;
	}

	/**
	 * ストレートを判定します。
	 * @param targetCards
	 * @return
	 */
	private Boolean straightJudge(String[] targetCards) {

		String[] number = numberChange(targetCards);

		//数字を数える入れ物を用意
		HashMap<String, Integer> numbers = numbersCreate();

		//引数として渡されたカードの数字をカウント
		for( int i=0; i<number.length; i++ ){

			numbers.put(number[i], numbers.get(number[i]) + 1);
		}

		//1から始まるストレート～9から始まるストレートを判定する
		for(int i=1; i<=9; i++ ){

			if( numbers.get(String.valueOf(i).toString()) >= 1 && numbers.get(String.valueOf(i + 1).toString())>=1 && numbers.get(String.valueOf(i + 2).toString())>=1 && numbers.get(String.valueOf(i + 3).toString())>=1 && numbers.get(String.valueOf(i + 4).toString())>=1 ){
				return true;
			}
		}

		//10から始まるストレートのみ1（エース）が含まれるため別で判定
		if(numbers.get("10") >= 1 && numbers.get("11") >= 1 && numbers.get("12") >= 1 && numbers.get("13") >= 1 && numbers.get("1") >= 1 ){
			//ロイヤルストレートフラッシュ成立可能性フラグをオンにする。
			royalFlag = true;
			return true;
		}

		//ここまで到達でストレートは成立していない
		return false;
	}

	/**
	 * ロイヤルストレートフラッシュ及びストレートフラッシュを判定します。
	 * @param targetCards
	 * @return
	 */
	private Boolean straightAndRoyalFlashJudge(String[] targetCards) {

		//マークごとに仕分けする入れ物を準備
		HashMap<String, ArrayList<String>> suits = new HashMap<String, ArrayList<String>>();
		ArrayList<String> array = new ArrayList<String>();
		suits.put("s", array);
		suits.put("c", array);
		suits.put("d", array);
		suits.put("h", array);



		//引数として渡されたカードをマークごとに分ける
		for( int i=0; i<targetCards.length; i++ ){
			String suit  = (targetCards[i].substring(0,1));
			hashMapAddArrayList(suits,suit,targetCards[i]);
		}

		//仕分けしたカードのデータを順に検証
		for(ArrayList<String> value : suits.values()){

			//そのマークのカードが5枚以上あって、かつストレートができていたら
			if( value.size() >= 5 && straightJudge((String[])value.toArray(new String[0]))){
				if(royalFlag == true) {
					return true;
				}
				return true;
			}
		}

		//検証結果をリターン
		return false;

	}

	/**
	 * HashMapにArrayListを追加する処理です。
	 * @param suits
	 * @param key
	 * @param value
	 */
	private void hashMapAddArrayList(HashMap<String, ArrayList<String>> suits, String key, String value) {
		ArrayList<String> valueList = suits.get(key);
		if (valueList.isEmpty()) {
			valueList = new ArrayList<String>();
		}
		valueList.add(value);
		suits.put(key, valueList);
	}

	/**
	 * カードをスートのみに変換します。
	 * @param card
	 * @return
	 */
	private String[] suitChange(String[] card) {

		String[] suit  = new String[card.length];

		for(int i = 0; i < card.length ;i++) {

			suit[i] =  (card[i].substring(0,1));
		}
		return suit;
	}

	/**
	 * カードを数字のみに変換します。
	 * @param card
	 * @return
	 */
	private String[] numberChange(String[] card) {

		String[] number  = new String[card.length];

		for(int i = 0; i < card.length ;i++) {
			number[i]  = (card[i].replaceAll("[^0-9]",""));
		}
		return number;
	}

	/**
	 * 1～13までのHashMapを作成します。
	 * @return
	 */
	private HashMap<String, Integer> numbersCreate(){

		HashMap<String, Integer> numbers = new HashMap<String, Integer>();
		numbers.put("1", 0);
		numbers.put("2", 0);
		numbers.put("3", 0);
		numbers.put("4", 0);
		numbers.put("5", 0);
		numbers.put("6", 0);
		numbers.put("7", 0);
		numbers.put("8", 0);
		numbers.put("9", 0);
		numbers.put("10", 0);
		numbers.put("11", 0);
		numbers.put("12", 0);
		numbers.put("13", 0);

		return numbers;
	}

	/**
	 * スート(スペード、クラブ、ダイヤ、ハート)のHashMapを作成します。
	 * @return
	 */
	private HashMap<String, Integer> suitsCreate(){

		HashMap<String, Integer> suits = new HashMap<String, Integer>();
		suits.put("s", 0);
		suits.put("c", 0);
		suits.put("d", 0);
		suits.put("h", 0);

		return suits;
	}

}
