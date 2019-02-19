
package com.basaccount.jaxrs.impl.achats;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.achats.FactureManagerRemote;
import com.basaccount.jaxrs.ifaces.achats.FactureRS;
import com.basaccount.model.achats.DocumentAchatState;
import com.basaccount.model.achats.Facture;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaColumn;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Wed Mar 14 21:10:27 GMT+01:00 2018
 * 
 */
@Path("/facture")
public class FactureRSImpl
    extends AbstractGenericService<Facture, Long>
    implements FactureRS
{

    public static String FOOTER="<tr style='border:none;'> <td></td><td></td><td></td><td></td><td></td><td style='font-weight: bold;'>Total HT</td><td class='text-right'>this.quantite;*;this.puht;*;(;100;-;this.remise;);/;100</td><td></td></tr><tr style='border:none;'> <td></td><td></td><td></td><td></td><td></td><td style='font-weight: bold;'>Escompte</td><td class='text-right'>this.quantite;*;this.puht;*;object.escompte;/;100</td><td></td></tr><tr style='border:none;'> <td></td><td></td><td></td><td></td><td></td><td  style='font-weight: bold;'>Taxes</td><td  class='text-right'>(;this.quantite;*;this.puht;*;(;100;-;this.remise;-;object.escompte;);/;100;);*;{\"op\":\"sum\",\"source\":\"this\",\"data\":\"taxes\",\"field\":\"montant\"};/;100</td><td></td></tr><tr style='border:none;'> <td></td><td></td><td></td><td></td><td></td><td  style='font-weight: bold;'>Total TTC</td><td  class='text-right'  style='font-weight: bold;'>(;this.quantite;*;this.puht;*;(;100;-;this.remise;-;object.escompte;);/;100;);*;(;100;+;{\"op\":\"sum\",\"source\":\"this\",\"data\":\"taxes\",\"field\":\"montant\"};);/;100</td><td></td></tr><tr style='border:none;'><td></td><td></td><td></td><td></td><td></td><td style='font-weight: bold;'>Total Acomptes</td><td class='text-right'   style='font-weight: bold;'>{\"op\":\"sum\",\"source\":\"object\",\"data\":\"acomptes\",\"field\":\"montant\"}</td><td></td></tr><tr style='border:none;'> <td></td><td></td><td></td><td></td><td></td><td style='font-weight: bold;'>Net à payer</td><td class='text-right'    style='font-weight: bold;'>(;this.quantite;*;this.puht;*;(;100;-;this.remise;-;object.escompte;);/;100;);*;(;100;+;{\"op\":\"sum\",\"source\":\"this\",\"data\":\"taxes\",\"field\":\"montant\"};);/;100;-;{\"op\":\"sum\",\"source\":\"object\",\"data\":\"acomptes\",\"field\":\"montant\"}</td><td></td></tr>";
    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "FactureManagerImpl", interf = FactureManagerRemote.class)
    protected FactureManagerRemote manager;

    public FactureRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Facture, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
             MetaData meta= MetaDataUtil.getMetaData(new Facture(), new HashMap<String, MetaData>(), new ArrayList<String>());
            MetaColumn workbtn = new MetaColumn("button", "work1", "Imprimer la facture", false, "workflow", null);
            workbtn.setValue("{'model':'baseaccount','entity':'facture','method':'imprime'}");
            workbtn.setRoles(new String[]{"Administrateur"});
            workbtn.setStates(new String[]{"transfere","prisencompte","comptabilise"});
            meta.getHeader().add(workbtn);
            workbtn = new MetaColumn("button", "work3", "prise.en.compte", false, "link", null);
            workbtn.setValue("{'name':'base_account_ach02_1',template:{'facture':'object'},'header':['facture']}");
            workbtn.setRoles(new String[]{"Administrateur"});
            workbtn.setStates(new String[]{"transfere"});
            workbtn.setPattern("btn btn-danger");
            meta.getHeader().add(workbtn);
            MetaColumn stautsbar = new MetaColumn("workflow", "state", "State", false, "statusbar", null);
            meta.getHeader().add(stautsbar);
            for(MetaColumn col:meta.getColumns()){
                if(col.getFieldName().equalsIgnoreCase("emplacement")){
                    col.setHide(true);
                }
            }//end for(MetaColumn col:meta.getColumns())
            for(MetaGroup group:meta.getGroups()){
                if(group.getGroupName().trim().equalsIgnoreCase("group1")){
//                    System.out.println(FactureRSImpl.class.toString()+"  =============================== "+group.getMetaArray()+" ====== "+group.getMetaArray().isCustomfooter()+" ==== "+group.getMetaArray().getFooterScript());
                     group.getMetaArray().get(0).setFooterScript(FOOTER);
                }//end if(group.getGroupName().trim().equalsIgnoreCase("group1"))
            }//end for(MetaGroup group:meta.getGroups())
            return meta;
        } catch (InstantiationException ex) {
            Logger.getLogger(FactureRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FactureRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void processBeforeUpdate(Facture entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("numero.piece.required");
        }else if(entity.getDatecommande()==null){
            throw new KerenExecption("date.required");
        }else if(entity.getFournisseur()==null){
            throw new KerenExecption("fournisseur.required");
        }else if(entity.getType()==null||entity.getType().trim().isEmpty()){
            throw new KerenExecption("type.facture.required");
        }else if(entity.getCompte()==null){
            throw new KerenExecption("compte.required");
        }else if(entity.getJournal()==null){
            throw new KerenExecption("journal.comptable.required");
        }else if(entity.getLignes().isEmpty()){
            throw new KerenExecption("lignes.facture.isempty");
        }
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(Facture entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("numero.piece.required");
        }else if(entity.getDatecommande()==null){
            throw new KerenExecption("date.required");
        }else if(entity.getFournisseur()==null){
            throw new KerenExecption("fournisseur.required");
        }else if(entity.getType()==null||entity.getType().trim().isEmpty()){
            throw new KerenExecption("type.facture.required");
        }else if(entity.getCompte()==null){
            throw new KerenExecption("compte.required");
        }else if(entity.getJournal()==null){
            throw new KerenExecption("journal.comptable.required");
        }else if(entity.getLignes().isEmpty()){
            throw new KerenExecption("lignes.facture.isempty");
        }
        entity.setTypedocument(DocumentAchatState.COMPTABILITE);
        entity.setState("transfere");
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}
