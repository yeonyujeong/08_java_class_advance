
package step8_02.atm_v2.만들어보기;

import java.util.Scanner;

public class TransManager {

	
	private TransManager() {}
	static private TransManager instance = new TransManager();
	static public TransManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	UserManager um = UserManager.getInstance();
	AccountManager am = AccountManager.getInstance();
	
	void deposit(int identifier) {
		am.printAcc(identifier);
		System.out.print("입금하실 계좌를 입력해주세요 : ");
		String depositAcc = scan.next();
		String checkAcc =  "";
		for (String acc : um.user.get(identifier).acc.keySet()) {
			if(depositAcc.equals(acc)) {
				checkAcc = acc;
			}
		}
		if(checkAcc.equals("")) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		System.out.print("입금하실 금액을 입력해주세요 : ");
		int depositMoney = scan.nextInt();
		
		um.user.get(identifier).acc.put(checkAcc, +depositMoney);
		System.out.println("입금이 완료되었습니다.");
	}
	
	void withdraw(int identifier) {
		am.printAcc(identifier);
		System.out.print("출금하실 계좌를 입력해주세요 : ");
		String withdrawAcc = scan.next();
		String checkAcc =  "";
		for (String acc : um.user.get(identifier).acc.keySet()) {
			if(withdrawAcc.equals(acc)) {
				checkAcc = acc;
			}
		}
		if(checkAcc.equals("")) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		System.out.print("출금하실 금액을 입력해주세요 : ");
		int withdrawMoney = scan.nextInt();
		
		if(withdrawMoney <= um.user.get(identifier).acc.get(withdrawAcc)) {
			um.user.get(identifier).acc.put(withdrawAcc, +withdrawMoney);
		}else {
			System.out.println("출금하실 금액이 부족합니다.");
		}
	}
	
	void remittance(int identifier) {
		am.printAcc(identifier);
		System.out.print("송금하실 내계좌를 입력해주세요 : ");
		String remitAcc = scan.next();
		String checkAcc =  "";
		for (String acc : um.user.get(identifier).acc.keySet()) {
			if(remitAcc.equals(acc)) {
				checkAcc = acc;
			}
		}
		if(checkAcc.equals("")) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		System.out.print("송금하실 금액을 입력해주세요 : ");
		int remitMoney = scan.nextInt();
		
		if(remitMoney < um.user.get(identifier).acc.get(remitAcc)) {
			System.out.println("송금하실 금액이 부족합니다.");
			return;
		}
		
		System.out.print("송금하실 계좌를 입력해주세요 : ");
		String remitYourAcc = scan.next();
		String checkYourAcc = "";
		int checkYourAccIdx = -1;
		for (int i = 0; i < um.user.size(); i++) {
			for (String acc : um.user.get(i).acc.keySet()) {
				if(remitYourAcc.equals(acc)) {
					checkYourAcc = acc;
					checkYourAccIdx = i;
				}
			}
		}
		if(checkYourAcc.equals("")) {
			System.out.println("해당하는 계좌가 없습니다.");  
			return;
		}
		
		um.user.get(identifier).acc.put(remitAcc, -remitMoney);
		um.user.get(checkYourAccIdx).acc.put(remitYourAcc, +remitMoney);
		System.out.println("송금이 완료되었습니다.");
		
	}
	
	
	
	
	

}
