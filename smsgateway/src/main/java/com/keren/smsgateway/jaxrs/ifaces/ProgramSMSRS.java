
package com.keren.smsgateway.jaxrs.ifaces;

import com.keren.smsgateway.model.Contact;
import com.keren.smsgateway.model.ProgramSMS;
import com.megatimgroup.generic.jax.rs.layer.ifaces.GenericService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


/**
 * Interface du service JAX-RS

 * @since Fri Feb 01 10:56:16 WAT 2019
 * 
 */
public interface ProgramSMSRS
    extends GenericService<ProgramSMS, Long>
{

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("contacts")
    public List<Contact> contacts(@Context HttpHeaders headers);

}
