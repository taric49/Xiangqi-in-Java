public class Pawn extends Item 
{
    
    public Pawn(String colour, String position) {
        super(colour, position);
        point= 1;
    }
    

    public void setTiles(Item[][] tiles) {
        calcMove(tiles);
        this.tiles = tiles;
    }

    public void move(String destination) throws HataliHamleException {
        int gidilecek = positionIndex(destination);
        int pozisyon = positionIndex(this.positionInt);
        boolean throwit = true;
        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i) == gidilecek) {
                tiles[pozisyon / 10][pozisyon % 10] = null;
                if( tiles[gidilecek/10][gidilecek%10] != null)
                {
                    tiles[gidilecek/10][gidilecek%10].setRealPosition(-11);
                    tiles[gidilecek/10][gidilecek%10].positionInt = "";
                }
                tiles[gidilecek / 10][gidilecek % 10] = this;
                if (gidilecek < 10) {
                    setPosition("0" + gidilecek);
                } else {
                    setPosition("" + gidilecek);
                }
                throwit = false;
            }
        }
        if (throwit)
            throw new HataliHamleException();
    }

    public void calcMove(Item[][] tiles) {
        moves.clear();
        int xy = positionIndex(this.positionInt);
        int row = xy / 10;
        int column = xy % 10;
     

        if (getColour().equals("red")) {
            if (row > 4) {
                if (tiles[row - 1][column] == null || !tiles[row - 1][column].getColour().equals(this.getColour())) {
                    moves.add((row - 1) * 10 + column);
                }

            } else if (row < 5 && -1 < row) {
                if (-1<row-1 && tiles[row - 1][column] == null || -1<row-1 && !tiles[row - 1][column].getColour().equals(this.getColour())) {
                    moves.add((row - 1) * 10 + column);
                }
                if (column + 1 < 9 && tiles[row][column + 1] == null || column + 1 < 9 && !tiles[row][column + 1].getColour().equals(this.getColour())) {
                    moves.add(row * 10 + column + 1);
                }
                //System.out.println(row+" "+column);
                if ((column -1 >-1 && tiles[row][column - 1] == null) || (column -1 >-1 &&  !tiles[row][column - 1].getColour().equals(this.getColour()))) {
                    moves.add(row * 10 + column - 1);
                }
            }
        }
         else if(getColour().equals("white")) {
            if (row < 5) {
                if (tiles[row + 1][column] == null || !tiles[row + 1][column].getColour().equals(this.getColour())) {
                    moves.add((row + 1) * 10 + column);
                }

            } else if (row >= 5) {
                if ( row+1 < 10 && (tiles[row + 1][column] == null || row+1 < 10 &&   !tiles[row + 1][column].getColour().equals(this.getColour()))) {
                    moves.add((row + 1) * 10 + column);
                }
                if (column + 1 < 9 && tiles[row][column + 1] == null || column + 1 < 9 && !tiles[row][column + 1].getColour().equals(this.getColour())) {
                    moves.add(row * 10 + column + 1);
                }
                if (column -1> -1 && tiles[row][column - 1] == null || column -1> -1 &&  !tiles[row][column - 1].getColour().equals(this.getColour())) {
                    moves.add(row * 10 + column - 1);
                }
            }

        }
    }
}
