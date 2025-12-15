package edu.masanz.da.torneo.dao;

import java.util.Arrays;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import edu.masanz.da.torneo.config.Config;
import edu.masanz.da.torneo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.masanz.da.torneo.config.Config.*;
import static edu.masanz.da.torneo.dao.DataDaoMock.*;

public class TorneoDaoMock implements ITorneoDao {

    private static final Logger log = LoggerFactory.getLogger(TorneoDaoMock.class);

    @Override
    public boolean authenticate(String alias, String password) {
        // TODO 01: Implementar la autenticación de usuario


        for (Usuario usuario : usuarios) {
            if(usuario != null && usuario.getAlias().equals(alias) && usuario.getPassword().equals(password)){
                    return true;

            }

        }





        return false;
    }

    @Override
    public Usuario getUsuarioByAlias(String alias) {
        // TODO 02: Implementar la búsqueda de usuario por alias


        for (int i = 0; i < usuarios.length; i++) {
            Usuario u = usuarios[i];

            if(u != null && u.getAlias().equals(alias)){
                return u;
            }

        }



        return null;
    }

    @Override
    public Usuario getUsuarioByNombre(String nombre) {
        // TODO 03: Implementar la búsqueda de usuario por nombre


        for (int i = 0; i < usuarios.length; i++) {
            Usuario u = usuarios[i];
            if(u != null && u.getNombre() == nombre){
                    return u;
            }

        }




        return null;
    }

    @Override
    public TorneoFaseDto[] getTorneosFases() {
        // TODO 04: Implementar la obtención de los torneos con sus fases
        // Este array no debe tener referencias nulas y su tamaño debe ser el justo
        TorneoFaseDto[] a = new TorneoFaseDto[torneos.length];
        int k = 0;

        for (int i = 0; i < torneos.length; i++) {
           Torneo t = torneos[i];
           if(t != null){
               int idFaseTorneo = t.getFase();
               String nombreFaseTorneo = null;
               // buscar la fase que corresponde

               for (int j = 0; j <fases.length ; j++) {
                   Fase f = fases[i];

                   if(f.getId() == idFaseTorneo){
                       nombreFaseTorneo = f.getNombre();
                   }
               }
               TorneoFaseDto tf = new TorneoFaseDto(t.getId(),t.getNombre(),nombreFaseTorneo);
                a[k] = tf;
                k++;
           }
        }














        return Arrays.copyOfRange(a, 0, k);
    }

    private String getNombreEquipo(int idEquipo) {
        // TODO 05: Implementar la obtención del nombre del equipo por su id


        for (int i = 0; i < usuarios.length; i++) {
            Usuario t = new Usuario();
            if(t != null && idEquipo == t.getRol()){
              return t.getNombre();

            }

        }



        return "";
    }

    @Override
    public Torneo getTorneo(int idTorneo) {
        // TODO 06: Implementar la búsqueda de torneo por id

        for (int i = 0; i < torneos.length; i++) {
            Torneo t = torneos[i];

            if(t != null && t.getId() == idTorneo){
                return t;
            }

        }




        return null;
    }

    @Override
    public Torneo getTorneo(String nombreTorneo) {
        // TODO 07: Implementar la búsqueda de torneo por nombre

        for (int i = 0; i < torneos.length; i++) {

            Torneo t = torneos[i];

            if(t != null && t.getNombre() == nombreTorneo){

                return t;
            }
        }




        return null;
    }

    @Override
    public Fase getFase(int idTorneo) {
        // TODO 08: Implementar la obtención de la fase actual de un torneo

        for (int i = 0; i < torneos.length; i++) {
            Torneo t = torneos[i];
            if(t != null && t.getId() == idTorneo){
                for (int j = 0; j < fases.length; j++) {
                    Fase f = fases[j];
                    if(f.getId() ==  t.getFase()){
                        return  f;
                    }
                }

            }
        }







        return null;
    }

    private int getNumeroRegistrosFase(int idFase) {
        // TODO 09: Implementar la obtención del número de registros (partidos) de una fase
        // Usar el array fases. El número de registros (partidos) es la mitad del número de equipos

        for (int i = 0; i < fases.length; i++) {

            Fase t = fases[i];
            if (t != null && t.getId() == idFase){

            return t.getNumeroEquipos()/2;

            }
        }





        return 0;
    }

