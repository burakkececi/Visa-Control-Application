package tr.iyte.edu.tr.visacontrolapplication.entities.concretes;

public class ApplicationInfo {

	private String applicantID;
	private String applicantName;

	public ApplicationInfo() {
		this(null, null);
	}

	public ApplicationInfo(String applicantID, String applicantName) {
		this.applicantID = applicantID;
		this.applicantName = applicantName;
	}

	public ApplicationInfo(ApplicationInfo applicationInfo) {
		if (applicationInfo == null) {
			String error = "Applicant Does Not Have A Application!";
			applicationInfo = new ApplicationInfo(error, error);
		}
		this.applicantID = applicationInfo.applicantID;
		this.applicantName = applicationInfo.applicantName;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;

	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
}
