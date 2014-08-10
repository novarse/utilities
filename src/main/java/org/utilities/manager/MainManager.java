package org.utilities.manager;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.utilities.service.UploadService;

@Named
@SessionScoped
public class MainManager implements Serializable {

	private UploadedFile file;

	@Inject
	private UploadService uploadService;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		System.out.println("in set file");
		this.file = file;
	}

	public String upload() {
		return "index";
	}

	public void handleUpload(FileUploadEvent event) {
		uploadService.processUpload(event);
	}

	public void clearUpload() {
		file = null;
	}

}
