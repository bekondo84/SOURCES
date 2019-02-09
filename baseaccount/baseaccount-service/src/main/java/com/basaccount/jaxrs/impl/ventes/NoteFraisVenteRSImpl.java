
package com.basaccount.jaxrs.impl.ventes;

import com.basaccount.core.ifaces.comptabilite.PeriodeComptableManagerRemote;
import javax.ws.rs.Path;
import com.basaccount.core.ifaces.ventes.NoteFraisVenteManagerRemote;
import com.basaccount.jaxrs.ifaces.ventes.NoteFraisVenteRS;
import com.basaccount.model.achats.LigneNoteFrais;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.ventes.NoteFraisVente;
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

 * @since Tue Jan 15 23:13:06 WAT 2019
 * 
 */
@Path("/notefraisvente")
public class NoteFraisVenteRSImpl
    extends AbstractGenericService<NoteFraisVente, Long>
    implements NoteFraisVenteRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "NoteFraisVenteManagerImpl", interf = NoteFraisVenteManagerRemote.class)
    protected NoteFraisVenteManagerRemote manager;
    
    @Manager(application = "baseaccount", name = "PeriodeComptableManagerImpl", interf = PeriodeComptableManagerRemote.class)
    protected PeriodeComptableManagerRemote periodemanager;

    public NoteFraisVenteRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<NoteFraisVente, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new NoteFraisVente(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(NoteFraisVenteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NoteFraisVenteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeUpdate(NoteFraisVente entity) {
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
    protected void processBeforeSave(NoteFraisVente entity) {
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
        entity.setState("etabli");
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public NoteFraisVente valider(HttpHeaders headers, NoteFraisVente entity) {
        //To change body of generated methods, choose Tools | Templates.
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
    public NoteFraisVente imprimer(HttpHeaders headers, NoteFraisVente entity) {
         //To change body of generated methods, choose Tools | Templates.
        return entity;
    }
    
    

}
