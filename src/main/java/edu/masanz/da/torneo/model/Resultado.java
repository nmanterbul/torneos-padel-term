package edu.masanz.da.torneo.model;

public class Resultado {
    private int id;
    private int registro;
    private int numero;

    private int valor1;
    private int valor2;

    public Resultado() {
        this(0, 0, 0, 0, 0);
    }

    public Resultado(int id, int registro, int numero, int valor1, int valor2) {
        this.id = id;
        this.registro = registro;
        this.numero = numero;
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    // region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getValor1() {
        return valor1;
    }

    public void setValor1(int valor1) {
        this.valor1 = valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }

    // endregion

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<resultado>");
        sb.append("<numero>").append(numero).append("</numero>");
        sb.append("<valor1>").append(valor1).append("</valor1>");
        sb.append("<valor2>").append(valor2).append("</valor2>");
        sb.append("</resultado>");
        return sb.toString();
    }

}
