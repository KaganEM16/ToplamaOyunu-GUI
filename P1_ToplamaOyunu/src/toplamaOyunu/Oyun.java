package toplamaOyunu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Oyun extends JFrame implements ActionListener
{
	String[] isimler;
	JLabel[] etiketler;
	JLabel[] canlar;
	int[] can = {100, 100};
	JLabel[] sorular;
	double cevap;
	JLabel[] cevapKontrol;
	JLabel sonucDurumu;
	JPanel[] paneller;
	JButton[] butonlar;
	JTextField[] alanlar;
	Color[] renkler = {Color.BLUE, Color.BLACK, Color.BLUE};
	boolean birMi = true;
	boolean basladiMi;
	String bosluk = "                    ";
	
	Oyun(String baslik, String isim1, String isim2)
	{
		super(baslik);
		isimler = new String[2];
		isimler[0] = isim1;
		isimler[1] = isim2;		
		int en = 600;
		int boy = 300;
		this.setSize(en, boy);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		paneller = new JPanel[3];
		for(int i=0 ; i<paneller.length ; i++) {
			paneller[i] = new JPanel();
			paneller[i].setBounds(i*en/3, 0, en/3, boy);
			paneller[i].setBackground(renkler[i]);			
			this.add(paneller[i]);
		}
		paneller[1].setLayout(new GridLayout(4, 1));
				
		etiketler = new JLabel[2];		
		for(int i=0 ; i<etiketler.length ; i++) {
			etiketler[i] = new JLabel("<html>" + isimler[i] + "<br></html>");
			etiketler[i].setForeground(Color.CYAN);
			etiketler[i].setFont(new Font("Ariel", Font.PLAIN, 30));			
		}		

		canlar = new JLabel[2];
		for(int i=0 ; i<canlar.length ; i++) {
			canlar[i] = new JLabel("<html>Puan: " + can[i] + "<br></html>");
			canlar[i].setForeground(Color.CYAN);
			canlar[i].setFont(new Font("Ariel", Font.PLAIN, 30));
		}
		paneller[0].add(etiketler[0]);
		paneller[0].add(canlar[0]);
		paneller[2].add(etiketler[1]);
		paneller[2].add(canlar[1]);
		
		sorular = new JLabel[2];
		for(int i=0 ; i<sorular.length ; i++) {
			sorular[i] = new JLabel(bosluk);
			sorular[i].setForeground(Color.WHITE);
		}
		paneller[0].add(sorular[0]);
		paneller[2].add(sorular[1]);
		
		alanlar = new JTextField[2];
		for(int i=0 ; i<alanlar.length ; i++) {
			alanlar[i] = new JTextField();
			alanlar[i].setPreferredSize(new Dimension(en/20, 20));
			alanlar[i].addActionListener(this);
		}
		paneller[0].add(alanlar[0]);
		paneller[2].add(alanlar[1]);
		
		String[] butonaYazilacakKelime = {"Gönder", "Başla", "Gönder", "Çıkış Yap"};
		butonlar = new JButton[4];
		for(int i=0 ; i<butonlar.length ; i++) {
			butonlar[i] = new JButton(butonaYazilacakKelime[i]);
			butonlar[i].addActionListener(this);
			butonlar[i].setPreferredSize(new Dimension(120, 40));			
		}
		paneller[0].add(butonlar[0]);
		paneller[1].add(butonlar[1]);
		paneller[1].add(butonlar[3]);
		paneller[2].add(butonlar[2]);
		
		cevapKontrol = new JLabel[2];
		for(int i=0 ; i<cevapKontrol.length ; i++) {
			cevapKontrol[i] = new JLabel(bosluk);
			cevapKontrol[i].setForeground(Color.WHITE);
			cevapKontrol[i].setFont(new Font("Serif", Font.PLAIN, 14));
		}
		paneller[0].add(cevapKontrol[0]);
		paneller[2].add(cevapKontrol[1]);
		
		sonucDurumu = new JLabel();
		sonucDurumu.setForeground(Color.WHITE);
		sonucDurumu.setFont(new Font("Ariel", Font.PLAIN, 15));
		paneller[1].add(sonucDurumu);
		
		this.setVisible(true);
	}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{		
			if(e.getSource() instanceof JButton) {
				JButton basilanButon = (JButton)e.getSource();
				if(basilanButon == butonlar[1] && basladiMi==false) {
					sorular[0].setText(rastgeleSoru());
					basladiMi=true;
				}else if(basilanButon == butonlar[0] || basilanButon == butonlar[2]) {
					if(basilanButon == butonlar[0] && birMi==true) {					
						if(can[1] > 0 && can[0]!=0 && alanlar[0].getText()!=bosluk) {
							cevapKontrol[1].setText("");
							if(Double.valueOf(alanlar[0].getText()) == cevap) {
								cevapKontrol[0].setText("Cevabınız doğru.");
								can[1]-=20;
								canlar[1].setText("<html>Puan: " + can[1] + "<br></html>");
							}
							else if(Double.valueOf(alanlar[0].getText()) != cevap) {
								cevapKontrol[0].setText("<html>Cevabınız yanlış canınız düşüyor<br>ve sıra rakibinize geçiyor.</html>");
								can[0]-=20;
								canlar[0].setText("<html>Puan: " + can[0] + "<br></html>");
							}						
							sorular[0].setText(bosluk);						
							alanlar[0].setText("");
							sorular[1].setText(rastgeleSoru());
							birMi=false;							
							if(can[0]==0) {					
								sonucDurumu.setText("   " + isimler[1] + " oyunu kazandı.");
								sorular[1].setText(bosluk);
							}
						}
				}else if(basilanButon == butonlar[2] && birMi==false) {					
					if(can[0] > 0 && can[1]!=0 && alanlar[1].getText()!=bosluk) {
						cevapKontrol[0].setText("");
						if(Double.valueOf(alanlar[1].getText()) == cevap) {
							cevapKontrol[1].setText("Cevabınız doğru.");
							can[0]-=20;
							canlar[0].setText("Puan: " + can[0]);
						}
						else if(Double.valueOf(alanlar[1].getText()) != cevap) {
							cevapKontrol[1].setText("<html>Cevabınız yanlış canınız düşüyor<br>ve sıra rakibinize geçiyor.</html>");
							can[1]-=20;
							canlar[1].setText("Puan: " + can[1]);
						}						
						sorular[1].setText(bosluk);						
						alanlar[1].setText("");
						sorular[0].setText(rastgeleSoru());
						birMi=true;							
						if(can[1]==0) {			
							sonucDurumu.setText("   " + isimler[0] + " oyunu kazandı.");
							sorular[0].setText(bosluk);
						}
					}
				}
			}else if(basilanButon == butonlar[3]){
					System.exit(EXIT_ON_CLOSE);
			}
		}
	}
		
	public String rastgeleSoru()
	{
		double sayi1 = rastgeleHasar();
		double sayi2 = rastgeleHasar();
		cevap = sayi1 + sayi2;
			
		return (sayi1 + " + " + sayi2 + " = ?");
	}
		
	public double rastgeleHasar()
	{
		return (int)(Math.random()*100);
	}
}