// 2021.02.03
package step8_02.atm_v2.copy;

import java.util.Scanner;

public class TransManager {

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
		
		
		
	}
	
	// 송금
	// 내계좌 선택 > 송금계좌 입력받기/확인 >  송금액 입력받기/내계좌 돈 확인 > 송금완료
	void remittance (int identifier){
		
	}
}
