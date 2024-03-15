import java.io.Serializable;

public class Board extends AbstractBoard implements Serializable {

	Item[][] tiles;
	Item redGeneral;   // actually white general i messed up
	Item whiteGeneral;  // red general

	public Board() { // sets items on board assign themcolour and first positions

		tiles = new Item[10][9];

		tiles[0][0] = new Rook("white", "00");
		tiles[0][1] = new Knight("white", "01");
		tiles[0][2] = new Bishop("white", "02");
		tiles[0][3] = new Advisor("white", "03");
		tiles[0][4] = new General("white", "04");
		redGeneral = tiles[0][4];
		tiles[0][5] = new Advisor("white", "05");
		tiles[0][6] = new Bishop("white", "06");
		tiles[0][7] = new Knight("white", "07");
		tiles[0][8] = new Rook("white", "08");
		tiles[3][0] = new Pawn("white", "30");
		tiles[3][2] = new Pawn("white", "32");
		tiles[3][4] = new Pawn("white", "34");
		tiles[3][6] = new Pawn("white", "36");
		tiles[3][8] = new Pawn("white", "38");
		tiles[2][1] = new Canon("white", "21");
		tiles[2][7] = new Canon("white", "27");

		tiles[9][0] = new Rook("red", "90");
		tiles[9][1] = new Knight("red", "91");
		tiles[9][2] = new Bishop("red", "92");
		tiles[9][3] = new Advisor("red", "93");
		tiles[9][4] = new General("red", "94");
		whiteGeneral = tiles[9][4];
		tiles[9][5] = new Advisor("red", "95");
		tiles[9][6] = new Bishop("red", "96");
		tiles[9][7] = new Knight("red", "97");
		tiles[9][8] = new Rook("red", "98");
		tiles[6][0] = new Pawn("red", "60");
		tiles[6][2] = new Pawn("red", "62");
		tiles[6][4] = new Pawn("red", "64");
		tiles[6][6] = new Pawn("red", "66");
		tiles[6][8] = new Pawn("red", "68");
		tiles[7][1] = new Canon("red", "71");
		tiles[7][7] = new Canon("red", "77");

		tiles[0][0].setTiles(tiles);	// this method does 2 thing for items first assign their 2D item array  to this tiles and initialize the moves they can make  
		tiles[0][1].setTiles(tiles);
		tiles[0][2].setTiles(tiles);
		tiles[0][3].setTiles(tiles);
		tiles[0][4].setTiles(tiles);
		tiles[0][5].setTiles(tiles);
		tiles[0][6].setTiles(tiles);
		tiles[0][7].setTiles(tiles);
		tiles[0][8].setTiles(tiles);
		tiles[3][0].setTiles(tiles);
		tiles[3][2].setTiles(tiles);
		tiles[3][4].setTiles(tiles);
		tiles[3][6].setTiles(tiles);
		tiles[3][8].setTiles(tiles);
		tiles[2][1].setTiles(tiles);
		tiles[2][7].setTiles(tiles);
		tiles[9][0].setTiles(tiles);
		tiles[9][1].setTiles(tiles);
		tiles[9][2].setTiles(tiles);
		tiles[9][3].setTiles(tiles);
		tiles[9][4].setTiles(tiles);
		tiles[9][5].setTiles(tiles);
		tiles[9][6].setTiles(tiles);
		tiles[9][7].setTiles(tiles);
		tiles[9][8].setTiles(tiles);
		tiles[6][0].setTiles(tiles);
		tiles[6][2].setTiles(tiles);
		tiles[6][4].setTiles(tiles);
		tiles[6][6].setTiles(tiles);
		tiles[6][8].setTiles(tiles);
		tiles[7][1].setTiles(tiles);
		tiles[7][7].setTiles(tiles);

		SetItemsOnBoard();

	}

