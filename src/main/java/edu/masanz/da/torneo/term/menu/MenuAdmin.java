package edu.masanz.da.torneo.term.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.masanz.da.torneo.model.*;
import edu.masanz.da.torneo.service.TorneoService;
import edu.masanz.da.torneo.term.ui.Gui;

import static edu.masanz.da.torneo.config.Config.*;
import static edu.masanz.da.torneo.term.config.Ctes.*;

public class MenuAdmin implements IMenu {

    private static Logger logger = LogManager.getLogger();

    private  static TorneoService torneoService = new TorneoService();

    @Override
    public void run() {
        logger.info("menu-admin");
        Gui.mostrarTituloMenuAdmin();
        Gui.mostrarMenuAdmin();
        int opc = Gui.leerOpcionMenu(OPC_SALIR, OPC_ADM_TORNEO_BORRADO);
        while (opc != OPC_SALIR) {
            switch (opc) {
                case OPC_ADM_TORNEOS:
                    mostrarTorneos();
                    break;
                case OPC_ADM_RESULTADOS:
                    verResultados();
                    break;
                case OPC_ADM_AVANZAR:
                    avanzarFase();
                    break;
                case OPC_ADM_TORNEO_NUEVO:
                    crearTorneo();
                    break;
                case OPC_ADM_GEST_EQUIPOS:
                    gestionarEquipos();
                    break;
                case OPC_ADM_GEST_USUARIOS:
                    gestionarUsuarios();
                    break;
                case OPC_ADM_TORNEO_BORRADO:
                    borrarTorneo();
                    break;
            }
            Gui.mostrarTituloMenuAdmin();
            Gui.mostrarMenuAdmin();
            opc = Gui.leerOpcionMenu(OPC_SALIR, OPC_ADM_TORNEO_BORRADO);
        }
    }

    private void mostrarTorneos() {
        TorneoFaseDto[] torneosFases = torneoService.getTorneosFases();
        if (torneosFases.length == 0) {
            Gui.mostrarError("No hay torneos en este momento.");
            return;
        }
        Gui.mostrarTorneosFases(torneosFases);
    }

    private void verResultados() {
        mostrarTorneos();
        int idTorneo = Gui.leerNumero("Id Torneo: ", 0);
        RegistroResultadosDto[] resultados = torneoService.getResultadosTorneo(idTorneo);
        if (resultados == null) {
            Gui.mostrarError("No hay resultados para ese torneo.");
            return;
        }
        Gui.mostrarResultadosTorneo(resultados);
    }

    private void avanzarFase() {
        IMenu maa = new MenuAdminAvanzar();
        maa.run();
    }

    private void crearTorneo() {
        String nombre = Gui.leerTexto("Nombre del torneo: ", 0);
        if (torneoService.getTorneo(nombre) != null) {
            Gui.mostrarError("No se puede. Ya existe un torneo con ese nombre.");
            return;
        }
        boolean crearArbitro = Gui.leerConfirmacion("¿Crear usuario árbitro para el torneo? (s/n): ", 0, "s");
        String arbitro = null;
        String password = null;
        if (crearArbitro) {
            arbitro = Gui.leerTexto("Nombre del árbitro: ", 0);
            if (torneoService.getUsuarioByAlias(arbitro) != null) {
                Gui.mostrarError("No se puede. Ya existe ese árbitro.");
                return;
            }
            password = Gui.leerTexto("Password del árbitro: ", 0);
        }
        if (torneoService.crearTorneo(nombre, arbitro, arbitro, password)) {
            Gui.mostrarTexto("Torneo creado correctamente.");
        } else {
            Gui.mostrarError("No se ha podido crear el torneo");
        }
    }

    private void gestionarEquipos() {
        mostrarTorneos();
        int idTorneo = Gui.leerNumero("Id Torneo: ", 0);
        EquipoDto[] equipos = torneoService.getEquiposTorneo(idTorneo);
        Gui.mostrarEquipos(equipos);
        int idEquipo = Gui.leerNumero("Id Equipo a modificar (0 para salir): ", 0);
        if (idEquipo == 0) {
            return;
        }
        Usuario usuario = torneoService.getUsuarioById(idEquipo);
        if (usuario == null) {
            Gui.mostrarError("No se puede. Debe modificar un equipo que existe.");
            return;
        }
        String nombre = Gui.leerTexto("Nombre del equipo: ", 0);
        if (torneoService.getUsuarioByNombre(nombre) != null) {
            Gui.mostrarError("No se puede. Ya existe un equipo con ese nombre.");
            return;
        }
        String alias = Gui.leerTexto("Alias del equipo: ", 0);
        if (torneoService.getUsuarioByAlias(alias) != null) {
            Gui.mostrarError("No se puede. Ya existe un equipo con ese alias.");
            return;
        }
        String password = Gui.leerTexto("Password del equipo: ", 0);
        usuario = new Usuario(idEquipo, alias, password, nombre, ROL_EQUIPO_ID, idTorneo, true);
        boolean b = torneoService.updateUsuario(usuario);
        if (b) {
            Gui.mostrarTexto("Equipo modificado correctamente.");
        } else {
            Gui.mostrarError("No se ha podido modificar el equipo");
        }
    }

    private void gestionarUsuarios() {
        IMenu magu = new MenuAdminGestionarUsuarios();
        magu.run();
    }

    private void borrarTorneo() {
        mostrarTorneos();
        int idTorneo = Gui.leerNumero("Id Torneo (0 para salir): ", 0);
        if (idTorneo == 0) {
            return;
        }
        Torneo torneo = torneoService.getTorneo(idTorneo);
        if (torneo == null) {
            Gui.mostrarError("No se puede. Debe ser un torneo que exista.");
            return;
        }
        String s = String.format("¿Confirma el borrado del torneo %s (s/n): ", torneo.getNombre());
        boolean b = Gui.leerConfirmacion(s, 0, "s");
        if (b) {
            torneoService.borrarTorneo(idTorneo);
            Gui.mostrarTexto("Torneo borrado correctamente.");
        } else {
            Gui.mostrarError("No se ha podido borrar el torneo.");
        }
    }

}
