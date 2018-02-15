package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RankingActionBean {
    
    /**
     * メニュー画面に遷移します。
     * @return 
     */
    public String goBack() throws Exception{
        
        return "/menu.xhtml?faces-redirect=true";
    }
    
}