	public void SetItemsOnBoard() { // set items on 2D items array to 1D items
		Item[] items = new Item[32];
		int index = 0;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				if (tiles[i][j] != null) {
					items[index] = tiles[i][j];

					index++;
				}
			}
		}
		setItems(items);

	}

	public void print() {
	System.out.println("j\t"+str(tiles[0][0])+"--"+str(tiles[0][1])+"--"+str(tiles[0][2])+"--"+str(tiles[0][3])+"--"+str(tiles[0][4])+"--"+str(tiles[0][5])+"--"+str(tiles[0][6])+"--"+str(tiles[0][7])+"--"+str(tiles[0][8])+""); 
	System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
    System.out.println("i\t"+str(tiles[1][0])+"--"+str(tiles[1][1])+"--"+str(tiles[1][2])+"--"+str(tiles[1][3])+"--"+str(tiles[1][4])+"--"+str(tiles[1][5])+"--"+str(tiles[1][6])+"--"+str(tiles[1][7])+"--"+str(tiles[1][8])+"");
    System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
	System.out.println("h\t"+str(tiles[2][0])+"--"+str(tiles[2][1])+"--"+str(tiles[2][2])+"--"+str(tiles[2][3])+"--"+str(tiles[2][4])+"--"+str(tiles[2][5])+"--"+str(tiles[2][6])+"--"+str(tiles[2][7])+"--"+str(tiles[2][8])+"");
	System.out.println(" \t|  |  |  |  |  |  |  |  |");
	System.out.println("g\t"+str(tiles[3][0])+"--"+str(tiles[3][1])+"--"+str(tiles[3][2])+"--"+str(tiles[3][3])+"--"+str(tiles[3][4])+"--"+str(tiles[3][5])+"--"+str(tiles[3][6])+"--"+str(tiles[3][7])+"--"+str(tiles[3][8])+"");
	System.out.println(" \t|  |  |  |  |  |  |  |  |");
	System.out.println("f\t"+str(tiles[4][0])+"--"+str(tiles[4][1])+"--"+str(tiles[4][2])+"--"+str(tiles[4][3])+"--"+str(tiles[4][4])+"--"+str(tiles[4][5])+"--"+str(tiles[4][6])+"--"+str(tiles[4][7])+"--"+str(tiles[4][8])+"");
	System.out.println(" \t|                       |");
	System.out.println("e\t"+str(tiles[5][0])+"--"+str(tiles[5][1])+"--"+str(tiles[5][2])+"--"+str(tiles[5][3])+"--"+str(tiles[5][4])+"--"+str(tiles[5][5])+"--"+str(tiles[5][6])+"--"+str(tiles[5][7])+"--"+str(tiles[5][8])+"");
	System.out.println(" \t|  |  |  |  |  |  |  |  |");
	System.out.println("d\t"+str(tiles[6][0])+"--"+str(tiles[6][1])+"--"+str(tiles[6][2])+"--"+str(tiles[6][3])+"--"+str(tiles[6][4])+"--"+str(tiles[6][5])+"--"+str(tiles[6][6])+"--"+str(tiles[6][7])+"--"+str(tiles[6][8])+"");
	System.out.println(" \t|  |  |  |  |  |  |  |  |");
	System.out.println("c\t"+str(tiles[7][0])+"--"+str(tiles[7][1])+"--"+str(tiles[7][2])+"--"+str(tiles[7][3])+"--"+str(tiles[7][4])+"--"+str(tiles[7][5])+"--"+str(tiles[7][6])+"--"+str(tiles[7][7])+"--"+str(tiles[7][8])+"");
	System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
	System.out.println("b\t"+str(tiles[8][0])+"--"+str(tiles[8][1])+"--"+str(tiles[8][2])+"--"+str(tiles[8][3])+"--"+str(tiles[8][4])+"--"+str(tiles[8][5])+"--"+str(tiles[8][6])+"--"+str(tiles[8][7])+"--"+str(tiles[8][8])+"");
	System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
	System.out.println("a\t"+str(tiles[9][0])+"--"+str(tiles[9][1])+"--"+str(tiles[9][2])+"--"+str(tiles[9][3])+"--"+str(tiles[9][4])+"--"+str(tiles[9][5])+"--"+str(tiles[9][6])+"--"+str(tiles[9][7])+"--"+str(tiles[9][8])+"");
	System.out.println();
	System.out.println(" \t1--2--3--4--5--6--7--8--9");
	}

	public static String str(Item item) // Makes string represantation of items
	{
		if(item == null)
		{
			return "-";
		}

		String str = "";
		switch(item.getClass().getName())
		{
			case "Rook":
			str += "K";
			break;
			case "Pawn":
			str += "P";
			break;
			case "Bishop":
			str += "F";
			break;
			case "Knight":
			str += "A";
			break;
			case "Advisor":
			str += "V";
			break;
			case "General":
			str += "Ş";
			break;
			case "Canon":
			str += "T";
			break;

		}
		if(item.getColour().equals("red"))
		{
		    return str.toLowerCase();
		}
		else
		{
			return str;
		}
	}

}
