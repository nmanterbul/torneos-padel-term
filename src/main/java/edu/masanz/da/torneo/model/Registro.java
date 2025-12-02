package edu.masanz.da.torneo.model;

public class Registro {

    private int id;
    private int torneo;
    private int fase;
    private int arbitro;
    private int equipo1;
    private int equipo2;
    private int ganador;

    public Registro() {
        this(0, 0, 0, 0, 0, 0, 0);
    }

    public Registro(int id, int torneo, int fase, int arbitro, int equipo1, int equipo2, int ganador) {
        this.id = id;
        this.torneo = torneo;
        this.fase = fase;
        this.arbitro = arbitro;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.ganador = ganador;
    }

    // region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTorneo() {
        return torneo;
    }

    public void setTorneo(int torneo) {
        this.torneo = torneo;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getArbitro() {
        return arbitro;
    }

    public void setArbitro(int arbitro) {
        this.arbitro = arbitro;
    }

    public int getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(int equipo1) {
        this.equipo1 = equipo1;
    }

    public int getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(int equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    // endregion

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<registro>");
        sb.append("<torneo>").append(torneo).append("</torneo>");
        sb.append("<fase>").append(fase).append("</fase>");
        sb.append("<arbitro>").append(arbitro).append("</arbitro>");
        sb.append("<equipo1>").append(equipo1).append("</equipo1>");
        sb.append("<equipo2>").append(equipo2).append("</equipo2>");
        sb.append("<ganador>").append(ganador).append("</ganador>");
        sb.append("</registro>");
        return sb.toString();
    }
}
