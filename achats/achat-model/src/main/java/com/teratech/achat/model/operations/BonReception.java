/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.achat.model.operations;

import com.core.base.State;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.Predicate;
import com.teratech.achat.model.base.Tier;
import java.io.Serializable;
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
 * @author BEKO
 */
@Entity
@DiscriminatorValue("BR")
public class BonReception extends DocumentStock implements Serializable{

     @ManyToOne
    @JoinColumn(name = "FOUR_ID")
    @Predicate(label = "Fournisseur",type = Tier.class,target = "many-to-one",optional = false,search = true)
    @Filter(value = "[{\"fieldName\":\"type\",\"value\":\"1\"}]")
    protected Tier fournisseur ;
     
     @Predicate(label = "Type de bon",target = "combobox",values = "Normal;Retour")
     private String typebon = "0";
     
//    @Predicate(label = "Document d'origine")
    private String origine ;    
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "BR_ID")
    @Predicate(label = " ",type = LigneDocumentStock.class,target = "one-to-many",group =true ,groupName = "group1",groupLabel = "Articles",edittable = true)
    private List<LigneDocumentStock> lignes = new ArrayList<LigneDocumentStock>();
    
    @ManyToOne
    @JoinColumn(name = "CMDE_ID")
    @Predicate(label = "Bon commande",type = BonCommande.class,target = "many-to-one",search = true,hide = true)
    private BonCommande commande ;
    
    @OneToMany(mappedBy = "docachat",fetch = FetchType.LAZY)
//    @Predicate(label = "Factures",type = Facture.class,target = "one-to-many",editable = false,group = true,groupName = "group4",groupLabel = "Factures")
    private List<Facture> factures = new ArrayList<Facture>();
    
    

    

    /**
     * 
     * @param da 
     */
    public BonReception(DocumentAchat da) {
        super();
//        this.typedocument = DocumentAchatState.BONLIVRAISON;
        
    }

    public BonReception(BonReception entity) {
        super(entity);
        if(entity.getFournisseur()!=null){
            this.fournisseur = new Tier(entity.fournisseur);
        }
        this.origine = entity.origine;
        if(entity.commande!=null){
            this.commande = new BonCommande(entity.commande);
        }
        this.typebon = entity.typebon;
        this.state = entity.getState();
    }

    public BonReception() {
    }

    
   
    

    /**
     * 
     * @return 
     */
    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public List<LigneDocumentStock> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneDocumentStock> lignes) {
        this.lignes = lignes;
    }

    public Tier getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Tier fournisseur) {
        this.fournisseur = fournisseur;
    }

    public BonCommande getCommande() {
        return commande;
    }

    public void setCommande(BonCommande commande) {
        this.commande = commande;
    }

    public String getTypebon() {
        return typebon;
    }

    public void setTypebon(String typebon) {
        this.typebon = typebon;
    }
    
    

    @Override
    public boolean isActivatefollower() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "bc270220181144"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return !state.equalsIgnoreCase("etabli")
                &&!state.equalsIgnoreCase("qualite"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return !state.equalsIgnoreCase("etabli")
                &&!state.equalsIgnoreCase("qualite")
                &&!state.equalsIgnoreCase("transfere"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return super.isDesablecreate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }

    

     @Override
    public List<State> getStates() {
        List<State> states = new ArrayList<State>();
        State state = null;
        if(this.state.equalsIgnoreCase("etabli")){
             state = new State("etabli", "Brouillon");
             states.add(state);
            state = new State("transfere", "Disponible");
            states.add(state);        
        }else if(this.state.equalsIgnoreCase("qualite")){
            state = new State("qualite", "Brouillon");
            states.add(state);
            state = new State("controle", "Qualité controlée");
            states.add(state);
        }else if(this.state.equalsIgnoreCase("transfere")){
             state = new State("transfere", "Attente de transfert");
            states.add(state);
            state = new State("disponible", "Disponible en stock");
            states.add(state);
//            state = new State("annule", "Annulé");
//            states.add(state);
        }else if(this.state.equalsIgnoreCase("disponible")){
            state = new State("disponible", "Disponible en stock");
            states.add(state);
        }      
        return states; //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String getListTitle() {
        return "BONS DE RECEPTIONS"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "BON DE RECEPTION"; //To change body of generated methods, choose Tools | Templates.
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
    
    
    
}
