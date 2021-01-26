package step8_01.atm_v1.연습;

public class User {

	String id;
	Account []  acc;
	int accCount;
	
	void printAccount() {
		for (int i = 0; i < accCount; i++) {
			acc[i].printOwnAccount();
		}
	}
	
}
