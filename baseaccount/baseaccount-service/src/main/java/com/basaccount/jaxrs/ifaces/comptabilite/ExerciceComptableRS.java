
package com.basaccount.jaxrs.ifaces.comptabilite;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


/**
 * Interface du service JAX-RS

 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
public interface ExerciceComptableRS
    extends GenericService<ExerciceComptable, Long>
{

     @PUT
     @Produces({MediaType.APPLICATION_JSON})
     @Consumes({MediaType.APPLICATION_JSON})
     @Path("mensuelle")
     public ExerciceComptable mensuelle(@Context HttpHeaders headers ,ExerciceComptable entity);
     
     @PUT
     @Produces({MediaType.APPLICATION_JSON})
     @Consumes({MediaType.APPLICATION_JSON})
     @Path("trimestrielle")
     public ExerciceComptable trimestrielle(@Context HttpHeaders headers ,ExerciceComptable entity);
     
     @PUT
     @Produces({MediaType.APPLICATION_JSON})
     @Consumes({MediaType.APPLICATION_JSON})
     @Path("open")
     public ExerciceComptable open(@Context HttpHeaders headers ,ExerciceComptable entity);
     
     @PUT
     @Produces({MediaType.APPLICATION_JSON})
     @Consumes({MediaType.APPLICATION_JSON})
     @Path("close")
     public ExerciceComptable close(@Context HttpHeaders headers ,ExerciceComptable entity);
}
