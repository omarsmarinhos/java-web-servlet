package com.omarinhos.servlet.repositories;

import com.omarinhos.servlet.models.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario> {

    Usuario porUsername(String username) throws Exception;

}
