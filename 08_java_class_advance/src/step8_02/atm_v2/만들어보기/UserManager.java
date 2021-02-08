
package step8_02.atm_v2.만들어보기;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {

	private UserManager () {}
	static private UserManager instance = new UserManager();
	static public UserManager getInstance() {
		return instance;
	}
	
	AccountManager am = AccountManager.getInstance();
	Scanner scan = new Scanner(System.in);
	ArrayList<User> user = new ArrayList<>();
	
	
	void printAllUser() {
		for (int i = 0; i < user.size(); i++) {
			System.out.println("ID : "+ user.get(i).id + " / PW : "+ user.get(i).pw);
			if(user.get(i).acc.size() != 0) {
			am.printAcc(i);
			}
			else {
				System.out.println("생성된 계좌 없음");
				System.out.println("=============================================");
			}
		}
	}

	int logUser() {
		int identifier = -1;
		System.out.print("ID를 입력하세요 : ");
		String logId = scan.next();
		System.out.print("PW를 입력하세요 : ");
		String logPw = scan.next();
		
		for (int i = 0; i < user.size(); i++) {
			if(logId.equals(user.get(i).id) && logPw.equals(user.get(i).pw)) {
				identifier = i;
			}
		}
		return identifier;	
	}
	
	boolean checkId(String joinId) { //id중복체크 true면 중복아닌ㅁ
		boolean isIdDuple = true;
		for (int i = 0; i < user.size(); i++) {
			if(joinId.equals(user.get(i).id)) {
				isIdDuple = false;
			}
		}
		
		return isIdDuple;
	}
	 
	void joinMember() {
		while(true) {
			System.out.print("가입하실 ID를 입력해주세요 : ");
			String joinId = scan.next();
			if(!checkId(joinId)) {
				System.out.println("이미 존재하는 ID입니다.");
				continue;
			}
			else {			
				System.out.print("가입하실 ID의 PW를 입력해주세요 : ");
				String joinPw = scan.next();
				HashMap<String, Integer> acc = new HashMap<String, Integer>();
				user.add(new User(joinId, joinPw , acc ));
				System.out.println(joinId + "님, 가입되었습니다.");
				break;
			}
		}
	}
	
	void deleteMember(int identifier) {
		if(identifier == -1) {
			System.out.println("로그인 후에 이용해주세요.");return;
		}
		user.remove(identifier);
		System.out.println("탈퇴되었습니다.");
		identifier = -1;
	}
	
	
	
	
	
	
	
}
