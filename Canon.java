public class Canon extends Item {
    public Canon(String colour, String position) {
        super(colour, position);
        point = 4.5;
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
                    setPosition("0" + gidilecek);
                } else{
                    setPosition("" + gidilecek);
                }
                throwit = false;
                break; 
                
            }
        }
        if (throwit)
        {
            throw new HataliHamleException();
        }
    }

    public void calcMove(Item[][] tiles) {
        moves.clear();
        int jumpedIndex = -1;
        int xy = positionIndex(this.positionInt);
        int row = xy / 10;
        int column = xy % 10;
        //System.out.println(xy +"!");

        //row down
        for(int i = row+1; i<10; i++)
        {
            if(tiles[i][column] != null)
            {
                jumpedIndex = i;
                break;
            }
        }
        
        for (int i = row + 1; i < 10; i++) {
           
            if(i == jumpedIndex)
            {
                for(int j = i+1; j<10; j++)
                {
                    if(tiles[j][column] != null && !(tiles[j][column].getColour().equals(this.getColour())))
                    {
                        moves.add(j*10+column);
                        break;
                    }
                    else if(tiles[j][column] != null && (tiles[j][column].getColour().equals(this.getColour())))
                    break;
                }
                break;
            }
             if ( tiles[i][column] == null) {
                moves.add(i * 10 + column);
            } 
            
            }

            // row up
            for(int i = row-1; i>-1; i--)
            {
                if(tiles[i][column] != null)
                {
                    jumpedIndex = i;
                    break;
                }
            }
    
            for (int i = row - 1; i >-1; i--) {
               
                if(i == jumpedIndex)
                {
                    for(int j = i-1; j>-1; j--)
                    {
                        if(tiles[j][column] != null && !tiles[j][column].getColour().equals(this.getColour()))
                        {
                            moves.add(j*10+column);
                            break;
                        }
                        else if(tiles[j][column] != null && (tiles[j][column].getColour().equals(this.getColour())))
                           break;
                    }
                    break;
                }
                 if ( tiles[i][column] == null) {
                    moves.add(i * 10 + column);
                } 
                
                }
                //column right

                for(int i = column+1; i<9; i++)
                {
                    if(tiles[row][i] != null)
                    {
                        jumpedIndex = i;
                        break;
                    }
                }
        
                for (int i = column + 1; i < 9; i++) {
                   
                    if(i == jumpedIndex)
                    {
                        for(int j = i+1; j<9; j++)
                        {
                            if(tiles[row][j] != null && !tiles[row][j].getColour().equals(this.getColour()))
                            {
                                moves.add(row*10+j);
                                break;
                            }
                            else if(tiles[row][j] != null && (tiles[row][j].getColour().equals(this.getColour())))
                                break;
                        }
                        break;
                    }

                    if (tiles[row][i] == null) 
                    {
                        moves.add(row*10+i);
                    } 
                    
                    }
                    // column left

                    for(int i = column-1; i>-1; i--)
                    {
                       if(tiles[row][i] != null)
                       {
                           jumpedIndex = i;
                           break;
                       }
                    }
        
                for (int i = column - 1; i >-1; i--) {
                   
                    if(i == jumpedIndex)
                    {
                        for(int j = i-1; j>-1; j--)
                        {
                            if(tiles[row][j] != null && !tiles[row][j].getColour().equals(this.getColour()))
                            {
                                moves.add(row*10+j);
                                break;
                            }
                            else if(tiles[row][j] != null && (tiles[row][j].getColour().equals(this.getColour())))
                                break;
                        }
                        break;
                    }

                    if ( tiles[row][i] == null) 
                    {
                       moves.add(row * 10 + i);
                    } 
                    
                    }

            }

        
        }
    


