package toplamaOyunu;

import java.util.Scanner;

public class Test 
{
	public static void main(String[] args) 
	{
		String gisim1, gisim2;
		
		Scanner s = new Scanner(System.in);
		System.out.print("Ilk oyuncunun ismini giriniz: ");
		String isim1 = s.nextLine();
		
		System.out.println();
		System.out.print("Ikinci oyuncunun ismini giriniz: ");
		String isim2 = s.nextLine();
		
		s.close();
		
		int sayi = (int)(Math.random()*2);
		if(sayi==0) {
			gisim1 = isim1;
			gisim2 = isim2;
		}else {
			gisim1 = isim2;
			gisim2 = isim1;
		}		
		new Oyun("Oyun", gisim1, gisim2);	
	}
}
