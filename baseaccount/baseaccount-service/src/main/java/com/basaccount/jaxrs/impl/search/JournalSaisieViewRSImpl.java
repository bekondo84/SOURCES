
package com.basaccount.jaxrs.impl.search;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.search.JournalSaisieViewManagerRemote;
import com.basaccount.jaxrs.ifaces.search.JournalSaisieViewRS;
import com.basaccount.model.search.JournalSaisieView;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Thu Jan 17 09:54:18 WAT 2019
 * 
 */
@Path("/journalsaisieview")
public class JournalSaisieViewRSImpl
    extends AbstractGenericService<JournalSaisieView, Long>
    implements JournalSaisieViewRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "JournalSaisieViewManagerImpl", interf = JournalSaisieViewManagerRemote.class)
    protected JournalSaisieViewManagerRemote manager;

    public JournalSaisieViewRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<JournalSaisieView, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new JournalSaisieView(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(JournalSaisieViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JournalSaisieViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
