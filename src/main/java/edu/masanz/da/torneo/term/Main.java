package edu.masanz.da.torneo.term;

import edu.masanz.da.torneo.term.menu.*;

public class Main {

    public static void main(String[] args) {
        IMenu menu = new Login();
//        IMenu menu = new MenuAdmin();
//        IMenu menu = new MenuAdminAvanzar();
//        IMenu menu = new MenuAdminGestionarUsuarios();
        menu.run();
    }

}
