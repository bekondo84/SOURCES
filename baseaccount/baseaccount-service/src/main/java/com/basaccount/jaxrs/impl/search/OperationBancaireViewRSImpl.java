
package com.basaccount.jaxrs.impl.search;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.search.OperationBancaireViewManagerRemote;
import com.basaccount.jaxrs.ifaces.search.OperationBancaireViewRS;
import com.basaccount.model.search.OperationBancaireView;
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
@Path("/operationbancaireview")
public class OperationBancaireViewRSImpl
    extends AbstractGenericService<OperationBancaireView, Long>
    implements OperationBancaireViewRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "OperationBancaireViewManagerImpl", interf = OperationBancaireViewManagerRemote.class)
    protected OperationBancaireViewManagerRemote manager;

    public OperationBancaireViewRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<OperationBancaireView, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta =null;
        try {
            meta = MetaDataUtil.getMetaData(new OperationBancaireView(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(OperationBancaireViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(OperationBancaireViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public OperationBancaireView save(HttpHeaders headers, OperationBancaireView entity) {
        return entity; //To change body of generated methods, choose Tools | Templates.
    }

    
}
