package edu.masanz.da.torneo.model;

public class Fase {

    private int id;
    private String nombre;

    private int numeroEquipos;

    public Fase() {
        this(0, "", 8);
    }

    public Fase(int id, String nombre, int numeroEquipos) {
        this.id = id;
        this.nombre = nombre;
        this.numeroEquipos = numeroEquipos;
    }

    // region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroEquipos() {
        return numeroEquipos;
    }

    public void setNumeroEquipos(int numeroEquipos) {
        this.numeroEquipos = numeroEquipos;
    }

    // endregion

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<fase>");
        sb.append("<id>").append(id).append("</id>");
        sb.append("<nombre>").append(nombre).append("</nombre>");
        sb.append("<numeroEquipos>").append(numeroEquipos).append("</numeroEquipos>");
        sb.append("</fase>");
        return sb.toString();
    }
}
