public class General extends Item {

    public General(String colour, String position) {
        super(colour, position);

    }

    public void setTiles(Item[][] tiles) {
        calcMove(tiles);
        this.tiles = tiles;
    }

    public void move(String destination) throws HataliHamleException {
        int gidilecek = positionIndex(destination);
        int pozisyon = positionIndex(this.positionInt);
        boolean throwit = true;

        for (int i = 0; i < moves.size(); i++) 
        {
            if (gidilecek == moves.get(i)) 
            {
                tiles[pozisyon / 10][pozisyon % 10] = null;

                if( tiles[gidilecek/10][gidilecek%10] != null)
                {
                    tiles[gidilecek/10][gidilecek%10].setRealPosition(-11);
                    tiles[gidilecek/10][gidilecek%10].positionInt = "";
                }
                tiles[gidilecek / 10][gidilecek % 10] = this;
                if (gidilecek < 10) {
                    this.setPosition("0" + gidilecek);
                } else
                    this.setPosition("" + gidilecek);
                throwit = false;
            }
        }
        if (throwit) {
            throw new HataliHamleException();
        }
    }

    public void calcMove(Item[][] tiles) {
        moves.clear();
        int xy = positionIndex(this.positionInt);
        int row = xy / 10;
        int column = xy % 10;

       
        if(this.getColour().equals("white"))
        {
            if(row<3 && row >-1 && column <6 && column >2)
            {
                if(row+1 < 3 && (tiles[row+1][column] == null || !tiles[row+1][column].getColour().equals(this.getColour())))
                {
                    moves.add((row+1)*10 +column);
                }

                if(row-1 >-1 && (tiles[row-1][column] == null || !tiles[row-1][column].getColour().equals(this.getColour())))
                {
                    moves.add((row-1)*10+ column);
                }
                
                if(column+1 <6 && (tiles[row][column+1] == null || !tiles[row][column+1].getColour().equals(this.getColour())))
                {
                    moves.add((row)*10 +column+1);
                }

                if(column-1 > 2 && (tiles[row][column-1] == null || !tiles[row][column-1].getColour().equals(this.getColour())))
                {
                    moves.add((row)*10 +column-1);
                }


            }
        }
        else
        {
            if(row<10 && row > 6 && column <6 && column > 2)
            {
                if(row+1 < 10 && (tiles[row+1][column] == null || !tiles[row+1][column].getColour().equals(this.getColour())))
                {
                    moves.add((row+1)*10 +column);
                }

                if(row-1 >6 && (tiles[row-1][column] == null || !tiles[row-1][column].getColour().equals(this.getColour())))
                {
                    moves.add((row-1)*10+ column);
                }
                
                if(column+1 <6 && (tiles[row][column+1] == null || !tiles[row][column+1].getColour().equals(this.getColour())))
                {
                    moves.add((row)*10 +column+1);
                }

                if(column-1 > 2 && (tiles[row][column-1] == null || !tiles[row][column-1].getColour().equals(this.getColour())))
                {
                    moves.add((row)*10 +column-1);
                }
            }
        }

    }
}
