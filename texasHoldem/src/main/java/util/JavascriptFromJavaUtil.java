package util;

import java.io.Serializable;
import org.primefaces.context.RequestContext;

public final class JavascriptFromJavaUtil implements Serializable {

    private JavascriptFromJavaUtil() {
    }

    /**
     * javaからjavascriptを呼び出します。
     *
     * @param javascript
     */
    public static void execute(String javascript) {

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(javascript);
    }
}