    @Override
    public RegistroResultadosDto[] getResultadosTorneo(int idTorneo) {
        // TODO 10: Implementar la obtención de los resultados de un torneo
        // Primero comprueba que el torneo existe
        // Siempre habrá 8 registros en un torneo de 8 equipos:
        // cuartos (4), semifinales (2), tercer puesto (1) y final (1)



        RegistroResultadosDto[] rrd = new RegistroResultadosDto[8];



        for (int i = 0; i < torneos.length; i++) {



                Torneo t = torneos[i];


                if (t != null){
                    if(t.getId() == idTorneo){


                        return rrd;
                    }



                }






        }














        return rrd;
    }

    @Override
    public RegistroResultadosDto[] getResultadosTorneoFase(int idTorneo, int idFase) {
        // TODO 11: Implementar la obtención de los resultados de una fase de un torneo
        int n = getNumeroRegistrosFase(idFase);
        RegistroResultadosDto[] rrd = new RegistroResultadosDto[n];


        for (int i = 0; i < torneos.length; i++) {

            Torneo t = torneos[i];

            if (t != null && t.getId() == idTorneo  ){

                for (int j = 0; j < fases.length; j++) {

                    Fase f = fases[i];

                    if(f !=null && f.getId() == idFase){

                        return rrd;

                    }

                }
            }
        }
















        return null;
    }

    public RegistroResultadosDto[] getResultadosTorneoFases(int idTorneo, int idFase1, int idFase2) {
        // TODO 12: Implementar la obtención de los resultados de dos fases de un torneo
        RegistroResultadosDto[] rrd1 = getResultadosTorneoFase(idTorneo, idFase1);
        RegistroResultadosDto[] rrd2 = getResultadosTorneoFase(idTorneo, idFase2);
        RegistroResultadosDto[] rrd = new RegistroResultadosDto[rrd1.length + rrd2.length];

        boolean existeFase1 = false;
        boolean existeFase2 = false;

        for (int j = 0; j < torneos.length; j++) {

            Torneo t = torneos[j];

            if (t != null && t.getId() == idTorneo){
                for (int i = 0; i < fases.length; i++) {

                    Fase f = fases[i];

                    if (f !=null && f.getId() == idFase1){
                        return rrd1;
                    }

                }

            }


        }


        for (int j = 0; j < torneos.length; j++) {

            Torneo t = torneos[j];

            if (t != null && t.getId() == idTorneo){
                for (int i = 0; i < fases.length; i++) {

                    Fase f = fases[i];

                    if (f !=null   &&f.getId() == idFase2){
                        return rrd2;
                    }

                }
            }

        }

        if (!existeFase1 || !existeFase2) {
            return new RegistroResultadosDto[0];
        }


        for (int i = 0; i < rrd1.length; i++) {

            rrd[i] = rrd1[i];

        }

        for (int i = 0; i < rrd2.length; i++) {

            rrd[rrd1.length +i] = rrd2[i];
        }




        return rrd;
    }

