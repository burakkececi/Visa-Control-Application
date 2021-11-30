package tr.iyte.edu.tr.visacontrolapplication.business.abstracts;

import java.util.ArrayList;
import java.util.List;

import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.ApplicationInfo;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Documents;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.FinancialStatus;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Passport;
import tr.iyte.edu.tr.visacontrolapplication.entities.concretes.Photo;
import tr.iyte.edu.tr.visacontrolapplication.validations.PassportValidation;
import tr.iyte.edu.tr.visacontrolapplication.validations.PhotoValidation;

public abstract class Applicant {

	private ApplicationInfo applicationInfo;
	private Passport passport;
	private Photo photo;
	private FinancialStatus financialStatus;
	private List<Documents> documents;

	public Applicant() {
		this(null, null, null, null, new ArrayList<Documents>());
	}

	public Applicant(ApplicationInfo applicationInfo, Passport passport, Photo photo, FinancialStatus financialStatus,
			List<Documents> documents) {
		this.applicationInfo = applicationInfo;
		this.passport = passport;
		this.photo = photo;
		this.financialStatus = financialStatus;
		this.documents = documents;
	}

	public final boolean checkPassport(Applicant applicant) {
		PassportValidation passportValidation = new PassportValidation(applicant);
		return passportValidation.isPassportValid();
		
	}
	public final boolean checkPhoto(Applicant applicant) {
		PhotoValidation photoValidation = new PhotoValidation(applicant);
		return photoValidation.isPhotoValid();
	}
	public abstract boolean checkFinancialStatus(Applicant applicant);
	
	public abstract boolean checkFinancialStatus(Applicant applicant, int i);
	
	public abstract boolean checkDocuments(Applicant applicant);
	
	public abstract boolean isApplicationValid(Applicant applicant);
	
	public abstract String visaDuration(Applicant applicant);	
	
	//mutator and anchestor methods
	
	public ApplicationInfo getApplicationInfo() {
		return new ApplicationInfo(applicationInfo);
	}

	public Passport getPassport() {
		return new Passport(passport);
	}

	public Photo getPhoto() {
		return new Photo(photo);
	}

	public FinancialStatus getFinancialStatus() {
		return new FinancialStatus(financialStatus);
	}

	public List<Documents> getDocuments() {
		List<Documents> tempDocuments = new ArrayList<Documents>();
		for (Documents document : documents) {
			tempDocuments.add(new Documents(document));
		}
		return tempDocuments;
	}

	public void setApplicationInfo(String id, String name) {
		applicationInfo = new ApplicationInfo(id, name);

	}

	public void setPassport(String applicantID, String passportNumber, String expirationDate) {
		passport = new Passport(applicantID, passportNumber, expirationDate);
	}

	public void setPhoto(String applicantID, String resolution, String position) {
		photo = new Photo(applicantID, resolution, position);
	}

	public void setFinancialStatus(String applicantID, String income, String savings) {
		financialStatus = new FinancialStatus(applicantID, income, savings);
	}

	public void setDocuments(String applicantID, String documentType, String durationInMonths) {
		Documents document = new Documents(applicantID, documentType, durationInMonths);
		this.documents.add(document);
	}

	

}
