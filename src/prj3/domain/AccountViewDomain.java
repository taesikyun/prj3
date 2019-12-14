package prj3.domain;

public class AccountViewDomain {
	
	private String account_number, bank;

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "AccountViewDomain [account_number=" + account_number + ", bank=" + bank + "]";
	}
	
}
