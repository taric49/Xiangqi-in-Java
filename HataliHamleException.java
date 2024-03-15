public class HataliHamleException extends Exception {
    String mesaj;

    public HataliHamleException(String mesaj) {
        this.mesaj = mesaj;
    }

    public HataliHamleException() {
        mesaj = "hatali hareket";
    }
}
