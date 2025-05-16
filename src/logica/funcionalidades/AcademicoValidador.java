package logica.funcionalidades;

import accesoadatos.dto.AcademicoEvaluadorDTO;

public class AcademicoValidador {
    
    public static void validarNumeroDeTrabajador(String numeroDeTrabajador, int longitudExacta) {
        if (numeroDeTrabajador.isEmpty()) {
            throw new IllegalArgumentException("El número de trabajador es obligatorio");
        }
        if (numeroDeTrabajador.length() != longitudExacta) {
            throw new IllegalArgumentException("El número de trabajador debe tener exactamente "+longitudExacta+" caracteres");
        }
    }
    
    public static void validarNombre(String nombreAcademico, int longitudMaxima) {
        if (nombreAcademico.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (nombreAcademico.length() > longitudMaxima) {
            throw new IllegalArgumentException("El nombre no puede exceder los " + longitudMaxima + " caracteres");
        }
    }
    
   
    public static void validarAcademico(AcademicoEvaluadorDTO academicoEvaluadorDTO) {
        validarNumeroDeTrabajador(academicoEvaluadorDTO.getNumeroDeTrabajador(),9);
        validarNombre(academicoEvaluadorDTO.getNombreAcademico(), 100);
    }
}
