import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(15, 8, 20, 30);
        game.display();

        ArrayList<Pilchard> pilchards = new ArrayList<Pilchard>();

        pilchards.addAll(game.getPilchards());

        for (Pilchard pil : pilchards) {
            pil.play();
        }

       game.display();
    }
}
