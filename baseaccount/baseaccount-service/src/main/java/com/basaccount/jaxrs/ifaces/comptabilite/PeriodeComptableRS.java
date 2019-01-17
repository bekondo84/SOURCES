
package com.basaccount.jaxrs.ifaces.comptabilite;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
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

 * @since Wed Jan 16 14:16:28 WAT 2019
 * 
 */
public interface PeriodeComptableRS
    extends GenericService<PeriodeComptable, Long>
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("exercice")
    public List<ExerciceComptable> getExercices(@Context HttpHeaders headers );
    
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("open")
    public PeriodeComptable open(PeriodeComptable entity);
    
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("close")
    public PeriodeComptable close(PeriodeComptable entity);

}
