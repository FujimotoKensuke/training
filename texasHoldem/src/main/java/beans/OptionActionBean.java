package beans;

import common.TexasHoldemConstants;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.OptionService;
import util.MessageUtil;

@Named
@RequestScoped
public class OptionActionBean {

    @Inject
    private OptionService optionService;

    public void init() throws Exception {

        optionService.init();
    }

    /**
     * ユーザーテーブルにコメントを追加します。
     */
    public void commentsUpdate() throws Exception {

        optionService.execute();
    }

    /**
     * メニュー画面に遷移します。
     *
     * @return
     */
    public String goBack() throws Exception {

        return "/menu.xhtml?faces-redirect=true";
    }
}
