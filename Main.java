import Model.Game;
import Utility.Controller;
import view.IO;
import view.Menu;

import static Utility.Controller.game;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de las clases necesarias (Game, Controller, IO, etc.)
        String desk = IO.readString("Introduzca tipo de baraja: 'French' o 'Spanish': " );
        int nplayers = IO.readInt("Introduzca numero de jugadores: ",1,4);
        Controller.startApp(nplayers,desk);
    }
}