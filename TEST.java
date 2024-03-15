
public class TEST {
  public static void main(String[] args) {
    Game g = new Game("A", "B");

    g.play("a1", "b1");
    g.board.print();
    g.play("h8", "h6");
    g.board.print();
    g.play("b1", "b3");
    g.board.print();
    g.play("g5", "f5");
    g.board.print();
    g.play("d5", "e5");
    g.board.print();
    g.play("f5", "e5");
    g.board.print();
    g.play("a8", "c7");
    g.board.print();
    g.play("j3", "h1");
    g.board.print();
    g.play("c7", "d5");
    g.board.print();
    g.play("e5", "e4");
    g.board.print();
    g.play("d3", "e3");
    g.board.print();
    g.play("e4", "e3");
    g.board.print();
    g.play("b3", "e3");
    g.board.print();
    g.play("h6", "h8");
    g.board.print();
    g.play("a7", "c9");
    g.board.print();
    g.play("j9", "i9");
    g.board.print();
    g.play("c8", "c5");
    g.board.print();
    g.play("j4", "i5");
    g.board.print();
    g.play("c5", "i5");
    g.board.print();

    g.save_text("text.txt");

    g.play("i9", "i5");
    g.board.print();
    g.play("e3", "g3");
    g.board.print();
    g.play("i5", "i6");
    g.board.print();
    g.play("a9", "a8");
    g.board.print();
    g.play("j5", "j4");
    g.board.print();
    g.play("g3", "j3");
    g.board.print();
    g.play("j4", "i4");
    g.board.print();
    g.play("d5", "e7");
    g.board.print();
    g.play("h8", "h3");
    g.board.print();
    g.play("a8", "b8");
    g.board.print();
    g.play("h3", "i3");
    g.board.print();
    g.play("e7", "g8");
    g.board.print();
    g.play("h2", "h3");
    g.board.print();
    g.play("c2", "d2");
    g.board.print();
    g.play("h3", "j3");
    g.board.print();

    g.load_text("text.txt");

    // for(int i = 0; i<g.board.items.length; i++)
    // {
    // if(g.board.items[i] != null)
    // System.out.println(g.board.items[i].position);
    // }

    g.play("i9", "i5");
    g.board.print();
    g.play("e3", "g3");
    g.board.print();
    g.play("i5", "i6");
    g.board.print();
    g.play("a9", "a8");
    g.board.print();
    g.play("j5", "j4");
    g.board.print();
    g.play("g3", "j3");
    g.board.print();
    g.play("j4", "i4");
    g.board.print();
    g.play("d5", "e7");
    g.board.print();
    g.play("h8", "h3");
    g.board.print();
    g.play("a8", "b8");
    g.board.print();
    g.play("h3", "i3");
    g.board.print();
    g.play("e7", "g8");
    g.board.print();
    g.play("h2", "h3");
    g.board.print();
    g.play("c2", "d2");
    g.board.print();
    g.play("h3", "j3");
    g.board.print();
    g.play("b8", "b4");
    g.board.print();

  }

}
