package tr.iyte.edu.tr.visacontrolapplication.validations;

import java.time.LocalDate;
import java.time.Period;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;

public class PassportValidation {

	private Applicant applicant;
	private String passportNumber;

	public PassportValidation(Applicant applicant) {
		if (applicant == null) {
			System.out.println("Error!");
			System.exit(0);
		}
		this.applicant = applicant;
		this.passportNumber = applicant.getPassport().getPassportNumber();
	}

	public boolean isPassportValid() {
		return (isExpirationDateValid() && isPassportHeadValid() && isPassportLenghtValid() && isPassportEndValid());
	}

	private boolean isPassportEndValid() {
		char[] lastIndex = null;
		lastIndex = passportNumber.substring(passportNumber.length() - 3, passportNumber.length()).toCharArray();
		for (int i = 0; i < lastIndex.length; i++) {
			if (!(Character.isDigit(lastIndex[i]))) {
				System.out.println("Passport Number Is Not Valid!");
				return false;
			}
		}

		return true;

	}

	private boolean isPassportLenghtValid() {

		if (!(passportNumber.length() == 10)) {
			System.out.println("Passport Number Is Not Valid!");
			return false;
		}
		return true;
	}

	private boolean isPassportHeadValid() {
		String head = passportNumber.substring(0, 1);
		if (!(head.equals("P"))) {
			System.out.println("Passport Number Is Not Valid!");
			return false;
		}
		return true;

	}

	public boolean isExpirationDateValid() {

		String[] date = applicant.getPassport().getExpirationDate().split("-");
		Period period = null;
		try {
			LocalDate expDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
					Integer.parseInt(date[2]));
			LocalDate now = LocalDate.now();
			period = Period.between(now, expDate);

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		if (period != null) {

			if (period.getYears() >= 1)
				return true;
			else if (period.getMonths() >= 6)
				return true;
			else {
				System.out.println("Expiration Date Is Not Valid!");
				return false;
			}
		}
		return false;

	}

}
