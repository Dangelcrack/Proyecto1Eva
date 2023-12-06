package Model;
public class Game {
    private int turn;
    private int type;
    private Player[] players;
    private Desk desk;

    public int getType() {
        return type;
    }

    public Player firstPlayer() {
        // Implementar lógica para obtener el primer jugador
        return null;
    }

    public void printResumePlayer(Player player) {
        // Implementar lógica para imprimir el resumen del jugador
    }

    public Card getCard() {
        // Implementar lógica para obtener una carta
        return null;
    }

    public Player[] calculateWinners() {
        // Implementar lógica para calcular los ganadores
        return null;
    }

    public float calculatePoints(Player player) {
        // Implementar lógica para calcular los puntos de un jugador
        return 0.0f;
    }

    public boolean hasFinishedPlayer(Player player) {
        // Implementar lógica para verificar si un jugador ha terminado
        return false;
    }

    public boolean updateCardsPlayer(Player player, Card card) {
        // Implementar lógica para actualizar las cartas de un jugador
        return false;
    }

    public Player getNextPlayer() {
        // Implementar lógica para obtener el siguiente jugador
        return null;
    }

    public boolean areAllPlayers() {
        // Implementar lógica para verificar si todos los jugadores han jugado
        return false;
    }

    public boolean isAlreadyPlayer(String namePlayer) {
        // Implementar lógica para verificar si un jugador ya existe
        return false;
    }

    public boolean addPlayer(Player player) {
        // Implementar lógica para agregar un jugador
        return false;
    }

    public Game() {
        // Constructor por defecto
    }

    public Game(int type, int nPlayers) {
        // Constructor con parámetros
        this.type = type;
        this.players = new Player[nPlayers];
        this.desk = new Desk();
        this.turn = 0;
    }
}