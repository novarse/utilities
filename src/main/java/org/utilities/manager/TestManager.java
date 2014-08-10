package org.utilities.manager;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.utilities.view.util.FacesUtils;

@Named
@SessionScoped
public class TestManager implements Serializable {

	private String val1;

	public void submit() {
		System.out.println("submitting");
		FacesUtils.addErrorMessage("Erorrrr");
		FacesUtils.popupErrorMessage("errooorr");
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

}
