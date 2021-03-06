package util;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * メッセージ表示のクラスです。
 */
@RequestScoped
public final class MessageUtil implements Serializable{

	private MessageUtil() {}

	/**
	 * メッセージを作成します。
	 * @param s
	 */
	public static void facesMessage(String s) {

		FacesMessage msg = new FacesMessage(s);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
}
