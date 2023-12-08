package Test;

import Model.Card;
import Model.Desk;

import java.util.Arrays;

public class TestDesk {
    public static void main(String[] args) {
        // Crear una instancia de la clase Desk
        Desk desk = new Desk("French");

        // Imprimir información sobre la baraja
        System.out.println("Tipo de baraja: " + desk.getType());
        System.out.println("Cartas en la baraja: " + Arrays.toString(desk.getCards()));

        // Realizar algunas operaciones de prueba, por ejemplo, barajar y sacar una carta
        desk.shuffleDesk();
        Card drawnCard = desk.drawCard();

        // Imprimir información después de barajar y sacar una carta
        System.out.println("Barajado y carta sacada: " + Arrays.toString(desk.getCards()));
        System.out.println("Carta sacada: " + drawnCard);

        // Agregar más operaciones de prueba según sea necesario

        // Finalizar
        System.out.println("Pruebas completadas.");
    }
}