package tr.iyte.edu.tr.visacontrolapplication.entities.concretes;

public class Photo {

	private String applicantID;
	private String resolution;
	private String position;
	
	public Photo(String applicantID, String resolution, String position) {
		this.applicantID = applicantID;
		this.resolution = resolution;
		this.position = position;
	}
	public Photo(Photo photo) {
		if(photo == null) {
			String error = "Applicant Does Not Have A Photo!";
			photo = new Photo(error, error, error);
		}
		this.applicantID = photo.applicantID;
		this.resolution = photo.resolution;
		this.position = photo.position;
	}
	
	public String getApplicantID() {
		return applicantID;
	}
	public String getResolution() {
		return resolution;
	}
	public String getPosition() {
		return position;
	}
}
