// 2021.02.01
package step8_02.atm_v2.copy;

import java.util.Random;
import java.util.Scanner;

public class UserManager {

	private UserManager() {}
	static private UserManager instance = new UserManager();
	static public UserManager getInstance () {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	
	final int ACC_MAX_CNT = 3;
	User[] userList = null;
	int userCnt = 0;
	
	void printAllUser() {
		
		for (int i = 0; i < userCnt; i++) {
			System.out.println((i+1) + ": ID )" + userList[i].id + "PW )" + userList[i].pw  );
			if(userList[i].accCnt != 0) {
				for (int j = 0; j < userList[i].accCnt; j++) {
					System.out.println("(" + userList[i].acc[j].accNumber + ":" + userList[i].acc[j].money);
				}
			}
			System.out.println();
		}

	}
	
	boolean getCheckAcc(String account) {  // 중복이면 true
		boolean isDuple = false;
		for (int i = 0; i < userCnt; i++) {
			for (int j = 0; j < userList[i].accCnt; j++) {
				if(account.equals(userList[i].acc[j].accNumber)) {
					isDuple = true;
				}
			}
		}
		return isDuple;
	}
	
	
	int logUser() {
		
		int identifier = -1;
		
		System.out.print("로그인할 아이디를 입력하세요 : ");
		String logId = scan.next();
		System.out.print("로그인할 패스워드를 입력하세요 : ");
		String logPw = scan.next();
		
		for (int i = 0; i < userCnt; i++) {
			if(logId.equals(userList[i].id) && logPw.equals(userList[i].pw)) {
				identifier = i;
			}
		}

		
		return identifier;
	}
	
	boolean checkId(String id) { //아이디 중복체크 중복되면 true
		
		boolean isDuple = false;
		for (int i = 0; i < userCnt; i++) {
			if(userList[i].id.equals(id)) {
				isDuple = true;
			}
		}
		
		return isDuple;
	}
	
	void joinMember() {
		
		System.out.print("가입할 아이디를 입력하세요 : ");
		String id = scan.next();
		System.out.print("가입할 패스워드를 입력하세요 : ");
		String pw = scan.next();
		
		boolean isResult = checkId(id);
		
		if(!isResult) {
			if(userCnt == 0) {
				userList = new User[userCnt + 1];
				userList[0] = new User();
			}
			else {
				User[] temp = userList;
				userList = new User[userCnt + 1];
				for (int i = 0; i < userCnt; i++) {
					userList[i] = temp[i];
				}
				temp = null;
				userList[userCnt] = new User();				
			}
			userList[userCnt].id = id;
			userList[userCnt].pw = pw;
			userCnt++;
			System.out.println(id +"님 회원가입되었습니다.");
			
			FileManager.getInstance().save();
		}
		else {
			System.out.println("아이디가 중복됩니다.");return;
		}

	}
	
	int deletMember(int identifier) {
		
		User[] temp = userList;
		userList = new User[userCnt - 1];
		
		for (int i = 0; i < identifier; i++) {
			userList[i] = temp[i];
		}
		for (int i = identifier; i < userCnt - 1; i++) {
			userList[i] = temp[i+1];
		}
		System.out.println(temp[identifier].id +"님 탈퇴되었습니다.");
		
		temp = null;
		userCnt--;
		identifier = -1;
		FileManager.getInstance().save();
		return identifier;
	}
	
	
	
	
	
	
	
}
