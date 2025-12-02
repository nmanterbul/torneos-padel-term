package edu.masanz.da.torneo.term.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.masanz.da.torneo.model.Usuario;
import edu.masanz.da.torneo.service.TorneoService;
import edu.masanz.da.torneo.term.ui.Gui;

import static edu.masanz.da.torneo.config.Config.*;

public class Login implements IMenu {

    private static Logger logger = LogManager.getLogger();

    private  static TorneoService torneoService = new TorneoService();

    public void run() {
        logger.info("login");

        String alias = null;
        String password = null;
        boolean authenticated = false;
        Usuario usuario = null;

        while(true) {
            Gui.mostrarTituloAutenticacion();
            alias = Gui.leerTexto(   "Usuario   : ", 40);
            password = Gui.leerTexto("Contraseña: ", 40);
            if (alias.isEmpty() || password.isEmpty()) {
                Gui.mostrarTituloExit();
                break;
            }

            authenticated = torneoService.authenticate(alias, password);
            logger.info("alias: " + alias  + " " + (authenticated?"AUTH":"NO AUTH"));
            Gui.mostrarAutenticacion(authenticated);
            if (authenticated) {
                usuario = torneoService.getUsuarioByAlias(alias);
                switch (usuario.getRol()) {
                    case ROL_ADMIN_ID:
                        IMenu menu = new MenuAdmin();
                        menu.run();
                        break;
                    default:

                }
            }
        }
    }
}

