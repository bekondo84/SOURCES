/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.ventes;

import com.basaccount.model.achats.EcheanceReglement;
import com.basaccount.model.achats.ModeReglement;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.tiers.Tier;
import com.megatim.common.annotations.Observer;
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
@DiscriminatorValue("VTE")
public class LigneReglementClient extends LigneReglement implements Serializable{

    @ManyToOne
    @JoinColumn(name = "MOREG_ID")
    @Predicate(label = "mode.reglement", type = ModeReglement.class, target = "many-to-one",editable = false, search = true, optional = false,observable = true)
    private ModeReglement modereglement;
    
    @ManyToOne
    @JoinColumn(name = "COPA_ID")
    @Predicate(label = "compte",type = Compte.class,target = "many-to-one",search = true,optional = false)
    @Observer(observable = "modereglement",source = "field:compte")
    private Compte compte;
    
    @Predicate(label = "type.piece",search = false,editable = false)
    private String typepiece;
    
//    @ManyToOne
//    @JoinColumn(name = "FAC_ID")
//    @Predicate(label = "numero.piece",type = Facture.class,target = "many-to-one",search = true,editable = false)
//    private FactureVente piece ;
    
    @Predicate(label = "solde",type = Double.class,target = "number",search = true,editable = false)
    private Double solde ;
    
    @Predicate(label = "client",type = Tier.class,target = "many-to-one",search = false)
    @Transient
    private Tier fournisseur;
    
   
    
    private Long echeaneid ;

    public LigneReglementClient() {
    }


    /**
     * 
     * @param entity 
     */
    public LigneReglementClient(LigneReglementClient entity) {
        super(entity);
        this.typepiece = entity.typepiece;
        this.solde = entity.solde;
        if(entity.compte!=null){
            this.compte = new Compte(entity.compte);
        }
        if(entity.modereglement!=null){
            this.modereglement = new ModeReglement(entity.modereglement);
        }
        this.echeaneid = entity.echeaneid;
    }

    /**
     * 
     * @param entity 
     */
    public LigneReglementClient(EcheanceReglement entity){
        super(entity);
        this.solde = entity.getSolde();
        this.echeaneid = entity.getId();
        if(entity.getMode()!=null){
            this.modereglement = new ModeReglement(entity.getMode());
        }
        if(entity.getMode()!=null){
            this.compte = new Compte(entity.getMode().getCompte());
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

    public Long getEcheaneid() {
        return echeaneid;
    }

    public void setEcheaneid(Long echeaneid) {
        this.echeaneid = echeaneid;
    }
    
    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
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
