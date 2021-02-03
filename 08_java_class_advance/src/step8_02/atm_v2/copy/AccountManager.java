// 2021.02.01 // 2021.02.03
package step8_02.atm_v2.copy;

import java.util.Random;
import java.util.Scanner;

public class AccountManager {
	
	private AccountManager() {}
	static private AccountManager instance = new AccountManager();
	static public AccountManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	UserManager um = UserManager.getInstance();
	
	void createAcc(int identifier) {
		
		int accCntByUser = um.userList[identifier].accCnt;
		
		if(accCntByUser == 3) {
			System.out.println("계좌개설은 3개까지만 가능합니다.");
			return;
		}
		
		um.userList[identifier].acc[accCntByUser] = new Account();
		
		String makeAccount = "";
		while(true) {
			makeAccount = ran.nextInt(9000000) + 1000001 +"";
			if(!um.getCheckAcc(makeAccount)) {
				break; 
			}
		}
		um.userList[identifier].acc[accCntByUser].accNumber = makeAccount;
		um.userList[identifier].accCnt++;
		System.out.println(makeAccount + "계좌가 생성되었습니다. \n");
	}

	void printAcc (int identifier) {
		
		User temp = um.userList[identifier];
		System.out.println("==================");
		System.out.println("ID : " + temp.id);
		System.out.println("==================");
		for (int i = 0; i < temp.accCnt; i++) {
			System.out.println("accNumber : " + temp.acc[i].accNumber + "/ money : " + temp.acc[i].money);
		}
		System.out.println("========================\n");
		
	}
	
	void deleteAcc (int identifier) {
		User temp = um.userList[identifier];
		int accCntByUser = um.userList[identifier].accCnt;
		
		if(temp.accCnt == 0) {
			System.out.println("계좌가 존재하지 않습니다.");
			return;
		}
		System.out.println("삭제할 계좌를 입력해주세요 : ");
		String delAcc = scan.next();
		int delIdx = -1;
		for (int i = 0; i < accCntByUser; i++) {
			if(delAcc.equals(temp.acc[i].accNumber)) {
				delIdx = i;
			}
		}	
		if(delIdx == -1) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		
		Account[] tmp = um.userList[identifier].acc;	
		um.userList[identifier].acc = new Account[3];
		
		
		for (int i = 0; i < delIdx; i++) {
			um.userList[identifier].acc[i] = tmp[i];
		}
		for (int i = delIdx; i < accCntByUser - 1; i++) {
			um.userList[identifier].acc[i] = tmp[i+1];
		}
		um.userList[identifier].accCnt--;
		System.out.println("계좌가 삭제되었습니다. \n");
		
	}
	

	
}