    @Override
    public void revolverEquipos(int idTorneo) {
        // TODO 13: Implementar la función de revolver los equipos en la fase Sin Empezar
        // Solo se pueden revolver los equipos si el torneo está en fase Sin Empezar
        // Buscar los registros del torneo (de la fase Sin Empezar únicamente)
        //  - En un array guardar las posiciones de esos registros
        //  - En otro array guardar los ids de los equipos de esos registros
        // Revolver los equipos entre esos registros
        //  - Hacer 32 intercambios aleatorios entre los ids de los equipos
        // Fijar los equipos revolvidos en los registros del torneo
        //  - Recorrer las posiciones de los registros y asignar los ids de los equipos revolvidos

        // Solo se pueden revolver los equipos si el torneo está en fase Sin Empezar
        Torneo torneo = torneos[idTorneo];
        if (torneo == null || torneo.getFase() != FASE_SIN_EMPEZAR_ID) { return; }
        int[] posRegistros = new int[4];
        int i = 0;
        int[] equipoIds = new int[8];
        int j = 0;
        // Buscar los registros del torneo (de la fase Sin Empezar únicamente)
        //  - En un array guardar las posiciones de esos registros
        //  - En otro array guardar los ids de los equipos de esos registros
        for (int k = 0; k < registros.length; k++) {
            Registro r = registros[k];
            if( r.getId() == idTorneo && r.getFase() == FASE_SIN_EMPEZAR_ID){
                posRegistros[i++] = k;
                equipoIds[j++] = r.getEquipo1();
                equipoIds[j++] = r.getEquipo2();
            }
        }
        // Revolver los equipos entre esos registros
        //  - Hacer 32 intercambios aleatorios entre los ids de los equipos
        for (int k = 0; k < 32; k++) {
            int x = (int)  (Math.random()*8);
            int y = (int)  (Math.random()*8);
            int t = equipoIds[x];
            equipoIds[x] = equipoIds[y];
            equipoIds[y] = t;
        }
        // Fijar los equipos revolvidos en los registros del torneo
        //  - Recorrer las posiciones de los registros y asignar los ids de los equipos revolvidos
        j = 0;
        for (int k = 0; k < 4; k++) {
            int pos = posRegistros[k];
            Registro r = registros[pos];
            int equipo1 = equipoIds[j++];
            r.setEquipo1(equipo1);
            int equipo2 = equipoIds[j++];
            r.setEquipo2(equipo2);
        }
    }

    @Override
    public boolean avanzarFase(int idTorneo) {
        Torneo torneo = getTorneo(idTorneo);
        if (torneo == null) { return false; }
        int idFaseActual = torneo.getFase();
        switch (idFaseActual) {
            case FASE_SIN_EMPEZAR_ID:
                return avanzarFaseSinEmpezar(torneo);
            case FASE_CUARTOS_ID:
                return avanzarFaseCuartos(torneo);
            case FASE_SEMIFINALES_ID:
                return avanzarFaseSemifinales(torneo);
            case FASE_TERCER_CUARTO_ID:
            case FASE_FINAL_ID:
                return avanzarFaseFinales(torneo);
            case FASE_TERMINADO_ID:
                return false;// Nunca se puede avanzar desde Terminado
            default:
                return false;
        }
    }

    private boolean checkFinFase(int idTorneo, int idFaseActual) {
        // TODO 14: Implementar la comprobación de si se puede avanzar de fase
        // Nunca se puede avanzar desde Terminado
        // Se puede avanzar de fase si todos los registros de la fase actual tienen un ganador distinto de cero
        // Para ello, recorrer todos los registros buscando los del torneo y fase actual
        // Si la fase actual es tercer y cuarto puesto o final, comprobar ambos registros

        Torneo t = torneos[idTorneo];
        if(t == null && t.getFase() != FASE_SIN_EMPEZAR_ID){
            return false;
        }


        for (int i = 0; i < registros.length; i++) {

            Registro r = registros[i];

            if(r != null && r.getFase() == idFaseActual && ! Boolean.parseBoolean(String.valueOf(Config.FASE_SIN_EMPEZAR_ID)) ){

            }


        }





//
//        for (int i = 0; i < registros.length; i++) {
//
//            Registro r = registros[i];
//
//            if(r !=null && !Boolean.parseBoolean(Config.FASE_TERMINADO) && r.getGanador() >0 ){
//                if(idFaseActual == FASE_TERCER_CUARTO_ID && idFaseActual == FASE_FINAL_ID){
//                    avanzarFase(idTorneo);
//                }
//
//            }
//
//
//        }











        return true;

    }

    /**
     * Se supone que los equipos jugarán emparejados como están en los registros,
     * si no, antes se tenía que revolver los equipos en otra opción de la app
     * @param torneo El torneo a avanzar de fase
     * @return true Siempre se puede avanzar desde Sin Empezar
     */
    private boolean avanzarFaseSinEmpezar(Torneo torneo) {
        // TODO 15: Implementar el avance de fase desde Sin Empezar a Cuartos
        // Poner la fase de todos los registros del torneo que estuviesen en Sin Empezar a Cuartos

        for (int i = 0; i < torneos.length; i++) {

            Torneo t = torneos[i];
            if(t!=null && t == torneo && Boolean.parseBoolean(Config.FASE_SIN_EMPEZAR)){
                t.avanzarFase();
                return true;
            }

        }







        return false;
    }

