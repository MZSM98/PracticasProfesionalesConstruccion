
package accesoadatos.dto;

public class OrganizacionVinculadaDTO {

    private String rfcMoral;
    private String nombreOV;
    private String telefonoOV;
    private String direccionOV;
            
    public String getRfcMoral() {
        return rfcMoral;
    }

    public void setRfcMoral(String rfcMoral) {
        this.rfcMoral = rfcMoral;
    }

    public String getNombreOV() {
        return nombreOV;
    }

    public void setNombreOV(String nombreOV) {
        this.nombreOV = nombreOV;
    }

    public String getDireccionOV() {
        return direccionOV;
    }

    public void setDireccionOV(String direccionOV) {
        this.direccionOV = direccionOV;
    }

    public String getTelefonoOV() {
        return telefonoOV;
    }

    public void setTelefonoOV(String telefonoOV) {
        this.telefonoOV = telefonoOV;
    }
    
}
