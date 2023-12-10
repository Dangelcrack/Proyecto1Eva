import Utility.Controller;
import view.IO;

public class Main {
    public static void main(String[] args) {
        String deck = IO.readString("Introduzca tipo de baraja: 'French' o 'Spanish': " );
        int nplayers = IO.readInt("Introduzca numero de jugadores: ",1,4);
        Controller.startApp(nplayers,deck);
    }
}