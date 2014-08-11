package org.utilities.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.utilities.manager.UploadManager;
import org.utilities.manager.UploadManager.FieldGrp;
import org.utilities.view.util.FacesUtils;

@Named
@SessionScoped
public class UploadService implements Serializable {

	Logger logger = Logger.getLogger(UploadService.class.getName());

	@Inject
	private UploadManager mainManager;

	public void processUpload(FileUploadEvent event) {
		mainManager.setFile(event.getFile());
		String fileName = mainManager.getFile().getFileName();
	}

	public void processFile(UploadedFile file, List<FieldGrp> fieldGrps) {
		int errors = 0;
		String fileName = file.getFileName();
		try (InputStream is = file.getInputstream()) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			errors = processEvent(reader);

			System.out.println("errors = " + errors);

			FacesUtils.addInfoMessage(fileName + " has been processed.");

		} catch (FileNotFoundException ex) {
			System.err.println("Missing file " + fileName);
			FacesUtils.addErrorMessage("Processing Failed: Missing file "
					+ fileName);
		} catch (IOException e) {
			FacesUtils
					.addErrorMessage("Processing Failed: An error occurred processing the file");
			e.printStackTrace();
		} catch (RuntimeException e) {
			FacesUtils
					.addErrorMessage("Processing Failed: An error occurred processing the file: "
							+ fileName + ". " + e.getMessage());
			logger.warning(e.getMessage());
		}
		if (errors > 0) {
			logger.warning("There " + (errors == 1 ? "was " : "were ") + errors
					+ (errors == 1 ? " error " : " errors ")
					+ "found during processing " + fileName);
			FacesUtils.addErrorMessage("Processing Failed: There "
					+ (errors == 1 ? "was " : "were ") + errors
					+ (errors == 1 ? " error " : " errors ")
					+ "found during processing " + fileName
					+ ". Check logs for details.");
		}
	}

	private int processEvent(BufferedReader reader) throws IOException {
		int result = 0;
		String line = null;
		String[] parts;
		line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			try {
				parts = line.split(",");
			} catch (Exception e) {
				result++;
				logger.warning(e.getMessage() + ": " + line);
			}
		}
		return result;
	}

}
