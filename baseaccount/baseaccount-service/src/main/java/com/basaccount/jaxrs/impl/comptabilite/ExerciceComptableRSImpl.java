
package com.basaccount.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.comptabilite.ExerciceComptableManagerRemote;
import com.basaccount.core.ifaces.comptabilite.PeriodeComptableManagerRemote;
import com.basaccount.jaxrs.ifaces.comptabilite.ExerciceComptableRS;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.commons.DateHelper;
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

 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
@Path("/exercicecomptable")
public class ExerciceComptableRSImpl
    extends AbstractGenericService<ExerciceComptable, Long>
    implements ExerciceComptableRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "ExerciceComptableManagerImpl", interf = ExerciceComptableManagerRemote.class)
    protected ExerciceComptableManagerRemote manager;
    
    @Manager(application = "baseaccount", name = "PeriodeComptableManagerImpl", interf = PeriodeComptableManagerRemote.class)
    protected PeriodeComptableManagerRemote periodemanager;

    public ExerciceComptableRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<ExerciceComptable, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new ExerciceComptable(), new HashMap<String, MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(ExerciceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExerciceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ExerciceComptable> filter(HttpHeaders headers, int firstResult, int maxResult) {
        return super.filter(headers, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    protected void processBeforeUpdate(ExerciceComptable data) {
//        System.out.println(ExerciceComptableRSImpl.class.getName()+" ================"+data);
        ExerciceComptable entity = (ExerciceComptable) data;
          if(entity.getDebut()==null){
            throw new KerenExecption("Le champ date de debut est obligatoire");
        }else if(entity.getFin()==null){
            throw new KerenExecption("Le champ date de fin est obligatoire");
        }else if(DateHelper.numberOfMonth(entity.getDebut(), entity.getFin())<12){
            throw new KerenExecption("Un exercice comptable doit contenir 12 Mois");            
        }
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(ExerciceComptable data) {
         ExerciceComptable entity = (ExerciceComptable) data;
        if(entity.getDebut()==null){
            throw new KerenExecption("Le champ date de debut est obligatoire");
        }else if(entity.getFin()==null){
            throw new KerenExecption("Le champ date de fin est obligatoire");
        }else if(DateHelper.numberOfMonth(entity.getDebut(), entity.getFin())<12){
            throw new KerenExecption("Un exercice comptable doit contenir 12 Mois");            
        }
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExerciceComptable mensuelle(HttpHeaders headers, ExerciceComptable entity) {
         //To change body of generated methods, choose Tools | Templates.        
        return manager.mensuelle(entity);
    }

    @Override
    public ExerciceComptable trimestrielle(HttpHeaders headers, ExerciceComptable entity) {
        //To change body of generated methods, choose Tools | Templates.
        return manager.trimestrielle(entity);
    }

    @Override
    public ExerciceComptable open(HttpHeaders headers, ExerciceComptable entity) {
        entity.setState("open");
        manager.update(entity.getId(), entity);
        return entity;
    }

    @Override
    public ExerciceComptable close(HttpHeaders headers, ExerciceComptable entity) {
        entity.setState("close");
        manager.update(entity.getId(), entity);
        return entity;
    }

    
}
