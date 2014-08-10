package org.utilities.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.utilities.manager.MainManager;
import org.utilities.view.util.FacesUtils;

@Named
@SessionScoped
public class UploadService implements Serializable {

	@Inject
	private MainManager mainManager;

	public void processUpload(FileUploadEvent event) {
		mainManager.setFile(event.getFile());
		String fileName = mainManager.getFile().getFileName();
		FacesUtils.popupMessage(fileName + " is uploaded.");
		// int errors = 0;
		// try (InputStream is = event.getFile().getInputstream()) {
		// BufferedReader reader = new BufferedReader(
		// new InputStreamReader(is));
		//
		// if (Constants.EVENT.equals(dataType)) {
		// errors = processEvent(reader);
		// } else if (Constants.EVENT_SEASON.equals(dataType)) {
		// errors = processEventSeason(reader);
		// } else if (Constants.EVENT_DESC.equals(dataType)) {
		// errors = processEventDesc(reader);
		// } else if (Constants.EVENT_LOCATION.equals(dataType)) {
		// errors = processEventLocation(reader);
		// } else if (Constants.EVENT_TYPE.equals(dataType)) {
		// errors = processEventType(reader);
		// } else if (Constants.MEMBER.equals(dataType)) {
		// errors = processMember(reader);
		// } else if (Constants.RACE_HISTORY.equals(dataType)) {
		// errors = processRaceHistory(reader, fileName);
		// }
		//
		// FacesUtils.displayMessage(Constants.UPLOAD_MESSAGE, "Successful",
		// fileName + " is processed.");
		//
		// } catch (FileNotFoundException ex) {
		// System.err.println("Missing file " + fileName);
		// FacesUtils.displayError(Constants.UPLOAD_MESSAGE,
		// "Processing Failed", "Missing file " + fileName);
		// } catch (IOException e) {
		// FacesUtils.displayError(Constants.UPLOAD_MESSAGE,
		// "Processing Failed",
		// "An error occurred processing the file");
		// e.printStackTrace();
		// } catch (RuntimeException e) {
		// FacesUtils.displayError(Constants.UPLOAD_MESSAGE,
		// "Processing Failed",
		// "An error occurred processing the file: " + fileName + ". "
		// + e.getMessage());
		// log.warning(e.getMessage());
		// }
		// if (errors > 0) {
		// log.warning("There " + (errors == 1 ? "was " : "were ") + errors
		// + (errors == 1 ? " error " : " errors ")
		// + "found during processing " + fileName);
		// FacesUtils.displayError(Constants.UPLOAD_MESSAGE,
		// "Processing Failed", "There "
		// + (errors == 1 ? "was " : "were ") + errors
		// + (errors == 1 ? " error " : " errors ")
		// + "found during processing " + fileName
		// + ". Check logs for details.");
		// }
	}

	// private int processEvent(BufferedReader reader) throws IOException {
	// int result = 0;
	// String line = null;
	// String[] parts;
	// line = reader.readLine();
	// if (line != null
	// && !Constants.EVENT.toLowerCase().equals(
	// line.trim().toLowerCase())) {
	// throw new RuntimeException(
	// "The first part of this file must begin with the word 'event'");
	// }
	// while ((line = reader.readLine()) != null) {
	// try {
	// parts = line.split("\t");
	// Event ent = new Event();
	// ent.setId(Long.parseLong(parts[0]));
	// ent.setDate(Utils.getDDMMYYYYHHMMDateFromStr(parts[1]));
	//
	// if (parts[2] != null && !parts[2].isEmpty()) {
	// Member director = memberDao.find(Utils
	// .getLongFromStr(parts[2]));
	// if (director == null) {
	// result++;
	// log.warning("Could not find director. Event added without director: "
	// + line);
	// } else {
	// ent.setDirector(director);
	// }
	// }
	//
	// if (parts[3] != null && !parts[3].isEmpty()) {
	// EventSeason season = seasonDao.find(Utils
	// .getLongFromStr(parts[3]));
	// if (season == null) {
	// result++;
	// log.warning("Could not find season: " + line);
	// continue;
	// }
	// ent.setEventSeason(season);
	// }
	//
	// EventLocation location = locationDao.find(Utils
	// .getLongFromStr(parts[4]));
	// if (location == null) {
	// result++;
	// log.warning("Could not find location: " + line);
	// continue;
	// }
	// ent.setEventLocation(location);
	//
	// EventDesc desc = descDao.find(Utils.getLongFromStr(parts[5]));
	// if (desc == null) {
	// result++;
	// log.warning("Could not find description: " + line);
	// continue;
	// }
	// ent.setEventDesc(desc);
	//
	// EventType type = typeDao.find(Utils.getLongFromStr(parts[6]));
	// if (type == null) {
	// result++;
	// log.warning("Could not find event type: " + line);
	// continue;
	// }
	// ent.setEventType(type);
	//
	// saveEvent(ent);
	//
	// } catch (Exception e) {
	// result++;
	// log.warning(e.getMessage() + ": " + line);
	// }
	// }
	// return result;
	// }

}
