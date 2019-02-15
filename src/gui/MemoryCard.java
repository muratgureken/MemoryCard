package gui;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import methods.DefineCardBoundaries;
import methods.RandomCardChoose;
import methods.RegisterControl;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import vectorList.VectorList;

public class MemoryCard extends JFrame{
	Semaphore sem = new Semaphore(0); 
	int zamanKriteri=20;
	int maxCardType=21,cardNumber, anlikKartSayisi=0, toplamKartSayisi=0, oncekiKart, oncekiKartID, level;
	int miliseconds,seconds,minutes;
	String[] images = new String[maxCardType];
	private String[] userScores=new String[2];
	private JTextField txtUserName;
	private JPasswordField pswPassword;
	JButton btnYeniKullanici,btnGiris,btnDevam,btnYeniOyun;
	JLabel lblBaslik,lblSolPanel, lblSagPanel, lblKullancAdi, lblSifre,lblGiris, lblNScoreTable;
	JButton[] array= new JButton[50];
	RegisterControl rg;
	RandomCardChoose rcc;
	DefineCardBoundaries dcb;
	VectorList<Integer> cardRegister=new VectorList<Integer>();
	VectorList<String> seciliKartlar=new VectorList<String>();
	String coverphoto = "/images/lor.jpg";
	private JLabel lblKullaniciustkose;
	boolean ilkSecim=true,state=true;
	private JLabel lblZaman;

	public MemoryCard() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		rg = new RegisterControl();
		//rg.NewUserControl("zeytin", "10");
		rcc = new RandomCardChoose();
		dcb = new DefineCardBoundaries();

		/*DEFINE IMAGES*/
		images[0] = "/images/ananas.jpg";
		images[1] = "/images/araba.jpg";
		images[2] = "/images/aslankral.jpg";
		images[3] = "/images/ataturk.jpg";
		images[4] = "/images/besiktas.png";
		images[5] = "/images/cilek.jpg";
		images[6] = "/images/dart.png";
		images[7] = "/images/donald.jpg";
		images[8] = "/images/federer.jpg";
		images[9] = "/images/honda.png";
		images[10] = "/images/kardanadam.jpg";
		images[11] = "/images/kiraz.jpg";
		images[12] = "/images/kopek.jpg";
		images[13] = "/images/kurbaga.jpg";
		images[14] = "/images/lamborg.jpg";
		images[15] = "/images/nba.jpg";
		images[16] = "/images/odtu.png";
		images[17] = "/images/tatilci.jpg";
		images[18] = "/images/temelreis.png";
		images[19] = "/images/toyota.jpg";		
		images[20] = "/images/zurafa.jpg";

		JButton btnKart1 = new JButton("");
		//btnKart1.setSelectedIcon(new ImageIcon(MemoryCard.class.getResource("/images/kedi.png")));
		btnKart1.setBounds(0, 202, 100, 150);
		//btnKart1.setIcon(new ImageIcon(getClass().getResource("/images/kedi.png")));
		btnKart1.setIcon(new ImageIcon(getClass().getResource("/images/lor.jpg")));
		getContentPane().add(btnKart1);
		btnKart1.setVisible(false);
		getContentPane().setLayout(null);

		lblGiris = new JLabel("");
		lblGiris.setBounds(374, 193, 297, 246);
		lblGiris.setIcon(new ImageIcon(getClass().getResource("/images/anasayfa.jpg")));
		getContentPane().add(lblGiris);

		lblBaslik = new JLabel("MEMORY CARDS");
		lblBaslik.setForeground(Color.RED);
		lblBaslik.setFont(new Font("Jokerman", Font.PLAIN, 30));
		lblBaslik.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaslik.setBounds(354, 146, 299, 43);
		getContentPane().add(lblBaslik);

		lblKullancAdi = new JLabel("Username");
		lblKullancAdi.setForeground(Color.RED);
		lblKullancAdi.setFont(new Font("Harrington", Font.BOLD, 14));
		lblKullancAdi.setBounds(274, 506, 100, 14);
		getContentPane().add(lblKullancAdi);

