package edu.masanz.da.torneo.model;

public class Rol {

    private int id;
    private String nombre;

    public Rol() {
        this(0, "");
    }

    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
    // endregion

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<rol id='").append(id).append("'>");
        sb.append("<nombre>").append(nombre).append("</nombre>");
        sb.append("</rol>");
        return sb.toString();
    }
}
