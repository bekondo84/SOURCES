
package com.basaccount.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.comptabilite.PeriodeComptableManagerRemote;
import com.basaccount.jaxrs.ifaces.comptabilite.PeriodeComptableRS;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Wed Jan 16 14:16:29 WAT 2019
 * 
 */
@Path("/periodecomptable")
public class PeriodeComptableRSImpl
    extends AbstractGenericService<PeriodeComptable, Long>
    implements PeriodeComptableRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "PeriodeComptableManagerImpl", interf = PeriodeComptableManagerRemote.class)
    protected PeriodeComptableManagerRemote manager;

    public PeriodeComptableRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<PeriodeComptable, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new PeriodeComptable(), new HashMap<String, MetaData>(), new ArrayList<String>()); //To change body of generated methods, choose Tools | Templates.
        } catch (InstantiationException ex) {
            Logger.getLogger(PeriodeComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PeriodeComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return meta;
    }

    @Override
    public List<ExerciceComptable> getExercices(HttpHeaders headers) {
        //To change body of generated methods, choose Tools | Templates.
        return manager.getExercices();
    }

    @Override
    public PeriodeComptable open(PeriodeComptable entity) {       
        if(!entity.getExercice().getState().equalsIgnoreCase("open")){
            throw new KerenExecption("baseaccount.periodecomptable.open.exercice");
        }//end if(!entity.getExercice().getState().equalsIgnoreCase("open")){
         //To change body of generated methods, choose Tools | Templates.
        entity.setState("open");
        manager.update(entity.getId(), entity);
        return entity;
    }

    @Override
    public PeriodeComptable close(PeriodeComptable entity) {
        //To change body of generated methods, choose Tools | Templates.
         if(!entity.getExercice().getState().equalsIgnoreCase("open")){
            throw new KerenExecption("baseaccount.periodecomptable.open.exercice");
        }//end if(!entity.getExercice().getState().equalsIgnoreCase("open")){
         //To change body of generated methods, choose Tools | Templates.
        entity.setState("close");
        manager.update(entity.getId(), entity);
        return entity;
    }
    
    

}
