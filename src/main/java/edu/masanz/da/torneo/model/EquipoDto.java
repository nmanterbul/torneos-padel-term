package edu.masanz.da.torneo.model;

public class EquipoDto {
    private int id;
    private String nombre;

    public EquipoDto() {
        this(0, "");
    }

    public EquipoDto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // region getters & setters

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


}
