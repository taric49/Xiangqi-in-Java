
public abstract class AbstractItem implements ItemInterface {
	
	  String position;  // tahtadaki konumu gösterir. Örneğin, a1

	 public String getPosition() {
		 return position;
	 }

	public void setRealPosition(String a)
	{
		String str = "";
        switch (Integer.parseInt(a.charAt(0)+"")) {
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

        switch (Integer.parseInt(a.charAt(1)+"")) {
            case 8:
                str += "9";
                break;
            case 7:
                str += "8";
                break;
            case 6:
                str += "7";
                break;
            case 5:
                str += "6";
                break;
            case 4:
                str += "5";
				break;
            case 3:
                str += "4";
                break;
            case 2:
                str += "3";
                break;
            case 1:
                str += "2";
                break;
            case 0:
                str += "1";
				
        }
		this.position = str;
	}

	public void setRealPosition(int a)
	{
		String str = "";
        switch (a / 10) {   // for dead pieces i gave this metods parameter -11
			case -1:
			   str += "x";
			   break;
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
			case -1:
			str +="x";      
			 break;
            case 9:
                str += "10";
                break;
            case 8:
                str += "9";
                break;
            case 7:
                str += "8";
                break;
            case 6:
                str += "7";
                break;
            case 5:
                str += "6";
                break;
            case 4:
                str += "5";
				break;
            case 3:
                str += "4";
                break;
            case 2:
                str += "3";
                break;
            case 1:
                str += "2";
                break;
            case 0:
                str += "1";
        }
		this.position = str;
	}

	
	
}
