package pe.edu.ecommerceZapatillas.dto;

import pe.edu.ecommerceZapatillas.entity.Roles;

public record UsuariosDto(Integer id ,String nombre, String email, Integer rolId, String contrasenia) {
}
