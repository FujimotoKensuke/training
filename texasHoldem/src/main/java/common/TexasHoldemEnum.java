package common;

/**
 * テキサスホールデムに関連する列挙型クラスです。
 */
public class TexasHoldemEnum {

    /**
     * ベットラウンドの列挙型クラスです。
     */
    public enum betRound {

        PREFLOP("プリフロップ"),
        FLOP("フロップ"),
        TURN("ターン"),
        RIVER("リバー"),
        SHOWDOWN("ショーダウン");

        private final String betRound;

        /**
         * コンストラクタ
         *
         * @param String betRound
         */
        private betRound(final String betRound) {
            this.betRound = betRound;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "betRound"
         
         */
        @Override
        public String toString() {
            return this.betRound;
        }

    }

    /**
     * ブラインドの列挙型クラスです。
     */
    public enum blind {

        BB("BB"),
        SB("SB");

        private final String blind;

        /**
         * コンストラクタ
         *
         * @param String blind
         */
        private blind(final String blind) {
            this.blind = blind;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "blind"
         
         */
        @Override
        public String toString() {
            return this.blind;
        }

    }

    /**
     * 勝敗結果の列挙型クラスです。
     */
    public enum battleResult {

        WIN("勝ち"),
        LOSE("負け"),
        DRAW("引き分け");

        private final String battleResult;

        /**
         * コンストラクタ
         *
         * @param String battleResult
         */
        private battleResult(final String battleResult) {
            this.battleResult = battleResult;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "battleResult"
         
         */
        @Override
        public String toString() {
            return this.battleResult;
        }

    }

    /**
     * 成立役の列挙型クラスです。
     */
    public enum hand {

        HIGHCARD("ハイカード", 1),
        ONEPAIR("ワンペア", 2),
        TWOPAIR("ツーペア", 3),
        THREECARDS("スリーカード", 4),
        STRAIGHT("ストレート", 5),
        FLASH("フラッシュ", 6),
        HULLHOUSE("フルハウス", 7),
        FOURCARD("フォーカード", 8),
        STRAIGHTFLASH("ストレートフラッシュ", 9),
        ROYALSTRAIGHTFLASH("ロイヤルストレートフラッシュ", 10);

        private final String hand;
        private final int handRank;

        /**
         * コンストラクタ
         *
         * @param String hand
         */
        private hand(final String hand, final int handRank) {
            this.hand = hand;
            this.handRank = handRank;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "hand"
         
         */
        @Override
        public String toString() {
            return this.hand;
        }

        /**
         * 設定された数字を返します。
         *
         * @return int handRank
         
         */
        public int handRankReturn() {
            return this.handRank;
        }

    }

    /**
     * アクションの列挙型クラスです。
     */
    public enum action {

        BET("ベット"),
        RAISE("レイズ"),
        CALL("コール"),
        CHECK("チェック"),
        FOLD("フォールド");

        private final String action;

        /**
         * コンストラクタ
         *
         * @param String action
         */
        private action(final String action) {
            this.action = action;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "action"
         
         */
        @Override
        public String toString() {
            return this.action;
        }

    }

    /**
     * トランプのスートの列挙型クラスです。
     */
    public enum suit {

        HART("h", "♡"),
        SPADE("s", "♠"),
        CLUB("c", "♣"),
        DAIA("d", "♢");

        private final String suitName;
        private final String suitMark;

        /**
         * コンストラクタ
         *
         * @param String suitName
         * @param String suitMark
         */
        private suit(final String suitName, final String suitMark) {
            this.suitName = suitName;
            this.suitMark = suitMark;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "suitName"
         
         */
        @Override
        public String toString() {
            return this.suitName;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "suitMark"
         
         */
        public String suitMarkString() {
            return this.suitMark;
        }
    }

    /**
     * トランプの数字の列挙型クラスです。
     */
    public enum cardNumber {

        CARDRANK1("2", "2"),
        CARDRANK2("3", "3"),
        CARDRANK3("4", "4"),
        CARDRANK4("5", "5"),
        CARDRANK5("6", "6"),
        CARDRANK6("7", "7"),
        CARDRANK7("8", "8"),
        CARDRANK8("9", "9"),
        CARDRANK9("10", "10"),
        CARDRANK10("J", "11"),
        CARDRANK11("Q", "12"),
        CARDRANK12("K", "13"),
        CARDRANK13("A", "1");

        private final String cardNumber;
        private final String cardImgNumber;

        /**
         * コンストラクタ
         *
         * @param String cardNumber
         * @param String cardImgNumber
         */
        private cardNumber(final String cardNumber, final String cardImgNumber) {
            this.cardNumber = cardNumber;
            this.cardImgNumber = cardImgNumber;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "cardImgNumber"
         
         */
        @Override
        public String toString() {
            return this.cardImgNumber;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "cardNumber"
         
         */
        public String cardNumberString() {
            return this.cardNumber;
        }

    }
    
    /**
     * 削除フラグの列挙型クラスです。
     */
    public enum deleteFlg {
        
        OFF(0),
        ON(1);

        private final int number;
        
        /**
         * コンストラクタ
         * @param number 
         */
        private deleteFlg(final int number){
            
            this.number = number;
        }
        
        /**
         * 設定された整数を返します。
         * @return int number
         */
        public int getNumber() {
            return number;
        }
    }
    
    /**
     * 画面表示フラグの列挙型クラスです。
     */
    public enum displyFlg {
        
        OFF(0),
        ON(1);

        private final int number;
        
        /**
         * コンストラクタ
         * @param number 
         */
        private displyFlg(final int number){
            
            this.number = number;
        }
        
        /**
         * 設定された整数を返します。
         * @return int number
         */
        public int getNumber() {
            return number;
        }
    }

    /**
     * ランキング種別の列挙型クラスです。
     */
    public enum rankingType {

        TOTALCHANGEPOINT("総変動Pt"),
        NUMBEROFMATCH("試合数"),
        WINNING("勝率");

        private final String rankingType;

        /**
         * コンストラクタ
         *
         * @param String rankingType
         */
        private rankingType(final String rankingType) {
            this.rankingType = rankingType;
        }

        /**
         * 設定された文字列を返します。
         *
         * @return String "battleResult"
         
         */
        @Override
        public String toString() {
            return this.rankingType;
        }

    }
}
