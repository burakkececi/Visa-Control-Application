package tr.iyte.edu.tr.visacontrolapplication.validations;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;

public class PhotoValidation {
	
	private Applicant applicant;
	private String[] resulations;
	
	public PhotoValidation(Applicant applicant) {
		if(applicant == null) {
			System.out.println("Error!");
			System.exit(0);
		}
		this.applicant = applicant;
	}

	public boolean isPhotoValid() {
		
		return (isResulationValid() && isPhotoSquare() && isPhotoPositionValid());
	}

	private boolean isPhotoPositionValid() {
		String photoPosition = applicant.getPhoto().getPosition();
		if(!(photoPosition.equalsIgnoreCase("neutral face") || photoPosition.equalsIgnoreCase("natural smile"))) {
			System.out.println("Photo Position Is Not Valid!");
			return false;
		}
		return true;
	}

	private boolean isResulationValid() {
		
		String photoResulation = applicant.getPhoto().getResolution();
		resulations = (photoResulation.split("x"));
		if(!(isLessThan(resulations) && isHigherThan(resulations))) {
			System.out.println("Photo Resulation Not Valid!");
			return false;
		}
		return true;
	}

	private boolean isHigherThan(String[] resulations) {
		int lowestResulation = 600;
		int [] resulationsInt = returnIntArray(resulations);
		return (resulationsInt[0] >= lowestResulation && resulationsInt[1] >= lowestResulation);
	}

	private boolean isLessThan(String[] resulations) {
		int highestResulation = 1200;
		int [] resulationsInt = returnIntArray(resulations);
		return (resulationsInt[0] <= highestResulation && resulationsInt[1] <= highestResulation);
	}
	
	private int[] returnIntArray(String[] resulations) {
		int [] resulationsInt = new int[2];
		for (int i = 0; i<resulations.length ; i++) {
			try {
				resulationsInt[i] = Integer.parseInt(resulations[i]);
			}catch (NumberFormatException e) {
				System.out.println("NO VALID DATA!");
			}
			
		}
		return resulationsInt;
	}
	
	private boolean isPhotoSquare() {
		int [] resulationsInt = returnIntArray(resulations);
		if(!(resulationsInt[0] == resulationsInt[1])) {
			System.out.println("Photo Size Not Valid!");
			return false;
		}
		return true;
	}

}
