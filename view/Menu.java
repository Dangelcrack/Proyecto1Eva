package view;

import Model.Player;

public class Menu {
    public static int mainMenu(Player player) {
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println("Bienvenido a BlackJack " + player.getName());
        System.out.println("1. Listar tus cartas");
        System.out.println("2. Mostrar tus puntos");
        System.out.println("3. Añadir carta");
        System.out.println("4. Reglas");
        System.out.println("5. Pasar turno");
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println();
        return UI.readInt("Elija una opción: ", 1, 5);
    }

    public static String insert_player() {
        return UI.readString("Inserte nombre de jugador 1: ");
    }

    public static String insert_players(int nplayers) {

        return UI.readString("Inserte nombre de jugador " + nplayers + ": ");
    }

    public static Player create_ia() {
        String player = "IA";
        System.out.println("Se ha añadido el jugador 1  IA.");
        return new Player(player);
    }
}