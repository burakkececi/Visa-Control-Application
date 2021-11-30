package tr.iyte.edu.tr.visacontrolapplication.business.concretes;

import java.util.ArrayList;
import java.util.List;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Documents;

public class Immigrant extends Applicant {

	private String visaType = "Immigrant";
	private Boolean hasIL = false;

	@Override
	public boolean checkFinancialStatus(Applicant applicant) {

		int savings = 0;
		String greenCard = "GC";
		List<String> documentType = new ArrayList<String>();
		try {

			savings = Integer.parseInt(applicant.getFinancialStatus().getSavings());

		} catch (NumberFormatException e) {
			System.out.println("Invalid Value!");
			return false;
		}

		for (Documents document : applicant.getDocuments()) {
			documentType.add(document.getDocumentType());
		}
		for (String string : documentType) {
			if (string.equalsIgnoreCase(greenCard)) {
				if (savings >= 4000)
					return true;
			}
		}
		if(savings >= 50000) return true;
		System.out.println("Applicant does not have a stable financial status");
		return false;
	}

	public boolean checkFinancialStatus(Applicant applicant, int division) {

		int savings = 0;
		String invitationLetter = "IL";
		List<String> documentType = new ArrayList<String>();
		try {

			savings = Integer.parseInt(applicant.getFinancialStatus().getSavings());

		} catch (NumberFormatException e) {
			System.out.println("Invalid Value!");
			return false;
		}

		for (Documents document : applicant.getDocuments()) {
			documentType.add(document.getDocumentType());
		}
		for (String string : documentType) {
			if (string.equalsIgnoreCase(invitationLetter)) {
				if (savings >= 4000 / division)
					return true;
			}
		}
		if(savings >= (50000 / division)) return true;
		System.out.println("Applicant does not have a stable financial status!");
		return false;
	}

	@Override
	public boolean checkDocuments(Applicant applicant) {

		String invitationLetter = "IL";
		List<String> documentType = new ArrayList<String>();

		for (Documents document : applicant.getDocuments()) {
			documentType.add(document.getDocumentType());
		}
		for (String string : documentType) {
			if (string.equalsIgnoreCase(invitationLetter)) {
				hasIL = true;
			}
		}
		return true; // no document needed so always true
	}

	@Override
	public String visaDuration(Applicant applicant) {

		return("Permanant");
		

	}

	@Override
	public boolean isApplicationValid(Applicant applicant) {
		if (hasIL) {

			return (applicant.checkPassport(applicant) && applicant.checkPhoto(applicant)
					&& applicant.checkFinancialStatus(applicant, 2) && applicant.checkDocuments(applicant));
		} else {

			return (applicant.checkPassport(applicant) && applicant.checkPhoto(applicant)
					&& applicant.checkFinancialStatus(applicant) && applicant.checkDocuments(applicant));
		}

	}

	public String checkStatus() {
		if (!(isApplicationValid(this))) {
			return ("Rejected" + ", Reason : ");
		} else {
			return ("Accepted" + ", Visa Duration : " + visaDuration(this));

		}
	}

	public String toString() {
		String string = "Applicant ID: " + this.getApplicationInfo().getApplicantID() + ", Applicant Name : "
				+ this.getApplicationInfo().getApplicantName() + ", Visa Type : " + visaType + ", Status : "
				+ checkStatus();

		return string;
		}
	}


