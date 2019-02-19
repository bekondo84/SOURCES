
package com.basaccount.jaxrs.ifaces.achats;

import com.basaccount.model.achats.NoteFrais;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


/**
 * Interface du service JAX-RS

 * @since Fri Mar 16 09:30:48 GMT+01:00 2018
 * 
 */
public interface NoteFraisRS
    extends GenericService<NoteFrais, Long>
{

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("validate")
    public NoteFrais validate(@Context HttpHeaders headers,NoteFrais entity);

}
