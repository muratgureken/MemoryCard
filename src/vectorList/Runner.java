package vectorList;

public class Runner {

	public static void main(String[] args) {
		/*VectorList h = new VectorList(5); */// Hesapla yanina koseli parantez koymadigimiz icin class bizden default olarak object bekliyor.
		VectorList<Integer> sayi = new VectorList<Integer>();
		/*int gelen = sayi.cikar();*/
		sayi.ekle(6,0);
		sayi.ekle(3,1);
		sayi.ekle(1,2);
		sayi.ekle(4,3);
		sayi.ekle(5,4);
		sayi.ekle(6);
		sayi.ekle(9);
		sayi.ekle(3);
		sayi.ekle(1);
		sayi.ekle(0);
		sayi.ekle(-5);
		sayi.ekle(-4);
		sayi.ekle(10);
		sayi.ekle(12);
		sayi.ekle(-2);
		sayi.ekle(7);
		sayi.ekle(20);
		sayi.ekle(2);
		sayi.ekle(-8);
		sayi.ekle(-11);
		sayi.ekle(100,4);
		System.out.println("boy:"+sayi.boyut());
		sayi.yazdir(1);
		System.out.println("maks: "+sayi.maksbul()+" ind:"+sayi.indis);
		
		sayi.sirala();
		System.out.println("siralanmis veri");
		sayi.yazdir(1);
		
		sayi.yazdir(1);
		sayi.terscevir();
		sayi.yazdir(1);
		Integer sayi2 = sayi.getir(1);
		
		System.out.println("eleman:"+sayi2);
		System.out.println(sayi.toString());
		System.out.println(sayi.icerir(8));	
		System.out.println(sayi.icerir(9));	
				
		sayi.kopyala(2);
		sayi.temizle();
		sayi.ekle(2);
		sayi.ekle(4);
		sayi.ekle(-1);
		sayi.ekle(-5);
		sayi.ekle(0);
		sayi.ekle(10);
		sayi.vtopla();
		sayi.yazdir(3);
		sayi.vstopla(50);
		sayi.yazdir(3);
		sayi.temizle();
		System.out.println("temizlendikten sonra");
		sayi.yazdir(1);
		
	}

}
