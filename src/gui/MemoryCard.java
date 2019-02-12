package gui;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import methods.RegisterControl;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class MemoryCard extends JFrame{
	
	int size=21;
	String[] images = new String[size];
	static int[] userScores=new int[2];
	private JTextField txtUserName;
	private JPasswordField psswPassword;
	
	public MemoryCard() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		RegisterControl rg = new RegisterControl();
		rg.NewUserControl("zeytin", "10");
		
		/*DEFINE IMAGES*/
		images[0] = "ananas.jpg";
		images[1] = "araba.jpg";
		images[2] = "aslankral.jpg";
		images[3] = "ataturk.jpg";
		images[4] = "besiktas.png";
		images[5] = "cilek.jpg";
		images[6] = "dart.png";
		images[7] = "donald.jpg";
		images[8] = "federer.jpg";
		images[9] = "honda.png";
		images[10] = "kardanadam.jpg";
		images[11] = "kiraz.jpg";
		images[12] = "kopek.jpg";
		images[13] = "kurbaga.jpg";
		images[14] = "lamborg.jpg";
		images[15] = "nba.jpg";
		images[16] = "odtu.png";
		images[17] = "tatilci.jpg";
		images[18] = "temelreis.png";
		images[19] = "toyota.jpg";		
		images[20] = "zurafa.jpg";
		
		JButton btnKart1 = new JButton("");
		//btnKart1.setSelectedIcon(new ImageIcon(MemoryCard.class.getResource("/images/kedi.png")));
		btnKart1.setBounds(0, 0, 100, 150);
		//btnKart1.setIcon(new ImageIcon(getClass().getResource("/images/kedi.png")));
		btnKart1.setIcon(new ImageIcon(getClass().getResource("/images/zurafa.jpg")));
		getContentPane().add(btnKart1);
		btnKart1.setVisible(false);
		getContentPane().setLayout(null);
		
		JLabel lblGiris = new JLabel("");
		lblGiris.setBounds(374, 193, 297, 246);
		lblGiris.setIcon(new ImageIcon(getClass().getResource("/images/anasayfa.jpg")));
		getContentPane().add(lblGiris);
		
		JLabel lblBaslik = new JLabel("MEMORY CARDS");
		lblBaslik.setForeground(Color.RED);
		lblBaslik.setFont(new Font("Jokerman", Font.PLAIN, 30));
		lblBaslik.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaslik.setBounds(354, 146, 299, 43);
		getContentPane().add(lblBaslik);
		
		JLabel lblKullancAdi = new JLabel("Username");
		lblKullancAdi.setForeground(Color.RED);
		lblKullancAdi.setFont(new Font("Harrington", Font.BOLD, 14));
		lblKullancAdi.setBounds(274, 506, 100, 14);
		getContentPane().add(lblKullancAdi);
		
		JLabel lblSifre = new JLabel("Password");
		lblSifre.setForeground(Color.RED);
		lblSifre.setFont(new Font("Harrington", Font.BOLD, 14));
		lblSifre.setBounds(274, 543, 92, 14);
		getContentPane().add(lblSifre);
		
		JButton btnGiris = new JButton("Log In");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*LOG IN*/
				/*kullanicinin ad-sifre kombinasyonu txt dosyasinda var mi kontrol et*/
				/*kullanici adinda ":" isareti olmasin, varsa uyar*/
				txtUserName.getText();
			}
		});
		btnGiris.setForeground(Color.RED);
		btnGiris.setFont(new Font("Harrington", Font.BOLD, 14));
		btnGiris.setBounds(539, 503, 144, 23);
		getContentPane().add(btnGiris);
		
		JButton btnYeniKullanici = new JButton("New User");
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
		
		psswPassword = new JPasswordField();
		psswPassword.setBounds(399, 541, 116, 20);
		getContentPane().add(psswPassword);
	}
}
