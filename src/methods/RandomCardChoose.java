package methods;

import java.util.HashMap;
import java.util.Random;

import vectorList.VectorList;

public class RandomCardChoose {
	private HashMap<Integer, Integer> LevelCardNoMap = new HashMap<>();

	private int MaxCardNo=32;
	private VectorList<Integer> cards=new VectorList<Integer>();
	private VectorList<Integer> cardOrders=new VectorList<Integer>();

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
	}

	public int SelectCards(int level, int MaxCardTypeNo)
	{
		int randNumber, cardNumber=0;
		boolean state=false;

		cards.temizle();
		if(LevelCardNoMap.containsKey(level))
		{
			cardNumber = LevelCardNoMap.get(level);
			Random rand = new Random();
			randNumber = rand.nextInt(MaxCardTypeNo);
			cards.ekle(randNumber);

			for(int i=1; i<cardNumber; i++)
			{
				randNumber = rand.nextInt(MaxCardTypeNo);
				state = true;
				while(state)
				{
					if(cards.bul(randNumber)!=-1)
					{
						cards.ekle(randNumber);
						state= false;
					}
				}
			}
			
			cardOrders.temizle();
			randNumber = rand.nextInt(MaxCardNo);
			cardOrders.ekle(randNumber);

			for(int i=1; i<(cardNumber*2); i++)
			{
				randNumber = rand.nextInt(MaxCardNo);
				state = true;
				while(state)
				{
					if(cardOrders.bul(randNumber)!=-1)
					{
						cardOrders.ekle(randNumber);
						state= false;
					}
				}
			}

		}
		
		return cardNumber;
	}

	public VectorList<Integer> getCards() {
		return cards;
	}

	public void setCards(VectorList<Integer> cards) {
		this.cards = cards;
	}


}
