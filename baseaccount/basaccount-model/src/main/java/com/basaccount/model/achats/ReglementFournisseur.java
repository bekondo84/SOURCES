/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.tiers.Tier;
import com.basaccount.model.ventes.ReglementTmp;
import com.core.base.State;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.KHeader;
import com.megatim.common.annotations.KHeaders;
import com.megatim.common.annotations.KValue;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author BEKO
 */
@KHeaders(statubar = true,value={
  @KHeader(type = "button",name = "work1",label = "Imprimer le reglement",target = "report",roles = {"administrateur","gestionnaire"}
       ,states = {"etabli","valide"}, value = @KValue("{'name':'baseaccount_reglementach01','model':'baseaccount','entity':'reglementfournisseur','method':'imprime'}")
   ),
   @KHeader(type = "button",name = "work2",label = "reglement.valide",target = "workflow",roles = {"administrateur","gestionnaire"},pattern = "btn btn-danger"
       ,states = {"etabli"}, value = @KValue("{'model':'baseaccount','entity':'reglementfournisseur','method':'valide'}")
   )
})
@Entity
@DiscriminatorValue("ACH")
public class ReglementFournisseur extends ReglementTmp implements Serializable{
   
    @ManyToOne
    @JoinColumn(name = "FOUR_ID")
    @Predicate(label = "fournisseur",type = Tier.class,target = "many-to-one",search = true,optional = false)
    @Filter("[{\"fieldName\":\"type\",\"value\":\"1\"}]")
    private Tier fournisseur;
    
    
    @ManyToOne
    @JoinColumn(name = "MOREG_ID")
//    @Predicate(label = "mode.reglement",type = ModeReglement.class,target = "many-to-one",search = true,optional = false)
    private ModeReglement modereglement ;
    
    @ManyToOne
    @JoinColumn(name = "JOCO_ID")
    @Predicate(label = "journal.comptable" ,type = JournalComptable.class,target = "many-to-one",search = true)
    @Filter("[{\"fieldName\":\"type\",\"value\":\"1\"}]")
    private JournalComptable journal ;
    
    @Predicate(label = "reference",search = true)
    private String source;
    
    @Predicate(label = "montant.paye",type = Double.class,search = true,editable = false,hide = true)
    private Double totalrecu =0.0;
    
    @Predicate(label = "montant.attendu",type = Double.class,search = true,editable = false,hide = true)
    private Double totalattendu;
    
    @Predicate(label = "montant.reste",type = Double.class,search = true,editable = false,hide = true)
    private Double solde = 0.0; 
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "REFO_ID")
    @Predicate(label = " ",type = LigneReglementFournisseur.class,target = "many-to-many-list",edittable = true,group = true,groupName = "reglement.lignes")
    @Filter("[{\"fieldName\":\"fournisseur\",\"value\":\"object.fournisseur\",\"searchfield\":\"code\"}]")
    private List<LigneReglementFournisseur> lignes = new ArrayList<LigneReglementFournisseur>();

    @Predicate(label = " ",target = "state",search = true,hide = true)
    private String state ="etabli";
    /**
     * 
     */
    public ReglementFournisseur() {
    }

   

    /**
     * 
     * @param entity 
     */
     public ReglementFournisseur(ReglementFournisseur entity) {
        super(entity);
        if(entity.fournisseur!=null){
            this.fournisseur = new Tier(entity.fournisseur);
        }
        if(entity.modereglement!=null){
            this.modereglement = new ModeReglement(entity.modereglement);
        }
        this.source = entity.source;
        if(entity.journal!=null){
            this.journal = entity.journal;
        }
        this.totalattendu = entity.totalattendu;
        this.totalrecu = entity.totalrecu;
        this.solde = entity.solde;
        this.state = entity.state;
    }
    
   
    public Tier getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Tier fournisseur) {
        this.fournisseur = fournisseur;
    }

    public ModeReglement getModereglement() {
        return modereglement;
    }

    public void setModereglement(ModeReglement modereglement) {
        this.modereglement = modereglement;
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }

    

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<LigneReglementFournisseur> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneReglementFournisseur> lignes) {
        this.lignes = lignes;
    }

    public Double getTotalrecu() {
        return totalrecu;
    }

    public void setTotalrecu(Double totalrecu) {
        this.totalrecu = totalrecu;
    }

    public Double getTotalattendu() {
        return totalattendu;
    }

    public void setTotalattendu(Double totalattendu) {
        this.totalattendu = totalattendu;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    

    @Override
    public String getOwnermodule() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return super.isDesableupdate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivatefollower() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public List<State> getStates() {
        states = new ArrayList<State>();
        State stat = new State("etabli", "brouillon");
        stat.setCouleur("#b22222");
        states.add(stat);
        stat = new State("valide", "valide");
        stat.setCouleur("#008b8b");
        states.add(stat);
        return states; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return super.isDesabledelete(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return super.isDesablecreate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        StringBuilder builder = new StringBuilder(code);
        builder.append("/")
                .append(fournisseur.getDesignation());
        return builder.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "reglement.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "reglement.detail"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
