package view;

import Model.Player;

import static Utility.GameController.*;

public class Menu {
    public static int mainMenu(Player player) {
        System.out.println("***************");
        System.out.println("***************");
        System.out.println("Bienvenido a BlackJack " + player.getName());
        System.out.println("1. Listar tus cartas");
        System.out.println("2. Mostrar tus puntos");
        System.out.println("3. Añadir carta");
        System.out.println("4. Reglas");
        System.out.println("5. Pasar turno");
        System.out.println("***************");
        System.out.println("***************");
        System.out.println();
        return UI.readInt("Elija una opción: ", 1, 5);
    }

    public static String create_deck() {
        return UI.readDeck("Introduzca tipo de baraja: 'French' o 'Spanish': ");
    }

    public static int number_of_players() {
        return UI.readInt("Introduzca numero de jugadores, 1 para jugar contra la IA y 4 el máximo: ", 1, 4);
    }

    public static String insert_player() {
        return UI.readString("Inserte nombre de jugador 1: ");
    }

    public static String insert_players(int nplayers) {
        nplayers++;
        return UI.readString("Inserte nombre de jugador " + nplayers + ": ");
    }

    public static void displayRules() {
        System.out.println("\n¡Bienvenido al juego de Blackjack!");
        System.out.println("¡Esperamos que disfrutes del juego. ¡Buena suerte!");
        System.out.println("Reglas del Blackjack:");
        System.out.println("1. El objetivo es conseguir una mano con un valor cercano a 21 sin pasarse.");
        System.out.println("2. Cada carta numérica vale su valor, las figuras valen 10 y el As vale 11.");
        System.out.println();
        // Todos los jugadores están obligados a recibir una carta.
        System.out.println("Todos los jugadores han recibido 1 carta!");
    }

    public static void displayResults() {
        System.out.println("Resultados finales:");
        // Iterar sobre cada jugador y mostrar su puntuación
        for (Player player : getPlayers()) {
            if (player != null) {
                System.out.println(player.getName() + ": Puntuación - " + calculatePoints(player));
            }
        }
        // Identificar al ganador (o ganadores en caso de empate)
        Player[] winners = calculateWinners();
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

    public static Player create_ia() {
        String player = "IA";
        System.out.println("Se ha añadido el jugador IA.");
        return new Player(player);
    }

    public static void finished() {
        displayResults();
        System.out.println("Gracias por jugar. ¡Hasta luego!");
    }
}