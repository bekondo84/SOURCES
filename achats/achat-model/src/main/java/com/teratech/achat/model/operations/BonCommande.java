/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.achat.model.operations;

import com.core.base.State;
import com.megatim.common.annotations.Predicate;
import com.megatim.common.annotations.TableFooter;
import com.teratech.achat.model.base.ConditionPaiement;
import com.teratech.achat.model.base.Entrepot;
import com.teratech.achat.model.base.Tier;
import com.teratech.achat.model.comptabilite.Taxe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@DiscriminatorValue("BC")
public class BonCommande extends DocumentAchat implements Serializable{

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "LIDOAC_ID")
    @Predicate(label = " ",type = LigneDocumentAchat.class,target = "one-to-many",group = true,groupName = "group1",groupLabel = "articles",customfooter = true,edittable = true)
    @TableFooter(value = "<tr style='border:none;'><td></td><td></td><td></td><td></td><td'></td><td></td><td style='font-weight: bold;'>Total HT</td> <td class='text-center'>this.quantite;*;this.puht;*;(;100;-;this.remise;);/;100</td><td></td></tr><tr style='border:none;'><td></td><td></td><td></td><td></td><td></td><td  style='font-weight: bold;'>Taxes</td><td  class='text-center'>(;this.quantite;*;this.puht;*;(;100;-;this.remise;);/;100;);*;{\"op\":\"sum\",\"source\":\"this\",\"data\":\"taxes\",\"field\":\"montant\"};/;100</td><td></td></tr><tr style='border:none;'><td></td><td></td><td></td><td></td><td></td><td  style='font-weight: bold;'>Total TTC</td><td  class='text-center'  style='font-weight: bold;'>(;this.quantite;*;this.puht;*;(;100;-;this.remise;);/;100;);*;(;100;+;{\"op\":\"sum\",\"source\":\"this\",\"data\":\"taxes\",\"field\":\"montant\"};);/;100</td><td></td></tr>")
    protected List<LigneCommande> lignes = new ArrayList<LigneCommande>();
    
    @Predicate(label = "methode.acturation",target = "combobox",values = "Basé sur le bon de commande;Basé sur les receptions",group = true,groupName = "group3",groupLabel = "livraisons.et.factures")
    private String method="0";
    
    @Predicate(label = "facture.recue",type = Boolean.class,editable = false,group = true,groupName = "group3",groupLabel = "livraisons.et.factures")
    private Boolean facturerecue = false;
    
//    @ManyToOne
//    @JoinColumn(name = "APOF_ID")
//    @Predicate(label = "Appel d'offres",type = AppelOffre.class,target = "many-to-one",group = true,groupName = "group3",groupLabel = "livraisons.et.factures")
//    private AppelOffre appeloffre ;
    
    @ManyToOne
    @JoinColumn(name = "CORE_ID")
    @Predicate(label = "condition.reglement",type = ConditionPaiement.class,target = "many-to-one",group = true,groupName = "group3",groupLabel = "livraisons.et.factures")
    private ConditionPaiement condreglement ;
    
    @OneToMany(mappedBy = "docachat",fetch = FetchType.LAZY)
