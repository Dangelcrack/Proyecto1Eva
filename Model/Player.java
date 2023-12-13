package Model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Player {
    private Card[] cards;
    private String name;


    public Player() {
        this("");
    }


    public Player(String name) {
        this.name = name;
        this.cards = new Card[0];

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return Objects.equals(name, player.name) && Arrays.equals(cards, player.cards);
    }

    public boolean addCard(Card card) {
        if (card != null) {
            int length = cards.length;
            cards = Arrays.copyOf(cards, length + 1);
            cards[length] = card;
            return true;
        }
        return false;
    }

    private void setCards(Card[] cards) {
        if (cards != null) {
            this.cards = Arrays.copyOf(cards, cards.length);
        }
    }
    /**
     * Devuelve el de cartas según su longitud
     */
    public Card[] getCards() {
        return Arrays.copyOf(cards, cards.length);
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}