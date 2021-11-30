package tr.iyte.edu.tr.visacontrolapplication;

import java.util.List;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;
import tr.iyte.edu.tr.visacontrolapplication.business.concretes.Immigrant;
import tr.iyte.edu.tr.visacontrolapplication.dataAccess.concretes.FileIO;

public class VisaControlApplication {

	public static void main(String []args) {
		
		FileIO fileIn = new FileIO();
		List<Applicant> applicants = fileIn.getApplicants();
		for (Applicant applicant : applicants) {
			System.out.println(applicant.toString());
		}
		
	}
}
