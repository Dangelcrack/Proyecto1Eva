package Model;

import java.util.Objects;

public class Card {
    private int value;
    // esto es una prueba de guardado
    private String suit;
    private String rank;


    public Card() {
        this(0, null, null);
    }

    public Card(int value, String suit, String rank) {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    private String getSuit() {
        return suit;
    }

    private void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && Objects.equals(suit, card.suit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Diseño simplificado de la carta con ícono en el medio
        sb.append("┌─────────┐\n");

        // Añade el valor y el ícono en el medio según la suit
        if (isNumericValue()) {
            sb.append("│").append(getValueLetter(rank)).append("       │\n");
        } else {
            sb.append("│ ").append(getValueLetter(rank)).append("       │\n");
        }

        // Añade el ícono en el medio según la suit
        sb.append("│    ").append(getSuitIcon()).append("   │\n");

        // Añade el valor invertido
        if (isNumericValue()) {
            sb.append("│      ").append(getValueLetter(rank)).append(" │\n");
        } else {
            sb.append("│       ").append(getValueLetter(rank)).append(" │\n");
        }

        // Añade la parte inferior de la carta
        sb.append("└─────────┘");

        return sb.toString();
    }

    private String getSuitIcon() {
        String icon = null;
        switch (suit.toLowerCase()) {
            case "hearts" -> icon = "♥ ";
            case "diamonds" -> icon = "♦ ";
            case "clubs" -> icon = "♣ ";
            case "spades" -> icon = "♠ ";
            case "swords" -> icon = "⚔ ";
            case "cups" -> icon = "🍷 ";
            case "coins" -> icon = "🥇";
            case "bastos" -> icon = "🏑";
            default -> {
                System.out.println("Valor inesperado para suit: " + suit);
            }
        }

        return icon;
    }

    private String getValueLetter(String rank) {
        return switch (rank) {
            case "1", "J", "Q", "K", "A" -> " " + rank;
            case "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" -> rank;
            default -> ("Valor inesperado para suit: " + rank);
        };
    }

    // Método auxiliar para verificar si el valor es numérico
    private boolean isNumericValue() {
        String stringValue = String.valueOf(value);

        // Verifica que la cadena no sea nula y tiene una longitud de 2
        return stringValue.length() == 2;
    }

    /**
     * Método auxiliar para saber si la suit vale A y, por lo tanto, es un ACE y si value == 1 es para la baraja española
     */
    public boolean isAce() {
        return "A".equals(suit) || value == 1;
    }
}
