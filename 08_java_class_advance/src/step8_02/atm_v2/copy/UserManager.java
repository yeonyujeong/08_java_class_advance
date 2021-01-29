package step8_02.atm_v2.copy;

import java.util.Random;
import java.util.Scanner;

public class UserManager {

	private UserManager() {}
	static private UserManager instance = new UserManager();
	static public UserManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	
	final int ACC_MAX_CNT = 3;
	User[] userList = null;
	int userCnt = 0;
	
	
	void printAllUser() {
		
		for (int i = 0; i < userCnt; i++) {
			System.out.print((i+1) + ".ID(" + userList[i].id + ")\tPW(" + userList[i].pw + ")\t");
			if (userList[i].accCnt != 0) {
				for (int j = 0; j < userList[i].accCnt; j++) {
					System.out.println("(" +userList[i].acc[j].accNumber + ":" + userList[i].acc[j].money + ")");
				}
			}
			System.out.println();
		}
		
	}
	
	boolean getCheckAcc (String account) { //계좌 중복 확인
		boolean isDuple = false;
		for (int i = 0; i <userCnt; i++) {
			for (int j = 0; j < userList[i].accCnt; j++) {
				if(account.equals(userList[i].acc[j].accNumber)) {
					isDuple = true;
				}
			}
		}
		return isDuple;
	}
	
	int logUser() { //로그인
		
		int identifier = -1;
		
		System.out.println("[로그인] 아이디를 입력하세요 : ");
		String id = scan.next();
		System.out.println("[로그인] 패스워드를 입력하세요 : ");
		String pw = scan.next();
		
		for (int i = 0; i < UserManager.instance.userCnt; i++) {
			if(userList[i].id.equals(id) && userList[i].pw.equals(pw)) {
				identifier = i;
			}
		}
		return identifier;
	}
	
	boolean checkId(String id) {
		
		boolean isDuple = false;
		for (int i = 0; i < userCnt; i++) {
			if(userList[i].id.equals(id)) {
				isDuple = true;
			}
		}
		return isDuple;
	}
	
	
	void joinMember () {
		
		System.out.print("[회원가입] 아이디를 입력하세요 : ");
		String id = scan.next();
		System.out.println("[회원가입] 패스워드를 입력하세요 : ");
		String pw = scan.next();
		
		boolean isResult =  checkId(id); //아이디 중복확인
		
		if(isResult) {
			System.out.println("[메세지] 아이디가 중복됩니다.");
			return;
		}
		if(userCnt == 0) { //아이디 하나도 없으면 새로 하나 만들기
			userList = new User[userCnt + 1];
			userList[userCnt] = new User();
		}
		else {
			User[] tmp = userList;
			userList = new User[userCnt + 1];
			userList[userCnt] = new User();
			
			for (int i = 0; i < userCnt; i++) {
				userList[i] = tmp[i];
			}
			tmp = null;
		}
		userList[userCnt].id = id;
		userList[userCnt].pw = pw;
		
		userCnt++;
		System.out.println("[메세지]회원가입을 축하합니다.");
		
		//FileManager.getInstance().save();
		//파일에 저장
		
	}
	
	int deleteMember(int identifier) {
		
		User[] tmp = userList;
		userList = new User[userCnt - 1];
		
		int j = 0;
		for (int i = 0; i < userCnt; i++) {
			if(i != identifier) {
				userList[j++] = tmp[i];
			}
		}
		userCnt--;
		tmp = null;
		identifier = -1;
		
		System.out.println("[메세지] 탈퇴되었습니다.");
		
		//FileManager.getInstance().save();
		// 파일에 삭제된거 저장
		
		return identifier;
		// 로그아웃 시키기
	}
	
	
	
	
	
}
