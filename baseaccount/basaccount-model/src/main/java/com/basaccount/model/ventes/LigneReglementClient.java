/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.ventes;

import com.basaccount.model.achats.*;
import com.basaccount.model.comptabilite.Compte;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_LIRECL_COM")
public class LigneReglementClient extends BaseElement implements Serializable,Comparable<LigneReglementClient>{

    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "echeance",type = Date.class,target = "date",search = true)
    private Date echeance ;
    
    @Predicate(label = "montant",target = "number",type = Double.class,search = true,optional = false)
    private Double montant ;
    
    @ManyToOne
    @JoinColumn(name = "COPA_ID")
    @Predicate(label = "compte",type = Compte.class,target = "many-to-one",search = true,optional = false)
    private Compte compte;
    
    @Predicate(label = "type.piece",search = true,editable = false)
    private String typepiece;
    
    @ManyToOne
    @JoinColumn(name = "FAC_ID")
    @Predicate(label = "numero.piece",type = Facture.class,target = "many-to-one",search = true,editable = false)
    private FactureVente piece ;
    
    @Predicate(label = "solde",type = Double.class,target = "number",search = true,editable = false)
    private Double solde ;

    public LigneReglementClient() {
    }

    public LigneReglementClient(Date echeance, Double montant, String typepiece, FactureVente piece, Double solde, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.echeance = echeance;
        this.montant = montant;
        this.typepiece = typepiece;
        this.piece = piece;
        this.solde = solde;
//        this.compte = compte;
    }
    
    
    public LigneReglementClient(LigneReglementClient entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.echeance = entity.echeance;
        this.montant = entity.montant;
        this.typepiece = entity.typepiece;
        if(entity.piece!=null){
            this.piece = new FactureVente(entity.piece);
        }
        this.solde = entity.solde;
        if(entity.compte!=null){
            this.compte = new Compte(entity.compte);
        }
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getTypepiece() {
        return typepiece;
    }

    public void setTypepiece(String typepiece) {
        this.typepiece = typepiece;
    }

    public FactureVente getPiece() {
        return piece;
    }

    public void setPiece(FactureVente piece) {
        this.piece = piece;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    @Override
    public String getOwnermodule() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return super.getSerial(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
       return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "ligne.reglement.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "ligne.reglement.detail"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public int compareTo(LigneReglementClient o) {
         //To change body of generated methods, choose Tools | Templates.
        return echeance.compareTo(o.echeance);
    }
    
}
