package Utility;

import Model.Card;
import Model.Game;
import Model.Player;
import view.UI;
import view.Menu;

import java.util.Arrays;
import java.util.Objects;

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
                if (!Objects.equals(player.getName(), "IA")) {
                    option = Menu.mainMenu(player);
                    mainController(option, string, player);
                } else {
                    //calculo los puntos antes de entrar en el loop
                    int points = (int) game.calculatePoints(player);
                    while (points <= 15) {
                        mainController(3, string, player);
                        //actualizo los puntos en el loop porque si no es infinito
                        points = (int) game.calculatePoints(player);
                    }
                    option=5;
                }

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
                    System.out.println("Carta para " + player.getName() + ":");
                    for (int i = 0; i < 1; i++) {
                        Card drawnCard = game.getCard(string);
                        player.addCard(drawnCard);
                    }
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