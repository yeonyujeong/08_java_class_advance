package step8_02.atm_v2.만들어보기;

import java.util.Random;
import java.util.Scanner;

public class AccountManager {

	private AccountManager () {};
	static private AccountManager instance = new AccountManager();
	static public AccountManager getInstance() {
		return instance;
	}
	
	UserManager um = UserManager.getInstance();
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
		
	
	boolean checkAccCnt(int identifier) {
		boolean isCheckAccCnt = true;
		if(um.user.get(identifier).acc.size() == 5) {
			isCheckAccCnt = false;
		}
		return isCheckAccCnt;
	}	
	
	
	void createAcc(int identifier) {
		if(!checkAccCnt(identifier)) {
			System.out.println("더이상 계좌를 추가할 수 없습니다.");
			return;
		}
		while(true) {
			int makeAccount = ran.nextInt(99999)+10001;
			String newAccount = Integer.toString(makeAccount);
			
			boolean isAccDuple = true;
			for (int i = 0; i < um.user.size(); i++) {
				for (String acc : um.user.get(i).acc.keySet()) {
					if(acc.equals(newAccount)) {
						isAccDuple = false; 
					}
				}
			}
			if(isAccDuple) {
				um.user.get(identifier).acc.put(newAccount , 0);
				System.out.println(newAccount + "계좌가 생성되었습니다.");
				break;
			}
		}
	}
	
	
	void printAcc(int identifier) {
		System.out.println("=============================================");
		for (String acc : um.user.get(identifier).acc.keySet()) {
			System.out.println("계좌번호 : " + acc + "/ 현금 : " + um.user.get(identifier).acc.get(acc)+"원");
		}
		System.out.println("=============================================");
	}
	
	
	void deleteAcc(int identifier) {
		printAcc(identifier);
		System.out.print("삭제하실 계좌번호를 입력해주세요 : ");
		String deleteAcc = scan.next();
		String deleteKey = "";
		for (String acc : um.user.get(identifier).acc.keySet()) {
			if(deleteAcc.equals(acc)) {
				deleteKey = acc;
			}
		}
		
		if(deleteKey.equals("")) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		
		um.user.get(identifier).acc.remove(deleteKey);
		System.out.println(deleteAcc + "계좌가 삭제되었습니다.");
		
	}
	

	
	
	
	

	
}
