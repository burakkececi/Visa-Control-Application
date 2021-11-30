package tr.iyte.edu.tr.visacontrolapplication.entities.concretes;

public class FinancialStatus {

	private String applicantID;
	private String income;
	private String savings;

	public FinancialStatus(String applicantID, String income, String savings) {
		this.applicantID = applicantID;
		this.income = income;
		this.savings = savings;
	}

	public FinancialStatus(FinancialStatus financialStatus) {
		if (financialStatus == null) {
			String error = "Applicant Does Not Have A Financial Status!";
			financialStatus = new FinancialStatus(error, error, error);
		}
		this.applicantID = financialStatus.applicantID;
		this.income = financialStatus.income;
		this.savings = financialStatus.savings;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public String getIncome() {
		return income;
	}

	public String getSavings() {
		return savings;
	}

}
