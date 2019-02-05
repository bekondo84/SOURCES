/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.tiers.Tier;
import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_ECRCO_CBT")
public class EcritureBanque extends BaseElement implements Serializable,Comparable<EcritureBanque>{

    
    @Temporal(TemporalType.DATE)
    @Predicate(label = "date",target = "date",optional = false,updatable = false,type = Date.class,search = true,colsequence = 1,sequence = 0)
    private Date dateEcriture ;
    
    @Predicate(label = "numero.piece",search = true,colsequence = 2,sequence = 2)
    private String refPiece ;
    
    @Predicate(label = "piece.tresorerie",search = true,colsequence = 2,sequence = 2)
    private String tresorerie ;
    
    @Predicate(label = "libelle",search = true,colsequence = 3,sequence = 3)
    private String libelle ;
    
    @ManyToOne
    @JoinColumn(name = "JRN_ID")
    @Predicate(label = "journal.comptable" , type = JournalComptable.class,sequence = 3,colsequence = 3,target = "many-to-one",optional = false,updatable = false)
    private JournalComptable journal ;    
    
    @ManyToOne
    @JoinColumn(name = "CPTE_ID")
    @Predicate(label = "compte",type = Compte.class,updatable = false,optional = false,target = "many-to-one",search = true,colsequence = 4,sequence = 4,searchfields = "code,libelle")
    private Compte compte ;
    
    @ManyToOne
    @JoinColumn(name = "TIER_ID")
    @Predicate(label = "compte.tier",type = Tier.class,target = "many-to-one",colsequence = 5,sequence = 5,search = true)
    private Tier tier ;
    
    
    @Predicate(label = "decaissement",type = BigDecimal.class,search = true,colsequence = 6,sequence = 6,updatable = false,optional = false)
    private BigDecimal debit =BigDecimal.ZERO;
    
    @Predicate(label = "encaissement",type = BigDecimal.class,search = true,colsequence = 7,sequence = 7,updatable = false,optional = false)
    private BigDecimal credit = BigDecimal.ZERO;
    
//    @ManyToOne
//    @JoinColumn(name = "JRNSAISIE_ID")
//    private JournalSaisie journaldesaisie;
    
    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "ECRIT_ANAL_ID")
    private List<EcritureAnalytique> analytiques = new ArrayList<EcritureAnalytique>();

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "ECRIT_TIER_ID")
    private EcritureTier ecrituretier ;    
   
    
//    @ManyToOne
//    @JoinColumn(name = "PIEC_ID")
//    private PieceComptable piece ;
    
    /**
     * 
     * @param libelle
     * @param compte 
     */
    public EcritureBanque(String libelle, Compte compte) {
        this.libelle = libelle;
        this.compte = compte;
    }

    /**
     * 
     * @param libelle
     * @param compte
     * @param id
     * @param designation
     * @param moduleName 
     */
    public EcritureBanque(String libelle, Compte compte, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.libelle = libelle;
        this.compte = compte;
    }

    /**
     * 
     * @param dateEcriture
     * @param refPiece
     * @param libelle
     * @param journal
     * @param compte
     * @param tier
     * @param id
     * @param designation
     * @param moduleName 
     */
    public EcritureBanque(Date dateEcriture, String refPiece, String libelle, JournalComptable journal, Compte compte, Tier tier, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.dateEcriture = dateEcriture;
        this.refPiece = refPiece;
        this.libelle = libelle;
        this.journal = journal;
        this.compte = compte;
        this.tier = tier;
    }
    
    /**
     * 
     * @param ecriture 
     */
    public EcritureBanque(EcritureBanque ecriture) {
        super(ecriture.id, ecriture.designation, ecriture.moduleName,ecriture.compareid);
        this.dateEcriture = ecriture.dateEcriture;
        this.refPiece = ecriture.refPiece;
        this.libelle = ecriture.libelle;
        this.journal = ecriture.journal;
        this.debit = ecriture.debit;
        this.credit = ecriture.credit;
        this.tresorerie = ecriture.tresorerie;
//        if(ecriture.getPiece()!=null){
//            this.piece = new PieceComptable(ecriture.getPiece());
//        }
        if(ecriture.getCompte()!=null){
            this.compte = new Compte(ecriture.compte);
        }//end if(ecriture.getCompte()!=null)
        if(ecriture.getTier()!=null){
            this.tier = new Tier(ecriture.tier);
        }//end if(ecriture.getTier()!=null)
//        if(ecriture.getJournaldesaisie()!=null){
//            this.journaldesaisie = new JournalSaisie(ecriture.journaldesaisie);
//        }
        
    }
     
    /**
     * 
     */
    public EcritureBanque() {
    }

    
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public String getRefPiece() {
        return refPiece;
    }

    public void setRefPiece(String refPiece) {
        this.refPiece = refPiece;
    }

    public Date getDateEcriture() {
        return dateEcriture;
    }

    public void setDateEcriture(Date dateEcriture) {
        this.dateEcriture = dateEcriture;
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }    

    public String getTresorerie() {
        return tresorerie;
    }

    public void setTresorerie(String tresorerie) {
        this.tresorerie = tresorerie;
    }
    
    

    @Override
    public String getDesignation() {
        return libelle; //To change body of generated methods, choose Tools | Templates.
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    } 

   
    public List<EcritureAnalytique> getAnalytiques() {
        return analytiques;
    }

    public void setAnalytiques(List<EcritureAnalytique> analytiques) {
        this.analytiques = analytiques;
    }

    public EcritureTier getEcrituretier() {
        return ecrituretier;
    }

    public void setEcrituretier(EcritureTier ecrituretier) {
        this.ecrituretier = ecrituretier;
    }  

     

    @Override
    public String getListTitle() {
        return "ecriture.comptable.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "ecriture.comptable.detail"; //To change body of generated methods, choose Tools | Templates.
    }

//    public PieceComptable getPiece() {
//        return piece;
//    }
//
//    public void setPiece(PieceComptable piece) {
//        this.piece = piece;
//    }

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "baseaccount_00120"; //To change body of generated methods, choose Tools | Templates.
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
    public boolean isDesabledelete() {
        return super.isDesabledelete(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return super.isDesablecreate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return super.isCreateonfield(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(EcritureBanque o) {
         //To change body of generated methods, choose Tools | Templates.
        return compte.compareTo(o.compte);
    }
    
}
