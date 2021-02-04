// 2021.02.03 // 2021.02.04
package step8_02.atm_v2.copy;

import java.util.Scanner;

public class TransManager {

	private TransManager() {}
	static private TransManager instance = new TransManager();
	static public TransManager getInstance() {
		return instance;
	}
	
	
	Scanner scan = new Scanner(System.in);
	UserManager um = UserManager.getInstance();
	
	// 입금
	// 내계좌 선택 > 입금
	void deposit(int identifier) {
		AccountManager.getInstance().printAcc(identifier);
		System.out.print("입금하실 계좌를 입력해주세요 : ");
		String depositAcc = scan.next();
		int depositAccIdx = -1;
		for (int i = 0; i < um.userList[identifier].accCnt; i++) {
			if(depositAcc.equals(um.userList[identifier].acc[i].accNumber)) {
				depositAccIdx = i;
			}
		}
		if(depositAccIdx == -1) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		System.out.print("입금하실 금액을 입력해주세요 : ");
		int depositMoney = scan.nextInt();
		
		um.userList[identifier].acc[depositAccIdx].money += depositMoney;
		System.out.println("입금이 완료되었습니다.");
	}

	
	// 출금
	// 내계좌 선택 > 출금액 입력받기 > 돈 확인 > 출금 완료
	void withdraw(int identifier) {
		AccountManager.getInstance().printAcc(identifier);
		System.out.print("출금하실 계좌를 입력해주세요 : ");
		String withdrawAcc = scan.next();
		int withdrawAccIdx = -1;
		for (int i = 0; i < um.userList[identifier].accCnt; i++) {
			if(withdrawAcc.equals(um.userList[identifier].acc[i].accNumber)) {
				withdrawAccIdx = i;
			}
		}if(withdrawAccIdx == -1) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		System.out.print("출금하실 금액을 입력해주세요 : ");
		int withdrawMoney = scan.nextInt();
		
		if(um.userList[identifier].acc[withdrawAccIdx].money >= withdrawMoney) {
			um.userList[identifier].acc[withdrawAccIdx].money -= withdrawMoney;
			System.out.println("출금이 완료되었습니다.");
		}
		else {
			System.out.println("잔액이 부족합니다.");
			return;
		}
	}
	
	// 송금
	// 내계좌 선택 > 송금계좌 입력받기/확인 >  송금액 입력받기/내계좌 돈 확인 > 송금완료
	void remittance (int identifier){
		AccountManager.getInstance().printAcc(identifier);
		System.out.print("계좌를 입력해주세요 : ");
		String remitMyAcc = scan.next();
		int remitMyAccIdx = -1;
		for (int i = 0; i < um.userList[identifier].accCnt; i++) {
			if(remitMyAcc.equals(um.userList[identifier].acc[i].accNumber)) {
				remitMyAccIdx = i;				
			}
		}
		if(remitMyAccIdx == -1) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
		System.out.print("송금하실 계좌를 입력해주세요 : ");
		String remitYourAcc = scan.next();
		int remitYourAccIdxI = -1;
		int remitYourAccIdxJ = -1;
		for (int i = 0; i < um.userCnt; i++) {
			for (int j = 0; j < um.userList[i].accCnt; j++) {
				if(remitYourAcc.equals(um.userList[i].acc[j].accNumber)) {
					remitYourAccIdxI = i;
					remitYourAccIdxJ = j;
				}	
			}
		}
		
		if(remitYourAccIdxI == -1 || remitYourAccIdxJ == -1) {
			System.out.println("해당하는 계좌가 없습니다.");
			return;
		}
	
		System.out.print("송금하실 금액을 입력해주세요 : ");
		int remitMoney = scan.nextInt();
		if(um.userList[identifier].acc[remitMyAccIdx].money >= remitMoney) {
			um.userList[identifier].acc[remitMyAccIdx].money -= remitMoney;
			um.userList[remitYourAccIdxI].acc[remitYourAccIdxJ].money += remitMoney;
			System.out.println("송금이 완료되었습니다.");
		}
		else {
			System.out.println("잔액이 부족합니다.");
			return;
		}

	}
}
