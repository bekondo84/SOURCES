
package com.keren.kerenpaie.jaxrs.impl.structures;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.keren.kerenpaie.core.ifaces.structures.TypeDemandeManagerRemote;
import com.keren.kerenpaie.jaxrs.ifaces.structures.TypeDemandeRS;
import com.keren.kerenpaie.model.structures.TypeDemande;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import javax.ws.rs.core.Context;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Thu Mar 01 10:22:26 GMT+01:00 2018
 * 
 */
@Path("/typedemande")
public class TypeDemandeRSImpl
    extends AbstractGenericService<TypeDemande, Long>
    implements TypeDemandeRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kerenpaie", name = "TypeDemandeManagerImpl", interf = TypeDemandeManagerRemote.class)
    protected TypeDemandeManagerRemote manager;

    public TypeDemandeRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<TypeDemande, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("kerenpaie");
    }
    
    @Override
    public MetaData getMetaData(HttpHeaders headers) {

        // TODO Auto-generated method stub
        try {
            return MetaDataUtil.getMetaData(new TypeDemande(),new HashMap<String, MetaData>()
                            , new ArrayList<String>());
        } catch (Exception e) {
            
            // TODO Auto-generated catch block
            throw new WebApplicationException(e);
        }
    }
    
    @Override
    public TypeDemande delete(@Context HttpHeaders headers , Long id) {

        // TODO Auto-generated method stub
        TypeDemande entity = manager.find("id", id);

        try{

            //on supprimme l'objet
            super.delete(headers,id);

        }catch(Exception ex){
            throw new KerenExecption("Suppresion impossible<br/>car cet objet est deja en cours d'utilisation par d'autres objets");
        }

        return entity;
    }
}
