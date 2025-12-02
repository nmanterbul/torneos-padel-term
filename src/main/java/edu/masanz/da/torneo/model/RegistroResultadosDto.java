package edu.masanz.da.torneo.model;

import java.util.Arrays;

public class RegistroResultadosDto {
    private String equipo1;
    private String equipo2;
    private int[] valores;

    private int ganador; // 0=sin determinar, 1=equipo1, 2=equipo2

    public RegistroResultadosDto() {
        this.equipo1 = "";
        this.equipo2 = "";
        this.valores = new int[6];
        this.ganador = 0;
    }

    public RegistroResultadosDto(String equipo1, String equipo2, int[] valores, int ganador) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.valores = valores;
        this.ganador = ganador;
    }

    public RegistroResultadosDto(String equipo1, String equipo2, int v1, int v2, int v3, int v4, int v5, int v6, int ganador) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.valores = new int[]{v1, v2, v3, v4, v5, v6};
        this.ganador = ganador;
    }

    // region Getters and Setters

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int[] getValores() {
        return valores;
    }

    public void setValores(int[] valores) {
        this.valores = valores;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    // endregion

    /**
     * Obtiene el valor (1-6) del array de valores
     * @param num Número (1-6)
     * @return Valor correspondiente
     */
    public int getValor(int num) {
        return valores[num-1];
    }

    /**
     * Obtiene el valor del resultado (1-3) del equipo (1-2)
     * @param numEquipo Número de equipo (1-2)
     * @param numRes Número del valor (1-3)
     * @return Valor correspondiente
     */
    public int getValor(int numEquipo, int numRes) {
        if (numEquipo == 1) {
            return valores[2*(numRes - 1)];
        }else if (numEquipo == 2) {
            return valores[2*(numRes - 1)+1];
        }
        return 0;
    }

    public String getResultado(int num) {
        int val1 = getValor(1, num);
        int val2 = getValor(2, num);
        return String.format("%d - %d", val1, val2);
    }

    public int getNumeroResultados() {
        if (valores == null) {
            return 0;
        }
        if (valores[4] != 0 || valores[5] != 0) {
            return 3;
        }else if (valores[2] != 0 || valores[3] != 0) {
            return 2;
        } else if (valores[0] != 0 || valores[1] != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toParticipantes() {
        return String.format("%-18s vs %18s",
                equipo1, equipo2);
    }

    public String toResultado() {
        String resultado3 = " ".repeat(5);
        if (getNumeroResultados() >= 3) {
            resultado3 = getResultado(3);
        }
        String equipoGanador;
        if (ganador == 1) {
            equipoGanador = equipo1;
        } else if (ganador == 2) {
            equipoGanador = equipo2;
        } else {
            equipoGanador = "Indefinido";
        }
        return String.format("%-18s vs %18s : %s %s %s --> %s",
                equipo1, equipo2,
                getResultado(1),
                getResultado(2),
                resultado3,
                equipoGanador);
    }

    @Override
    public String toString() {
        return toResultado();
    }

}
