package Model;

import view.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Utility.Controller.game;

public class Game {
    private String type;
    private Player[] players;

    public Game(int nplayers) {
        if (nplayers <= 1) {
            nplayers = 2;
        }

        this.players = new Player[nplayers];

        // Lógica para agregar jugadores al juego
        for (int i = 0; i < nplayers; i++) {
            int j = i + 1;
            String playerName = IO.readString("Ingrese el nombre del Jugador " + j + ": ");
            Player player = new Player(playerName);
            this.players[i] = player;
        }
    }

    public static void play(String string) {
        System.out.println("Comienza la partida...");
        if (game == null) {
            System.out.println("Error: La referencia al juego es nula.");
            return;
        }
        Player[] players = game.getPlayers();

        for (Player player : players) {
            if (player != null) {  // Verificar si el jugador no es nulo
                for (int i = 0; i < 1; i++) {
                    Card drawnCard = game.getCard(string);
                    player.addCard(drawnCard);
                }
            } else {
                System.out.println("Error: Se encontró un jugador nulo.");
            }
        }

        System.out.println("Todos los jugadores han recibido 1 carta!");
    }


    public Card getCard(String string) {
        Desk desk = new Desk(string);
        if (desk != null) {
            // Verificar si hay cartas en el mazo
            if (desk.hasCards()) {
                return desk.drawCard();
            } else {
                System.out.println("El mazo está vacío. No se pueden obtener más cartas.");
                return null;
            }
        } else {
            System.out.println("No se ha inicializado el mazo de cartas.");
            return null;
        }
    }

    public static void displayRules() {
        System.out.println("Reglas del Blackjack:");
        System.out.println("1. El objetivo es conseguir una mano con un valor cercano a 21 sin pasarse.");
        System.out.println("2. Cada carta numérica vale su valor, las figuras valen 10 y el As vale 1 y resta 10 puntos, es decir -9.");
        System.out.println();
    }

    public Player[] calculateWinners() {
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

    public float calculatePoints(Player player) {
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


    public Player[] getPlayers() {
        // Devuelve un array vacío si players es nulo
        return Objects.requireNonNullElseGet(players, () -> new Player[0]);
    }

    public String getType() {
        return type;
    }

    public boolean setDesk(Desk desk) {

        return false;
    }

    public static void finished() {
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


}