    private boolean avanzarFaseCuartos(Torneo torneo) {
        // TODO 16: Implementar el avance de fase desde Cuartos a Semifinales
        // Comprobar si se puede avanzar de fase
        // Si se puede avanzar la fase del torneo
        // idFaseAnterior = FASE_CUARTOS_ID = 1, idFaseNueva = FASE_SEMIFINALES_ID = 2
        // Obtener un array auxiliar con los ids de los equipos ganadores de cuartos de los registros
        // Definir los partidos de semifinales con los ganadores de cuartos en los registros

        boolean avanzarFase = true;
        int IdFaseAnterior = 0;
        int IDFaseNueva = 0;
        for (int i = 0; i < torneos.length; i++) {

            Torneo t = torneos[i];
            if(t !=null && t == torneo &&!Boolean.parseBoolean(Config.FASE_SIN_EMPEZAR)){
                t.avanzarFase();
                return avanzarFase;
            } else if (avanzarFase) {
                if (IdFaseAnterior == FASE_CUARTOS_ID && IDFaseNueva == FASE_SEMIFINALES_ID){
                    return true;

                }
            }
        }























        return false;
    }

    private boolean avanzarFaseSemifinales(Torneo torneo) {
        // TODO 17: Implementar el avance de fase desde Semifinales a Final y Tercer y Cuarto Puesto
        // Comprobar si se puede avanzar de fase
        // Avanzar de fase el torneo
        // idFaseAnterior = FASE_SEMIFINALES_ID = 2, idFaseNueva = FASE_FINAL_ID = 4
        // Obtener un array auxiliar con los ids de los equipos ganadores de semifinales de los registros
        // Definir los partidos de tercer puesto y final con los ganadores y perdedores de semifinales en los registros





































        return true;
    }

    private boolean avanzarFaseFinales(Torneo torneo) {
        // TODO 18: Implementar el avance de fase desde Tercer y Cuarto Puesto o Final a Terminado
        // Comprobar si se puede avanzar de fase
        // idFaseAnterior = FASE_TERCER_CUARTO_ID 3 o FASE_FINAL_ID 4, idFaseNueva = FASE_TERMINADO_ID 5
        // Si se puede, avanzar la fase del torneo a Terminado






        return true;
    }

    @Override
    public boolean crearTorneo(String nombreTorneo, String arbitro, String alias, String password) {
        // TODO 19: Implementar la creación de un nuevo torneo
        // Buscar índice de torneo vacío para crear el torneo
        // Si no hay espacio para un nuevo torneo devolver false
        // Crear un nuevo torneo en fase sin empezar y asignarlo al array de torneos en su índice correspondiente
        // Crear los 8 registros para los equipos en el array de usuarios
        // en base al id del torneo asigna las posiciones correspondientes
        // utiliza como alias, password y nombre el formato: tX_equipoY
        // siendo x el id del torneo e y el número de equipo del 1 al 8
        // el rol es de equipo
        // Crear el usuario árbitro si se ha indicado, sino ponlo a null
        // Crear el usuario espectador por defecto en el índice idTorneo*10
        // Resetea los registros de los partidos del torneo
        // pon los equipos que van a jugar en cuartos a priori (sin revolver)
        // el equipo 1 contra el 2, el 3 contra el 4, etc.
        // establece la fase de esos registros a sin empezar
        // Resetea todos los resultados a 0-0






































        return true;
    }

    @Override
    public EquipoDto[] getEquiposTorneo(int idTorneo) {
        // TODO 20: Implementar la obtención de los equipos de un torneo
        // Devuelve un array con la referencia a los 8 equipos del torneo
        EquipoDto[] equipos = new EquipoDto[8];


        for (int i = 0; i < torneos.length; i++) {

            Torneo t = torneos[i];

            if (t != null && t.getId() == idTorneo){
                return equipos;
            }

        }





        return equipos;
    }

