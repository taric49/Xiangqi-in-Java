import java.io.Serializable;

public class Player implements Serializable {
	
	 float puan; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.
	 String name = "";

	 public Player(String s)
	 {
		name = s;
	 }

	public float getPuan() {
		return puan;
	}

	public void setPuan(float puan) {
		this.puan = puan;
	}
	
	

	

}
