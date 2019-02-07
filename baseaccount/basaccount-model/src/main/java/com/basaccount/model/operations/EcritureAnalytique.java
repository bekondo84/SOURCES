/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.CompteAnalytique;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_ECRIT_ANAL")
public class EcritureAnalytique extends BaseElement implements Serializable,Comparable<EcritureAnalytique>{

    @Temporal(TemporalType.DATE)
    @Predicate(label = "date.ecriture",target = "date",optional = false,updatable = false,type = Date.class,search = true,colsequence = 1,sequence = 0)
    private Date dateEcriture ;
    
    @Predicate(label = "reference",search = true,colsequence = 2,sequence = 2)
    private String refPiece ;
    
    @Predicate(label = "intitule",search = true,colsequence = 3,sequence = 3)
    private String libelle ;
    
       
    @ManyToOne
    @JoinColumn(name = "CPTE_ID")
    @Predicate(label = "compte.analytique",type = CompteAnalytique.class,updatable = false,optional = false,target = "many-to-one",search = true,colsequence = 4,sequence = 4)
    private CompteAnalytique compte ;
    
   
    @Predicate(label = "debit",type = Double.class,search = true,colsequence = 6,sequence = 6,updatable = false)
    private Double debit =0.0;
    
    @Predicate(label = "credit",type = Double.class,search = true,colsequence = 7,sequence = 7,updatable = false)
    private Double credit = 0.0;
    
    @ManyToOne
    @JoinColumn(name = "PECBT_ID")
    private PeriodeComptable periode;

    /**
     * 
     * @param dateEcriture
     * @param refPiece
     * @param libelle
     * @param compte 
     */
    public EcritureAnalytique(Date dateEcriture, String refPiece, String libelle, CompteAnalytique compte) {
        this.dateEcriture = dateEcriture;
        this.refPiece = refPiece;
        this.libelle = libelle;
        this.compte = compte;
    }

    /**
     * 
     * @param dateEcriture
     * @param refPiece
     * @param libelle
     * @param compte
     * @param id
     * @param designation
     * @param moduleName 
     */
    public EcritureAnalytique(Date dateEcriture, String refPiece, String libelle, CompteAnalytique compte, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.dateEcriture = dateEcriture;
        this.refPiece = refPiece;
        this.libelle = libelle;
        this.compte = compte;
    }
    
    /**
     * 
     * @param data 
     */
    public EcritureAnalytique(EcritureAnalytique data) {
        super(data.id, data.designation, data.moduleName,data.compareid);
        this.dateEcriture = data.dateEcriture;
        this.refPiece = data.refPiece;
        this.libelle = data.libelle;
        this.compte = data.compte;
        this.debit = data.debit;
        this.credit = data.credit;
        if(data.getPeriode()!=null){
             this.periode = new PeriodeComptable(data.periode);
        }
    }
    
   /**
    * 
    * @param data
    * @param compte
    * @param debit
    * @param credit 
    */
    public EcritureAnalytique(EcritureComptable data,CompteAnalytique compte , Double debit , Double credit) {
        this.dateEcriture = data.getDateEcriture();
        this.refPiece = data.getRefPiece();
        this.libelle = data.getLibelle();
        this.debit = debit ;
        this.credit = credit ;
        this.compte =compte;
        this.periode = data.getPeriode();
    }

    public EcritureAnalytique() {
    }

    public Date getDateEcriture() {
        return dateEcriture;
    }

    public void setDateEcriture(Date dateEcriture) {
        this.dateEcriture = dateEcriture;
    }

    public String getRefPiece() {
        return refPiece;
    }

    public void setRefPiece(String refPiece) {
        this.refPiece = refPiece;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CompteAnalytique getCompte() {
        return compte;
    }

    public void setCompte(CompteAnalytique compte) {
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

    @Override
    public String getDesignation() {
        return libelle; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "interrogations.analytiques"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "interrogation.analytique"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    public PeriodeComptable getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeComptable periode) {
        this.periode = periode;
    }

    @Override
    public boolean isDesableupdate() {
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
    public int compareTo(EcritureAnalytique o) {
        //To change body of generated methods, choose Tools | Templates.
        return refPiece.compareTo(o.refPiece);
    }
    
}
