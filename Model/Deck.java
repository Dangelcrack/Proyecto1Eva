package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private String type;
    private List<Card> cards;

    public Deck() {
        // Constructor por defecto
    }

    public Deck(String type) {
        this.type = type;
        if ("French".equals(type)) {
            makeFrenchDeck();
        } else if ("Spanish".equals(type)) {
            makeSpanishDeck();
        } else {
            System.out.println("Tipo de baraja no válido. Se creará una baraja francesa por defecto.");
            makeFrenchDeck();
        }
        shuffleDeck();
    }

    public Card[] getCards() {
        return cards.toArray(new Card[0]);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    private void makeFrenchDeck() {
        cards = new ArrayList<>();

        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int value;
                if ("J".equals(rank) || "Q".equals(rank) || "K".equals(rank)) {
                    value = 10;
                } else if ("A".equals(rank)) {
                    value = 11;  // O puedes elegir 11 dependiendo de las reglas de tu juego
                } else {
                    value = Integer.parseInt(rank);
                }

                Card card = new Card(value, suit,rank);
                cards.add(card);
            }
        }
    }

    private void makeSpanishDeck() {
        cards = new ArrayList<>();

        String[] suits = {"cups", "coins", "swords", "bastos"};
        int[] ranks = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};

        for (String suit : suits) {
            for (int rank : ranks) {
                String s = String.valueOf(rank);
                Card card = new Card(rank, suit,s);
                cards.add(card);
            }
        }
    }

    public Card drawCard() {
        if (cards != null && !cards.isEmpty()) {
            return cards.remove(0);
        } else {
            System.out.println("El mazo está vacío. No se pueden sacar más cartas.");
            return null;
        }
    }

    public boolean hasCards() {
        return cards != null && !cards.isEmpty();
    }


}