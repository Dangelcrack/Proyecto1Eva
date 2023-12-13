package Utility;

import Model.Card;
import Model.Deck;
import Model.Player;
import view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static view.Menu.*;

public class GameController {

    private static Player[] players;

    public GameController() {
    }

    public static void startApp() {
        String string = create_deck();
        int nplayers = number_of_players();
        create_players(nplayers);
        displayRules();
        play(string);
        int option = -1;
        Player[] players = getPlayers();
        for (Player player : players) {
            do {
                if (!Objects.equals(player.getName(), "IA")) {
                    option = Menu.mainMenu(player);
                    mainController(option, string, player);
                    int points = (int) calculatePoints(player);
                    if (points > 21) {
                        System.out.println("Te has pasado crack has perdido");
                        option = 5;
                    }
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
                System.out.println("***************");
                System.out.println(player.getName() + " tienes las siguientes cartas: ");
                for (Card card : player.getCards()) {
                    System.out.println(card);
                }
                System.out.println("***************");
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

    private static void create_players(int nplayers) {
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
                // Verificar si el nombre del jugador ya existe
                boolean jugadorExistente = false;
                for (int j = 0; j < nplayers; j++) {
                    if (players[j] != null && playerName.equals(players[j].getName())) {
                        jugadorExistente = true;
                        System.out.println("¡Ya existe un jugador con ese nombre! Introduce otro nombre.");
                        i--;  // Decrementa i para repetir la iteración y pedir otro nombre
                        break;
                    }
                }

                if (!jugadorExistente) {
                    players[i] = player;
                }
            }
        }
    }


    private static void play(String string) {
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
    }

    /**
     * Devuelve un array vacío si players es nulo, si no pues devuelve los jugadores
     */
    public static Player[] getPlayers() {
        // Devuelve un array vacío si players es nulo
        return Objects.requireNonNullElseGet(players, () -> new Player[0]);
    }

    private static Card getCard(String string) {
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

    /**
     * Devuelve el ganador o ganadores
     */
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

    /**
     * Calcula los puntos de cada persona
     */
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
                totalPoints -= 10;
                numberOfAces--;
            }
            return totalPoints;
        } else {
            System.out.println("Jugador nulo. No se pueden calcular los puntos.");
            return 0.0f;
        }
    }
}