package com.omarinhos.servlet.interceptors;

import com.omarinhos.servlet.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalInterceptorJpa {

    @Inject
    EntityManager em;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        try {
            log.info(" ------> Iniciando transacción " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());
            em.getTransaction().begin();
            Object result = invocation.proceed();
            em.getTransaction().commit();
            log.info(" ------> realizando commit y finalizando transacción " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());
            return result;
        } catch (ServiceJdbcException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
