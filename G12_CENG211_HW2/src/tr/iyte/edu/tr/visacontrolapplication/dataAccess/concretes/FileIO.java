package tr.iyte.edu.tr.visacontrolapplication.dataAccess.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import tr.iyte.edu.tr.visacontrolapplication.business.abstracts.Applicant;
import tr.iyte.edu.tr.visacontrolapplication.business.concretes.*;

public class FileIO {

	private static List<Applicant> applicants = new ArrayList<Applicant>();
	private static String filePath = "src\\HW2_ApplicantsInfo.csv";

	public FileIO() {
		initialize();
	}

	private static void initialize() {
		setData(getData());
	}

	public List<Applicant> getApplicants() {

		return FileIO.applicants; // doesn't matter to return a deep copy one
	}

	private static List<String> getData() { // get data as a line

		Scanner keyboard = null;
		String line = null;
		List<String> data = new ArrayList<>();
		try {

			keyboard = new Scanner(new File(filePath));
			while (keyboard.hasNext()) {
				line = keyboard.nextLine();
				data.add(line);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fatal Error! " + e.getMessage());
			System.exit(0);
		}
		Collections.sort(data);

		return data;
	}

	private static void setData(List<String> list) {

		Applicant applicant = null;
		String[] values;
		String id = null;

		for (String string : list) {

			values = string.split(",");
			id = string.split(",")[1];

			switch (values[0]) {

			case "A":

				applicant = createApplicantByID(applicant, id); // sorted list-- @case "a" that only create new instance

				applicant.setApplicationInfo(values[1], values[2]);
				break;

			case "S":

				applicant = returnApplicant(id); // exact applicant that matches
				applicant.setPassport(values[1], values[2], values[3]);
				break;

			case "P":

				applicant = returnApplicant(id);
				applicant.setPhoto(values[1], values[2], values[3]);
				break;

			case "F":

				applicant = returnApplicant(id);
				applicant.setFinancialStatus(values[1], values[2], values[3]);
				break;

			case "D":

				applicant = returnApplicant(id);

				if (values.length <= 3) {

					applicant.setDocuments(values[1], values[2], null);
					break;

				} else {

					applicant.setDocuments(values[1], values[2], values[3]);
					break;
				}

			default:
				System.out.println("Not found!");
			}

			if (applicant != null) {
				if (hasApplicant(applicant, applicants)) {

					saveApplicant(applicant, applicants); // used to set method by arraylist

				} else {

					applicants.add(applicant);
				}
			}

		}

	}

	private static Applicant createApplicantByID(Applicant applicant, String id) {

		String prefixOfApplicantType = id.substring(0, 2);

		if (prefixOfApplicantType.equals("11"))
			applicant = new Tourist();
		else if (prefixOfApplicantType.equals("23"))
			applicant = new Worker();
		else if (prefixOfApplicantType.equals("25"))
			applicant = new Educational();
		else if (prefixOfApplicantType.equals("30"))
			applicant = new Immigrant();

		return applicant;
	}

	private static Applicant returnApplicant(String id) {

		for (Applicant a : applicants) {
			if (a.getApplicationInfo().getApplicantID().equals(id))
				return a;
		}

		return null;
	}

	private static void saveApplicant(Applicant applicant2, List<Applicant> applicants2) {

		int index = applicants2.indexOf(applicant2);
		applicants2.set(index, applicant2);

	}

	private static boolean hasApplicant(Applicant applicant2, List<Applicant> applicants2) {

		for (Applicant applicant : applicants2) {
			if (applicant == applicant2) // controlling memory adresses
				return true;
		}

		return false;
	}

}
