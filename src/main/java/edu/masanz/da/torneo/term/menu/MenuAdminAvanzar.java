package edu.masanz.da.torneo.term.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.masanz.da.torneo.model.RegistroResultadosDto;
import edu.masanz.da.torneo.model.Torneo;
import edu.masanz.da.torneo.model.TorneoFaseDto;
import edu.masanz.da.torneo.service.TorneoService;
import edu.masanz.da.torneo.term.ui.Gui;

import static edu.masanz.da.torneo.config.Config.*;
import static edu.masanz.da.torneo.term.config.Ctes.*;

public class MenuAdminAvanzar implements IMenu{

    private static Logger logger = LogManager.getLogger();

    private  static TorneoService torneoService = new TorneoService();

    @Override
    public void run() {
        logger.info("menu-admin-avanzar");
        Gui.mostrarTituloMenuAdminAvanzar();
        Torneo torneo = pedirTorneo();
        if (torneo == null) {
            Gui.mostrarError("No se puede. Ese torneo no existe.");
            return;
        }
        Gui.mostrarSubtitulo(torneo.getIdNombre());
        mostrarFaseTorneo(torneo);
        Gui.mostrarMenuAdminAvanzar();
        int opc = Gui.leerOpcionMenu(OPC_SALIR, OPC_ADM_AVANZAR_TERMINAR);
        while (opc != OPC_SALIR) {
            switch (opc) {
                case OPC_ADM_AVANZAR_REVOLVER:
                    revolver(torneo);
                    break;
                case OPC_ADM_AVANZAR_EMPEZAR:
                    empezar(torneo);
                    break;
                case OPC_ADM_AVANZAR_AVANZAR:
                    avanzar(torneo);
                    break;
                case OPC_ADM_AVANZAR_TERMINAR:
                    terminar(torneo);
                    break;
            }
            boolean b = Gui.leerConfirmacionSalir();
            if (b) {
                break;
            }
            Gui.mostrarTituloMenuAdminAvanzar();
            torneo = pedirTorneo();
            if (torneo == null) {
                // Gui.mostrarError("No se puede. Ese torneo no existe.");
                break;
            }
            Gui.mostrarSubtitulo(torneo.getIdNombre());
            mostrarFaseTorneo(torneo);
            Gui.mostrarMenuAdminAvanzar();
            opc = Gui.leerOpcionMenu(OPC_SALIR, OPC_ADM_AVANZAR_TERMINAR);
        }

    }

    private Torneo pedirTorneo() {
        TorneoFaseDto[] torneosFases = torneoService.getTorneosFases();
        Gui.mostrarTorneosFases(torneosFases);
        int idTorneo = Gui.leerNumero("Id Torneo: ", 0);
        Torneo torneo = torneoService.getTorneo(idTorneo);
        return torneo;
    }

    private void mostrarFaseTorneo(Torneo torneo) {
        int idTorneo = torneo.getId();
        RegistroResultadosDto[] resultados = torneoService.getResultadosTorneo(idTorneo);
        if (torneo.getFase() == FASE_SIN_EMPEZAR_ID) {
            Gui.mostrarResultadosTorneoSinEmpezar(resultados, torneo.getFase());
        }else {
            Gui.mostrarResultadosTorneo(resultados, torneo.getFase());
        }
    }

    private void revolver(Torneo torneo) {
        if (torneo.getFase() != FASE_SIN_EMPEZAR_ID) {
            Gui.mostrarError("No se puede. No está en fase sin empezar.");
            return;
        }
        torneoService.revolverEquipos(torneo.getId());
        mostrarResultados(torneo);
    }

    private void empezar(Torneo torneo) {
        if (torneo.getFase() != FASE_SIN_EMPEZAR_ID) {
            Gui.mostrarError("No se puede. No está en fase sin empezar.");
            return;
        }
        torneoService.avanzarFase(torneo.getId());
        mostrarResultados(torneo);
    }

    private void avanzar(Torneo torneo) {
        if (torneo.getFase() == FASE_TERMINADO_ID) {
            Gui.mostrarError("No se puede. El torneo está terminado.");
            return;
        }
        if (torneoService.avanzarFase(torneo.getId())) {
            mostrarResultados(torneo);
        }else {
            Gui.mostrarError("No se puede. Revise que los partidos están terminados.");
        }
    }

    private void terminar(Torneo torneo) {
        avanzar(torneo);
    }

    private void mostrarResultados(Torneo torneo) {
        RegistroResultadosDto[] resultados = torneoService.getResultadosTorneo(torneo.getId());
        Gui.mostrarTexto(torneo.getIdNombre());
        Gui.mostrarSubtitulo(torneo.getIdNombre());
        if (torneo.getFase() != FASE_SIN_EMPEZAR_ID) {
            Gui.mostrarResultadosTorneo(resultados, torneo.getFase());
        }else {
            Gui.mostrarResultadosTorneoSinEmpezar(resultados, torneo.getFase());
        }
    }

}
