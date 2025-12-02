package edu.masanz.da.torneo.model;

public class TorneoFaseDto {
    private int idTorneo;
    private String nombreTorneo;
    private String nombreFase;

    public TorneoFaseDto() {
        this(0, "", "");
    }

    public TorneoFaseDto(int idTorneo, String nombreTorneo, String nombreFase) {
        this.idTorneo = idTorneo;
        this.nombreTorneo = nombreTorneo;
        this.nombreFase = nombreFase;
    }

    // region Getters and Setters

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    // endregion

    public String getDescripcion() {
        return nombreTorneo + " - " + nombreFase;
    }

}
