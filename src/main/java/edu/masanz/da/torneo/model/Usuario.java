package edu.masanz.da.torneo.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String alias;
    private String password;
    private String nombre;
    private int rol;
    private int torneo;
    private boolean esEquipo;

    public Usuario() {
        this(0, "", "", "", 0, 0, false);
    }

    public Usuario(int id, String alias, String password, String nombre, int rol, int torneo, boolean esEquipo) {
        this.id = id;
        this.alias = alias;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
        this.torneo = torneo;
        this.esEquipo = esEquipo;
    }

    // region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getTorneo() {
        return torneo;
    }

    public void setTorneo(int torneo) {
        this.torneo = torneo;
    }

    public boolean isEsEquipo() {
        return esEquipo;
    }

    public void setEsEquipo(boolean esEquipo) {
        this.esEquipo = esEquipo;
    }

    // endregion

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<usuario id='").append(id).append("'>");
        sb.append("<alias>").append(alias).append("</alias>");
        sb.append("<password>").append(password).append("</password>");
        sb.append("<nombre>").append(nombre).append("</nombre>");
        sb.append("<rol>").append(rol).append("</rol>");
        sb.append("<torneo>").append(torneo).append("</torneo>");
        sb.append("<esEquipo>").append(esEquipo).append("</esEquipo>");
        sb.append("</usuario>");
        return sb.toString();
    }

    public String toJson() {
        return String.format("{\"id\":%d,\"alias\":\"%s\",\"password\":\"%s\",\"nombre\":\"%s\",\"rol\":%d,\"torneo\":%d,\"esEquipo\":%b}", id, alias, password, nombre, rol, torneo, esEquipo);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
