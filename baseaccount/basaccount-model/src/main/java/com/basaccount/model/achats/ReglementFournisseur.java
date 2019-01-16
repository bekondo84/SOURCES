/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.tiers.Tier;
import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_REGFO_COM")
public class ReglementFournisseur extends BaseElement implements Serializable,Comparable<ReglementFournisseur>{

    @Predicate(label = "numero.piece",optional = false,unique = true,search = true)
    private String code;
    
    @ManyToOne
    @JoinColumn(name = "FOUR_ID")
    @Predicate(label = "fournisseur",type = Tier.class,target = "many-to-one",search = true,optional = false)
    private Tier fournisseur;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "date",type = Date.class,target = "date",optional = false,search = true)
    private Date date ;
    
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
     * @param code
     * @param fournisseur
     * @param date
     * @param modereglement
     * @param montant
     * @param source
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public ReglementFournisseur(String code, Tier fournisseur, Date date, ModeReglement modereglement, Double montant, String source, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.fournisseur = fournisseur;
        this.date = date;
        this.modereglement = modereglement;
        this.source = source;
    }

    /**
     * 
     * @param entity 
     */
     public ReglementFournisseur(ReglementFournisseur entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.code;
        if(entity.fournisseur!=null){
            this.fournisseur = new Tier(entity.fournisseur);
        }
        this.date = entity.date;
        this.modereglement = entity.modereglement;
        this.source = entity.source;
        if(entity.journal!=null){
            this.journal = entity.journal;
        }
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Tier getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Tier fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    public String getSerial() {
        return "160120191109REFO"; //To change body of generated methods, choose Tools | Templates.
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
    
    
    
    
    @Override
    public int compareTo(ReglementFournisseur o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
