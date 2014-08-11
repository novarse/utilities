package org.utilities.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.utilities.service.UploadService;
import org.utilities.view.util.FacesUtils;

@Named
@SessionScoped
public class UploadManager implements Serializable {

	/**
	 * Used to hold the field names and their expected position in the text
	 * file. The first position is 0.
	 */
	public class FieldGrp {
		private String fieldName;

		private Integer position;

		public Integer getPosition() {
			return position;
		}

		public void setPosition(Integer position) {
			this.position = position;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
	}

	private List<FieldGrp> fieldGrps;

	private UploadedFile file;

	@Inject
	private UploadService uploadService;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
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

	public List<FieldGrp> getFieldGrps() {
		if (fieldGrps == null) {
			fieldGrps = new ArrayList<FieldGrp>();
		}
		if (fieldGrps.isEmpty()) {
			fieldGrps.add(new FieldGrp());
		}
		return fieldGrps;
	}

	public void setFieldGrps(List<FieldGrp> fieldGrps) {
		this.fieldGrps = fieldGrps;
	}

	public void addSection() {
		fieldGrps.add(new FieldGrp());
	}

	public void removeSection(int idx) {
		fieldGrps.remove(idx);
	}

	/**
	 * Only show add button if the button is less than the maximum allowed
	 * buttons and is the last of the array
	 * 
	 * @param idx
	 * @param size
	 * @param max
	 * @return
	 */
	public boolean showAddBtn(int idx, int size, int max) {
		return idx < max - 1 && idx == size - 1;
	}

	public void processFile() {
		if (file == null) {
			System.out.println("file = null");
			FacesUtils.popupErrorMessage("A CSV file needs to be selected");
		} else {

			uploadService.processFile(file, fieldGrps);
		}
	}
}
