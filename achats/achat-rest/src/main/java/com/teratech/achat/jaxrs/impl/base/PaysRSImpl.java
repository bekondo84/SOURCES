
package com.teratech.achat.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.achat.core.ifaces.base.PaysManagerRemote;
import com.teratech.achat.jaxrs.ifaces.base.PaysRS;
import com.teratech.achat.model.base.Pays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Tue Feb 27 14:29:40 GMT+01:00 2018
 * 
 */
@Path("/pays")
public class PaysRSImpl
    extends AbstractGenericService<Pays, Long>
    implements PaysRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechachat", name = "PaysManagerImpl", interf = PaysManagerRemote.class)
    protected PaysManagerRemote manager;

    public PaysRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Pays, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechachat");
    }
    
     @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            return MetaDataUtil.getMetaData(new Pays(), new HashMap<String, MetaData>(), new ArrayList<String>()); //To change body of generated methods, choose Tools | Templates.
        } catch (InstantiationException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void processBeforeUpdate(Pays entity) {
        for(int i=0;i<entity.getEtats().size();i++){
            if(entity.getEtats().get(i).getId()<=0){
                entity.getEtats().get(i).setId(-1);
            }//end if(entity.getEtats().get(i).getId()<=0){
        }//end for(int i=0;i<entity.getEtats().size();i++){
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(Pays entity) {
        for(int i=0;i<entity.getEtats().size();i++){
            if(entity.getEtats().get(i).getId()<=0){
                entity.getEtats().get(i).setId(-1);
            }//end if(entity.getEtats().get(i).getId()<=0){
        }//end for(int i=0;i<entity.getEtats().size();i++){
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
