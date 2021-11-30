package tr.iyte.edu.tr.visacontrolapplication.entities.concretes;

public class Passport {

	private String applicantID;
	private String passportNumber;
	private String expirationDate;
	
	public Passport(String applicantID, String passportNumber, String expirationDate) {
		this.applicantID = applicantID;
		this.passportNumber = passportNumber;
		this.expirationDate = expirationDate;
	}
	public Passport(Passport passport) {
		if(passport == null) {
			String error = "Applicant Does Not Have A Passport!";
			passport = new Passport(error, error, error);
		}
		this.applicantID = passport.applicantID;
		this.passportNumber = passport.passportNumber;
		this.expirationDate = passport.expirationDate;
	}
	
	public String getApplicantID() {
		return applicantID;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
}
