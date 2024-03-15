import java.io.Serializable;
import java.util.ArrayList;

public class Item extends AbstractItem implements Serializable {
	double point = 0;
	private String colour;
	String positionInt; // row and column representatıon with integers but data type is string
	Item[][] tiles; // board that item moves

	public String getPositionInt() {
		return positionInt;
	}

	public void setPosition(String position) {
		setRealPosition(positionIndex(position)); // sets the position that repersented with chararcter and number
		this.positionInt = position;
	}

	ArrayList<Integer> moves = new ArrayList<Integer>(); // stores the possible moves item can do at the time

	public String getColour() {
		return colour;
	}

	public void calcMove(Item[][] tiles) {
	}

	public void MOVE(String destination) throws HataliHamleException { // this move metotd designed for move unconditially
		int des = positionIndex(destination); // converting cordinates to int for use it in the 2D array
		int pos = positionIndex(positionInt);

		
		if (tiles[des / 10][des % 10] != null)
			tiles[des / 10][des % 10].setRealPosition(-11); // -11 means set real possition to xx for dead pieces
			
		tiles[pos/10][pos%10] = null;
		tiles[des / 10][des % 10] = this;
	}

    public void moveForLoad(String destination) // move metods for load methods
	{
		int des = realPositionConvert(destination);
		tiles[des/10][des%10] = this;
	}

	

	public Item[][] getTiles() {
		return tiles;
	}

	public void setTiles(Item[][] tiles) {
		this.tiles = tiles;
	}

	public void move(String destination) throws HataliHamleException {
	}

	public Item(String colour, String position) {
		this.positionInt = position;
		this.colour = colour;
		setRealPosition(positionInt);
	}

	public static int positionIndex(String s) {
		if (s.equals("xx")) {
			return -11;
		}
		int row;
		if (s.charAt(0) <= 'j' && s.charAt(0) >= 'a')
			row = -1 * ((int) s.charAt(0) - (int) 'j');
		else
			row = Integer.parseInt("" + s.charAt(0));

		int column = Integer.parseInt("" + s.charAt(1));
		int ans = row * 10 + column;
		return ans;

	}

	public static int realPositionConvert(String s) {
		int row = -1 * ((int) s.charAt(0) - (int) 'j');
		int column = Integer.parseInt("" + s.charAt(1)) - 1;
		return row * 10 + column;

	}

}
