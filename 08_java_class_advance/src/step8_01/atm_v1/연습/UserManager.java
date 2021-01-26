package step8_01.atm_v1.연습;

import java.util.Scanner;

public class UserManager {
	
	Scanner scan = new Scanner(System.in);
	User[] user = null;
	int userCount = 0;
	
	void printAllUser() {
		for (int i = 0; i < userCount; i++) {
			user[i].printAccount();
		}
	}
	
	void addUser() { // atm 회원가입
		
		if(userCount == 0) {
			user = new User[1];
		}
		else {
			User[] temp = user;
			user =  new User[userCount + 1];
			for (int i = 0; i < userCount; i++) {
				user[i] = temp[i];
			}
			temp = null;
		}
		System.out.print("[가입] 아이디를 입력하세요 : ");
		String id = scan.next();
		
		boolean isDuple = false;
		for (int i = 0; i < userCount; i++) {
			if(user[i].id.equals(id)) {
				isDuple = true;
			}
		}
		if(!isDuple) {
			user[userCount] = new User();
			user[userCount].id = id;
			System.out.println("[메세지] ID : '" + id + "'가입되었습니다.\n");
			userCount++;
		}
		else {
			System.out.println("[메세지] '" + id + "'는 이미 있는 아이디입니다.\n");
		}
		
	}	
	int logUser() { //atm로그인
			
		int identifier = -1;
		System.out.print("[입력] 아이디를 입력하세요 : ");
		String name = scan.next();
			
		for (int i = 0; i < userCount; i++) {
			if(name.equals(user[i].id)) {
				identifier = i;
				System.out.println("[" + user[identifier].id + "님 로그인\n");
			}
		}
		return identifier;
	}
	
	void leave( ) {
		
		System.out.print("탈퇴할 아이디를 입력해주세요 : ");
		String name = scan.next();
		int idx = -1;
		for (int i = 0; i < userCount; i++) {
			if(name.equals(user[i].id)) {
				idx = i;
			}
		}
		if(idx == -1) {
			System.out.println("해당하는 아이디가 없습니다.");
			return;
		}
		else {
			User[] temp = user;
			user = new User[userCount - 1];
			
			for (int i = 0; i < idx; i++) {
				user[i] = temp[i];
			}
			for (int i = idx; i < userCount - 1; i++) {
				user[i] = temp[i+1];
			}
			userCount--;
			System.out.println("아이디 '" + name + "'이 탈퇴되었습니다.");
		}
	}
		
		
		
		
		
		
		
		
		
		
		
		

}
	
	
	

