package com.omarinhos.servlet.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object logging(InvocationContext invocationContext) throws Exception {
        log.info(" ***** entrando antes de invocar el método "
                + invocationContext.getMethod().getName() + " de la clase " +
                invocationContext.getMethod().getDeclaringClass()) ;
        Object resultado = invocationContext.proceed();

        log.info(" ***** saliendo de la invocacion del metodo " +
                invocationContext.getMethod().getName());
        return resultado;
    }

}
