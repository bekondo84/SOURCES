
package com.basaccount.jaxrs.ifaces.ventes;

import com.basaccount.model.ventes.NoteFraisVente;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


/**
 * Interface du service JAX-RS

 * @since Tue Jan 15 23:13:06 WAT 2019
 * 
 */
public interface NoteFraisVenteRS
    extends GenericService<NoteFraisVente, Long>
{

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("validate")
    public NoteFraisVente valider(@Context HttpHeaders headers,NoteFraisVente entity);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("imprime")
    public NoteFraisVente imprimer(@Context HttpHeaders headers,NoteFraisVente entity);
}
