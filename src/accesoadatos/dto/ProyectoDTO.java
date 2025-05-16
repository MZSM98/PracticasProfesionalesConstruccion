package accesoadatos.dto;

public class ProyectoDTO {
    
    private int proyectoID;
    private String titulo;
    private String descripcion;
    private String periodoEscolar;
    private String rfcMoral;
    
    public int getProyectoID() {
        return proyectoID;
    }
    
    public void setProyectoID(int proyectoID) {
        this.proyectoID = proyectoID;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getPeriodoEscolar() {
        return periodoEscolar;
    }
    
    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }
    
    public String getRfcMoral() {
        return rfcMoral;
    }
    
    public void setRfcMoral(String rfcMoral) {
        this.rfcMoral = rfcMoral;
    }
}