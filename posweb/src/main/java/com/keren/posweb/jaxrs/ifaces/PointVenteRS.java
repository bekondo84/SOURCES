
package com.keren.posweb.jaxrs.ifaces;

import com.keren.posweb.model.PointVente;
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

 * @since Wed Sep 05 10:26:16 GMT+01:00 2018
 * 
 */
public interface PointVenteRS
    extends GenericService<PointVente, Long>
{
   
     @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("enable")
    public PointVente enable(@Context HttpHeaders headers , PointVente entity);
    
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("desable")
    public PointVente desable(@Context HttpHeaders headers , PointVente entity);

}
