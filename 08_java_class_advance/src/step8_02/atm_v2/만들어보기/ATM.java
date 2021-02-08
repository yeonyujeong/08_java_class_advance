package step8_02.atm_v2.만들어보기;

import java.util.Scanner;

public class ATM {
	
	UserManager um = UserManager.getInstance();
	Scanner scan = new Scanner(System.in);
	int identifier = -1;
	
	
	void play() {
		
		FileManager.getIstance().load();
		um.printAllUser();
		
		while(true) {		
			System.out.println("[ ATM ]");
			System.out.println("[1.회원가입] [2.로그인] [0.종료]");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			if(sel == 1) {join();}
			else if(sel == 2) {logIn();}
			else if(sel == 0) {break;}

		}
	}
	
	void join() {
		um.joinMember();
		FileManager.getIstance().save();
	}
	
	void logIn() {
		
		identifier = um.logUser();
		if(identifier == -1) {
			System.out.println("ID와PW를 확인해주세요");return;
		}
		else {
			System.out.println(um.user.get(identifier).id + "님, 로그인되었습니다.");

			logMenu();
		}

	}
	
	void logMenu() {		
		
		while(true) {
			System.out.println("[1.계좌생성]\n[2.계좌삭제]\n[3.조회]\n[4.입금]\n[5.출금]\n[6.송금]\n[7.회원탈퇴]\n[0.로그아웃]");
			System.out.print("메뉴 선택 : ");
			int choice = scan.nextInt();
			
			if(choice == 1) {
				AccountManager.getInstance().createAcc(identifier);
				FileManager.getIstance().save();
			}
			else if(choice == 2) {
				AccountManager.getInstance().deleteAcc(identifier);
				FileManager.getIstance().save();
			}
			else if(choice == 3) {
				if(um.user.get(identifier).acc.size() != 0) {
				AccountManager.getInstance().printAcc(identifier);
				}else {
					System.out.println("생성된 계좌가 없습니다.");
				}
			}
			else if(choice == 4) {			
				TransManager.getInstance().deposit(identifier);
				FileManager.getIstance().save();
			}
			else if(choice == 5) {
				TransManager.getInstance().withdraw(identifier);
				FileManager.getIstance().save();		
			}
			else if(choice == 6) {
				TransManager.getInstance().remittance(identifier);
				FileManager.getIstance().save();			
			}
			else if(choice == 7) {
				UserManager.getInstance().deleteMember(identifier);
				FileManager.getIstance().save();
				break;
			}
			
			else if(choice == 0) {
				identifier = -1;
				System.out.println("로그아웃되었습니다.");
				break;
			}
			
	}
		
		
		
	}
	
	
	
	
	
}
