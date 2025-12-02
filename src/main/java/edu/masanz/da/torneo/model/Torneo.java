package edu.masanz.da.torneo.model;

import static edu.masanz.da.torneo.config.Config.*;

public class Torneo {

    private int id;
    private String nombre;
    private int fase;

    public Torneo() {
        this(0, "", 0);
    }

    public Torneo(int id, String nombre, int fase) {
        this.id = id;
        this.nombre = nombre;
        this.fase = fase;
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

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public String getIdNombre() {
        return id + ". " + nombre;
    }
    // endregion

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<torneo>");
        sb.append("<id>").append(id).append("</id>");
        sb.append("<nombre>").append(nombre).append("</nombre>");
        sb.append("<fase>").append(fase).append("</fase>");
        sb.append("</torneo>");
        return sb.toString();
    }

    /**
     * Avanza la fase del torneo al siguiente estado.
     * Si la fase actual es el tercer cuarto, se avanza a la fase final que se juegan a la vez.
     * Si la fase actual es mayor que la fase terminada, se mantiene en la fase terminada.
     * Valores de fase:
     * FASE_TERMINADO_ID = 5
     * FASE_FINAL_ID = 4
     * FASE_TERCER_CUARTO_ID = 3
     * FASE_SEMIFINALES_ID = 2
     * FASE_CUARTOS_ID = 1
     * FASE_SIN_EMPEZAR_ID = 0
     */
    public int avanzarFase() {
        this.fase += 1;
        if (this.fase == FASE_TERCER_CUARTO_ID) {
            this.fase = FASE_FINAL_ID;
        }
        if (this.fase > FASE_TERMINADO_ID) {
            this.fase = FASE_TERMINADO_ID;
        }
        return this.fase;
    }

    public String toJson() {
        return String.format("{\"id\":%d,\"nombre\":\"%s\",\"fase\":%d}", id, nombre, fase);
    }

    @Override
    public String toString() {
        return toJson();
    }

}
