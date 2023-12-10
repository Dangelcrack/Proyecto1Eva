package Utility;

import Model.Card;
import Model.Game;
import Model.Player;
import view.UI;
import view.Menu;

import java.util.Arrays;

import static Model.Game.*;

public class Controller {
    public static Game game;
    public static UI UI;

    public Controller(Game game, UI UI) {
        Controller.UI = UI;
        Controller.game = game;
    }

    public static void startApp(int nplayers, String string) {
        game = new Game(nplayers);
        System.out.println("¡Bienvenido al juego de Blackjack!");
        System.out.println("¡Esperamos que disfrutes del juego. ¡Buena suerte!");
        displayRules();
        play(string);
        int option = -1;
        Player[] players = game.getPlayers();
        for (Player player : players) {
            do {

                option = Menu.mainMenu(player);
                mainController(option, string, player);

            } while (option != 5);
        }
        finished();

    }

    public static void mainController(int option, String string, Player player) {
        switch (option) {
            case 1: //mostrar las cartas del jugador
                System.out.println("*********************************************");
                System.out.println(player.getName() + " tienes las siguientes cartas: " + Arrays.toString(player.getCards()));
                System.out.println("*********************************************");
                break;
            case 2:
                float points = game.calculatePoints(player);
                System.out.println("Tus puntos son: " + points);
                break;
            case 3:

                if (player != null) {  // Verificar si el jugador no es nulo
                    System.out.println("Cartas para " + player.getName() + ":");
                    for (int i = 0; i < 1; i++) {
                        Card drawnCard = game.getCard(string);
                        player.addCard(drawnCard);
                    }

                    // Mostrar las cartas que tiene cada jugador después de recibir las cartas
                    System.out.println(player.getName() + " tiene las siguientes cartas: " + Arrays.toString(player.getCards()));
                } else {
                    System.out.println("Error: Se encontró un jugador nulo.");
                }
                break;
            case 4:
                displayRules();
                break;
            case 5:
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();


                break;
            default:
                System.out.println("Opción incorrecta");
        }
    }

}