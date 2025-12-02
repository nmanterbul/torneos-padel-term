package edu.masanz.da.torneo.service;


import java.util.ArrayList;
import java.util.List;

import edu.masanz.da.torneo.dao.*;
import edu.masanz.da.torneo.model.*;
import static edu.masanz.da.torneo.config.Config.*;

public class TorneoService {

    private ITorneoDao torneoDao = new TorneoDaoMock();

    // private ITorneoDao torneoDao = new TorneoDao();

    public boolean authenticate(String alias, String password) {
        return torneoDao.authenticate(alias, password);
    }

    public Usuario getUsuarioByAlias(String alias) {
        return torneoDao.getUsuarioByAlias(alias);
    }

    public Usuario getUsuarioByNombre(String nombre) {
        return torneoDao.getUsuarioByNombre(nombre);
    }

    public Usuario getUsuarioById(int idUsuario) {
        return torneoDao.getUsuarioById(idUsuario);
    }

    public Torneo getTorneo(int idTorneo) {
        return torneoDao.getTorneo(idTorneo);
    }

    public Torneo getTorneo(String nombreTorneo) {
        return torneoDao.getTorneo(nombreTorneo);
    }

    public Fase getFase(int idTorneo) {
        return torneoDao.getFase(idTorneo);
    }
    public TorneoFaseDto[] getTorneosFases() {
        return torneoDao.getTorneosFases();
    }

    public List<String> getAccionesMenuAdm() {
        return new ArrayList<>(List.of(
                MENU_ACCION_VER_RESULTADOS,
                MENU_ACCION_AVANZAR_FASE,
                MENU_ACCION_CREAR_TORNEO,
                MENU_ACCION_GESTIONAR_EQUIPOS,
                MENU_ACCION_GESTIONAR_USUARIOS,
                MENU_ACCION_BORRAR_TORNEO,
                MENU_ACCION_SALIR
        ));
    }

    public List<String> getAccionesMenuAvanzarSinEmpezar() {
        return new ArrayList<>(List.of(
                MENU_AVANZAR_REVOLVER,
                MENU_AVANZAR_EMPEZAR,
                MENU_AVANZAR_REGRESAR,
                MENU_AVANZAR_SALIR
        ));
    }

    public List<String> getAccionesMenuAvanzarCuartosSemis() {
        return new ArrayList<>(List.of(
                MENU_AVANZAR_REGRESAR,
                MENU_AVANZAR_AVANZAR,
                MENU_AVANZAR_SALIR
        ));
    }

    public List<String> getAccionesMenuAvanzarTercerFinal() {
        return new ArrayList<>(List.of(
                MENU_AVANZAR_REGRESAR,
                MENU_AVANZAR_TERMINAR,
                MENU_AVANZAR_SALIR
        ));
    }

    public List<String> getAccionesMenuGestionarUsuarios() {
        return new ArrayList<>(List.of(
                MENU_USUARIOS_BORRAR,
                MENU_USUARIOS_CAMBIAR,
                MENU_USUARIOS_AGREGAR
        ));
    }

    public RegistroResultadosDto[] getResultadosTorneo(int idTorneo) {
        return torneoDao.getResultadosTorneo(idTorneo);
    }

    public RegistroResultadosDto[] getResultadosTorneoFase(int idTorneo, int idFase) {
        return torneoDao.getResultadosTorneoFase(idTorneo, idFase);
    }

    public RegistroResultadosDto[] getResultadosTorneoFases(int idTorneo, int idFase1, int idFase2) {
        return torneoDao.getResultadosTorneoFases(idTorneo, idFase1, idFase2);
    }

    public void revolverEquipos(int idTorneo) {
        torneoDao.revolverEquipos(idTorneo);
    }

    public boolean avanzarFase(int idTorneo) {
        return torneoDao.avanzarFase(idTorneo);
    }

    public boolean crearTorneo(String nombreTorneo, String arbitro, String alias, String password) {
        return torneoDao.crearTorneo(nombreTorneo, arbitro, alias, password);
    }

    public EquipoDto[] getEquiposTorneo(int idTorneo) {
        return torneoDao.getEquiposTorneo(idTorneo);
    }

    public UsuarioRolDto[] getUsuariosRolsEaj(int idTorneo) {
        return torneoDao.getUsuariosRolsEaj(idTorneo);
    }

    public boolean updateUsuario(Usuario usuario) {
        return torneoDao.updateUsuario(usuario);
    }

    public boolean insertUsuario(Usuario usuario) {
        return torneoDao.insertUsuario(usuario);
    }

    public boolean deleteUsuarioEaj(String alias) {
        return torneoDao.deleteUsuarioEaj(alias);
    }

    public void borrarTorneo(int idTorneo) {
        torneoDao.borrarTorneo(idTorneo);
    }
}
