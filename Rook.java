
public class Rook extends Item {
    public Rook(String colour, String position) {
        super(colour, position);
        point = 9;
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
            if (gidilecek == moves.get(i)) {
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
        {
            throw new HataliHamleException();
        }
            

    }

    public void calcMove(Item[][] tiles) {
        moves.clear();
        int xy = positionIndex(this.positionInt);
        int row = xy / 10;
        int column = xy % 10;
        for (int i = row + 1; i < 10; i++) {
            if (tiles[i][column] != null) 
            {
                if (tiles[i][column].getColour().equals(this.getColour())) 
                {
                    break;
                } else if (!tiles[i][column].getColour().equals(this.getColour())) 
                {
                    moves.add(i * 10 + column);
                    break;
                }
                
            }
            else if(tiles[i][column] == null)
            {
                moves.add(i*10+column);
            }

        }

        for (int i = row - 1; -1 < i; i--) {
            if (tiles[i][column] != null) 
            {
                if (tiles[i][column].getColour().equals(this.getColour())) 
                {
                    break;
                } else if (!tiles[i][column].getColour().equals(this.getColour())) 
                {
                    moves.add(i * 10 + column);
                    break;
                } 
                
            }
            else if(tiles[i][column] == null)
            {
                moves.add(i * 10 + column);
            }

        }

        for (int i = column + 1; i < 9; i++) {
            if (tiles[row][i] != null) 
            {
                if (tiles[row][i].getColour().equals(this.getColour())) 
                {
                    break;
                } 
                else if (!tiles[row][i].getColour().equals(this.getColour())) 
                {
                    moves.add(row * 10 + i);
                    break;
                }
            }
            else if(tiles[row][i] == null)
            {
                moves.add(row * 10 + i);
            }
        }

        for (int i = column - 1; -1 < i; i--)
         {
            if (tiles[row][i] != null) {
                if (tiles[row][i].getColour().equals(this.getColour())) 
                {
                    break;
                } else if (!tiles[row][i].getColour().equals(this.getColour())) {
                    moves.add(row * 10 + i);
                    break;
                } 
                break;
            }
            else if(tiles[row][i] == null)
            {
                moves.add(row * 10 + i);
            }

        }
    }
}
