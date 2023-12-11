import Utility.Controller;
import view.UI;

public class Main {
    public static void main(String[] args) {
        String deck = UI.readDeck("Introduzca tipo de baraja: 'French' o 'Spanish': " );
        int nplayers = UI.readInt("Introduzca numero de jugadores: ",1,4);
        Controller.startApp(nplayers,deck);
    }
}