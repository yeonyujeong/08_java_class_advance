package step8_02.atm_v2.만들어보기;

import java.util.HashMap;

public class User {
	String id;
	String pw;
	HashMap<String, Integer> acc = new HashMap<>();
		
	User(String id, String pw, HashMap<String , Integer> acc){
		this.id = id;
		this.pw = pw;
		this.acc = acc;
	}
	
	
}


