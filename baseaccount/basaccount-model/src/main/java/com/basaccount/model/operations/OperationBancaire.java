/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.JournalComptable;
import com.core.base.State;
import com.megatim.common.annotations.Predicate;
import com.megatim.common.annotations.TableFooter;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Commercial_2
 */
@Entity
@DiscriminatorValue("OBQ")
public class OperationBancaire extends PieceComptableTmp implements Serializable{
    @ManyToOne
    @JoinColumn(name = "JRN_ID")
    @Predicate(label = "jouranl.comptable",type = JournalComptable.class,target = "many-to-one",optional = false,editable = false,search = true)
    private JournalComptable journal;
   
    @Predicate(label = "solde.initial",type = BigDecimal.class,updatable = false,search = true,editable = false,hide = true)
    private BigDecimal soldedebut = BigDecimal.ZERO;
    
    @Predicate(label = "solde.final",type = BigDecimal.class,updatable = false , search = true,editable = false,hide = true)
    private BigDecimal soldefin = BigDecimal.ZERO;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "OPEBAN_ID")
    @Predicate(label=" ",group = true,groupLabel = "ecritures.comptable",groupName = "group1",type = EcritureBanque.class,target = "one-to-many",customfooter = true,edittable = true)
    @TableFooter(value = "<tr style='border:none;'><td></td><td></td><td></td><td></td><td></td><td>Total Debit</td><td></td><td>this.debit</td><td></td></tr> <tr style='border:none;'><td></td><td></td><td></td><td></td><td></td><td>Total Credit</td><td></td><td>this.credit</td><td></td></tr><tr style='border:none;'><td></td><td></td><td></td><td></td><td></td><td>Solde</td><td></td><td>this.debit;-;this.credit</td><td></td></tr>")
    private List<EcritureBanque> ecritures = new ArrayList<EcritureBanque>();    
    
    @Predicate(label = " ",target = "state",search = true,hide = true)
    private String state ="etabli";
   
    /**
     * 
     * @param libelle
     * @param journal 
     */
    public OperationBancaire(String libelle, JournalComptable journal) {
        this.libelle = libelle;
        this.journal = journal;
    }

    

    public OperationBancaire(OperationBancaire piece) {
        super(piece);
        this.code = piece.code;
        this.libelle = piece.libelle;
        this.journal = piece.journal;
        this.datePiece = piece.datePiece;
        this.soldedebut = piece.soldedebut;
        this.soldefin = piece.soldefin;
        this.state = piece.state;
    }
    /**
     * 
     */
    public OperationBancaire() {
    }    
  
    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }

    public List<EcritureBanque> getEcritures() {
        return ecritures;
    }

    public void setEcritures(List<EcritureBanque> ecritures) {
        this.ecritures = ecritures;
    }


    public BigDecimal getSoldedebut() {
        return soldedebut;
    }

    public void setSoldedebut(BigDecimal soldedebut) {
        this.soldedebut = soldedebut;
    }

    public BigDecimal getSoldefin() {
        return soldefin;
    }

    public void setSoldefin(BigDecimal soldefin) {
        this.soldefin = soldefin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
    @Override
    public String getDesignation() {
        return libelle; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "operation.bancaire.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "operation.bancaire.detail"; //To change body of generated methods, choose Tools | Templates.
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
        List<State> etats = new ArrayList<State>();
        etats.add(new State("etabli", "broullion"));
        etats.add(new State("valide", "valide"));
        return etats; //To change body of generated methods, choose Tools | Templates.
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
    
}
