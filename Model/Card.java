package Model;

import java.util.Objects;

public class Card {
    private int value;
    // esto es una prueba de guardado
    private String suit;


    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card(String s) {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
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
        sb.append("\n┌─────────┐\n");

        // Añade el valor y el ícono en el medio según la suit
        if (isNumericValue()) {
            sb.append("│").append(value).append("       │\n");
        } else {
            sb.append("│ ").append(value).append("       │\n");
        }

        // Añade el ícono en el medio según la suit
        sb.append("│    ").append(getSuitIcon()).append("   │\n");

        // Añade el valor invertido
        if (isNumericValue()) {
            sb.append("│      ").append(value).append(" │\n");
        } else {
            sb.append("│       ").append(value).append(" │\n");
        }

        // Añade la parte inferior de la carta
        sb.append("└─────────┘");

        return sb.toString();
    }

    // Método auxiliar para obtener el ícono según la suit
    private String getSuitIcon() {
        String icon=null;
        switch (suit.toLowerCase()) {
            case "hearts" -> icon = "♥ ";
            case "diamonds" -> icon = "♦ ";
            case "clubs" -> icon = "♣ ";
            case "spades" -> icon = "♠ ";
            case "swords" -> icon = "⚔ ";
            case "cups" -> icon = "🍷";
            case "coins" -> icon = "🥇";
            case "bastos" -> icon = "🏑";
            default -> {
                System.out.println("Valor inesperado para suit: " + suit);
            }
        }

        return icon;
    }

    // Método auxiliar para verificar si el valor es numérico
    private boolean isNumericValue() {
        String stringValue = String.valueOf(value);

        // Verifica que la cadena no sea nula y tiene una longitud de 2
        return stringValue != null && stringValue.length() == 2;
    }
    public boolean isAce() {
        return "A".equals(suit) || value == 1;
    }
}
