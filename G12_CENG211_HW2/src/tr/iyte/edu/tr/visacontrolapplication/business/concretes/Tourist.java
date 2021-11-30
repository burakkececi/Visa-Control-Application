package tr.iyte.edu.tr.visacontrolapplication.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.ApplicationInfo;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Documents;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.FinancialStatus;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Passport;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Photo;


public class Tourist extends Applicant{
	
	String visaType = "Tourist";
	String status = null;
	Boolean hasIL = false;
	
	public Tourist() {
		super();
	}
	public Tourist(ApplicationInfo applicantInfo,Passport passport,Photo photo
			,FinancialStatus financialStatus,ArrayList<Documents> document) {
		super(applicantInfo,passport,photo,financialStatus,document);
		
	}

	@Override
	public boolean checkFinancialStatus(Applicant applicant) {
		int income = 0;
		int savings = 0;
		
		try {
			income = Integer.parseInt(applicant.getFinancialStatus().getIncome());
			savings = Integer.parseInt(applicant.getFinancialStatus().getSavings());
		}catch (NumberFormatException e) {
			System.out.println("Invalid value!");
			return false;
		}
		if(income >= 2000 && income <=3000) 
			if(savings >= 12000) return true;
		if(income >= 3000 && income <=4000) 
			if(savings >= 6000) return true;
		if(income >= 4000) 
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
		}catch (NumberFormatException e) {
			System.out.println("Invalid value!");
			return false;
		}
		if(income >= (2000/division) && income <= (3000/division)) 
			if(savings >= (12000/division)) return true;
		if(income >= (3000/division) && income <= (4000/division)) 
			if(savings >= (6000/division)) return true;
		if(income >= (4000/division)) 
			return true;
		
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
			if(string.equalsIgnoreCase(invitationLetter)) {
				hasIL = true;
			}
		}
		return true;  // no document needed so always true
		
	}
	
	@Override
	public String visaDuration(Applicant applicant) {
		
		
		int income = 0, savings = 0;
		int DC = 0;
		int visaDurationYear = 0, visaDurationMonth = 0;
		
		String[] date = applicant.getPassport().getExpirationDate().split("-");
		Period period = null;
		try {
			
			LocalDate expDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
			LocalDate now = LocalDate.now();
			period = Period.between(now, expDate);
			
			income = Integer.parseInt(applicant.getFinancialStatus().getIncome());
			savings = Integer.parseInt(applicant.getFinancialStatus().getSavings());
			
		}catch (NumberFormatException e) {
			System.out.println("Not valid number!");
		}
		
		if(hasIL) DC = ((income - 2000) * 6 + savings)/12000;
		else 	  DC = ((income - 2000) * 6 + savings)/6000; 
		
		if(DC >=1 && DC < 2)	 visaDurationMonth = 6;
		else if(DC >=2 && DC <4) visaDurationYear = 1;
		else if(DC >=4)      	 visaDurationYear = 5;
		else                     return "Invalid VisaDuration";
		
		if(visaDurationYear <= period.getYears() && period.getYears() != 0) {
			return (visaDurationYear + " Years");
		}else if(visaDurationYear > period.getYears() && period.getYears() != 0) {
			return ("1 Years");
		}else if(visaDurationMonth < period.getMonths()) {
			return (visaDurationMonth + " Months");
		}else if(visaDurationMonth > period.getMonths()) {
			return("Error!");
		}else {
			return ("Error!");
		}
		
		
		
	}
	@Override
	public boolean isApplicationValid(Applicant applicant) {

		if(hasIL) {
			return (applicant.checkPassport(applicant) && applicant.checkPhoto(applicant) 
					&& applicant.checkFinancialStatus(applicant, 2) && applicant.checkDocuments(applicant));
		}else {
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
				+ ", Visa Type : " + visaType + ", Status : " + checkStatus();
		return string;
	}

}