//    @Predicate(label = "Factures",type = Facture.class,target = "one-to-many",editable = false,group = true,groupName = "group4",groupLabel = "Factures")
    private List<Facture> factures = new ArrayList<Facture>();    
    
    private Double totaltaxes = 0.0;
    
    @Predicate(label = "total.ht",type = Double.class,search = true,hide = true)
    private Double totalht=0.0;
    
    @Predicate(label = "total.ttc",type = Double.class,search = true,hide = true)
    private Double totalttc = 0.0;    
          
    @Predicate(label = " ",target = "state",hide = true,search = true)
     protected String state ="etabli" ;
    
    /**
     * 
     * @param code
     * @param date
     * @param fornisseur
     * @param datecommande
     * @param codefourni
     * @param emplacement 
     */
    public BonCommande(String code, Date date, Tier fornisseur, Date datecommande, String codefourni, Entrepot emplacement) {
        super(code, date, fornisseur, datecommande, codefourni, emplacement);
        this.typedocument = DocumentAchatState.BONCOMMANDE;
    }

    /**
     * 
     * @param code
     * @param date
     * @param fornisseur
     * @param datecommande
     * @param codefourni
     * @param emplacement
     * @param id
     * @param designation
     * @param moduleName 
     */
    public BonCommande(String code, Date date, Tier fornisseur, Date datecommande, String codefourni, Entrepot emplacement, long id, String designation, String moduleName) {
        super(code, date, fornisseur, datecommande, codefourni, emplacement, id, designation, moduleName);
        this.typedocument = DocumentAchatState.BONCOMMANDE;
    }

    /**
     * 
     * @param da 
     */
    public BonCommande(DocumentAchat da) {
        super(da);
        this.typedocument = da.typedocument;
    }
    
     
     
     public BonCommande(BonCommande da) {
        super(da);
        this.method = da.getMethod();
        this.facturerecue = da.getFacturerecue();
//        if(da.getAppeloffre()!=null){
//            this.appeloffre = new AppelOffre(da.getAppeloffre());
//        }
        this.condreglement = da.getCondreglement();
        this.typedocument = da.getTypedocument();
        this.totalht = da.totalht;
        this.totalttc = da.totalttc;
        this.totaltaxes = da.totaltaxes;
    }
     
   public BonCommande(ReponseFournisseur entity) {
        super(entity.getCode(),new Date(),entity.getFournisseur(),new Date(),null,null);
        this.totalht=0.0;this.totaltaxes=0.0;this.totalttc=0.0;
        for(LigneReponseDP ligne:entity.getLignes()){
            this.lignes.add(new LigneCommande(ligne));
            this.totalht+=ligne.getTotalht();
            for(Taxe taxe:ligne.getTaxes()){
                this.totaltaxes+=ligne.getTotalht()*taxe.getMontant()/100;
            }//end for(Taxe taxe:ligne.getTaxes()){
        }//end for(LigneReponseDP ligne:entity.getLignes()){
        this.totalttc = this.totalht+this.totaltaxes;
    }
    /**
     * 
     */
    public BonCommande() {
        
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getFacturerecue() {
        return facturerecue;
    }

    public void setFacturerecue(Boolean facturerecue) {
        this.facturerecue = facturerecue;
    }

//    public AppelOffre getAppeloffre() {
//        return appeloffre;
//    }
//
//    public void setAppeloffre(AppelOffre appeloffre) {
//        this.appeloffre = appeloffre;
//    }

    public ConditionPaiement getCondreglement() {
        return condreglement;
    }

    public void setCondreglement(ConditionPaiement condreglement) {
        this.condreglement = condreglement;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneCommande> lignes) {
        this.lignes = lignes;
    }

    public Double getTotaltaxes() {
        return totaltaxes;
    }

    public void setTotaltaxes(Double totaltaxes) {
        this.totaltaxes = totaltaxes;
    }    

    public double getTotalht() {
        return totalht;
    }

    public void setTotalht(double totalht) {
        this.totalht = totalht;
    }

    public double getTotalttc() {
        return totalttc;
    }

    public void setTotalttc(double totalttc) {
        this.totalttc = totalttc;
    }
      
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String getOwnermodule() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return !state.equalsIgnoreCase("etabli"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return !state.equalsIgnoreCase("etabli"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    @Override
    public boolean isActivatefollower() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
         List<State> states = new ArrayList<State>();
        State state = new State("etabli", "broullion");
        states.add(state);
        state = new State("transmi", "transmi");
        states.add(state);
        state = new State("annule", "annule");
        states.add(state);
        return states; //To change body of generated methods, choose Tools | Templates.
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
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "commandes"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "commande"; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
    
    
    
}
