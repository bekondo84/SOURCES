
package com.basaccount.jaxrs.impl.search;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.search.SearchViewManagerRemote;
import com.basaccount.jaxrs.ifaces.search.SearchViewRS;
import com.basaccount.model.search.SearchView;
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

 * @since Thu Feb 07 16:18:51 WAT 2019
 * 
 */
@Path("/searchview")
public class SearchViewRSImpl
    extends AbstractGenericService<SearchView, Long>
    implements SearchViewRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "SearchViewManagerImpl", interf = SearchViewManagerRemote.class)
    protected SearchViewManagerRemote manager;

    public SearchViewRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<SearchView, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new SearchView(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(SearchViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SearchViewRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SearchView save(HttpHeaders headers, SearchView entity) {
        return entity; //To change body of generated methods, choose Tools | Templates.
    }

    
}