		lblSifre = new JLabel("Password");
		lblSifre.setForeground(Color.RED);
		lblSifre.setFont(new Font("Harrington", Font.BOLD, 14));
		lblSifre.setBounds(274, 543, 92, 14);
		getContentPane().add(lblSifre);

		btnGiris = new JButton("Log In");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*LOG IN*/
				/*kullanicinin ad-sifre kombinasyonu txt dosyasinda var mi kontrol et*/
				/*kullanici adinda ":" isareti olmasin, varsa uyar*/

				/*giris basarili*/
				if(rg.LoginControl(txtUserName.getText(), pswPassword.getText()))
				{
					txtUserName.setVisible(false);
					pswPassword.setVisible(false);
					btnGiris.setVisible(false);
					btnYeniKullanici.setVisible(false);
					userScores = rg.getUserScores();
					btnDevam.setVisible(true);
					btnYeniOyun.setVisible(true);
					lblSolPanel.setVisible(true);
					lblSagPanel.setVisible(true);
					lblKullancAdi.setVisible(false);
					lblSifre.setVisible(false);
				}
				/*giris basarisiz*/
				else
				{
					JOptionPane.showMessageDialog(MemoryCard.this, "Username or Password is not correct !");
				}
			}
		});

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JButton)
				{
					boolean durum=true;
					JButton button = (JButton) e.getSource();
					String cardID = button.getName();
					System.out.println("cardID:"+ cardID);
					int carddeger = Integer.parseInt(cardID);				    
					String photo = images[cardRegister.getir(carddeger)];
					seciliKartlar.setDeger(cardID);
					/*ilk secimle birlikte zaman baslar*/
					System.out.println("ilk secim: "+ilkSecim);
					if(ilkSecim)
					{

						Thread zaman = new Thread()
						{
							public void run()
							{								
								for(;;)
								{
									if(state)
									{
										try {
											sleep(1);
											if(miliseconds>999)
											{
												miliseconds = 0;
												seconds++;
											}
											if(seconds>=60)
											{
												seconds = 0;
												minutes++;
											}
											if(minutes>=60)
											{
												seconds = 0;
												minutes = 0;
											}
											lblZaman.setText(Integer.toString(minutes)+":"+Integer.toString(seconds)+":"+Integer.toString(miliseconds));
											miliseconds++;

										} catch (Exception e2) {

										}

										if(seconds==zamanKriteri)
										{
											state = false;
											toplamKartSayisi = 0;
											anlikKartSayisi = 0;
											seciliKartlar.temizle();											//kullanici level gelistirmisse
											System.out.println("Zaman doldu: yeni level:"+level+" eskisi:"+userScores[0]);
											if(level>Integer.parseInt(userScores[0]))
											{
												rg.UpdateUserInfo(txtUserName.getText(), level);
												userScores[0] = Integer.toString(level);
											}
											lblNScoreTable.setText("TIME IS OVER !...");
											/*TIMEDELAY*/
											oncekiEkranaDon();
										}
									}
									else
									{
										break;
									}
								}
							}
						};
						ilkSecim = false;
						zaman.start();
					}

					/*butonu enabled(false) yapmak goruntusel acidan kotuydu. O sebeple onceden acilan veya cifti bulunan
					 * kartlar uzerinden aciton alinmasina izin verilmiyor.*/
					if(seciliKartlar.bul()==-1)
					{
						anlikKartSayisi = anlikKartSayisi%2;
						anlikKartSayisi = anlikKartSayisi+1;
						if(anlikKartSayisi==1)
						{			    
							System.out.println("ilk kez tiklandi");
							button.setIcon(new ImageIcon(getClass().getResource(photo)));
							oncekiKart = cardRegister.getir(carddeger);
							oncekiKartID = carddeger;
							seciliKartlar.ekle(cardID);
						}
						else
						{
							System.out.println("ikinci kez tiklandi");
							button.setIcon(new ImageIcon(getClass().getResource(photo)));
							//sem.release();
							/*ayni kartlar acildi ise*/
							if(cardRegister.getir(carddeger)==oncekiKart)
							{
								toplamKartSayisi = toplamKartSayisi+2;
								seciliKartlar.ekle(cardID);
							}
							else
							{
								/*onceki eklenen eleman da cikarilir.*/
								seciliKartlar.cikar(seciliKartlar.boyut()-1);
								durum = false;
							}
						}

						System.out.println("level kontrol:"+toplamKartSayisi+" "+(cardNumber*2)+" level:"+level);

						if(toplamKartSayisi==(cardNumber*2))
						{
							//bu level bitti
							toplamKartSayisi = 0;
							anlikKartSayisi = 0;
							seciliKartlar.temizle();
							level++;
							if(level==20)
							{
								state = false;
								//oyun bitti
								lblNScoreTable.setText("YOU WIN :)");
								rg.UpdateUserInfo(txtUserName.getText(), level-1);
								userScores[0] = Integer.toString(level-1);
							}
							else
							{
								/*TIMEDELAY*/
								levelAtla();
								lblNScoreTable.setText("LEVEL: "+level);
							}
						}

						if((!durum)&&(anlikKartSayisi!=1))
						{
							/*TIMEDELAY*/
							zamanDurdur();
							array[oncekiKartID].setIcon(new ImageIcon(getClass().getResource(coverphoto)));
							button.setIcon(new ImageIcon(getClass().getResource(coverphoto)));
						}
					}
				}
			}
		};

		btnGiris.setForeground(Color.RED);
		btnGiris.setFont(new Font("Harrington", Font.BOLD, 14));
		btnGiris.setBounds(539, 503, 144, 23);
		getContentPane().add(btnGiris);

		btnYeniKullanici = new JButton("New User");
		btnYeniKullanici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("user name:"+txtUserName.getText());
				System.out.println("userkosul:"+(txtUserName.getText().isEmpty())+" pswkosul:"+(pswPassword.getText().isEmpty()));
				System.out.println("userkosul1:"+(txtUserName.getText().contains(" "))+" pswkosul1:"+(pswPassword.getText().contains(" ")));
								
				if((txtUserName.getText().isEmpty())||(pswPassword.getText().isEmpty())||
						(txtUserName.getText().contains(" "))||(pswPassword.getText().contains(" "))
						||(txtUserName.getText().contains(":")))
				{
					JOptionPane.showMessageDialog(MemoryCard.this, "Please enter valid a user name and a password !");										
				}
				else
				{
					if(rg.NewUserControl(txtUserName.getText(), pswPassword.getText()))
					{
						userScores[0] = Integer.toString(1);
						userScores[1] = Integer.toString(0);
						txtUserName.setVisible(false);
						pswPassword.setVisible(false);
						btnGiris.setVisible(false);
						btnYeniKullanici.setVisible(false);
						btnDevam.setVisible(true);
						btnYeniOyun.setVisible(true);
						lblSolPanel.setVisible(true);
						lblSagPanel.setVisible(true);
						lblKullancAdi.setVisible(false);
						lblSifre.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(MemoryCard.this, "The user already exists !");					
					}
				}
			}
		});
		btnYeniKullanici.setForeground(Color.RED);
		btnYeniKullanici.setFont(new Font("Harrington", Font.BOLD, 14));
		btnYeniKullanici.setBounds(539, 540, 144, 23);
		getContentPane().add(btnYeniKullanici);

		txtUserName = new JTextField();
		txtUserName.setBounds(399, 505, 116, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		pswPassword = new JPasswordField();
		pswPassword.setBounds(399, 541, 116, 20);
		getContentPane().add(pswPassword);

		btnDevam = new JButton("Continue");
		btnDevam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = true;
				ilkSecim = true;
				lblZaman.setText("0:0:0");
				btnYeniOyun.setVisible(false);
				btnDevam.setVisible(false);
				lblSolPanel.setVisible(false);
				lblSagPanel.setVisible(false);
				lblBaslik.setVisible(false);
				lblGiris.setVisible(false);
				System.out.println("yeni oyuncu contd level"+userScores[0]);
				level = Integer.parseInt(userScores[0]);
				System.out.println("yeni oyuncu contd level"+level);
				cardNumber = rcc.SelectCards(level, maxCardType);
				dcb.CalculateBoundaries(cardNumber);
				cardRegister.temizle();
				lblNScoreTable.setText("LEVEL: "+level);
				lblKullaniciustkose.setText("USER: "+txtUserName.getText());
				int j;
				for(int i=0; i<cardNumber*2; i++)
				{
					j=i%cardNumber;
					//System.out.println("indis:"+rcc.getCardOrders().getir(i)+" boundaries:"+dcb.getBoundaries().getir(rcc.getCardOrders().getir(i))+" "+dcb.getBoundaries().getir(rcc.getCardOrders().getir(i)+1));
					//System.out.println("orders: "+rcc.getCardOrders().getir(i)+" "+(rcc.getCardOrders().getir(i)+1));
					array[i].setBounds(dcb.getBoundaries().getir(2*rcc.getCardOrders().getir(i)), dcb.getBoundaries().getir(2*rcc.getCardOrders().getir(i)+1), 100, 150);
					//array iconlarini bir vectorlist'te tut. Kart acilacagi zaman set edilecek.
					cardRegister.ekle(rcc.getCards().getir(j));
					array[i].setIcon(new ImageIcon(getClass().getResource(coverphoto)));
					//array[i].setIcon(new ImageIcon(getClass().getResource(images[rcc.getCards().getir(j)])));
					array[i].setVisible(true);
				}				

			}
		});
		btnDevam.setBackground(new Color(210, 105, 30));
		btnDevam.setForeground(Color.WHITE);
		btnDevam.setFont(new Font("Jokerman", Font.BOLD, 20));
		btnDevam.setBounds(297, 478, 155, 109);
		btnDevam.setVisible(false);
		getContentPane().add(btnDevam);

		btnYeniOyun = new JButton("New Game");
		btnYeniOyun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state = true;
				btnYeniOyun.setVisible(false);
				btnDevam.setVisible(false);
				lblSolPanel.setVisible(false);
				lblSagPanel.setVisible(false);
				lblBaslik.setVisible(false);
				lblGiris.setVisible(false);
				level = 1;
				ilkSecim = true;
				cardNumber = rcc.SelectCards(level, maxCardType);
				dcb.CalculateBoundaries(cardNumber);
				lblNScoreTable.setText("LEVEL: "+level);
				lblKullaniciustkose.setText("USER: "+txtUserName.getText());
				lblZaman.setText("0:0:0");
				cardRegister.temizle();
				int j;
				for(int i=0; i<cardNumber*2; i++)
				{
					j=i%cardNumber;
					//System.out.println("indis:"+rcc.getCardOrders().getir(i)+" boundaries:"+dcb.getBoundaries().getir(rcc.getCardOrders().getir(i))+" "+dcb.getBoundaries().getir(rcc.getCardOrders().getir(i)+1));
					//System.out.println("orders: "+rcc.getCardOrders().getir(i)+" "+(rcc.getCardOrders().getir(i)+1));
					array[i].setBounds(dcb.getBoundaries().getir(2*rcc.getCardOrders().getir(i)), dcb.getBoundaries().getir(2*rcc.getCardOrders().getir(i)+1), 100, 150);
					//array iconlarini bir vectorlist'te tut. Kart acilacagi zaman set edilecek.
					cardRegister.ekle(rcc.getCards().getir(j));
					array[i].setIcon(new ImageIcon(getClass().getResource(coverphoto)));
					//array[i].setIcon(new ImageIcon(getClass().getResource(images[rcc.getCards().getir(j)])));
					array[i].setVisible(true);
				}

				//rcc.cardOrders;
			}
		});
		btnYeniOyun.setBackground(new Color(210, 105, 30));
		btnYeniOyun.setForeground(Color.WHITE);
		btnYeniOyun.setFont(new Font("Jokerman", Font.BOLD, 20));
		btnYeniOyun.setBounds(516, 478, 155, 109);
		btnYeniOyun.setVisible(false);
		getContentPane().add(btnYeniOyun);

		lblSolPanel = new JLabel("");
		lblSolPanel.setBackground(Color.WHITE);
		lblSolPanel.setIcon(new ImageIcon(MemoryCard.class.getResource("/images/zurafa.jpg")));
		lblSolPanel.setBounds(138, 460, 100, 150);
		lblSolPanel.setVisible(false);
		getContentPane().add(lblSolPanel);

		lblSagPanel = new JLabel("");
		lblSagPanel.setIcon(new ImageIcon(MemoryCard.class.getResource("/images/kurbaga.jpg")));
		lblSagPanel.setBackground(Color.WHITE);
		lblSagPanel.setBounds(726, 460, 100, 150);
		lblSagPanel.setVisible(false);
		getContentPane().add(lblSagPanel);	

		lblNScoreTable = new JLabel("");
		lblNScoreTable.setFont(new Font("Javanese Text", Font.PLAIN, 18));
		lblNScoreTable.setForeground(new Color(0, 0, 255));
		lblNScoreTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblNScoreTable.setBounds(374, 11, 238, 23);
		getContentPane().add(lblNScoreTable);

		lblKullaniciustkose = new JLabel("");
		lblKullaniciustkose.setHorizontalAlignment(SwingConstants.LEFT);
		lblKullaniciustkose.setForeground(Color.BLUE);
		lblKullaniciustkose.setFont(new Font("Javanese Text", Font.PLAIN, 18));
		lblKullaniciustkose.setBounds(28, 11, 135, 23);
		getContentPane().add(lblKullaniciustkose);

		lblZaman = new JLabel("");
		lblZaman.setFont(new Font("Javanese Text", Font.PLAIN, 18));
		lblZaman.setForeground(Color.BLUE);
		lblZaman.setBounds(829, 11, 84, 23);
		getContentPane().add(lblZaman);

		for(int i=0; i<array.length; i++)
		{
			array[i] = new JButton();
			//array[i].setFont(new Font("Tahoma", Font.PLAIN, 10));
			array[i].setBounds(i*10, 0, 100, 150);
			array[i].addActionListener(listener);
			array[i].setName(""+i);
			array[i].setVisible(false);
			getContentPane().add(array[i]);
		}
	}

	public void levelAtla()
	{
		for(int i=0; i<cardNumber*2; i++)
		{
			array[i].setVisible(false);
		}

		cardNumber = rcc.SelectCards(level, maxCardType);
		dcb.CalculateBoundaries(cardNumber);
		cardRegister.temizle();
		int j;
		for(int i=0; i<cardNumber*2; i++)
		{
			j=i%cardNumber;
			//System.out.println("indis:"+rcc.getCardOrders().getir(i)+" boundaries:"+dcb.getBoundaries().getir(rcc.getCardOrders().getir(i))+" "+dcb.getBoundaries().getir(rcc.getCardOrders().getir(i)+1));
			//System.out.println("orders: "+rcc.getCardOrders().getir(i)+" "+(rcc.getCardOrders().getir(i)+1));
			array[i].setBounds(dcb.getBoundaries().getir(2*rcc.getCardOrders().getir(i)), dcb.getBoundaries().getir(2*rcc.getCardOrders().getir(i)+1), 100, 150);
			//array iconlarini bir vectorlist'te tut. Kart acilacagi zaman set edilecek.
			cardRegister.ekle(rcc.getCards().getir(j));
			array[i].setIcon(new ImageIcon(getClass().getResource(coverphoto)));
			//array[i].setIcon(new ImageIcon(getClass().getResource(images[rcc.getCards().getir(j)])));
			array[i].setVisible(true);
		}		
	}

	public void oncekiEkranaDon()
	{
		for(int i=0; i<cardNumber*2; i++)
		{
			array[i].setVisible(false);
		}
		btnYeniOyun.setVisible(true);
		btnDevam.setVisible(true);
		lblSolPanel.setVisible(true);
		lblSagPanel.setVisible(true);
		lblBaslik.setVisible(true);
		lblGiris.setVisible(true);
		minutes = 0;
		miliseconds = 0;
		seconds = 0;
	}

	public void zamanDurdur()
	{
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}
}

