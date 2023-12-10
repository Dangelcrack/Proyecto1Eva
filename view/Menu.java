package view;

import Model.Player;

public class Menu {
    public static int mainMenu(Player player) {
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println("Bienvenido a BlackJack "+ player.getName());
        System.out.println("1. Listar tus cartas");
        System.out.println("2. Mostrar tus puntos");
        System.out.println("3. Añadir carta");
        System.out.println("4. Reglas");
        System.out.println("5. Pasar turno");
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println();
        return UI.readInt("Elija una opción: ",1,5);
    }

}