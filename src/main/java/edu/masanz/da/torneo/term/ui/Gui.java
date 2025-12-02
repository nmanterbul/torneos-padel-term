package edu.masanz.da.torneo.term.ui;

import java.util.List;
import java.util.Scanner;

import edu.masanz.da.torneo.model.*;

import static edu.masanz.da.torneo.config.Config.*;
import static edu.masanz.da.torneo.term.config.Ctes.*;

public class Gui {

    private static Scanner scanner = new Scanner(System.in);

    // region ------------------- GENERAL MOSTRAR TÍTULOS/TEXTO ------------------ //
    public static void mostrarTitulo(String titulo) {
        int n = LONG_TITULO, m1, m2;
        m1 = n - titulo.length();
        m2 = m1 / 2 - 1;
        m1 = m1 % 2 == 0? m2 : m2+1;
        System.out.println("*".repeat(n));
        System.out.println("*" + " ".repeat(m1) + titulo + " ".repeat(m2) + "*");
        System.out.println("*".repeat(n));
    }

    public static void mostrarSubtitulo(String titulo) {
        System.out.println();
        mostrarCentrado(titulo);
        System.out.println("-".repeat(LONG_TITULO));
    }

    public static void mostrarCentrado(String texto) {
        int n = LONG_TITULO, m1, m2;
        m1 = n - texto.length();
        m2 = m1 / 2;
        m1 = m1 % 2 == 0? m2 : m2+1;
        System.out.println(" ".repeat(m1) + texto + " ".repeat(m2));
    }

    public static void mostrarTexto(String txt) {
        System.out.println(txt);
    }

    // endregion

    // region ------------------- MOSTRAR TÍTULOS/TEXTO ------------------ //

    public static void mostrarTituloMenuAdmin() {
        String s = "MENÚ ADMINISTRADOR";
        mostrarTitulo(s);
    }

    public static void mostrarTituloMenuAdminAvanzar() {
        String s = "Avanzar fase";
        mostrarTitulo(s);
    }

    public static void mostrarTituloMenuGestionarUsuarios() {
        String s = "Usuarios";
        mostrarTitulo(s);
    }

    public static void mostrarTituloAutenticacion() {
        String s = "A U T E N T I C A C I Ó N";
        mostrarTitulo(s);
        System.out.println("Para salir deje algún campo vacío");
        System.out.println();
    }

    public static void mostrarTituloExit() {
        System.out.println();
        String s = "GRACIAS Y HASTA LA PRÓXIMA";
        mostrarTitulo(s);
    }

    public static void mostrarAutenticacion(boolean resultado) {
        if (resultado) {
            System.out.println("Autenticación correcta");
        } else {
            System.out.println("Autenticación incorrecta");
        }
        System.out.println();
    }

    public static void mostrarError(String txt) {
        Gui.mostrarTexto(txt);
    }

    // endregion

    // region ------------------- LEER VALORES ------------------ //
    public static int leerOpcion(String txt, int min, int max) {
        int opc = -1;
        if (max == Integer.MAX_VALUE) {
            System.out.printf("%s: ", txt);
        } else {
            System.out.printf("%s [%d,%d]: ", txt, min, max);
        }
        while(opc<min || opc>max) {
            try {
                opc = Integer.parseInt(scanner.nextLine().trim());
            }catch (Exception e) { }
            System.out.println();
        }
        return opc;
    }

    public static boolean leerConfirmacion(String txtMsg, int tam, String txtOk) {
        if (tam == 0) {
            tam = txtMsg.length();
        }
        System.out.printf("%" + tam + "s", txtMsg);
        String resp = scanner.nextLine().trim();
        return resp.toUpperCase().equals(txtOk.toUpperCase());
    }

    public static boolean leerConfirmacionSalir() {
        System.out.println();
        String s = "Desea salir [s/N]:";
        boolean b = Gui.leerConfirmacion(s, s.length(), "S");
        System.out.println();
        return b;
    }

    public static String leerTexto(String txt, int tam) {
        if (tam == 0) {
            tam = txt.length();
        }
        System.out.printf("%" + tam + "s", txt);
        return scanner.nextLine().trim();
    }

