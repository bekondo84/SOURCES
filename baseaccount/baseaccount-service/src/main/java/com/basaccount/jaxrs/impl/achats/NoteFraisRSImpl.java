
package com.basaccount.jaxrs.impl.achats;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.achats.NoteFraisManagerRemote;
import com.basaccount.core.ifaces.comptabilite.PeriodeComptableManagerRemote;
import com.basaccount.jaxrs.ifaces.achats.NoteFraisRS;
import com.basaccount.model.achats.LigneNoteFrais;
import com.basaccount.model.achats.NoteFrais;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
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

 * @since Fri Mar 16 09:30:48 GMT+01:00 2018
 * 
 */
@Path("/notefrais")
public class NoteFraisRSImpl
    extends AbstractGenericService<NoteFrais, Long>
    implements NoteFraisRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "NoteFraisManagerImpl", interf = NoteFraisManagerRemote.class)
    protected NoteFraisManagerRemote manager;    
    
    @Manager(application = "baseaccount", name = "PeriodeComptableManagerImpl", interf = PeriodeComptableManagerRemote.class)
    protected PeriodeComptableManagerRemote periodemanager;

    public NoteFraisRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<NoteFrais, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new NoteFrais(), new HashMap<String, MetaData>(), new ArrayList<String>()); //To change body of generated methods, choose Tools | Templates.
        } catch (InstantiationException ex) {
            Logger.getLogger(NoteFraisRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NoteFraisRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta;
    }

    @Override
    public NoteFrais validate(HttpHeaders headers, NoteFrais entity) {
        if(entity.getDate()==null){
            throw new KerenExecption("Veuillez saisir la date");
        }else if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("Le numéro de pièce est obligatoire");
        }else if(entity.getFournisseur()==null){
            throw new KerenExecption("Veuillez saisir le Client");
        }else if(entity.getFournisseur().getCompte()==null){
            throw new KerenExecption("Veuillez renseigner le compte associé au tier");
        }else if(entity.getComptecompens()==null){
            throw new KerenExecption("Veuillez renseigner le compte");
        }else if(entity.getJournal()==null){
            throw new KerenExecption("Veuillez renseigner le journal comptable");
        }
        for(LigneNoteFrais ligne : entity.getNotes()){
            if(ligne.getCompte()==null){
                throw new KerenExecption("Veuillez renseigner le compte de la note");
            }
        }//end for(LigneNoteFrais ligne : entity.getNotes()){
        PeriodeComptable periode = periodemanager.getPeriodeFromDate(entity.getDate());        
        if(periode==null){
            throw new KerenExecption("periode.comptable.not.found");
        }//end if(periode==null){
        return manager.valider(entity, periode);
    }

    @Override
    protected void processBeforeUpdate(NoteFrais entity) {
         if(entity.getDate()==null){
            throw new KerenExecption("Veuillez saisir la date");
        }else if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("Le numéro de pièce est obligatoire");
        }else if(entity.getFournisseur()==null){
            throw new KerenExecption("Veuillez saisir le Client");
        }else if(entity.getFournisseur().getCompte()==null){
            throw new KerenExecption("Veuillez renseigner le compte associé au tier");
        }else if(entity.getComptecompens()==null){
            throw new KerenExecption("Veuillez renseigner le compte");
        }else if(entity.getJournal()==null){
            throw new KerenExecption("Veuillez renseigner le journal comptable");
        }
        for(LigneNoteFrais ligne : entity.getNotes()){
            if(ligne.getCompte()==null){
                throw new KerenExecption("Veuillez renseigner le compte de la note");
            }
        }//end for(LigneNoteFrais ligne : entity.getNotes()){
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(NoteFrais entity) {
         if(entity.getDate()==null){
            throw new KerenExecption("Veuillez saisir la date");
        }else if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("Le numéro de pièce est obligatoire");
        }else if(entity.getFournisseur()==null){
            throw new KerenExecption("Veuillez saisir le Client");
        }else if(entity.getFournisseur().getCompte()==null){
            throw new KerenExecption("Veuillez renseigner le compte associé au tier");
        }else if(entity.getComptecompens()==null){
            throw new KerenExecption("Veuillez renseigner le compte");
        }else if(entity.getJournal()==null){
            throw new KerenExecption("Veuillez renseigner le journal comptable");
        }
        for(LigneNoteFrais ligne : entity.getNotes()){
            if(ligne.getCompte()==null){
                throw new KerenExecption("Veuillez renseigner le compte de la note");
            }
        }//end for(LigneNoteFrais ligne : entity.getNotes()){
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
