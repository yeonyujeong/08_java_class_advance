package atm_v1_arrayList;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Atm {
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	HashMap<String, User> user = new HashMap();
	Account account = new Account();
	String identifier = "logout";
	
	void printMainMenu() {
		
		while(true) {
		
			System.out.println("\n[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입] [4.회원탈퇴] [0.종료] : ");
			int sel = scan.nextInt();
			
			if      (sel == 1) 	    login();
			else if (sel == 2) 		logout();
			else if (sel == 3) 		join();
			else if (sel == 4) 		leave();
			else if (sel == 0) 		break;
			
		}
	}
	
	void login() {
		System.out.print("[로그인] ID 입력 : ");
		String logId = scan.next();
		int logCheck = -1;
		for (String key : user.keySet()) {
			if(logId == key) {
				logCheck = 1; identifier = key;
				System.out.println(key + "님, 로그인 되었습니다.");
				printAccountMenu();
			}
		}
		if(logCheck == 1) {
			System.out.println("아이디를 확인해주세요.");
			return;
		}		
	}
	
	void logout() {
		if(identifier.equals("logout")) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		else {
			identifier = "logout";
			System.out.println("로그아웃되었습니다.");
		}
	}
	
	void join() {
		System.out.print("가입할 ID 입력 : ");
		String addId = scan.next();
		int check = -1;
		for (String key : user.keySet()) {
			if(addId == key) {check = 1;}
		}
		if(check == -1) {
			user.put(addId, new User());
		}
		else {System.out.println("이미 존재하는 ID입니다.");}
	}
	
	void leave() {
		System.out.print("탈퇴할 ID 입력 : ");
		String leaveId = scan.next();
		int check = -1;
		for (String key : user.keySet()) {
			if(leaveId == key) {check = 1;}
		}
		if(check == 1) {
			user.remove(leaveId);
			logout();
		}
		else {System.out.println("존재하지 않는 ID입니다.");}
	}
	
	
	
	
	void printAccountMenu() {
		
		while(true) {
			
			System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
			int sel = scan.nextInt();
			
			String makeAccount = Integer.toString(ran.nextInt(90001) + 10000);	
					
			if(sel == 1) {
				if(user.get(identifier).accCount == 0) {
				user.get(identifier).acc = new Account[1];
				user.get(identifier).acc[0] = new Account();
				user.get(identifier).acc[0].number = makeAccount;
				}
				else {
					Account[] temp = user.get(identifier).acc;
					int tempAccCount = user.get(identifier).accCount;
					user.get(identifier).acc = new Account[tempAccCount + 1];
					user.get(identifier).acc[tempAccCount] = new Account();
					user.get(identifier).acc[tempAccCount].number = makeAccount;
				}
				user.get(identifier).accCount++;
			}
			
			else if(sel == 2) {
				if(user.get(identifier).accCount == 0) {
					System.out.println("삭제할 계좌가 없습니다.");
					return;
				}
				else if(user.get(identifier).accCount == 1) {
					user.clear();
					System.out.println("계좌가 삭제되었습니다.");
				}
				else {
					System.out.print("삭제할 계좌를 입력해주세요 : ");
					String delAcc = scan.next();
					int delIdx = -1;
					for (int i = 0; i < user.get(identifier).accCount; i++) {
						if(delAcc.equals(user.get(identifier).acc[i].number)) {
							delIdx = i;
						}
					}
					if(delIdx == -1) {
						System.out.println("해당하는 계좌가 없습니다.");
						return;
					}
					else {
						Account[] temp = user.get(identifier).acc;
						int tempCnt = user.get(identifier).accCount;
						user.get(identifier).acc = new Account[tempCnt - 1];
						for (int i = 0; i < delIdx; i++) {
							user.get(identifier).acc[i] = temp[i];
						}
						for (int i = delIdx; i < tempCnt -1; i++) {
							user.get(identifier).acc[i] = temp[i+1];
						}
						
					}
				
				}
			}
			
			
		}
	}
	
	
}
