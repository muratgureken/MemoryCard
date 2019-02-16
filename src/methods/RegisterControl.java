package methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import sun.print.PrinterJobWrapper;
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
	private VectorList<String> dosyaIcerik=new VectorList<String>();

	private File fReg, fReg2,fReg3;
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
		fReg = new File("C:\\Users\\vektorel\\git\\MemoryCard\\src\\database\\registers.txt");
		fReg2 = new File("C:\\Users\\vektorel\\git\\MemoryCard\\src\\database\\registersyedek.txt");
		fReg3 = new File("C:\\Users\\vektorel\\git\\MemoryCard\\src\\database\\oldu.txt");
		fScore = new File("C:\\Users\\vektorel\\git\\MemoryCard\\src\\database\\scores.txt");

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

		usernames.setDeger(username);
		index = usernames.bul();
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
				bw.write(username+":"+password+":1:0");
				bw.newLine();
				bw.flush();
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return result;
	}

	public void UpdateUserInfo(String username, int level)
	{	
		FileWriter fw;
		FileOutputStream writer;
		/*try {
			fReg2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			 writer = new FileOutputStream(fReg2);
			try {
				writer.write("".getBytes());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		/*olmuyorrrr*/
		/*try {
			BufferedReader br = new BufferedReader(new FileReader(fReg));			

			String s=null;
			try {
				fw = new FileWriter(fReg2, true);
				BufferedWriter bw = new BufferedWriter(fw);
				while((s = br.readLine()) != null)
				{
					String[] s1 = s.split(":"); // s'in 4 degeri var
					if(s1[0].equals(username))
					{
						bw.write(username+":"+s1[1]+":"+level+":"+s1[3]);
						bw.newLine();

					}
					else
					{
						bw.write(s1[0]+":"+s1[1]+":"+s1[2]+":"+s1[3]);				
						bw.newLine();
					}
				}
				bw.flush();
				bw.close();
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}*/
		System.out.println("Girdi:"+username+" "+level);

		dosyaIcerik.temizle();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fReg));			

			String s=null;
			try {
				while((s = br.readLine()) != null)
				{
					String[] s1 = s.split(":"); // s'in 4 degeri var

					dosyaIcerik.ekle(s1[0]);
					dosyaIcerik.ekle(s1[1]);
					dosyaIcerik.ekle(s1[2]);
					dosyaIcerik.ekle(s1[3]);
					System.out.println("icerik:"+s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]);
				}
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			writer = new FileOutputStream(fReg);
			try {
				writer.write("".getBytes());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			fw = new FileWriter(fReg, true);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0; i<(dosyaIcerik.boyut()/4); i++)
			{
				//System.out.println("icerik:"+dosyaIcerik.getir(4*i)+"gelenbilgi:"+username);
				if(dosyaIcerik.getir(4*i).equals(username))
				{
					bw.write(dosyaIcerik.getir(4*i)+":"+dosyaIcerik.getir(4*i+1)+":"+
							level+":"+dosyaIcerik.getir(4*i+3));	
				}
				else
				{
					bw.write(dosyaIcerik.getir(4*i)+":"+dosyaIcerik.getir(4*i+1)+":"+
							dosyaIcerik.getir(4*i+2)+":"+dosyaIcerik.getir(4*i+3));	
				}
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//fReg.delete();
		//fReg2.renameTo(fReg);

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
					points2Scr.ekle(s1[3]);

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
		points2Scr.setDeger(""+point);
		userRanking = points2Scr.bul()+1;
		for(int i=0; i<3; i++)
		{
			points2Scr.setDeger(points2Scr.getir(points2Scr.boyut()-(i+1)));
			index = pointsScr.bul();
			topThree[3*i] = usernamesScr.getir(index);
			topThree[3*i+1] = levelsScr.getir(index);
			topThree[3*i+2] = pointsScr.getir(index);
		}

		return userRanking;
	}
}

