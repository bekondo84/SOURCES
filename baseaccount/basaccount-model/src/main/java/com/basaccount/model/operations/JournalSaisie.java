/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Commercial_2
 */

public class JournalSaisie extends BaseElement implements Serializable,Comparable<JournalSaisie>{
    
    @Predicate(label = "periode",optional = false,updatable = false,search = true)
    private String code ;
    
    @ManyToOne
    @JoinColumn(name = "EXER_ID")
    @Predicate(label = "periode.comptable",type = PeriodeComptable.class,search = true,optional = false,updatable = false)
    private PeriodeComptable periode ;
    
    @ManyToOne
    @JoinColumn(name = "JRND_ID")
    @Predicate(label = "journal" ,search = true,type = JournalComptable.class,optional = false,updatable = false)
    private JournalComptable journal ;
    
    @Predicate(label = "debit" ,search = false,editable = false,hide = true,type = BigDecimal.class)
    private BigDecimal debit = BigDecimal.ZERO;
    
    @Predicate(label = "credit" ,search = false,editable = false,hide = true,type = BigDecimal.class)
    private BigDecimal credit = BigDecimal.ZERO;
    
   
    @Temporal(TemporalType.DATE)
    private Date debut ;
    
    @Temporal(TemporalType.DATE)
    private Date fin ;
    
    @Predicate(label = " ",group = true,groupName = "group1",groupLabel = "operations",type = EcritureComptable.class,target = "one-to-many")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "journaldesaisie")
    private List<EcritureComptable> operations = new ArrayList<EcritureComptable>();

    /**
     * 
     * @param code
     * @param debut
     * @param fin 
     */
    public JournalSaisie(String code, Date debut, Date fin) {
        this.code = code;
       this.debut = debut;
        this.fin = fin;
    }

    /**
     * 
     * @param code
     * @param exercice
     * @param debut
     * @param fin
     * @param id
     * @param designation
     * @param moduleName 
     */
    public JournalSaisie(String code, Date debut, Date fin, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.debut = debut;
        this.fin = fin;
    }

    public JournalSaisie(JournalSaisie journal) {
        super(journal.id, journal.designation, journal.moduleName,journal.compareid);
        this.code = journal.code;
        this.periode = journal.periode;
        this.debut = journal.debut;
        this.fin = journal.fin;
        this.journal = journal.getJournal();
        this.debit = journal.getDebit();
        this.credit = journal.getCredit();
        
    }
    
    public JournalSaisie() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PeriodeComptable getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeComptable periode) {
        this.periode = periode;
    }

  
    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public List<EcritureComptable> getOperations() {
        return operations;
    }

    public void setOperations(List<EcritureComptable> operations) {
        this.operations = operations;
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

    @Override
    public String getDesignation() {
         //To change body of generated methods, choose Tools | Templates.
        if(journal==null) return code;
        return code+" - "+journal.getCode();
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }
    
    /**
     * 
     * @param debit 
     */
    public void debit(BigDecimal debit){
        if(debit!=null){
            this.debit = this.debit.add(debit);
        }
    }
    
    /**
     * 
     * @param montant 
     */
    public void credit(BigDecimal montant){
        if(montant!=null){
            credit = credit.add(montant);
        }
    }

    @Override
    public String getListTitle() {
        //To change body of generated methods, choose Tools | Templates.
        return "JOURNAUX DE SAISIES";
    }

    @Override
    public String getEditTitle() {
       //To change body of generated methods, choose Tools | Templates.
        return "JOURNAL DE SAISIE";
    }

    @Override
    public boolean isDesablecreate() {
         //To change body of generated methods, choose Tools | Templates.
        return true;
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
    public int compareTo(JournalSaisie o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }

    
    
}
