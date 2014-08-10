package org.utilities.view.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class FacesUtils {

	public static Object getManagedBean(String beanName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elc = fc.getELContext();
		ExpressionFactory ef = fc.getApplication().getExpressionFactory();
		ValueExpression ve = ef.createValueExpression(elc, getJsfEl(beanName),
				Object.class);
		return ve.getValue(elc);

	}

	public static String getSessionId() {
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext()
				.getSession(false);

		String sessionId = (session == null ? "NULL" : session.getId());
		return sessionId;
	}

	private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}

	public static void addErrorMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		sendMessage(facesMessage);
	}

	public static void addWarningMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
		sendMessage(facesMessage);
	}

	private static void sendMessage(FacesMessage facesMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		String clientId = null;
		context.addMessage(clientId, facesMessage);
	}

	public static void addInfoMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		sendMessage(facesMessage);
	}

	public static void popupMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext context = FacesContext.getCurrentInstance();
		String clientId = "growl";
		context.addMessage(clientId, facesMessage);
	}

	public static void popupErrorMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext context = FacesContext.getCurrentInstance();
		String clientId = "growl";
		context.addMessage(clientId, facesMessage);
	}

}
