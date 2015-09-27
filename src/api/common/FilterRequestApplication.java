package api.common;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by root on 27/09/15.
 */
@Provider
@PreMatching
public class FilterRequestApplication implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger( FilterRequestApplication.class.getName() );

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.info( "Executing REST request filter" );

        if ( requestContext.getRequest().getMethod().equals( "OPTIONS" ) ) {
            log.info( "HTTP Method (OPTIONS) - Detected!" );

            requestContext.abortWith( Response.status(Response.Status.OK).build() );
        }
    }
}
