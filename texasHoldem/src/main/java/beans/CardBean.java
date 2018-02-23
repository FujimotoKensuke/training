package beans;

import common.TexasHoldemEnum.cardNumber;
import common.TexasHoldemEnum.suit;

/**
 * トランプカードのクラスです。
 */
public class CardBean {

	private cardNumber cardNumber;
	private suit suit;

	/**
	 * コンストラクタ
	 * @param cardNumber
	 * @param suit
	 */
	public CardBean(cardNumber cardNumber, suit suit) {
		this.cardNumber = cardNumber;
		this.suit = suit;
	}

	/**
	 * トランプのスートと数字の文字列を返します。
	 * @return String
	 */
	@Override
	public String toString() {
		return suit.toString() + cardNumber.toString();
	}

	/**
	 * トランプの画像イメージの文字列を返します。
	 * @return String
	 */
	public String imgString() {
		return suit.suitMarkString() + cardNumber.cardNumberString();
	}
}
