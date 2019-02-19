
package com.basaccount.jaxrs.impl.ventes;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.ventes.ReglementClientManagerRemote;
import com.basaccount.jaxrs.ifaces.ventes.ReglementClientRS;
import com.basaccount.model.ventes.LigneReglementClient;
import com.basaccount.model.ventes.ReglementClient;
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

 * @since Wed Jan 16 11:12:51 WAT 2019
 * 
 */
@Path("/reglementclient")
public class ReglementClientRSImpl
    extends AbstractGenericService<ReglementClient, Long>
    implements ReglementClientRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "ReglementClientManagerImpl", interf = ReglementClientManagerRemote.class)
    protected ReglementClientManagerRemote manager;

    public ReglementClientRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<ReglementClient, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new ReglementClient(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(ReglementClientRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReglementClientRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeUpdate(ReglementClient entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("numero.piece.required");
        }else if(entity.getDate()==null){
            throw new KerenExecption("date.piece.required");
        }else if(entity.getFournisseur()==null){
            throw new KerenExecption("client.required");
        }else if(entity.getModereglement()==null){
            throw new KerenExecption("mode.reglement.required");
        }else if(entity.getJournal()==null){
            throw new KerenExecption("journal.comptable.required");
        }else if(entity.getSource()==null||entity.getSource().trim().isEmpty()){
            throw new KerenExecption("reference.reglement.required");
        }else if(entity.getLignes().isEmpty()){
            throw new KerenExecption("lignes.reglement.isempty");
        }        
//        valideLine(entity);
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(ReglementClient entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("numero.piece.required");
        }else if(entity.getDate()==null){
            throw new KerenExecption("date.piece.required");
        }else if(entity.getFournisseur()==null){
            throw new KerenExecption("client.required");
        }else if(entity.getModereglement()==null){
            throw new KerenExecption("mode.reglement.required");
        }else if(entity.getJournal()==null){
            throw new KerenExecption("journal.comptable.required");
        }else if(entity.getSource()==null||entity.getSource().trim().isEmpty()){
            throw new KerenExecption("reference.reglement.required");
        }else if(entity.getLignes().isEmpty()){
            throw new KerenExecption("lignes.reglement.isempty");
        }
//        valideLine(entity);
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

//    private void valideLine(ReglementClient entity){
//        for(LigneReglementClient ligne:entity.getLignes()){
//            if(ligne.getSolde()>0){
//                throw new KerenExecption("montant.reglement.superieur.au.solde");
//            }
//        }
//    }
    
}