    public static int leerNumero(String txt, int tam) {
        if (tam == 0) {
            tam = txt.length();
        }
        System.out.printf("%" + tam + "s", txt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public static int leerOpcionMenu(int min, int max) {
        return leerOpcion("Opción: ", min, max);
    }

    // endregion


    // region ------------------- OPCIONES ADMINISTRADOR ------------------ //
    public static void mostrarMenuAdmin() {
        System.out.println();
        System.out.println("1. Mostrar torneos");
        System.out.println("2. Ver resultados");
        System.out.println("3. Avanzar fase");
        System.out.println("4. Crear un nuevo torneo");
        System.out.println("5. Gestionar equipos");
        System.out.println("6. Gestionar usuarios");
        System.out.println("7. Borrar torneo");
        System.out.println("0. Salir");
    }

    public static void mostrarMenuAdminAvanzar() {
        System.out.println();
        System.out.println("1. Revolver (sólo sin empezar)");
        System.out.println("2. Empezar (sólo sin empezar)");
        System.out.println("3. Avanzar");
        System.out.println("4. Terminar (sólo si concluido)");
        System.out.println("0. Salir");
    }

    public static void mostrarMenuGestionarUsuarios() {
        System.out.println();
        System.out.println("1. Mostrar usuarios");
        System.out.println("2. Agregar usuario");
        System.out.println("3. Actualizar usuario");
        System.out.println("4. Borrar usuario");
        System.out.println("0. Salir");
    }

    public static void mostrarTorneosFases(TorneoFaseDto[] torneosFases) {
        String s0 = String.format("|%s|", "-".repeat(56));
        String s1 = "| N.id | Nombre del torneo         | Fase actual         |";
        mostrarCentrado(s0);
        mostrarCentrado(s1);
        mostrarCentrado(s0);
        for (TorneoFaseDto tf : torneosFases) {
            mostrarCentrado(
                String.format("| %4d | %-25s | %-19s |",
                        tf.getIdTorneo(),
                        tf.getNombreTorneo(),
                        tf.getNombreFase())
            );
        }
        mostrarCentrado(s0);
    }

    public static void mostrarEquipos(EquipoDto[] equipos) {
        String s0 = String.format("|%s|", "-".repeat(34));
        String s1 = "| N.id | Nombre del equipo         |";
        mostrarCentrado(s0);
        mostrarCentrado(s1);
        mostrarCentrado(s0);
        for (EquipoDto e : equipos) {
            mostrarCentrado(
                    String.format("| %4d | %-25s |",
                            e.getId(),
                            e.getNombre())
            );
        }
        mostrarCentrado(s0);
        System.out.println();
    }

    public static void mostrarUsuariosRols(UsuarioRolDto[] usuarios) {
        String s0 = String.format("|%s|", "-".repeat(40));
        String s1 = "| Usuario              | Rol             |";
        mostrarCentrado(s0);
        mostrarCentrado(s1);
        mostrarCentrado(s0);
        for (UsuarioRolDto u : usuarios) {
            mostrarCentrado(
                    String.format("| %-20s | %-15s |",
                            u.getUsuario(),
                            u.getRol())
            );
        }
        mostrarCentrado(s0);
        System.out.println();
    }

    public static void mostrarRolsEaj() {
        String s0 = String.format("|%s|", "-".repeat(34));
        String s1 = "| N.id | Nombre del rol            |";
        mostrarCentrado(s0);
        mostrarCentrado(s1);
        mostrarCentrado(s0);
        mostrarCentrado( String.format("| %4d | %-25s |",
                ROL_ESPEC_ID, ROL_ESPEC) );
        mostrarCentrado( String.format("| %4d | %-25s |",
                ROL_JUGAD_ID, ROL_JUGAD) );
        mostrarCentrado( String.format("| %4d | %-25s |",
                ROL_ARBIT_ID, ROL_ARBIT) );
        mostrarCentrado(s0);
        System.out.println();
    }

    public static void mostrarResultadosTorneo(RegistroResultadosDto[] resultados) {
        mostrarSubtitulo("Cuartos de final");
        for (int i = 0; i < 4; i++) {
            System.out.println(resultados[i]);
        }
        mostrarSubtitulo("Semifinales");
        for (int i = 4; i < 6; i++) {
            System.out.println(resultados[i]);
        }
        mostrarSubtitulo("Tercer puesto");
        System.out.println(resultados[6]);
        mostrarSubtitulo("Final");
        System.out.println(resultados[7]);
    }

    public static void mostrarResultadosTorneoSinEmpezar(RegistroResultadosDto[] resultados, int fase) {
        if (fase == FASE_SIN_EMPEZAR_ID) {
            mostrarSubtitulo("Sin empezar");
            for (int i = 0; i < 4; i++) {
                mostrarCentrado(resultados[i].toParticipantes());
            }
        }
    }

    public static void mostrarResultadosTorneo(RegistroResultadosDto[] resultados, int fase) {
        if (fase == FASE_SIN_EMPEZAR_ID) {
            mostrarSubtitulo("Sin empezar");
            for (int i = 0; i < 4; i++) {
                System.out.println(resultados[i]);
            }
        }
        if (fase == FASE_CUARTOS_ID) {
            mostrarSubtitulo("Cuartos de final");
            for (int i = 0; i < 4; i++) {
                System.out.println(resultados[i]);
            }
        }
        if (fase == FASE_SEMIFINALES_ID) {
            mostrarSubtitulo("Semifinales");
            for (int i = 4; i < 6; i++) {
                System.out.println(resultados[i]);
            }
        }
        if (fase >= FASE_TERCER_CUARTO_ID) {
            mostrarSubtitulo("Tercer puesto");
            System.out.println(resultados[6]);
            mostrarSubtitulo("Final");
            System.out.println(resultados[7]);
        }
    }
    // endregion

    public static void mostrarListado(List<String> lista) {
        for (Object obj : lista) {
            System.out.println(obj);
        }
    }

}
