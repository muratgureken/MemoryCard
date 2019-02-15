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
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import vectorList.VectorList;

public class MemoryCard extends JFrame{
	
	int maxCardType=21,cardNumber, anlikKartSayisi=0, toplamKartSayisi=0;
	String[] images = new String[maxCardType];
	private String[] userScores=new String[2];
	private JTextField txtUserName;
	private JPasswordField pswPassword;
	JButton btnYeniKullanici,btnGiris,btnDevam,btnYeniOyun;
	JLabel lblBaslik,lblSolPanel, lblSagPanel, lblKullancAdi, lblSifre,lblGiris;
	JButton[] array= new JButton[50];
	RegisterControl rg;
	RandomCardChoose rcc;
	DefineCardBoundaries dcb;
	VectorList<Integer> cardRegister=new VectorList<Integer>();
	
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
		btnKart1.setBounds(0, 0, 100, 150);
		//btnKart1.setIcon(new ImageIcon(getClass().getResource("/images/kedi.png")));
		btnKart1.setIcon(new ImageIcon(getClass().getResource("/images/zurafa.jpg")));
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
                                    anlikKartSayisi = anlikKartSayisi%2;
                                    anlikKartSayisi = anlikKartSayisi+1;
                                    //sleep(1000);
                                    toplamKartSayisi++;
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
				/*yeni kullaniciyi kayder*/
				/*kullanici onceden kaydolmus mu kontrol et*/
				/*kullanici adinda ":" isareti olmasin, varsa uyar.*/
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
		btnDevam.setBackground(new Color(210, 105, 30));
		btnDevam.setForeground(Color.WHITE);
		btnDevam.setFont(new Font("Jokerman", Font.BOLD, 20));
		btnDevam.setBounds(297, 478, 155, 109);
		btnDevam.setVisible(false);
		getContentPane().add(btnDevam);
		
		btnYeniOyun = new JButton("New Game");
		btnYeniOyun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnYeniOyun.setVisible(false);
				btnDevam.setVisible(false);
				lblSolPanel.setVisible(false);
				lblSagPanel.setVisible(false);
				lblBaslik.setVisible(false);
				lblGiris.setVisible(false);
				cardNumber = rcc.SelectCards(19, maxCardType);
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
                                        array[i].setIcon(new ImageIcon(getClass().getResource(images[rcc.getCards().getir(j)])));
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
		
		for(int i=0; i<array.length; i++)
		{
			array[i] = new JButton();
			//array[i].setFont(new Font("Tahoma", Font.PLAIN, 10));
			array[i].setBounds(i*10, 0, 100, 150);
			array[i].addActionListener(listener);
			array[i].setName("btnCard"+String.valueOf(i+1));
			//panel.add(array[i]);
			array[i].setVisible(false);
			getContentPane().add(array[i]);
		}
	}
	
	public void deneme2()
	{
	
	}

}

