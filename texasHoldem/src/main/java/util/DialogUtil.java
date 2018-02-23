package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

import org.primefaces.context.RequestContext;

/**
 * ダイアログ表示のクラスです。
 */
@RequestScoped
public final class DialogUtil implements Serializable{
    
    private DialogUtil() {}
    /**
     * ダイアログを表示します。
     * @param xhtml
     */
    public static void modalDialogShow(String xhtml, int width) {
        
        final Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("showHeader", false);
        options.put("width", width);
        options.put("contentWidth", "100%");
        options.put("draggable", false);
        options.put("resizable", true);
        options.put("position", "top");
        RequestContext.getCurrentInstance().openDialog(xhtml, options, null);
        
    }
    
    /**
     * ダイアログを閉じます。
     */
    public static void modalDialogClose(){
        
        RequestContext.getCurrentInstance().closeDialog(null);
        
    }
    
}
