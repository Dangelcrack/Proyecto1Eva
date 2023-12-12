package Utility;

import Model.Card;
import Model.Deck;
import Model.Player;
import view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Controller {

    private static Player[] players;

    public Controller() {
    }

    public static void startApp(int nplayers, String string) {
        create_players(nplayers);
        displayRules();
        System.out.println("Comienza la partida...");
        play(string);
        int option = -1;
        Player[] players = getPlayers();
        for (Player player : players) {
            do {
                if (!Objects.equals(player.getName(), "IA")) {
                    option = Menu.mainMenu(player);
                    mainController(option, string, player);
                } else {
                    //calculo los puntos antes de entrar en el loop
                    int points = (int) calculatePoints(player);
                    while (points <= 15) {
                        mainController(3, string, player);
                        //actualizo los puntos en el loop porque si no es infinito
                        points = (int) calculatePoints(player);
                    }
                    option = 5;
                }

            } while (option != 5);
        }
        finished();

    }

    public static void mainController(int option, String string, Player player) {
        switch (option) {
            case 1: //mostrar las cartas del jugador
                System.out.println("*********************************************");
                System.out.println(player.getName() + " tienes las siguientes cartas: ");
                for (Card card : player.getCards()) {
                    System.out.println(card);
                }
                System.out.println("*********************************************");
                break;
            case 2:
                float points = calculatePoints(player);
                System.out.println("Tus puntos son: " + points);
                break;
            case 3:

                if (player != null) {  // Verificar si el jugador no es nulo
                    System.out.println("Carta para " + player.getName() + ":");
                    for (int i = 0; i < 1; i++) {
                        Card drawnCard = getCard(string);
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
                System.out.println("\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n");
                break;
            default:
                System.out.println("Opción incorrecta");
        }
    }

    public static void create_players(int nplayers) {
        if (nplayers <= 1) {
            nplayers = 2;
            players = new Player[nplayers];

            // Lógica para agregar jugadores al juego
            String playerName = Menu.insert_player();
            Player player = new Player(playerName);
            Player IA = Menu.create_ia();
            players[0] = player;
            players[1] = IA;

        } else {
            players = new Player[nplayers];

            // Lógica para agregar jugadores al juego
            for (int i = 0; i < nplayers; i++) {
                String playerName = Menu.insert_players(i);
                Player player = new Player(playerName);
                players[i] = player;
            }
        }
    }

    public static void displayRules() {
        System.out.println("¡Bienvenido al juego de Blackjack!");
        System.out.println("¡Esperamos que disfrutes del juego. ¡Buena suerte!");
        System.out.println("Reglas del Blackjack:");
        System.out.println("1. El objetivo es conseguir una mano con un valor cercano a 21 sin pasarse.");
        System.out.println("2. Cada carta numérica vale su valor, las figuras valen 10 y el As vale 11.");
        System.out.println();
    }

    public static void play(String string) {
        Player[] players = getPlayers();

        for (Player player : players) {
            if (player != null) {  // Verificar si el jugador no es nulo
                for (int i = 0; i < 1; i++) {
                    Card drawnCard = getCard(string);
                    player.addCard(drawnCard);
                }
            } else {
                System.out.println("Error: Se encontró un jugador nulo.");
            }
        }

        System.out.println("Todos los jugadores han recibido 1 carta!");
    }

    public static Player[] getPlayers() {
        // Devuelve un array vacío si players es nulo
        return Objects.requireNonNullElseGet(players, () -> new Player[0]);
    }

    public static Card getCard(String string) {
        Deck deck = new Deck(string);
        if (deck != null) {
            // Verificar si hay cartas en el mazo
            if (deck.hasCards()) {
                return deck.drawCard();
            } else {
                System.out.println("El mazo está vacío. No se pueden obtener más cartas.");
                return null;
            }
        } else {
            System.out.println("No se ha inicializado el mazo de cartas.");
            return null;
        }
    }


    public static Player[] calculateWinners() {
        List<Player> winners = new ArrayList<>();
        int maxPoints = 0;

        for (Player player : players) {
            // Calcular los puntos del jugador
            float points = calculatePoints(player);
            // Verificar si los puntos del jugador son válidos y superan al máximo actual
            if (points > 0 && points <= 21) {
                if (points > maxPoints) {
                    // Nuevo máximo, actualiza la lista de ganadores
                    maxPoints = (int) points;
                    winners.clear();
                    winners.add(player);
                } else if (points == maxPoints) {
                    // Empate, agrega a la lista de ganadores
                    winners.add(player);
                }
            }
        }

        // Devolver la lista de ganadores como un array
        return winners.toArray(new Player[0]);
    }

    public static float calculatePoints(Player player) {
        if (player != null) {
            List<Card> cards = List.of(player.getCards());
            int totalPoints = 0;
            int numberOfAces = 0;

            // Sumar los valores de las cartas
            for (Card card : cards) {
                totalPoints += card.getValue();
                if (card.isAce()) {
                    numberOfAces++;
                }
            }

            // Ajustar la puntuación por los ases (si los hay)
            while (numberOfAces > 0 && totalPoints > 21) {
                totalPoints += 10;
                numberOfAces--;
            }

            return totalPoints;
        } else {
            System.out.println("Jugador nulo. No se pueden calcular los puntos.");
            return 0.0f;
        }
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

    public static void finished() {
        // Mostrar resultados finales
        displayResults();
        System.out.println("Gracias por jugar. ¡Hasta luego!");
        // Otras acciones de salida o cierre de la aplicación si es necesario

    }


}