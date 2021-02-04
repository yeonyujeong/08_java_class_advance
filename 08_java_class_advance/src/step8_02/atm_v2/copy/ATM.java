// 2021.02.01 // 2021.02.03
package step8_02.atm_v2.copy;

import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	int identifier = -1;
	UserManager um = UserManager.getInstance();
	
	void play() {
		
		FileManager.getInstance().load();
		UserManager.getInstance().printAllUser();
		
		while(true) {
			
			System.out.println("[ ATM ]");
			System.out.println("[1.회원가입]\n[2.로그인]\n[0.종료]");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) join();
			else if(sel == 2) login();
			else if(sel == 0) break;
			
		}
	
	}
	
	void login() {
		
		identifier = um.logUser();
		if(identifier != -1) loginMenu();
		else				System.out.println("아이디와 패스워드를 확인해주세요.");
	}
	
	void loginMenu() {
		
		System.out.println("[" +  um.userList[identifier].id + "] 님 환영합니다." );
		
		while(true) {			
			System.out.println("[1.계좌생성]\n[2.계좌삭제]\n[3.조회]\n[4.입금]\n[5.출금]\n[6.송금]\n[7.회원탈퇴]\n[0.로그아웃]");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				AccountManager.getInstance().createAcc(identifier);
				FileManager.getInstance().save();
			}
			else if(sel == 2) {
				AccountManager.getInstance().deleteAcc(identifier);
				FileManager.getInstance().save();
			}
			else if(sel == 3) {
				AccountManager.getInstance().printAcc(identifier);
			}
			else if(sel == 4) {
				TransManager.getInstance().deposit(identifier);
				FileManager.getInstance().save();
			}
			else if(sel == 5) {		
				TransManager.getInstance().withdraw(identifier);
				FileManager.getInstance().save();
			}
			else if(sel == 6) {
				TransManager.getInstance().remittance(identifier);
				FileManager.getInstance().save();
			}
			else if(sel == 7) {
				identifier = um.deletMember(identifier);
				FileManager.getInstance().save();
				break;		
			}

			else if(sel == 0) {
				identifier = -1;
				System.out.println("로그아웃되었습니다.");
				break;
			}
		}	
	} //loginMenu
	
	void join() {
		UserManager.getInstance().joinMember();
	}
	
	
	
}
