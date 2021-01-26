// 2021.1.25
package step8_01.atm_v1.연습;

import java.util.Random;
import java.util.Scanner;

public class ATM {

	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	UserManager userManager = new UserManager();
	int identifier = -1;
	
	void printMainMenu() {
		
		while(true) {
			
			System.out.println("\n[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입] [4.회원탈퇴] [0.종료]");
			int sel = scan.nextInt();
			
			if(sel == 1) 		login();
			else if(sel == 2)	logout();
			else if(sel == 3)	join();
			else if(sel == 4)	leave();
			else if(sel == 0)	break;

		}
		System.out.println("[메세지] 프로그램을 종료합니다.");
	
	}
	
	void login() {
		
		identifier = userManager.logUser();
		
		if(identifier != -1) {
			printAccountMenu();
		}
		else {
			System.out.println("[메세지] 로그인 실패.");
		}
	}
	
	void logout() {
		
		if(identifier == -1) {
			System.out.println("[메세지] 로그인 후 이용해주세요.\n");
		}
		else {
			identifier = -1;
			System.out.println("[메세지] 로그아웃되었습니다.\n");
		}
	}
	
	void leave() {
		userManager.leave();
	}
	
	
	void printAccountMenu() { //로그인시 계좌생성

		while(true) {
			System.out.print("[1.계좌생성] [2. 계좌삭제] [3.조회] [0.로그아웃] : ");
			int sel = scan.nextInt();
			
			String makeAccount = Integer.toString(ran.nextInt(90001) + 10000);
			
			if(sel == 1) {
				if(userManager.user[identifier].accCount == 0) {
					userManager.user[identifier].acc = new Account[1];
					
					userManager.user[identifier].acc[0] = new Account();
					userManager.user[identifier].acc[0].number = makeAccount;
				}
				else {
					Account[] temp = userManager.user[identifier].acc;
					int tempAccCount = userManager.user[identifier].accCount;
					userManager.user[identifier].acc = new Account[tempAccCount+1];
					for (int i = 0; i < tempAccCount; i++) {
						userManager.user[identifier].acc[i] = temp[i];
					}
					userManager.user[identifier].acc[tempAccCount] = new Account();
					userManager.user[identifier].acc[tempAccCount].number = makeAccount;
				}
				userManager.user[identifier].accCount++;
				System.out.println("[메세지]'" + makeAccount +"'계좌가 생성되었습니다.");
			}
			
			else if(sel == 2) {
				if(userManager.user[identifier].accCount == 0) {
					System.out.println("[메세지] 삭제할 계좌가 없습니다.\n");
					continue;
				}
				else if(userManager.user[identifier].accCount == 1) {
					System.out.println("계좌번호 '" + userManager.user[identifier].acc[0].number +"'가 삭제되었습니다.\n");
					userManager.user[identifier].acc = null;
				}
				else {
					Account[] temp = userManager.user[identifier].acc;
					int tempAccCount = userManager.user[identifier].accCount;
					
					System.out.print("삭제할 계좌를 입력해주세요 : ");
					String delAcc = scan.next();
					int delIdx = -1;
					
					for (int i = 0; i < tempAccCount; i++) {
						if(delAcc.equals(temp[i].number)) {
							delIdx = i;
						}
					}
					if(delIdx == -1) {
						System.out.println("[메세지] 해당하는 계좌가 없습니다.");
						continue;
					}
					else {
						userManager.user[identifier].acc = new Account[tempAccCount - 1];
						for (int i = 0; i < delIdx; i++) {
							userManager.user[identifier].acc[i] = temp[i];
						}
						for (int i = delIdx; i < tempAccCount-1; i++) {
							userManager.user[identifier].acc[i] = temp[i+1];
						}
						userManager.user[identifier].accCount--;
						System.out.println("계좌 '" + delAcc +"'가 삭제되었습니다.");
					}
				
				}
			}
			else if(sel == 3) {
				if(userManager.user[identifier].accCount == 0) {
					System.out.println("[메세지] 생성된 계좌가 없습니다.");
				}
				else {
					userManager.user[identifier].printAccount();
				}
			}
			else if(sel == 0) {
				logout();
				break;
			}

		}
	
	}
	
	void join() {
		userManager.addUser();
	}
	
	
}