    @Override
    public UsuarioRolDto[] getUsuariosRolsEaj(int idTorneo) {
        // TODO 21: Implementar la obtención de los usuarios con rol equipo, árbitro o espectador de un torneo
        // Si el torneo no existe devolver un array de tamaño 0
        // El array devuelto no debe tener referencias nulas y su tamaño debe ser el justo
        UsuarioRolDto[] sel = new UsuarioRolDto[usuarios.length];
        int i = 0;






        for (int j = 0; j < torneos.length; j++) {
            Torneo s = torneos[j];
            if(s != null && s.getId() == idTorneo){
                for (int k = 0; k < usuarios.length; k++) {
                    Usuario t = usuarios[k];

                    if(t !=null && t.getTorneo() == idTorneo && (t.getRol()== ROL_ARBIT_ID || t.getRol() == ROL_EQUIPO_ID || t.getRol() == ROL_ESPEC_ID)){

                        sel[i++] = new UsuarioRolDto(t.getNombre(),t.getRol());


                    }


                }
            }
            if (s !=null && s.getId() != idTorneo){
                return new UsuarioRolDto[0];
            }

        }













        return Arrays.copyOfRange(sel, 0, i);
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) {
        // TODO 22: Implementar la obtención de un usuario por su id
        // Si no existe devolver null, asegurarse de que el id está en rango

        for (int i = 0; i < usuarios.length; i++) {
            Usuario u = usuarios[i];

            if (u != null && u.getId() == idUsuario){
                return u;
            }else {
                return null;
            }

        }


        return usuarios[idUsuario];
    }

    @Override
    public boolean insertUsuario(Usuario usuario) {
        // TODO 23: Implementar la inserción de un nuevo usuario de rol equipo, árbitro o espectador
        // Buscar espacio en el array de usuarios entre los id 51 y 60 (ambos incluidos)


        for (int i = 0; i < usuarios.length; i++) {
            Usuario u = usuarios[i];

            if(usuario!= null && usuario.getRol() == ROL_ARBIT_ID){
                usuario.setRol(ROL_ARBIT_ID);
            }

            if (usuario != null && usuario.getRol() == ROL_EQUIPO_ID){
                usuario.setRol(ROL_EQUIPO_ID);
            }

            if (usuario !=null && usuario.getRol() == ROL_ESPEC_ID){
                usuario.setRol(ROL_ESPEC_ID);
            }

        }




        return false;
    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        // TODO 24: Implementar la actualización de un usuario existente

        for (int i = 0; i < usuarios.length; i++) {

            Usuario u = usuarios[i];
            if(usuario !=null && usuario == u ){

//                usuario.se;
            }
        }

        return true;
    }

    @Override
    public boolean deleteUsuarioEaj(String alias) {
        // TODO 25: Implementar el borrado de un usuario de rol equipo, árbitro o espectador por su alias
        // Devuelve true si se borra (podría ser que no existiese)


        for (int i = 0; i < usuarios.length; i++) {

            Usuario u = usuarios[i];

            if (u != null && u.getAlias() == alias && alias == ROL_EQUIPO || alias == ROL_ARBIT || alias == ROL_ESPEC){

                return true;

            }

        }








        return false;
    }

    @Override
    public void borrarTorneo(int idTorneo) {
        // TODO 26: Implementar el borrado de un torneo por su id
        // Borrar el torneo y todos sus datos asociados
        // Poner a null la referencia de torneos
        // Poner a null la referencia de los usuarios del torneo
        // Resetea los registros de los partidos del torneo
        // Resetea todos los resultados a 0-0


        TorneoFaseDto[] torneosResultados = new TorneoFaseDto[torneos.length];

        for (int i = 0; i < torneos.length; i++) {
            Torneo t = torneos[i];

            if(t !=null && t.getId() == idTorneo){

                t.setId(0);
                t.setNombre(null);
                t.setFase(0);
                torneosResultados = null;
                for (int j = 0; j < usuarios.length; j++) {

                    Usuario u = usuarios[i];
                    u.setTorneo(0);
                    u.setId(0);
                    u.setAlias(null);
                    u.setRol(0);
                    u.setNombre(null);
                    u.setPassword(null);
                    u.setEsEquipo(false);

                }

            }



        }


















    }

}
