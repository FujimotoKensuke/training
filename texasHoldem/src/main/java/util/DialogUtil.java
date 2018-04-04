package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.context.RequestContext;

/**
 * ダイアログ表示のクラスです。
 */
public final class DialogUtil implements Serializable {

    private DialogUtil() {
    }

    /**
     * ダイアログを表示します。
     *
     * @param xhtml
     * @param width
     */
    public static void modalDialogShow(String xhtml, int width) {

        final Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("closable", false);
        options.put("width", width);
        options.put("contentWidth", "100%");
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("position", "top");
        RequestContext.getCurrentInstance().openDialog(xhtml, options, null);

    }

    /**
     * ダイアログを閉じます。
     */
    public static void modalDialogClose() {

        RequestContext.getCurrentInstance().closeDialog(null);

    }

}
