package tr.iyte.edu.tr.visacontrolapplication.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Documents;

public class Worker extends Applicant {

	String visaType = "Worker";
	String status = null;

	@Override
	public boolean checkFinancialStatus(Applicant applicant) {

		int savings = 0;

		try {
			savings = Integer.parseInt(applicant.getFinancialStatus().getSavings());
		} catch (NumberFormatException e) {
			System.out.println("Invalid value!");
			return false;
		}
		if (savings >= 2000)
			return true;

		System.out.println("Applicant does not have a stable financial status!");
		return false;
	}

	@Override
	public boolean checkDocuments(Applicant applicant) {

		String letterOfAcceptance = "LA";
		List<String> documentType = new ArrayList<String>();

		for (Documents document : applicant.getDocuments()) {
			documentType.add(document.getDocumentType());
		}
		for (String string : documentType) {
			if (string.equalsIgnoreCase(letterOfAcceptance)) {
				return true;
			}
		}
		System.out.println("Applicant does not have a letter of acceptance");
		return false;
	}

	@Override
	public String visaDuration(Applicant applicant) {

		int durationInMonths = 0;

		List<Documents> documents = applicant.getDocuments();

		String[] date = applicant.getPassport().getExpirationDate().split("-");
		Period period = null;
		int totalMonths = 0;

		try {

			LocalDate expDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
					Integer.parseInt(date[2]));
			LocalDate now = LocalDate.now();
			period = Period.between(now, expDate);
			totalMonths = period.getMonths() + period.getYears() * 12;

		} catch (NumberFormatException e) {
			System.out.println("Not valid number!");
		}

		for (Documents document : documents) {
			try {
				if (document.getDocumentType().equalsIgnoreCase("LA") && document.getDurationInMonths() != null) {
					durationInMonths = Integer.parseInt(document.getDurationInMonths());
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}

		if (durationInMonths <= totalMonths) {
			if (totalMonths < 24)
				return (" 1 Years");
			else if (totalMonths >= 24 && totalMonths < 60)
				return (" 2 Years");
			else if (totalMonths >= 60)
				return (" 5 Years");
		} else if (durationInMonths > totalMonths && totalMonths >= 12) {
			if (totalMonths < 24)
				return (" 1 Years");
			else if (totalMonths >= 24 && totalMonths < 60)
				return (" 2 Years");
			else if (totalMonths >= 60)
				return (" 5 Years");
		}

		return ("Invalid VisaStatus!");
	}

	@Override
	public boolean checkFinancialStatus(Applicant applicant, int i) {

		return false;
	}

	@Override
	public boolean isApplicationValid(Applicant applicant) {
		return (applicant.checkPassport(applicant) && applicant.checkPhoto(applicant)
				&& applicant.checkFinancialStatus(applicant) && applicant.checkDocuments(applicant));
	}

	public String checkStatus() {
		if (!(isApplicationValid(this))) {
			return ("Rejected" + ", Reason : ");
		} else {
			return ("Accepted" + ", Visa Duration : "  + visaDuration(this));

		}
	}

	public String toString() {
		String string = "Applicant ID: " + this.getApplicationInfo().getApplicantID() + ", Applicant Name : "
				+ this.getApplicationInfo().getApplicantName() + ", Visa Type : " + visaType + ", Status : "
				+ checkStatus();
		return string;
	}

}
