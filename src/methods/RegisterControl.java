package methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import vectorList.*;

public class RegisterControl {
	private VectorList<String> usernames=new VectorList<String>();
	private VectorList<String> passwords=new VectorList<String>();
	private VectorList<String> levels=new VectorList<String>();
	private VectorList<String> points=new VectorList<String>();
	private VectorList<String> usernamesScr=new VectorList<String>();
	private VectorList<String> levelsScr=new VectorList<String>();
	private VectorList<String> pointsScr=new VectorList<String>();
	private VectorList<String> points2Scr=new VectorList<String>();
	
	private File fReg;
	private File fScore;
	private String[] userScores=new String[2];
	private String[] topThree=new String[9];
	
	public String[] getTopThree() {
		return topThree;
	}

	public void setTopThree(String[] topThree) {
		this.topThree = topThree;
	}

	public String[] getUserScores() {
		return userScores;
	}

	public void setUserScores(String[] userScores) {
		this.userScores = userScores;
	}

	public RegisterControl()
	{
		/*INITIAL*/
		/*registers.txt dosyasini oku.*/
		fReg = new File("C:\\Users\\MGUREKEN\\eclipse-workspace\\MemoryCard\\src\\database\\registers.txt");
		fScore = new File("C:\\Users\\MGUREKEN\\eclipse-workspace\\MemoryCard\\src\\database\\scores.txt");
		
		/*dosyanin sadece son satirini okuma islemi yapilmali
		 * oyle olursa, vectorlist'lerin silinmesine gerek kalmaz.*/
		usernames.temizle();
		passwords.temizle();
		levels.temizle();
		points.temizle();		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fReg));
			String s=null;
			try {
				while((s = br.readLine()) != null)
				{
					String[] s1 = s.split(":"); // s'in 4 degeri var
					usernames.ekle(s1[0]);
					passwords.ekle(s1[1]);
					levels.ekle(s1[2]);
					points.ekle(s1[3]);
					System.out.println("REGISTERS: "+s1[0]+"."+s1[1]+"."+s1[2]+"."+s1[3]);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}
	
	public boolean LoginControl(String username, String password)
	{
		boolean result = false;
		int index;
		String pswControl;
		
		index = usernames.bul(username);
		if(index>-1)
		{
			pswControl = passwords.getir(index);
			if(pswControl.equals(password))
			{
				userScores[0] = levels.getir(index);
				userScores[1] = points.getir(index);
				result = true;
			}
		}
		return result;
	}
	
	public boolean NewUserControl(String username, String password)
	{
		boolean result=true;
				
		/*varolan bir kullanici mi*/
		if(usernames.icerir(username))
		{
			result = false;
		}
		/*yeni kullaniciyi dosyaya ekle.*/
		else
		{
			FileWriter fw;
			try {
				fw = new FileWriter(fReg, true);				
				BufferedWriter bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(username+":"+password+":0:0");
				bw.flush();
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return result;
	}
	
	public int ApplyScores(String username, int level, int point)
	{
		int userRanking=0, index;
		
		/*dosyaya yeni skoru isle*/
		FileWriter fw;
		try {
			fw = new FileWriter(fScore, true);				
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(username+":"+level+":"+point);
			bw.flush();
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		/*dosyanin sadece son satirini okuma islemi yapilmali
		 * oyle olursa, vectorlist'lerin silinmesine gerek kalmaz.*/
		usernamesScr.temizle();
		levels.temizle();
		pointsScr.temizle();
		points2Scr.temizle();
		
		/*dosyadan tum skorlari oku*/
		try {
			BufferedReader br = new BufferedReader(new FileReader(fScore));
			String s=null;
			try {
				while((s = br.readLine()) != null)
				{
					String[] s1 = s.split(":"); // s'in 4 degeri var
					usernamesScr.ekle(s1[0]);
					levels.ekle(s1[1]);
					pointsScr.ekle(s1[2]);
					points2Scr.ekle(s1[2]);
					
					System.out.println("SCORES: "+s1[0]+"."+s1[1]+"."+s1[2]);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		/*listeyi puana gore sirala*/
		points2Scr.sirala();
		userRanking = points2Scr.bul(""+point)+1;
		for(int i=0; i<3; i++)
		{
			index = pointsScr.bul(points2Scr.getir(points2Scr.boyut()-(i+1)));
			topThree[3*i] = usernamesScr.getir(index);
			topThree[3*i+1] = levelsScr.getir(index);
			topThree[3*i+2] = pointsScr.getir(index);
		}
		
		return userRanking;
	}
}

