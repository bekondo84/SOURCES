
package com.basaccount.jaxrs.ifaces.operations;

import com.basaccount.model.operations.OperationBancaire;
import com.basaccount.model.operations.PieceComptable;
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

 * @since Wed Jan 16 11:53:28 WAT 2019
 * 
 */
public interface OperationBancaireRS
    extends GenericService<OperationBancaire, Long>
{

     @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("validate")
    public OperationBancaire valider(@Context HttpHeaders headers,OperationBancaire entity);

}
