package common;

/**
 * テキサスホールデムに関連する定数クラスです。
 */
public class TexasHoldemConstants {
    // privateコンストラクタでインスタンス生成を抑止

    private TexasHoldemConstants() {
    }

    /**
     * トランプの数字の数です。
     */
    public static final int CARD_TYPE_NUMBER = 13;

    /**
     * スートの種類数です。
     */
    public static final int SUIT_TYPE_NUMBER = 4;

    /**
     * トランプ全体の数です。
     */
    public static final int ALL_CARD_NUMBER = 52;

    /**
     * 所持ポイントの初期値です。
     */
    public static final int FIRST_POINT = 100;

    /**
     * ブラインドがBBの場合の初期ベットポイントです。
     */
    public static final int FIRST_BB_BETPOINT = 2;

    /**
     * ブラインドがSBの場合の初期ベットポイントです。
     */
    public static final int FIRST_SB_BETPOINT = 1;

    /**
     * CPUのベットポイントのデフォルト値です。
     */
    public static final int CPU_BET_POINT = 2;

    /**
     * CPUのベットポイントのデフォルト値です。
     */
    public static final int ROUND_MAX_NUMBER = 10;

    /**
     * CPUの名前です。
     */
    public static final String CPU_NAME = "CPU";

    /**
     * トランプの裏面の画像です。
     */
    public static final String REVERSE_CARD_IMG = "/resources/images/z02.png";

    /**
     * ユーザーの状況です。"あなたのターンです"
     */
    public static final String YOUR_TURN = "あなたのターンです";

    /**
     * オールインのメッセージです。"オールイン"
     */
    public static final String ALL_IN = "オールイン";

    /**
     * ログイン画面でログイン出来なかった場合のエラーメッセージです。"
     */
    public static final String LOGIN_ERROR_MSG = "ユーザー名、または、パスワードが間違っています。";

    /**
     * ユーザー登録画面で登録出来なかった場合のエラーメッセージです。"
     */
    public static final String CREATE_ERROR_MSG = "既に同じユーザーIDがあります。ユーザーIDを変更して下さい。";

    /**
     * ユーザー情報を更新した場合のメッセージです。
     */
    public static final String UPDATE_MSG = "ユーザー情報を更新しました。";

    /**
     * ユーザー情報を更新出来なかった場合のメッセージです。
     */
    public static final String UPDATE_MISS_MSG = "ユーザー情報を更新出来ませんでした。もう一度確認してください。";

}
