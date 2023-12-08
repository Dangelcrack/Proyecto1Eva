package Utility;

import Model.Card;
import Model.Desk;
import Model.Game;
import Model.Player;
import view.IO;

import java.util.Arrays;

public class Controller {
    public static Game game;
    public static Player player;
    public static IO io;
    public Controller(Game game, IO io) {
        Controller.io = io;
        Controller.game = game;
    }

    public void finished() {
        // Mostrar resultados finales
        displayResults();
            System.out.println("Gracias por jugar. ¡Hasta luego!");
            // Otras acciones de salida o cierre de la aplicación si es necesario

    }

    public static void displayResults() {
        System.out.println("Resultados finales:");

        // Iterar sobre cada jugador y mostrar su puntuación
        for (Player player : game.getPlayers()) {
            if (player != null) {
                System.out.println(player.getName() + ": Puntuación - " + game.calculatePoints(player));
            }
        }

        // Identificar al ganador (o ganadores en caso de empate)
        Player[] winners = game.calculateWinners();
        if (winners.length == 1) {
            System.out.println("¡El ganador es " + winners[0].getName() + "!");
        } else if (winners.length > 1) {
            System.out.println("¡Hay un empate! Ganadores:");
            for (Player winner : winners) {
                System.out.println(winner.getName());
            }
        } else {
            System.out.println("El juego terminó sin un ganador claro.");
        }
    }



    public static void play(String string) {
        System.out.println("Comienza la partida...");
        if (game == null) {
            System.out.println("Error: La referencia al juego es nula.");
            return;
        }
        System.out.println(Arrays.toString(game.getPlayers()));
        Player[] players = game.getPlayers();

        for (Player player : players) {
            if (player != null) {  // Verificar si el jugador no es nulo
                System.out.println("Cartas para " + player.getName() + ":");
                for (int i = 0; i < 1; i++) {
                    Card drawnCard = game.getCard(string);
                    player.addCard(drawnCard);
                    System.out.println(drawnCard);
                }

                // Mostrar las cartas que tiene cada jugador después de recibir las cartas
                System.out.println(player.getName() + " tiene las siguientes cartas: " + Arrays.toString(player.getCards()));
            } else {
                System.out.println("Error: Se encontró un jugador nulo.");
            }
        }

        System.out.println("Todos los jugadores han recibido 1 carta!");
    }
    public static void startApp(int nplayers, String string) {
        game=new Game(nplayers);
        System.out.println("¡Bienvenido al juego de Blackjack!");
        displayRules();
        System.out.println("¡Esperamos que disfrutes del juego. ¡Buena suerte!");
        play(string);
        displayResults();


    }


    public static void displayRules() {
        // Muestra las reglas del juego de Blackjack
        System.out.println("Reglas del Blackjack:");
        // Agrega información sobre las reglas específicas del Blackjack
        System.out.println("1. El objetivo es conseguir una mano con un valor cercano a 21 sin pasarse.");
        System.out.println("2. Cada carta numérica vale su valor, las figuras valen 10 y el As puede valer 1 u 11.");
        // ... Agrega más reglas según sea necesario
        System.out.println();
    }

}