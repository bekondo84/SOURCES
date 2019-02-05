
package com.basaccount.jaxrs.impl.search;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.search.PieceComptableViewManagerRemote;
import com.basaccount.jaxrs.ifaces.search.PieceComptableViewRS;
import com.basaccount.model.search.PieceComptableView;
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

 * @since Tue Feb 05 11:56:28 WAT 2019
 * 
 */
@Path("/piececomptableview")
public class PieceComptableViewRSImpl
    extends AbstractGenericService<PieceComptableView, Long>
    implements PieceComptableViewRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "PieceComptableViewManagerImpl", interf = PieceComptableViewManagerRemote.class)
    protected PieceComptableViewManagerRemote manager;

    public PieceComptableViewRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<PieceComptableView, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new PieceComptableView(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(PieceComptableViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PieceComptableViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PieceComptableView save(HttpHeaders headers, PieceComptableView entity) {
        return entity; //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
