package tr.iyte.edu.tr.visacontrolapplication.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Documents;

public class Educational extends Applicant {

	String visaType = "Educational";
	String status = null;
	Boolean hasIL = false;

	@Override
	public boolean checkFinancialStatus(Applicant applicant) {

		int income = 0;
		int savings = 0;

		try {
			income = Integer.parseInt(applicant.getFinancialStatus().getIncome());
			savings = Integer.parseInt(applicant.getFinancialStatus().getSavings());
		} catch (NumberFormatException e) {
			System.out.println("Invalid value!");
			return false;
		}
		if (income >= 1000 && income <= 2000)
			if (savings >= 6000)
				return true;
		if (income >= 2000 && income <= 3000)
			if (savings >= 3000)
				return true;
		if (income >= 3000)
			return true;

		System.out.println("Applicant does not have a stable financial status!");
		return false;

	}

	public boolean checkFinancialStatus(Applicant applicant, int division) {

		int income = 0;
		int savings = 0;

		try {
			income = Integer.parseInt(applicant.getFinancialStatus().getIncome());
			savings = Integer.parseInt(applicant.getFinancialStatus().getSavings());
		} catch (NumberFormatException e) {
			System.out.println("Invalid value!");
			return false;
		}
		if (income >= (1000 / division) && income <= (2000 / division))
			if (savings >= (6000 / division))
				return true;
		if (income >= (2000 / division) && income <= (3000 / division))
			if (savings >= (3000 / division))
				return true;
		if (income >= (3000 / division))
			return true;
		
		System.out.println("Applicant does not have a stable financial status!");
		return false;

	}

	@Override
	public boolean checkDocuments(Applicant applicant) {

		String letterOfAcceptance = "LA";
		String invitationLetter = "IL";
		boolean isValid = false;
		List<String> documentType = new ArrayList<String>();

		for (Documents document : applicant.getDocuments()) {
			documentType.add(document.getDocumentType());
		}
		for (String string : documentType) {
			if (string.equalsIgnoreCase(letterOfAcceptance)) {
				isValid = true;
			}
			if (string.equalsIgnoreCase(invitationLetter)) {
				hasIL = true;
			}
		}
		if(!isValid) {
			System.out.println("Applicant does not have a letter of acceptance");
			return false;
		}
		
		return isValid;

	}

	@Override
	public String visaDuration(Applicant applicant) {
		
		int durationInMonths = 0;
		
		List<Documents> documents = applicant.getDocuments();
		
		String[] date = applicant.getPassport().getExpirationDate().split("-");
		Period period = null;
		int totalMonths = 0;
		
		try {
			
			LocalDate expDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
			LocalDate now = LocalDate.now();
			period = Period.between(now, expDate);
			totalMonths = period.getMonths() + period.getYears()*12;
			
		}catch (NumberFormatException e) {
			System.out.println("Not valid number!");
		}
		
		for (Documents document : documents) {
			try {
				if(document.getDocumentType().equalsIgnoreCase("LA") && document.getDurationInMonths() != null) {
					durationInMonths = Integer.parseInt(document.getDurationInMonths());
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		} 
		
		if(durationInMonths <= totalMonths) {
			if(totalMonths < 24) return (" 1 Years");
			else if(totalMonths >=24 && totalMonths <48) return (" 2 Years");
			else if(totalMonths >= 48) return (" 4 Years");
		}else if(durationInMonths > totalMonths && totalMonths >= 12) {
			if(totalMonths < 24) return (" 1 Years");
			else if(totalMonths >=24 && totalMonths <48) return (" 2 Years");
			else if(totalMonths >= 48) return (" 4 Years");
		}
		
		return 	("Invalid VisaStatus!");
			
		
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
			return ("Accepted"+ ", Visa Duration : " + visaDuration(this));
			
		}
	}
	
	public String toString() {
		String string = "Applicant ID: " + this.getApplicationInfo().getApplicantID() + ", Applicant Name : " + this.getApplicationInfo().getApplicantName() 
				+ ", Visa Type : " + visaType + ", Status : " +  checkStatus();
		return string;
	}

}
