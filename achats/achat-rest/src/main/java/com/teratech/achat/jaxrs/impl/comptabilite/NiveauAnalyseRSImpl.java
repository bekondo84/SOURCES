
package com.teratech.achat.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.achat.core.ifaces.comptabilite.NiveauAnalyseManagerRemote;
import com.teratech.achat.jaxrs.ifaces.comptabilite.NiveauAnalyseRS;
import com.teratech.achat.jaxrs.impl.base.CiviliteRSImpl;
import com.teratech.achat.model.base.Civilite;
import com.teratech.achat.model.comptabilite.NiveauAnalyse;
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
@Path("/niveauanalyse")
public class NiveauAnalyseRSImpl
    extends AbstractGenericService<NiveauAnalyse, Long>
    implements NiveauAnalyseRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechachat", name = "NiveauAnalyseManagerImpl", interf = NiveauAnalyseManagerRemote.class)
    protected NiveauAnalyseManagerRemote manager;

    public NiveauAnalyseRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<NiveauAnalyse, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechachat");
    }
    
     @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            return MetaDataUtil.getMetaData(new NiveauAnalyse(), new HashMap<String, MetaData>(), new ArrayList<String>()); //To change body of generated methods, choose Tools | Templates.
        } catch (InstantiationException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
