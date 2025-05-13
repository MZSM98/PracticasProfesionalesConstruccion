
package accesoadatos.dto;

import java.sql.Date;

public class ProyectoDTO {
    
    private String tituloProyecto;
    private String descripcionProyecto;
    private Date fechaInicioProyecto;
    private Date fechaFinalProyecto;
    private String periodoEscolar;

    public String getTituloProyecto() {
        return tituloProyecto;
    }

    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public Date getFechaInicioProyecto() {
        return fechaInicioProyecto;
    }

    public void setFechaInicioProyecto(Date fechaInicioProyecto) {
        this.fechaInicioProyecto = fechaInicioProyecto;
    }

    public Date getFechaFinalProyecto() {
        return fechaFinalProyecto;
    }

    public void setFechaFinalProyecto(Date fechaFinalProyecto) {
        this.fechaFinalProyecto = fechaFinalProyecto;
    }

    public String getPeriodoEscolar() {
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }
    
    
}
