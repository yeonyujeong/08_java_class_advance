// 2021.02.01 // 2021.02.03

package step8_02.atm_v2.copy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager {
	
	private FileManager() {}
	private static FileManager instance = new FileManager();
	public static FileManager getInstance() {
		return instance;
	}
	
	String fileName = "ATM2.txt";
	String data = "";
	UserManager um =  UserManager.getInstance();
	
	void setData() {
		
		data = "";
		int userCount = um.userCnt;
		data += userCount;
		data += "\n";
		
		for (int i = 0; i < userCount; i++) {
			data += um.userList[i].id;
			data += "\n";
			data += um.userList[i].pw;
			data += "\n";
			data += um.userList[i].accCnt;
			data += "\n";
			
			if(um.userList[i].accCnt == 0) {
				data += "0\n";
			}
			else {
				for (int j = 0; j < um.userList[i].accCnt; j++) {
					data += um.userList[i].acc[j].accNumber;
					data += "/";
					data += um.userList[i].acc[j].money;
					if(j != um.userList[i].accCnt - 1) {
						data += ",";
					}
				}
				data += "\n";
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
				try {fw.close();} catch (IOException e){}}
		}
	
	}
	
	void load() {
		
		File file = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			
			if(file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				while (true) {
					String line = br.readLine(); // line에 데이터 내용 다 저장
					if(line == null) {
						break;
					}
					data += line;
					data += "\n";
				}
				String[] temp = data.split("\n");
				um.userCnt = Integer.parseInt(temp[0]); //첫번째줄 userCnt수
				um.userList = new User[um.userCnt];
				for (int i = 0; i < um.userCnt; i++) {
					um.userList[i] = new User();
				}
				
				int j = 0;
				for (int i = 1; i < temp.length; i+=4) {
					String id = temp[i];
					String pw = temp[i+1];
					int accCnt = Integer.parseInt(temp[i+2]);
					
					um.userList[j].id = id;
					um.userList[j].pw = pw;
					um.userList[j].accCnt = accCnt;
					
					String accInfo = temp[i+3];
					
					if(accCnt == 1) {
						String[] tmp = accInfo.split("/");
						String acc = tmp[0];
						int money = Integer.parseInt(tmp[1]);
						um.userList[j].acc[0] = new Account();
						um.userList[j].acc[0].accNumber = acc;
						um.userList[j].acc[0].money = money;
					}
					
					if(accCnt > 1) {
						String[] tmp = accInfo.split(",");
						for (int k = 0; k < tmp.length; k++) {
							String[] parse = tmp[k].split("/");
							String acc = parse[0];
							int money = Integer.parseInt(parse[1]);
							um.userList[j].acc[k] = new Account();
							um.userList[j].acc[k].accNumber = acc;
							um.userList[j].acc[k].money = money;
						}
					}
					j++;
				}

			}
			
			else {
				setData();
				save();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(br != null) {try {br.close();} catch (IOException e) {}}
			if(fr != null) {try {fr.close();} catch (IOException e) {}}
		
		}
	}
	
	
	
}





