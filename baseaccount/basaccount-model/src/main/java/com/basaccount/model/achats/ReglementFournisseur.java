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
@Entity
@DiscriminatorValue("ACH")
public class ReglementFournisseur extends ReglementTmp implements Serializable{
   
    @ManyToOne
    @JoinColumn(name = "FOUR_ID")
    @Predicate(label = "fournisseur",type = Tier.class,target = "many-to-one",search = true,optional = false)
    private Tier fournisseur;
    
    
    @ManyToOne
    @JoinColumn(name = "MOREG_ID")
    @Predicate(label = "mode.reglement",type = Tier.class,target = "many-to-one",search = true,optional = false)
    private ModeReglement modereglement ;
    
    @ManyToOne
    @JoinColumn(name = "JOCO_ID")
    @Predicate(label = "journal.comptable" ,type = JournalComptable.class,target = "many-to-one",search = true)
    private JournalComptable journal ;
    
    @Predicate(label = "reference",search = true)
    private String source;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "REFO_ID")
    @Predicate(label = " ",type = LigneReglementFournisseur.class,target = "one-to-many",edittable = true,group = true,groupName = "reglement.lignes")
    private List<LigneReglementFournisseur> lignes = new ArrayList<LigneReglementFournisseur>();

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
        this.modereglement = entity.modereglement;
        this.source = entity.source;
        if(entity.journal!=null){
            this.journal = entity.journal;
        }
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
        return super.getStates(); //To change body of generated methods, choose Tools | Templates.
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
