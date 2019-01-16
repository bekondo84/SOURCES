/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.JournalComptable;
import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.Predicate;
import com.megatim.common.annotations.TableFooter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_PICO_COM")
public class PieceComptable extends BaseElement implements Serializable,Comparable<PieceComptable>{
    @ManyToOne
    @JoinColumn(name = "JRN_ID")
    @Predicate(label = "jouranl.comptable",type = JournalComptable.class,target = "many-to-one",optional = false,updatable = false,search = true)
    private JournalComptable journal;
    
    @Temporal(TemporalType.DATE)
    @Predicate(label = "date",type = Date.class,target = "date",optional = false,search = true)
    private Date datePiece ;
   
    @Predicate(label = "numero.piece" ,optional = false,updatable = false,search = true,unique = true)
    @Column(unique = true)
    private String code ;
    
    @Predicate(label = "libelle",search = true)
    private String libelle ;
    
    @Predicate(label = "debit",type = BigDecimal.class,updatable = false,search = true,editable = false,hide = true)
    private BigDecimal debit = BigDecimal.ZERO;
    
    @Predicate(label = "credit",type = BigDecimal.class,updatable = false , search = true,editable = false,hide = true)
    private BigDecimal credit = BigDecimal.ZERO;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "PIEC_ID")
    @Predicate(label=" ",group = true,groupLabel = "ecritures.comptable",groupName = "group1",type = EcritureComptable.class,target = "one-to-many",customfooter = true,edittable = true)
    @TableFooter(value = "<tr style='border:none;'> <td></td><td></td><td></td><td></td><td>Total Debit</td><td></td> <td>this.debit</td> </tr> <tr style='border:none;'> <td></td><td></td><td></td><td></td><td>Total Credit</td><td></td> <td>this.credit</td> </tr> <tr style='border:none;'> <td></td><td></td><td></td><td></td><td>Solde</td><td></td><td>this.debit;-;this.credit</td> </tr>")
    private List<EcritureComptable> ecritures = new ArrayList<EcritureComptable>();
    
    @ManyToOne
    @JoinColumn(name = "EXER_ID")
    private ExerciceComptable exercice;

    /**
     * 
     * @param libelle
     * @param journal 
     */
    public PieceComptable(String libelle, JournalComptable journal) {
        this.libelle = libelle;
        this.journal = journal;
    }

    /**
     * 
     * @param libelle
     * @param journal
     * @param id
     * @param designation
     * @param moduleName 
     */
    public PieceComptable(String libelle, JournalComptable journal, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.libelle = libelle;
        this.journal = journal;
    }

    public PieceComptable(PieceComptable piece) {
        super(piece.id, piece.designation, piece.moduleName,piece.compareid);
        this.code = piece.code;
        this.libelle = piece.libelle;
        this.journal = piece.journal;
        this.datePiece = piece.datePiece;
        this.debit = piece.debit;
        this.credit = piece.credit;
        this.exercice = piece.exercice;
    }
    /**
     * 
     */
    public PieceComptable() {
    }

    
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }

    public List<EcritureComptable> getEcritures() {
        return ecritures;
    }

    public void setEcritures(List<EcritureComptable> ecritures) {
        this.ecritures = ecritures;
    }

    public Date getDatePiece() {
        return datePiece;
    }

    public void setDatePiece(Date datePiece) {
        this.datePiece = datePiece;
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
        return libelle; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "piece.comptable.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "piece.comptable.detail"; //To change body of generated methods, choose Tools | Templates.
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
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ExerciceComptable getExercice() {
        return exercice;
    }

    public void setExercice(ExerciceComptable exercice) {
        this.exercice = exercice;
    }

    @Override
    public boolean isActivatefollower() {
         //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "piececomptable01234"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(PieceComptable o) {
         //To change body of generated methods, choose Tools | Templates.
        return journal.compareTo(o.journal);
    }
    
}
