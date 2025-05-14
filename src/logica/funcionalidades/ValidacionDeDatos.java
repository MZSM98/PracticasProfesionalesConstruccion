package logica.funcionalidades;


public class ValidacionDeDatos {
    
    public static void validarRFC(String rfc) {
        if (rfc.isEmpty()) {
            throw new IllegalArgumentException("El RFC es obligatorio");
        }
        if (rfc.length() != 12) {
            throw new IllegalArgumentException("El RFC debe tener exactamente 12 caracteres");
        }
    }
    
    public static void validarNombre(String nombre, int longitudMaxima) {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (nombre.length() > longitudMaxima) {
            throw new IllegalArgumentException("El nombre no puede exceder los " + longitudMaxima + " caracteres");
        }
    }
    
    public static void validarTelefono(String telefono) {
        if (telefono.isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (telefono.length() != 10) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 10 caracteres");
        }
        if (!telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono debe contener solo números");
        }
    }
    
    public static void validarDireccion(String direccion, int longitudMaxima) {
        if (direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (direccion.length() > longitudMaxima) {
            throw new IllegalArgumentException("La dirección no puede exceder los " + longitudMaxima + " caracteres");
        }
    }
    
    public static void validarOrganizacionVinculada(String rfc, String nombre, String telefono, String direccion) {
        validarRFC(rfc);
        validarNombre(nombre, 45);
        validarTelefono(telefono);
        validarDireccion(direccion, 200);
    }
}