package edu.masanz.da.torneo.model;

import static edu.masanz.da.torneo.config.Config.*;

public class UsuarioRolDto {

    private String usuario;
    private String rol;

    public UsuarioRolDto() {
        this("", "");
    }

    public UsuarioRolDto(String usuario, String rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public UsuarioRolDto(String usuario, int rol) {
        this.usuario = usuario;
        setRol(rol);
    }


// region Getters and Setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setRol(int rol) {
        switch (rol) {
            case ROL_ADMIN_ID:
                this.rol = ROL_ADMIN;
                break;
            case ROL_ARBIT_ID:
                this.rol = ROL_ARBIT;
                break;
            case ROL_EQUIPO_ID:
                this.rol = ROL_EQUIPO;
                break;
            case ROL_JUGAD_ID:
                this.rol = ROL_JUGAD;
                break;
            case ROL_ESPEC_ID:
                this.rol = ROL_ESPEC;
                break;
            default:
                this.rol = ROL_NOHAY;
                break;
        }
    }

    // endregion

}
