/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.ventes.LigneReglement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author BEKO
 */
@Entity
@DiscriminatorValue("ACH")
public class LigneReglementFournisseur extends LigneReglement implements Serializable{

    @ManyToOne
    @JoinColumn(name = "COPA_ID")
    @Predicate(label = "compte",type = Compte.class,target = "many-to-one",search = true,optional = false)
    private Compte compte;
    
    @Predicate(label = "type.piece",search = true,editable = false)
    private String typepiece;
    
    @ManyToOne
    @JoinColumn(name = "FAC_ID")
    @Predicate(label = "numero.piece",type = Facture.class,target = "many-to-one",search = true,editable = false)
    private Facture piece ;
    
    @Predicate(label = "solde",type = Double.class,target = "number",search = true,editable = false)
    private Double solde ;

    public LigneReglementFournisseur() {
    }

  
    
    public LigneReglementFournisseur(LigneReglementFournisseur entity) {
        super(entity);
        this.typepiece = entity.typepiece;
        if(entity.piece!=null){
            this.piece = new Facture(entity.piece);
        }
        this.solde = entity.solde;
        if(entity.compte!=null){
            this.compte = new Compte(entity.compte);
        }
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

    public Facture getPiece() {
        return piece;
    }

    public void setPiece(Facture piece) {
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
        
}
