package step8_02.atm_v2.만들어보기;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {
	
	private FileManager() {};
	static private FileManager instance = new FileManager();
	static public FileManager getIstance() {
		return instance;
	}
	
	String fileName = "ATM_practice.txt";
	String data = "";
	UserManager um = UserManager.getInstance();
	
	void setData() {
		
		// 첫번째줄에 유저수 저장
		data = "";
		data += um.user.size();
		data += "\n";
		
		// 계좌수 , id , pw , acc/money
		for (int i = 0; i < um.user.size(); i++) {
			if(um.user.get(i).acc == null) {data += 0;}
			else {data += um.user.get(i).acc.size();}			
			data += "\n";
			data += um.user.get(i).id;
			data += "\n";
			data += um.user.get(i).pw;
			data += "\n";
			if(um.user.get(i).acc != null) {
			for (String acc : um.user.get(i).acc.keySet()) {
				data += acc;
				data +="/";
				data += um.user.get(i).acc.get(acc);
				data += "\n";
			}
			}
		}
	}
	
	void save() {
		setData();
		
		FileWriter fw = null;
		try {			
			fw = new FileWriter(fileName);
			fw.write(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {fw.close();} catch (IOException e) {}}
		}
	}
	
	void load() {
		
		File file =  new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			
			if(file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				
				while(true) {
					String line = br.readLine();
					if(line == null) {
						break;
					}
					data += line;
					data += "\n";
				}
				
				String [] temp = data.split("\n");
				int userCnt = Integer.parseInt(temp[0]);
				um.user = new ArrayList<User>();
				
				for (int i = 1; i <temp.length; i++) {
					int accCnt = Integer.parseInt(temp[i]);
					if(accCnt == 0) {
						HashMap<String, Integer> accMap = new HashMap<>();
						um.user.add(new User(temp[i+1], temp[i+2], accMap));
					}
					else {
					HashMap<String, Integer> accMap = new HashMap<>();
					for (int j = 0; j < accCnt; j++) {
						String[] accTemp = temp[i+3+j].split("/");
						accMap.put(accTemp[0], Integer.parseInt(accTemp[1]));
					}
					um.user.add(new User(temp[i+1] , temp[i+2], accMap));
					}
					i+= 2+accCnt;
				}
			}
			else {
				setData();
				save();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!= null){try {br.close();} catch (IOException e) {}}
			if(fr!= null){try {fr.close();} catch (IOException e) {}}
		}	
	}

}





