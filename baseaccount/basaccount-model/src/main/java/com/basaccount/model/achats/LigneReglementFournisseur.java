/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.tiers.Tier;
import com.basaccount.model.ventes.LigneReglement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author BEKO
 */
@Entity
@DiscriminatorValue("ACH")
public class LigneReglementFournisseur extends LigneReglement implements Serializable{
    @ManyToOne
    @JoinColumn(name = "MOREG_ID")
    @Predicate(label = "mode.reglement",type = ModeReglement.class,target = "many-to-one",search = true,optional = false)
    private ModeReglement modereglement ;
 
    @ManyToOne
    @JoinColumn(name = "COPA_ID")
    @Predicate(label = "compte",type = Compte.class,target = "many-to-one",search = true,optional = false)
    private Compte compte;
    
//    @Predicate(label = "type.piece",search = true,editable = false)
    private String typepiece;
    
   
    @Predicate(label = "solde",type = Double.class,target = "number",search = true,editable = false)
    private Double solde ;

    private Long echeanceid ;
    
     
    @Predicate(label = "client",type = Tier.class,target = "many-to-one",search = false)
    @Transient
    private Tier fournisseur;
    
//    @Predicate(label = "mode.reglement",type = ModeReglement.class,target = "many-to-one",search = false)
//    @Transient
//    private ModeReglement modereglement ;
    
    public LigneReglementFournisseur() {
    }  
    
    /**
     * 
     * @param entity 
     */
    public LigneReglementFournisseur(LigneReglementFournisseur entity) {
        super(entity);
        this.typepiece = entity.typepiece;
        this.solde = entity.solde;
        if(entity.compte!=null){
            this.compte = new Compte(entity.compte);
        }
        if(entity.getModereglement()!=null){
            this.modereglement = new ModeReglement(entity.modereglement);
        }
        this.echeanceid = entity.echeanceid;
    }

    /**
     * 
     * @param entity 
     */
     public LigneReglementFournisseur(EcheanceReglement entity){
        super(entity);
        this.solde = entity.getSolde();
        this.echeanceid = entity.getId();
        if(entity.getMode()!=null){
            this.compte = new Compte(entity.getMode().getCompte());
            this.modereglement = new ModeReglement(entity.getMode());
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

    
    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Long getEcheanceid() {
        return echeanceid;
    }

    public void setEcheanceid(Long echeanceid) {
        this.echeanceid = echeanceid;
    }

    public Tier getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Tier fournisseur) {
        this.fournisseur = fournisseur;
    }

    public ModeReglement getModereglement() {
        return modereglement;
    }

    public void setModereglement(ModeReglement modereglement) {
        this.modereglement = modereglement;
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
