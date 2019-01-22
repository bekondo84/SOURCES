
package com.keren.posweb.jaxrs.ifaces;

import com.keren.posweb.model.Caissier;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


/**
 * Interface du service JAX-RS

 * @since Mon Jan 21 13:47:31 WAT 2019
 * 
 */
public interface CaissierRS
    extends GenericService<Caissier, Long>
{
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("enable")
    public Caissier enable(@Context HttpHeaders headers , Caissier entity);
    
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("desable")
    public Caissier desable(@Context HttpHeaders headers , Caissier entity);
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("email/{usermail}")
    public Caissier getCasherByMail(@Context HttpHeaders headers ,@PathParam("usermail") String mail);

}
