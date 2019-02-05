/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name="T_PIECOM_CBT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("TMP")
public class PieceComptableTmp extends BaseElement implements Serializable,Comparable<PieceComptableTmp>{
    
    @Predicate(label = "numero.piece" ,optional = false,updatable = false,search = true,unique = true)
    @Column(unique = true)
    protected String code ;
    
    @Temporal(TemporalType.DATE)
    @Predicate(label = "date",type = Date.class,target = "date",optional = false,search = true)
    protected Date datePiece ;
   
    @Predicate(label = "libelle",search = true)
    protected String libelle ;
    
    @ManyToOne
    @JoinColumn(name = "PERCB_ID")
    @Predicate(label = "periode.comptable",type = PeriodeComptable.class,target = "many-to-one",editable = false,search = false,group = true,groupName = "group2",groupLabel = "complements")
    protected PeriodeComptable periode;
    
    public PieceComptableTmp() {
    }

    /**
     * 
     * @param code
     * @param datePiece
     * @param libelle
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public PieceComptableTmp(String code, Date datePiece, String libelle, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.datePiece = datePiece;
        this.libelle = libelle;
    }
    /**
     * 
     * @param entity 
     */
    public PieceComptableTmp(PieceComptableTmp entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.code;
        this.datePiece = entity.datePiece;
        this.libelle = entity.libelle;
        if(entity.periode!=null){
            this.periode = new PeriodeComptable(entity.periode);
        }
    }
    
    @Override
    public int compareTo(PieceComptableTmp o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDatePiece() {
        return datePiece;
    }

    public void setDatePiece(Date datePiece) {
        this.datePiece = datePiece;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public PeriodeComptable getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeComptable periode) {
        this.periode = periode;
    }

   
    
}
