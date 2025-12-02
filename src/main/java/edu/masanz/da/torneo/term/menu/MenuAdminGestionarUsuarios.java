package edu.masanz.da.torneo.term.menu;

import edu.masanz.da.torneo.model.*;
import edu.masanz.da.torneo.service.TorneoService;
import edu.masanz.da.torneo.term.ui.Gui;

import static edu.masanz.da.torneo.config.Config.*;
import static edu.masanz.da.torneo.term.config.Ctes.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuAdminGestionarUsuarios implements IMenu {

    private static Logger logger = LogManager.getLogger();

    private  static TorneoService torneoService = new TorneoService();

    @Override
    public void run() {
        logger.info("menu-admin-gestionar-usuarios");
        Gui.mostrarTituloMenuGestionarUsuarios();
        Gui.mostrarMenuGestionarUsuarios();
        int opc = Gui.leerOpcionMenu(OPC_SALIR, OPC_ADM_USUARIOS_BORRAR);
        while (opc != OPC_SALIR) {
            switch (opc) {
                case OPC_ADM_USUARIOS:
                    mostrarUsuariosEajTorneo();
                    break;
                case OPC_ADM_USUARIOS_AGREGAR:
                    agregarUsuario();
                    break;
                case OPC_ADM_USUARIOS_CAMBIAR:
                    cambiarUsuario();
                    break;
                case OPC_ADM_USUARIOS_BORRAR:
                    borrarUsuario();
                    break;
            }
            Gui.mostrarTituloMenuGestionarUsuarios();
            Gui.mostrarMenuGestionarUsuarios();
            opc = Gui.leerOpcionMenu(OPC_SALIR, OPC_ADM_USUARIOS_BORRAR);
        }
    }

    private void mostrarTorneos() {
        TorneoFaseDto[] torneosFases = torneoService.getTorneosFases();
        Gui.mostrarTorneosFases(torneosFases);
    }

    private int mostrarUsuariosEajTorneo() {
        mostrarTorneos();
        int idTorneo = Gui.leerNumero("Id Torneo (0 para salir): ", 0);
        if (idTorneo == 0) {
            return 0;
        }
        UsuarioRolDto[] usuariosRols = torneoService.getUsuariosRolsEaj(idTorneo);
        Gui.mostrarUsuariosRols(usuariosRols);
        return idTorneo;
    }

    private void agregarUsuario() {
        int idTorneo = mostrarUsuariosEajTorneo();
        String alias = Gui.leerTexto("Alias del usuario (vacío para salir): ", 0);
        if (alias.trim().length() == 0) {
            return;
        }
        Usuario usuario = torneoService.getUsuarioByAlias(alias);
        if (usuario != null) {
            Gui.mostrarError("No se puede. Ya existe un usuario con ese alias.");
            return;
        }
        String password = Gui.leerTexto("Password del usuario: ", 0);
        Gui.mostrarRolsEaj();
        int rol = Gui.leerNumero("Rol (0 para salir): ", 0);
        if (rol == 0) {
            return;
        }
        if (rol != ROL_ESPEC_ID && rol != ROL_ARBIT_ID && rol != ROL_JUGAD_ID) {
            Gui.mostrarError("No se puede. Solo se pueden crear usuarios con rol de espectador, jugador o árbitro.");
            return;
        }
        usuario = new Usuario(0, alias, password, alias, rol, idTorneo, true);
        boolean b = torneoService.insertUsuario(usuario);
        if (b) {
            Gui.mostrarTexto("Equipo creado correctamente.");
        } else {
            Gui.mostrarError("No se ha podido crear el equipo");
        }
    }

    private void cambiarUsuario() {
        int idTorneo = mostrarUsuariosEajTorneo();
        String aliasOld = Gui.leerTexto("Alias del usuario a modificar (vacío para salir): ", 0);
        if (aliasOld.trim().length() == 0) {
            return;
        }
        Usuario usuario = torneoService.getUsuarioByAlias(aliasOld);
        if (usuario == null) {
            Gui.mostrarError("No se puede. Debe ser un alias que exista.");
            return;
        }
        if (usuario.getRol() != ROL_ESPEC_ID && usuario.getRol() != ROL_ARBIT_ID && usuario.getRol() != ROL_JUGAD_ID) {
            Gui.mostrarError("No se puede. Solo se pueden modificar usuarios con rol de espectador, jugador o árbitro.");
            return;
        }
        String s = String.format("Nuevo alias (def. %s): ", aliasOld);
        String aliasNew = Gui.leerTexto(s, 0);
        if (aliasNew.trim().length() == 0) {
            aliasNew = aliasOld;
        }
        int idUsuario = usuario.getId();
        String password = Gui.leerTexto("Password nueva del usuario: ", 0);
        Gui.mostrarRolsEaj();
        int rol = Gui.leerNumero("Rol (0 para salir): ", 0);
        if (rol == 0) {
            return;
        }
        if (rol != ROL_ESPEC_ID && rol != ROL_ARBIT_ID && rol != ROL_JUGAD_ID) {
            Gui.mostrarError("No se puede. Solo se pueden crear usuarios con rol de espectador, jugador o árbitro.");
            return;
        }
        usuario = new Usuario(idUsuario, aliasOld, password, aliasOld, rol, idTorneo, true);
        boolean b = torneoService.updateUsuario(usuario);
        if (b) {
            Gui.mostrarTexto("Usuario modificado correctamente.");
        } else {
            Gui.mostrarError("No se ha podido modificar el usuario.");
        }
    }

    private void borrarUsuario() {
        int idTorneo = mostrarUsuariosEajTorneo();
        String alias = Gui.leerTexto("Alias del usuario (vacío para salir): ", 0);
        if (alias.trim().length() == 0) {
            return;
        }
        Usuario usuario = torneoService.getUsuarioByAlias(alias);
        if (usuario == null) {
            Gui.mostrarError("No se puede. Debe ser un alias que exista.");
            return;
        }
        if (usuario.getRol() != ROL_ESPEC_ID && usuario.getRol() != ROL_ARBIT_ID && usuario.getRol() != ROL_JUGAD_ID) {
            Gui.mostrarError("No se puede. Solo se pueden borrar usuarios con rol de espectador, jugador o árbitro.");
            return;
        }
        boolean b = torneoService.deleteUsuarioEaj(alias);
        if (b) {
            Gui.mostrarTexto("Usuario borrado correctamente.");
        } else {
            Gui.mostrarError("No se ha podido borrar el usuario.");
        }
    }

}
