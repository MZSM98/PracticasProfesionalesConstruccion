package logica.funcionalidades;

import accesoadatos.dto.OrganizacionVinculadaDTO;


public class OrganizacionVinculadaValidador {
    
    public static void validarRfcMoral(String rfcMoral, int longitudExacta) {
        
        if (rfcMoral.isEmpty()) {
            
            throw new IllegalArgumentException("El RFC es obligatorio");
        }
        if (rfcMoral.length() != longitudExacta) {
            
            throw new IllegalArgumentException("El RFC debe tener exactamente 12 caracteres");
        }
    }
    
    public static void validarNombre(String nombreOV, int longitudMaxima) {
        
        if (nombreOV.isEmpty()) {
            
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (nombreOV.length() > longitudMaxima) {
            
            throw new IllegalArgumentException("El nombre no puede exceder los " + longitudMaxima + " caracteres");
        }
    }
    
    public static void validarTelefono(String telefonoOV, int longitudExacta) {
        
        if (telefonoOV.isEmpty()) {
            
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (!telefonoOV.matches("\\d+")) {
            
            throw new IllegalArgumentException("El teléfono debe contener solo números");
        }
        if (telefonoOV.length() != longitudExacta) {
            
            throw new IllegalArgumentException("El teléfono debe tener exactamente 10 caracteres");
        }
        
    }
    
    public static void validarDireccion(String direccionOV, int longitudMaxima) {
        if (direccionOV.isEmpty()) {
            
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (direccionOV.length() > longitudMaxima) {
            
            throw new IllegalArgumentException("La dirección no puede exceder los " + longitudMaxima + " caracteres");
        }
    }
    
    public static void validarOrganizacionVinculada(OrganizacionVinculadaDTO organizacionVinculadaDTO) {
        
        validarRfcMoral(organizacionVinculadaDTO.getRfcMoral(),12);
        validarNombre(organizacionVinculadaDTO.getNombreOV(), 45);
        validarTelefono(organizacionVinculadaDTO.getTelefonoOV(),10);
        validarDireccion(organizacionVinculadaDTO.getDireccionOV(), 200);
    }
    
}