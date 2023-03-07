package com.omarinhos.servlet.repositories;

import com.omarinhos.servlet.configs.Repository;
import com.omarinhos.servlet.models.entities.Usuario;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
@RepositoryJpa
public class UsuarioRepositoryJpaImpl implements UsuarioRepository{

    @Inject
    EntityManager em;

    @Override
    public List<Usuario> listar() throws Exception {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    @Override
    public Usuario porId(Long id) throws Exception {
        return em.find(Usuario.class, id);
    }

    @Override
    public void guardar(Usuario usuario) throws Exception {
        if (usuario.getId() != null && usuario.getId() > 0) {
            em.merge(usuario);
        } else {
            em.persist(usuario);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }

    @Override
    public Usuario porUsername(String username) throws Exception {
        return em.createQuery("select u from Usuario u where u.username = :username", Usuario.class)
        .setParameter("username", username).getSingleResult();
    }
}
