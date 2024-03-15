public class Knight extends Item {
    public Knight(String colour, String position) {
        super(colour, position);
        point = 4;
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
                } else
                    setPosition("" + gidilecek);
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
        if (row + 2 < 10 && tiles[row + 1][column] == null) {
            if (-1 < column - 1 && (tiles[row + 2][column - 1] == null || !tiles[row + 2][column - 1].getColour().equals(this.getColour()))) {
                moves.add((row + 2) * 10 + column - 1);
            }
            if (column + 1 < 9 && (tiles[row + 2][column + 1] == null|| !tiles[row + 2][column + 1].getColour().equals(this.getColour()))) {
                moves.add((row + 2) * 10 + column + 1);
            }
        }

        if (row - 2 > -1 && tiles[row - 1][column] == null) {
            if (-1 < column - 1 && (tiles[row - 2][column - 1] == null|| !tiles[row - 2][column - 1].getColour().equals(this.getColour()))) {
                moves.add((row - 2) * 10 + column - 1);
            }
            if (column + 1 < 9 && (tiles[row - 2][column + 1] == null
                    || !tiles[row - 2][column + 1].getColour().equals(this.getColour()))) {
                moves.add((row - 2) * 10 + column + 1);
            }
        }

        if (column + 2 < 9 && tiles[row][column + 1] == null) {
            if (-1 < row - 1 && (tiles[row - 1][column + 2] == null|| !tiles[row - 1][column + 2].getColour().equals(this.getColour()))) {
                moves.add((row - 1) * 10 + column + 2);
            }
            if (row + 1 < 10 && (tiles[row + 1][column + 2] == null
                    || !tiles[row + 1][column + 2].getColour().equals(this.getColour()))) {
                moves.add((row + 1) * 10 + column + 2);
            }
        }

        if (column - 2 > -1 && tiles[row][column - 1] == null) {
            if (-1 < row - 1 && (tiles[row - 1][column - 2] == null
                    || !tiles[row - 1][column - 2].getColour().equals(this.getColour()))) {
                moves.add((row - 1) * 10 + column - 2);
            }
            if (row + 1 < 10 && (tiles[row + 1][column - 2] == null
                    || !tiles[row + 1][column - 2].getColour().equals(this.getColour()))) {
                moves.add((row + 1) * 10 + column - 2);
            }
        }

    }
}
