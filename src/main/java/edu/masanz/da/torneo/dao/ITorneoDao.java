package edu.masanz.da.torneo.dao;

import edu.masanz.da.torneo.model.*;

import java.util.List;

public interface ITorneoDao {
    public boolean authenticate(String alias, String password);

    Usuario getUsuarioByAlias(String alias);
    Usuario getUsuarioByNombre(String nombre);

    TorneoFaseDto[] getTorneosFases();

    RegistroResultadosDto[] getResultadosTorneo(int torneo);

    Torneo getTorneo(int idTorneo);

    Torneo getTorneo(String nombreTorneo);

    Fase getFase(int idTorneo);

    RegistroResultadosDto[] getResultadosTorneoFase(int idTorneo, int idFase);
    RegistroResultadosDto[] getResultadosTorneoFases(int idTorneo, int idFase1, int idFase2);

    void revolverEquipos(int idTorneo);

    boolean avanzarFase(int idTorneo);

    boolean crearTorneo(String nombreTorneo, String arbitro, String alias, String password);

    EquipoDto[] getEquiposTorneo(int idTorneo);

    boolean updateUsuario(Usuario usuario);

    UsuarioRolDto[] getUsuariosRolsEaj(int idTorneo);

    boolean deleteUsuarioEaj(String alias);

    boolean insertUsuario(Usuario usuario);

    void borrarTorneo(int idTorneo);

    Usuario getUsuarioById(int idUsuario);
}
