package methods;

import java.util.HashMap;

import vectorList.VectorList;

public class DefineCardBoundaries {
	private HashMap<Integer, Integer> RowNumber = new HashMap<>();
	private HashMap<Integer, Integer> ColumnNumber = new HashMap<>();
	private HashMap<Integer, Integer> FirstElementX = new HashMap<>();
	private HashMap<Integer, Integer> FirstElementY = new HashMap<>();
	private VectorList<Integer> boundaries=new VectorList<Integer>();

	public DefineCardBoundaries() {
		RowNumber.put(2, 2);
		RowNumber.put(3, 2);
		RowNumber.put(4, 2);
		RowNumber.put(6, 3);
		RowNumber.put(8, 4);
		RowNumber.put(9, 3);
		RowNumber.put(10, 4);
		RowNumber.put(12, 4);
		RowNumber.put(14, 4);
		RowNumber.put(16, 4);

		ColumnNumber.put(2, 2);
		ColumnNumber.put(3, 3);
		ColumnNumber.put(4, 4);
		ColumnNumber.put(6, 4);
		ColumnNumber.put(8, 4);
		ColumnNumber.put(9, 6);
		ColumnNumber.put(10, 5);
		ColumnNumber.put(12, 6);
		ColumnNumber.put(14, 7);
		ColumnNumber.put(16, 8);

		FirstElementX.put(2, 415);
		FirstElementX.put(3, 360);
		FirstElementX.put(4, 305);
		FirstElementX.put(6, 305);
		FirstElementX.put(8, 305);
		FirstElementX.put(9, 195);
		FirstElementX.put(10, 250);
		FirstElementX.put(12, 195);
		FirstElementX.put(14, 140);
		FirstElementX.put(16, 85);

		FirstElementY.put(2, 270);
		FirstElementY.put(3, 270);
		FirstElementY.put(4, 270);
		FirstElementY.put(6, 190);
		FirstElementY.put(8, 110);
		FirstElementY.put(9, 190);
		FirstElementY.put(10, 110);
		FirstElementY.put(12, 110);
		FirstElementY.put(14, 110);
		FirstElementY.put(16, 110);
	}
	
	public VectorList<Integer> getBoundaries() {
		return boundaries;
	}

	public void setBoundaries(VectorList<Integer> boundaries) {
		this.boundaries = boundaries;
	}

	public void CalculateBoundaries(int cardNumber)
	{
		int rowNo, columnNo, posX, posY, posX1, posY1;
		
		boundaries.temizle();
		if(RowNumber.containsKey(cardNumber))
		{
			rowNo = RowNumber.get(cardNumber);
			columnNo = ColumnNumber.get(cardNumber);
			posX1 = FirstElementX.get(cardNumber);
			posY1 = FirstElementY.get(cardNumber);
			System.out.println("row:"+rowNo+" col:"+columnNo);	
			for(int i=0; i<rowNo; i++)
			{
				posX = posX1 + 110*i;
				for(int j=0; j<columnNo; j++)
				{
					posY = posY1 + 160*j;
					boundaries.ekle(posX);
					boundaries.ekle(posY);
				}
			}
		}
	}
}
