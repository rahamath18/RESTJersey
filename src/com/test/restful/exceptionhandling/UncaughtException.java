package com.test.restful.exceptionhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 

/*
 *     If you want to handle all uncaught exception before going to user screen, 
 *     you will have to map the Throwable itself.
 */

@Provider
public class UncaughtException extends Throwable implements ExceptionMapper<Throwable>
{
    private static final long serialVersionUID = 1L;
  
    @Override
    public Response toResponse(Throwable exception)
    {
        return Response.status(500).entity("Something bad happened. Please try again !!").type("text/plain").build();
    }
}
