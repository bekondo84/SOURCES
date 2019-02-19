
package com.basaccount.jaxrs.ifaces.operations;

import com.basaccount.model.operations.PieceComptable;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import java.util.List;
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

 * @since Sat Dec 23 09:07:30 WAT 2017
 * 
 */
public interface PieceComptableRS
    extends GenericService<PieceComptable, Long>
{

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("validate")
    public PieceComptable valider(@Context HttpHeaders headers,PieceComptable entity);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("priseenccompte")
    public List<PieceComptable> priseencompte(@Context HttpHeaders headers);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("priseenccompteachat")
    public List<PieceComptable> priseenccompteachat(@Context HttpHeaders headers);
}
