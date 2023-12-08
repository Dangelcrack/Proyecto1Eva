package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Desk {
    public String type;
    private List<Card> cards;

    public Desk() {
        // Constructor por defecto
    }

    public Desk(String type) {
        this.type = type;
        if ("French".equals(type)) {
            makeFrenchDesk();
        } else if ("Spanish".equals(type)) {
            makeSpanishDesk();
        } else {
            System.out.println("Tipo de baraja no válido. Se creará una baraja francesa por defecto.");
            makeFrenchDesk();
        }
        shuffleDesk();
    }

    public Card[] getCards() {
        return cards.toArray(new Card[0]);
    }

    private String[] getNamesCards() {
        String[] cardNames = new String[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            cardNames[i] = cards.get(i).toString();
        }
        return cardNames;
    }

    public void printCards(Card[] cards) {
        System.out.println(Arrays.toString(cards));
    }

    public void printCard(Card card) {
        System.out.println(card);
    }

    public void printDesk() {
        System.out.println("Type: " + type);
        System.out.println("Cards: " + Arrays.toString(getNamesCards()));
    }

    public Card pickRandomCard() {
        if (!cards.isEmpty()) {
            int randomIndex = (int) (Math.random() * cards.size());
            return cards.remove(randomIndex);
        } else {
            System.out.println("No hay cartas en la baraja.");
            return null;
        }
    }

    public void shuffleDesk() {
        Collections.shuffle(cards);
    }

    private void makeFrenchDesk() {
        cards = new ArrayList<>();

        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int value;
                if ("J".equals(rank) || "Q".equals(rank) || "K".equals(rank)) {
                    value = 10;
                } else if ("A".equals(rank)) {
                    value = 1;  // O puedes elegir 11 dependiendo de las reglas de tu juego
                } else {
                    value = Integer.parseInt(rank);
                }

                Card card = new Card(value,suit);
                cards.add(card);
            }
        }
    }

    private void makeSpanishDesk() {
        cards = new ArrayList<>();

        String[] suits = {"cups", "coins", "swords", "bastos"};
        int[] ranks = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};

        for (String suit : suits) {
            for (int rank : ranks) {
                Card card = new Card(rank,suit);
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

    public String getType() {
        return type;
    }
}