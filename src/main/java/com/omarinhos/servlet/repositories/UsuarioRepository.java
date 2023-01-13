package com.omarinhos.servlet.repositories;

import com.omarinhos.servlet.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {

    Usuario porUsername(String username) throws SQLException;

}
