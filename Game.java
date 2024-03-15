import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game extends AbstractGame {

    boolean siraKirmizida = true; // decide which players turn

    public Game(String A, String B) {
        super(A, B);
        setBoard(new Board());

    }

    public boolean isChecked(Item general) { // controls the general is checked
        boolean face = true;
        //Item item = new Item("blue","00");

        if (board.redGeneral.positionInt.charAt(1) == board.whiteGeneral.positionInt.charAt(1)) {
            int red = Item.positionIndex(board.whiteGeneral.positionInt);
            int white = Item.positionIndex(board.redGeneral.positionInt); // generals can  not face each other if there are no any item between theme
            for (int i = white / 10 + 1; i < red / 10; i++) {
                if (board.tiles[i][red % 10] != null) {
                    face = false;
                }
            }
        } else {
            face = false;
        }

        for (int i = 0; i < getBoard().items.length; i++) {
            if (!getBoard().items[i].position.equals("xx")
                    && !(getBoard().items[i].getColour().equals(general.getColour()))) {
                for (int k = 0; k < getBoard().items[i].moves.size(); k++) {
                    if (getBoard().items[i].moves.get(k) == Item.positionIndex(general.positionInt)) {
                        return true;
                    }
                }
            }
        }
        if (face) {
            //System.out.println(item.getClass().getName() + "  "+item.getColour());
            return true;

        } else {
            return false;
        }
    }

    void play(String from, String to) {

        if (from.length() == 2 &&  to.length() == 2 && to.charAt(0) <= 'j' && to.charAt(0) >= 'a'&& from.charAt(0) >= 'a' && from.charAt(0) <= 'j') { // control for right input 
            int x = Item.realPositionConvert(from);
            int y = Item.realPositionConvert(to);
            to = "" + to.charAt(0) + (Integer.parseInt("" + to.charAt(1)) - 1); // converting the column input to 1 decremented version because first I code accrding to that 
            from = "" + from.charAt(0) + (Integer.parseInt("" + from.charAt(1)) - 1);

            int columnValue = Integer.parseInt("" + to.charAt(1));
            if (columnValue < 9 && columnValue > -1)
                try {
                    if (siraKirmizida && (getBoard().tiles[x / 10][x % 10] != null // checking for turn items 
                            && getBoard().tiles[x / 10][x % 10].getColour().equals("red"))) {

                        if (to.charAt(0) <= 'j' && to.charAt(0) >= 'a' && columnValue < 9 && columnValue > -1) {

                            Item temp = getBoard().tiles[y / 10][y % 10]; // assiging destination tile  to temp incase we are doing a wrong move and we have to place pieces back 
                            getBoard().tiles[x / 10][x % 10].move(to); // makes the move yeeehaa

                            for (int j = 0; j < getBoard().items.length; j++) { // calculate every move for items on board
                                if (!getBoard().items[j].position.equals("xx")) {
                                    getBoard().items[j].calcMove(getBoard().tiles);
                                }
                            }
                            siraKirmizida = false; // change the turn

                            if (isChecked(board.whiteGeneral)) { // if move we make put our general danger or ignore the danger it is illegal move revers back 
                              
                                getBoard().tiles[y / 10][y % 10].MOVE(from); //diffrent move method
                                getBoard().tiles[y / 10][y % 10] = temp; 

                                if (temp != null)
                                    temp.setPosition("" + y / 10 + "" + y % 10);

                                getBoard().tiles[x/10][x%10].setPosition(""+x/10 + x%10);
                                for (int a = 0; a < getBoard().items.length; a++) {
                                    if (!getBoard().items[a].position.equals("xx")) {
                                        getBoard().items[a].calcMove(getBoard().tiles);
                                    }
                                }
                                siraKirmizida = true;
                                throw new HataliHamleException();
                            }

                            float blackP = 0f;
                            float redP = 0f;

                            for (int i = 0; i < getBoard().items.length; i++) { // calculate point of players

                                if (getBoard().items[i].getPosition().equals("xx")
                                        && getBoard().items[i].getColour().equals("red")) {
                                    blackP += getBoard().items[i].point;
                                } else if (getBoard().items[i].getPosition().equals("xx")
                                        && getBoard().items[i].getColour().equals("white")) {
                                    redP += getBoard().items[i].point;
                                }

                            }

                            black.puan = blackP;
                            red.puan = redP;

                            if (isChecked(board.redGeneral)) { // if after the move opposite colour general checked then start controlling to checkmate
                                boolean isCheckMate = true;
                                boolean canDo = true;
                                Item[][] tiles = getBoard().tiles;
                                // this method do every possible move by checked colour and controls if danger dismiss if it dosent in any move then its checkmate
                                for (int row = 0; row < 10; row++) 
                                {
                                    for (int column = 0; column < 9; column++)
                                    {
                                        
                                        if (tiles[row][column] != null && tiles[row][column].getColour().equals("white") && !tiles[row][column].position.equals("xx")) 
                                        {

                                            for (int i = 0; i < tiles[row][column].moves.size(); i++) 
                                            {
                                               
                                                canDo = true;
                                                String endCord = cordinateDecoder(tiles[row][column].moves.get(i));

                                                int intEndCord = tiles[row][column].moves.get(i);

                                                String startCord = cordinateDecoder(Integer.parseInt(tiles[row][column].positionInt));

                                                String intStartCord = tiles[row][column].positionInt;

                                                Item tmp = tiles[intEndCord / 10][intEndCord % 10];

                                                tiles[row][column].move(endCord);

                                                for (int a = 0; a < getBoard().items.length; a++) {
                                                    if (!getBoard().items[a].position.equals("xx")) {
                                                        getBoard().items[a].calcMove(getBoard().tiles);
                                                    }
                                                }
                                                if (isChecked(board.redGeneral)) 
                                                {

                                                    canDo = false;
                                                }
                                                tiles[intEndCord / 10][intEndCord % 10].setPosition(intStartCord);
                                                tiles[intEndCord / 10][intEndCord % 10].MOVE(startCord);
                                                tiles[intEndCord / 10][intEndCord % 10] = tmp;
                                                if (tmp != null) 
                                                {
                                                    if (intEndCord < 10)
                                                        getBoard().tiles[intEndCord / 10][intEndCord % 10]
                                                                .setPosition("0" + intEndCord);
                                                    else
                                                        getBoard().tiles[intEndCord / 10][intEndCord % 10]
                                                                .setPosition("" + intEndCord);
                                                }

                                                for (int a = 0; a < getBoard().items.length; a++) 
                                                {
                                                    if (!getBoard().items[a].position.equals("xx")) 
                                                    {
                                                        getBoard().items[a].calcMove(getBoard().tiles);
                                                    }
                                                }
                                                if (canDo) 
                                                {
                                                    isCheckMate = false;
                                                    break;
                                                }
                                            }
                                            if (canDo &&  isCheckMate == false) 
                                            {
                                                break;
                                            }
                                        }
                                        if (canDo &&  isCheckMate == false) 
                                        {
                                            break;
                                        }

                                    }
                                    if (canDo && isCheckMate == false) 
                                    {
                                        break;
                                    }

                                }
                                if (isCheckMate) {
                                    System.out.println("ŞAH MAT! "+red.name+" oyunu kazandı. "+red.name+"'in puanı: " + red.puan
                                            + ", "+black.name+"'nin puanı: " + black.puan);
                                }
                            }

                            
                        } else {
                            throw new HataliHamleException();
                        }
                        // doing same things for white
                    } else if (!siraKirmizida && getBoard().tiles[x / 10][x % 10] != null
                            && !getBoard().tiles[x / 10][x % 10].position.equals("xx")
                            && getBoard().tiles[x / 10][x % 10].getColour().equals("white")) {

                        if (to.charAt(0) <= 'j' && to.charAt(0) >= 'a' && columnValue < 9 && columnValue > -1) {
                            Item temp = getBoard().tiles[y / 10][y % 10];
                            getBoard().tiles[x / 10][x % 10].move(to);

                            for (int j = 0; j < getBoard().items.length; j++) {
                                if (!getBoard().items[j].position.equals("xx")) {
                                    getBoard().items[j].calcMove(getBoard().tiles);
                                }
                            }
                            siraKirmizida = true;

                            if (isChecked(board.redGeneral)) {
                              
                                getBoard().tiles[y / 10][y % 10].MOVE(from);
                                getBoard().tiles[y / 10][y % 10] = temp;

                                if (temp != null)
                                    temp.setPosition("" + y / 10 + "" + y % 10);

                                    getBoard().tiles[x/10][x%10].setPosition(""+x/10 + x%10);

                                for (int a = 0; a < getBoard().items.length; a++) {
                                    if (!getBoard().items[a].position.equals("xx")) {
                                        getBoard().items[a].calcMove(getBoard().tiles);
                                    }
                                }
                                siraKirmizida = false;
                                throw new HataliHamleException();
                            }

                            float blackP = 0f;
                            float redP = 0f;

                            for (int i = 0; i < getBoard().items.length; i++) {

                                if (getBoard().items[i].getPosition().equals("xx")
                                        && getBoard().items[i].getColour().equals("red")) {
                                    blackP += getBoard().items[i].point;
                                } else if (getBoard().items[i].getPosition().equals("xx")
                                        && getBoard().items[i].getColour().equals("white")) {
                                    redP += getBoard().items[i].point;
                                }

                            }

                            black.puan = blackP;
                            red.puan = redP;

                            if (isChecked(board.whiteGeneral)) 
                            {
                                boolean isCheckMate = true;
                                boolean canDo = true;
                                Item[][] tiles = getBoard().tiles;

                                for (int row = 0; row < 10; row++) 
                                {
                                    
                                    for (int column = 0; column < 9; column++) 
                                    {
                                        
                                        if (tiles[row][column] != null && tiles[row][column].getColour().equals("red") && !tiles[row][column].position.equals("xx")) 
                                        {
                                            for (int i = 0; i < tiles[row][column].moves.size(); i++) 
                                            {
                                                canDo = true;
                                                String endCord = cordinateDecoder(tiles[row][column].moves.get(i));
                                                int intEndCord = tiles[row][column].moves.get(i);
                                                String startCord = cordinateDecoder(Integer.parseInt(tiles[row][column].positionInt));
                                                String intStartCord = tiles[row][column].positionInt;
                                                Item tmp = tiles[intEndCord / 10][intEndCord % 10];

                                                tiles[row][column].move(endCord);
                                               
                                               
                                                for (int a = 0; a < getBoard().items.length; a++) {
                                                    if (!getBoard().items[a].position.equals("xx")) {
                                                        getBoard().items[a].calcMove(getBoard().tiles);
                                                    }
                                                }
                                                if (isChecked(board.whiteGeneral)) 
                                                {
                                                    canDo = false;
                                                }
                                                tiles[intEndCord / 10][intEndCord % 10].setPosition(intStartCord);
                                                tiles[intEndCord / 10][intEndCord % 10].MOVE(startCord);
                                                tiles[intEndCord / 10][intEndCord % 10] = tmp;

                                                if (tmp != null) 
                                                {
                                                    if (intEndCord < 10)
                                                        getBoard().tiles[intEndCord / 10][intEndCord % 10].setPosition("0" + intEndCord);
                                                    else
                                                        getBoard().tiles[intEndCord / 10][intEndCord % 10].setPosition("" + intEndCord);
                                                }

                                                for (int a = 0; a < getBoard().items.length; a++) 
                                                {
                                                    if (!getBoard().items[a].position.equals("xx"))
                                                    {
                                                        getBoard().items[a].calcMove(getBoard().tiles);
                                                    }
                                                }
                                                if (canDo)
                                                {
                                                    isCheckMate = false;
                                                    break;
                                                }
                                            }
                                        }
                                        if (canDo && isCheckMate == false) 
                                        {
                                            break;
                                        }

                                    }
                                    if (canDo && isCheckMate == false) 
                                    {
                                        break;
                                    }
                                }

                                if (isCheckMate) 
                                {
                                    System.out.println("ŞAH MAT! "+black.name+" oyunu kazandı. "+black.name+"'in puanı: " + black.puan
                                            + ", "+red.name+"'nin puanı: " + red.puan);
                                }
                            }

                        } else {
                            throw new HataliHamleException();
                        }
                    } else {
                        throw new HataliHamleException();
                    }

                } catch (HataliHamleException e) {
                    System.out.println(e.mesaj);
                }
        } else {
            System.out.println("hatali hareket");
        }

        for (int i = 0; i < board.getItems().length; i++) {
            if (!board.getItems()[i].position.equals("xx"))
                board.getItems()[i].calcMove(board.tiles);
        }

    }

    public String cordinateDecoder(int a) { // decode to original cordinate 
        String str = "";
        switch (a / 10) {
            case 9:
                str += "a";
                break;
            case 8:
                str += "b";
                break;
            case 7:
                str += "c";
                break;
            case 6:
                str += "d";
                break;
            case 5:
                str += "e";
                break;
            case 4:
                str += "f";
                break;
            case 3:
                str += "g";
                break;
            case 2:
                str += "h";
                break;
            case 1:
                str += "i";
                break;
            case 0:
                str += "j";
                break;
        }

        switch (a % 10) {
            case 9:
                str += "9";
                break;
            case 8:
                str += "8";
                break;
            case 7:
                str += "7";
                break;
            case 6:
                str += "6";
                break;
            case 5:
                str += "5";
                break;
            case 4:
                str += "4";
                break;
            case 3:
                str += "3";
                break;
            case 2:
                str += "2";
                break;
            case 1:
                str += "1";
                break;
            case 0:
                str += "0";
        }
        return str;
    }

    @Override
    void save_binary(String address) { // saves positions of items saves points of players and turn info
        ObjectOutputStream os;

        try {
            os = new ObjectOutputStream(new FileOutputStream(address));
            for(int i = 0; i<32; i++)
            {
                os.writeUTF(board.items[i].position);
            }
            os.writeFloat(red.puan);
            os.writeFloat(black.puan);
            os.writeBoolean(siraKirmizida);
            os.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

    @Override
    void save_text(String address) {
        PrintWriter pw;

        try {
            pw = new PrintWriter(new FileOutputStream(address));
            for(int i = 0; i<32; i++)
            {
                pw.println(board.items[i].position);
            }
            pw.println(red.puan);
            pw.println(black.puan);
            pw.println(siraKirmizida);
            pw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

    }

    @Override
    void load_text(String address) {
        Scanner sc;

        try
        {
            sc = new Scanner(new FileInputStream(address));

            for(int i = 0; i<32; i++)
            {
                board.items[i].position = sc.nextLine();
            }
            red.puan = Float.parseFloat(sc.nextLine());
            black.puan = Float.parseFloat(sc.nextLine());
            siraKirmizida = Boolean.parseBoolean(sc.nextLine());
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getStackTrace());
        }

        Item[][] newTiles = new Item[10][9];

        for(int i= 0; i<board.items.length; i++)
        {
            board.items[i].tiles =   newTiles;
          if(!board.items[i].position.equals("xx"))
          {
            if(board.items[i].getClass().getName().equals("General"))
            {
                if(board.items[i].getColour().equals("red"))
                {
                    board.whiteGeneral = board.items[i];
                }
                else
                {
                    board.redGeneral = board.items[i];
                }
            }

            board.items[i].moveForLoad(board.items[i].position);

            String str= ""+Item.realPositionConvert(board.items[i].position);
            if(str.length() == 2)
            {
                board.items[i].positionInt =""+Item.realPositionConvert(board.items[i].position);
            }
            else if(str.length() == 1)
            {
                board.items[i].positionInt ="0"+Item.realPositionConvert(board.items[i].position);
            }
          }
          else
          {
            board.items[i].positionInt = "xx";
          }
         
        }
        board.tiles = newTiles;
        for(int i  = 0; i<board.items.length; i++)
        {
          if(!board.items[i].position.equals("xx"))
            board.items[i].calcMove(board.tiles);
        }
       
    }

    @Override
    void load_binary(String address) {
        ObjectInputStream oi;

        try {
            oi = new ObjectInputStream(new FileInputStream(address));
            for(int i = 0; i<32; i++)
            {
                board.items[i].position = oi.readUTF();
            }
            red.puan = oi.readFloat();
            black.puan = oi.readFloat();
            siraKirmizida = oi.readBoolean();
            oi.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
     
        Item[][] newTiles = new Item[10][9];


        for(int i= 0; i<board.items.length; i++)
        {
            board.items[i].tiles =   newTiles;
          if(!board.items[i].position.equals("xx"))
          {
            if(board.items[i].getClass().getName().equals("General"))
            {
                if(board.items[i].getColour().equals("red"))
                {
                    board.whiteGeneral = board.items[i];
                }
                else
                {
                    board.redGeneral = board.items[i];
                }
            }

            board.items[i].moveForLoad(board.items[i].position);

            String str= ""+Item.realPositionConvert(board.items[i].position);
            if(str.length() == 2)
            {
                board.items[i].positionInt =""+Item.realPositionConvert(board.items[i].position);
            }
            else if(str.length() == 1)
            {
                board.items[i].positionInt ="0"+Item.realPositionConvert(board.items[i].position);
            }
          }
          else
          {
            board.items[i].positionInt = "xx";
          }
         
        }
        board.tiles = newTiles;
        for(int i  = 0; i<board.items.length; i++)
        {
          if(!board.items[i].position.equals("xx"))
            board.items[i].calcMove(board.tiles);
        }
    }

}
