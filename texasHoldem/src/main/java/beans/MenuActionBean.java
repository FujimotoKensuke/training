package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.MenuService;

/**
 * メインメニュー画面のアクションビーンクラスです。
 */
@Named
@RequestScoped
public class MenuActionBean {

    @Inject
    private MenuService menuService;

    /**
     * ゲームメイン画面へ遷移します。
     *
     * @return 遷移先名称
     * @throws Exception
     */
    public String goGameMain() throws Exception {

        return "/gameMain.xhtml?faces-redirect=true";
    }

    /**
     * 戦績表示画面へ遷移します。
     *
     * @return 遷移先名称
     * @throws Exception
     */
    public String goGameRecord() throws Exception {

        return "/battleRecord.xhtml?faces-redirect=true";

    }

    /**
     * ログアウト後、ログイン画面へ遷移します。
     *
     * @return 遷移先名称
     * @throws Exception
     */
    public String logout() throws Exception {

        return menuService.execute();

    }

    /**
     * オプション画面へ遷移します。
     *
     * @return 遷移先名称
     * @throws Exception
     */
    public String goOption() throws Exception {

        return "/option.xhtml?faces-redirect=true";

    }

    /**
     * ランキング画面へ遷移します。
     *
     * @return
     * @throws Exception
     */
    public String goRanking() throws Exception {

        return "/ranking.xhtml?faces-redirect=true";

    }

}
