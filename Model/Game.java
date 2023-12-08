package Model;

import view.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Utility.Controller.game;

public class Game {
    public static String setdesk;
    private int turn;
    private String type;
    private Player[] players;
    private Desk desk;

    public Game(int nplayers) {
        if (nplayers <= 1) {
            nplayers = 2;
        }

        this.players = new Player[nplayers];

        // Lógica para agregar jugadores al juego
        for (int i = 0; i < nplayers; i++) {
            int j = i+1;
            String playerName = IO.readString("Ingrese el nombre del Jugador " + j + ": ");
            Player player = new Player(playerName);
            this.players[i] = player;
        }
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

    public boolean isFull() {
        return howManyPlayers() == players.length;
    }

    public int howManyPlayers() {
        if (players != null) {
            return players.length;
        } else {
            return 0; // o algún otro valor predeterminado según tus necesidades
        }
    }

    private int searchPlayer(String playerName) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null && players[i].getName().equals(playerName)) {
                // Jugador encontrado
                System.out.println("Jugador encontrado en posición " + i);
                return i;
            }
        }

        // Jugador no encontrado
        System.out.println("Jugador no encontrado");
        return -1;
    }


    public int addPlayer(Player player) {
        int pos = -1;

        // Verificar si hay espacio disponible y el jugador no está duplicado
        if (!isFull() && searchPlayer(player.getName()) == -1) {
            for (int i = 0; i < players.length && pos == -1; i++) {
                if (players[i] == null) {
                    // Hay espacio disponible, agregar al jugador
                    players[i] = player;
                    pos = i;
                }
            }
        }


        return pos;
    }

    /**
     * pos = searFirstSpot();
     * if(pos>-1){
     * cars[pos]=car;
     * }
     */

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


}