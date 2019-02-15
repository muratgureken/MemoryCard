package methods;

import java.util.HashMap;
import java.util.Random;

import vectorList.VectorList;

public class RandomCardChoose {
	private HashMap<Integer, Integer> LevelCardNoMap = new HashMap<>();

	private int MaxCardNo=32;
	private VectorList<Integer> cards=new VectorList<Integer>();
	private VectorList<Integer> cardOrders=new VectorList<Integer>();
	private VectorList<Integer> cardIndis=new VectorList<Integer>();
	private VectorList<Integer> cardTypes=new VectorList<Integer>();
	
	public VectorList<Integer> getCardOrders() {
		return cardOrders;
	}

	public void setCardOrders(VectorList<Integer> cardOrders) {
		this.cardOrders = cardOrders;
	}

	public RandomCardChoose() {
		LevelCardNoMap.put(1, 2);
		LevelCardNoMap.put(2, 3);
		LevelCardNoMap.put(3, 3);
		LevelCardNoMap.put(4, 4);
		LevelCardNoMap.put(5, 4);
		LevelCardNoMap.put(6, 6);
		LevelCardNoMap.put(7, 6);
		LevelCardNoMap.put(8, 8);
		LevelCardNoMap.put(9, 8);
		LevelCardNoMap.put(10, 9);
		LevelCardNoMap.put(11, 9);
		LevelCardNoMap.put(12, 10);
		LevelCardNoMap.put(13, 10);
		LevelCardNoMap.put(14, 12);
		LevelCardNoMap.put(15, 12);
		LevelCardNoMap.put(16, 14);
		LevelCardNoMap.put(17, 14);
		LevelCardNoMap.put(18, 16);
		LevelCardNoMap.put(19, 16);
		
		cardIndis.ekle(0);
		cardIndis.ekle(1);
		cardIndis.ekle(2);
		cardIndis.ekle(3);
		cardIndis.ekle(4);
		cardIndis.ekle(5);
		cardIndis.ekle(6);
		cardIndis.ekle(7);
		cardIndis.ekle(8);
		cardIndis.ekle(9);
		cardIndis.ekle(10);
		cardIndis.ekle(11);
		cardIndis.ekle(12);
		cardIndis.ekle(13);
		cardIndis.ekle(14);
		cardIndis.ekle(15);
		cardIndis.ekle(16);
		cardIndis.ekle(17);
		cardIndis.ekle(18);
		cardIndis.ekle(19);
		cardIndis.ekle(20);
		cardIndis.ekle(21);
		cardIndis.ekle(22);
		cardIndis.ekle(23);
		cardIndis.ekle(24);
		cardIndis.ekle(25);
		cardIndis.ekle(26);
		cardIndis.ekle(27);
		cardIndis.ekle(28);
		cardIndis.ekle(29);
		cardIndis.ekle(30);
		cardIndis.ekle(31);
	}

	public int SelectCards(int level, int MaxCardTypeNo)
	{
		int randNumber, cardNumber=0;
		boolean state=false;
		Random rand = new Random();

		if(LevelCardNoMap.containsKey(level))
		{
			cardIndisYenile();
                        cards.temizle();
                        cardNumber = LevelCardNoMap.get(level);
			randNumber = rand.nextInt(MaxCardTypeNo);
			cards.ekle(randNumber);
                        cardIndis.cikar(randNumber);
			System.out.println("rand card : "+randNumber);

			for(int i=1; i<cardNumber; i++)
			{
				randNumber = rand.nextInt(MaxCardTypeNo-i);
                                cards.ekle(cardIndis.getir(randNumber));
                                cardIndis.cikar(randNumber);
                                System.out.println("rand card : "+cardIndis.getir(randNumber));
			}
			
                        cardIndisYenile();
			cardOrders.temizle();
			randNumber = rand.nextInt(cardNumber*2);
			cardOrders.ekle(randNumber);
			cardIndis.cikar(randNumber);
			System.out.println("rand order : "+randNumber);

			for(int i=1; i<(cardNumber*2); i++)
			{
				randNumber = rand.nextInt(cardNumber*2-i);
				cardOrders.ekle(cardIndis.getir(randNumber));
				cardIndis.cikar(randNumber);
				System.out.println("rand order : "+cardIndis.getir(randNumber));
			}
		}
		System.out.println("orders");
		cardOrders.yazdir(1);
		return cardNumber;
	}

        private void cardIndisYenile()
        {
            cardIndis.temizle();
            		cardIndis.ekle(0);
		cardIndis.ekle(1);
		cardIndis.ekle(2);
		cardIndis.ekle(3);
		cardIndis.ekle(4);
		cardIndis.ekle(5);
		cardIndis.ekle(6);
		cardIndis.ekle(7);
		cardIndis.ekle(8);
		cardIndis.ekle(9);
		cardIndis.ekle(10);
		cardIndis.ekle(11);
		cardIndis.ekle(12);
		cardIndis.ekle(13);
		cardIndis.ekle(14);
		cardIndis.ekle(15);
		cardIndis.ekle(16);
		cardIndis.ekle(17);
		cardIndis.ekle(18);
		cardIndis.ekle(19);
		cardIndis.ekle(20);
		cardIndis.ekle(21);
		cardIndis.ekle(22);
		cardIndis.ekle(23);
		cardIndis.ekle(24);
		cardIndis.ekle(25);
		cardIndis.ekle(26);
		cardIndis.ekle(27);
		cardIndis.ekle(28);
		cardIndis.ekle(29);
		cardIndis.ekle(30);
		cardIndis.ekle(31);
        }
        
        public VectorList<Integer> getCards() {
		return cards;
	}

	public void setCards(VectorList<Integer> cards) {
		this.cards = cards;
	}


}
