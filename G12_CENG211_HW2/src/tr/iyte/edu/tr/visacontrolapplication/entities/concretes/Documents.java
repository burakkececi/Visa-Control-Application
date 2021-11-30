package tr.iyte.edu.tr.visacontrolapplication.entities.concretes;

public class Documents {

	private String applicantID;
	private String documentType;
	private String durationInMonths;

	public Documents(String applicantID, String documentType, String durationInMonths) {
		this.applicantID = applicantID;
		this.documentType = documentType;
		this.durationInMonths = durationInMonths;
	}

	public Documents(Documents documents) {
		if (documents == null) {
			String error = "Applicant Does Not Have A Document!";
			documents = new Documents(error, error, error);
		}
		this.applicantID = documents.applicantID;
		this.documentType = documents.documentType;
		this.durationInMonths = documents.durationInMonths;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public String getDocumentType() {
		return documentType;
	}

	public String getDurationInMonths() {
		return durationInMonths;
	}
}
