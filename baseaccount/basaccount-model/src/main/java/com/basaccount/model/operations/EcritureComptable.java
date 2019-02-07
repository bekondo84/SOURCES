/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
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
public class EcritureComptable extends BaseElement implements Serializable,Comparable<EcritureComptable>{

    
    @Temporal(TemporalType.DATE)
    @Predicate(label = "date",target = "date",optional = false,updatable = false,type = Date.class,search = true,colsequence = 1,sequence = 0)
    private Date dateEcriture ;
    
    @Predicate(label = "numero.piece",search = true,colsequence = 2,sequence = 2)
    private String refPiece ;
    
    @Predicate(label = "libelle",search = true,colsequence = 3,sequence = 3)
    private String libelle ;
    
     
    @ManyToOne
    @JoinColumn(name = "PECBT_ID")
    @Predicate(label = "periode.comptable",type = PeriodeComptable.class,target = "many-to-one",search = true,editable = false)
    private PeriodeComptable periode ;
    
    @ManyToOne
    @JoinColumn(name = "JRN_ID")
    @Predicate(label = "journal.comptable" , type = JournalComptable.class,sequence = 3,colsequence = 3,target = "many-to-one",optional = false,search = true,editable = false)
    private JournalComptable journal ;    
    
    @ManyToOne
    @JoinColumn(name = "CPTE_ID")
    @Predicate(label = "compte",type = Compte.class,updatable = false,optional = false,target = "many-to-one",search = true,colsequence = 4,sequence = 4,searchfields = "code,libelle")
    private Compte compte ;
    
    @ManyToOne
    @JoinColumn(name = "TIER_ID")
    @Predicate(label = "compte.tier",type = Tier.class,target = "many-to-one",updatable = false,colsequence = 5,sequence = 5,search = true)
    private Tier tier ;
    
    
    @Predicate(label = "debit",type = Double.class,search = true,colsequence = 6,sequence = 6,updatable = false)
    private Double debit =0.0;
    
    @Predicate(label = "credit",type = Double.class,search = true,colsequence = 7,sequence = 7,updatable = false)
    private Double credit = 0.0;
    
//    @ManyToOne
//    @JoinColumn(name = "JRNSAISIE_ID")
//    private JournalSaisie journaldesaisie;
    
    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "ECRCBT_ID")
    private List<EcritureAnalytique> analytiques = new ArrayList<EcritureAnalytique>();

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "ECRCBT_ID")
    private EcritureTier ecrituretier ;
   
    
//    @ManyToOne
//    @JoinColumn(name = "PIEC_ID")
//    private PieceComptable piece ;
    
    /**
     * 
     * @param libelle
     * @param compte 
     */
    public EcritureComptable(String libelle, Compte compte) {
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
    public EcritureComptable(String libelle, Compte compte, long id, String designation, String moduleName) {
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
    public EcritureComptable(Date dateEcriture, String refPiece, String libelle, JournalComptable journal, Compte compte, Tier tier, long id, String designation, String moduleName) {
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
    public EcritureComptable(EcritureComptable ecriture) {
        super(ecriture.id, ecriture.designation, ecriture.moduleName,ecriture.compareid);
        this.dateEcriture = ecriture.dateEcriture;
        this.refPiece = ecriture.refPiece;
        this.libelle = ecriture.libelle;
        if(ecriture.journal!=null){
            this.journal = new JournalComptable(ecriture.journal);
        }
        this.debit = ecriture.debit;
        this.credit = ecriture.credit;
        if(ecriture.periode!=null){
            this.periode = new PeriodeComptable(ecriture.periode);
        }
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
     * @param entity
     * @param ecriture 
     */
     public EcritureComptable(PieceComptable entity ,LignePieceComptable ecriture) {
        super(-1L, null, null,-1L);
        this.dateEcriture = ecriture.getDateEcriture()!=null ? ecriture.getDateEcriture():entity.getDatePiece();
        this.refPiece = entity.getCode();
        this.libelle = ecriture.getLibelle();
        if(entity.getJournal()!=null){
            this.journal = entity.getJournal();
        }
        this.debit = ecriture.getDebit();
        this.credit = ecriture.getCredit();
        if(entity.getPeriode()!=null){
            this.periode = new PeriodeComptable(entity.getPeriode());
        }
//        if(ecriture.getPiece()!=null){
//            this.piece = new PieceComptable(ecriture.getPiece());
//        }
        if(ecriture.getCompte()!=null){
            this.compte = new Compte(ecriture.getCompte());
        }//end if(ecriture.getCompte()!=null)
        if(ecriture.getTier()!=null){
            this.tier = new Tier(ecriture.getTier());
        }//end if(ecriture.getTier()!=null)
//        if(ecriture.getJournaldesaisie()!=null){
//            this.journaldesaisie = new JournalSaisie(ecriture.journaldesaisie);
//        }
        
    }
     
     /**
      * 
      * @param entity
      * @param ecriture 
      */
     public EcritureComptable(OperationBancaire entity ,EcritureBanque ecriture) {
        super(-1L, null, null,-1L);
        this.dateEcriture = ecriture.getDateEcriture()!=null ? ecriture.getDateEcriture():entity.getDatePiece();
        this.refPiece = entity.getCode();
        this.libelle = ecriture.getLibelle();
        if(entity.getJournal()!=null){
            this.journal = entity.getJournal();
        }
        this.debit = ecriture.getDebit();
        this.credit = ecriture.getCredit();
        if(entity.getPeriode()!=null){
            this.periode = new PeriodeComptable(entity.getPeriode());
        }
//        if(ecriture.getPiece()!=null){
//            this.piece = new PieceComptable(ecriture.getPiece());
//        }
        if(ecriture.getCompte()!=null){
            this.compte = new Compte(ecriture.getCompte());
        }//end if(ecriture.getCompte()!=null)
        if(ecriture.getTier()!=null){
            this.tier = new Tier(ecriture.getTier());
        }//end if(ecriture.getTier()!=null)
//        if(ecriture.getJournaldesaisie()!=null){
//            this.journaldesaisie = new JournalSaisie(ecriture.journaldesaisie);
//        }
        
    }
     
    /**
     * 
     */
    public EcritureComptable() {
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

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
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

    public PeriodeComptable getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeComptable periode) {
        this.periode = periode;
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
        return true; //To change body of generated methods, choose Tools | Templates.
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
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
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
    public int compareTo(EcritureComptable o) {
         //To change body of generated methods, choose Tools | Templates.
        return compte.compareTo(o.compte);
    }
    
}
