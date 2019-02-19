/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_FAAR")
public class FamilleArticle extends BaseElement implements Serializable,Comparable<FamilleArticle>{

    @Predicate(label = "reference",optional = false,unique = true,search = true)
    private String code ;
    
    @Predicate(label = "intitule",search = true)
    private String intitule ;
    
    @Predicate(label = "type.de.famille",target = "combobox",values = "Détail;Total;Centralisateur",search = true)
    private String type ="0";
     
     @ManyToOne
    @JoinColumn(name = "FAAR_ID")
    @Predicate(label = "centralisation",type = FamilleArticle.class,target = "many-to-one",search = true)
    @Filter(value = "[{\"fieldName\":\"type\",\"value\":\"1\"}]")
    private FamilleArticle centralisateur ;    
   
    
    @ManyToOne
    @JoinColumn(name = "UNAC_ID")
    @Predicate(label = "unite.achat",type=UniteAchat.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "complements")
    private UniteAchat unitevente ;
    
    @ManyToOne
    @JoinColumn(name = "UNGE_ID")
    @Predicate(label = "unite.de.gestion",type = UniteGestion.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "complements")
    private UniteGestion unite ;    
   
    
    @Predicate(label = "suivi.stock" ,target = "combobox",values = "Aucun;Sérialisé;CMUP;FIFO;LIFO;Par lot",search = true)
    private String suivistock = "0"; 
    
    @Predicate(label = "garantie.mois",type = Short.class,search = true)
    private Short garantie = 0 ;
    
    @Predicate(label = "cout.de.stockage",type = Double.class,group = true,groupName = "group1",groupLabel = "complements")
    private Double coutstockage = 0.0;
    
    @Predicate(label = "cout.de.transport",type = Double.class,group = true,groupName = "group1",groupLabel = "complements")
    private Double couttransport = 0.0;
    
    @Predicate(label = "delai.de.livraison",type = Short.class,group = true,groupName = "group1",groupLabel = "complements")
    private Short delailivraison =0 ;    
    
    @Predicate(label = "code.fiscal",search = true)
    private String codefiscal ;

    /**
     * 
     * @param code
     * @param intitule
     * @param unitevente
     * @param unite
     * @param centralisateur
     * @param categorie
     * @param pays
     * @param codefiscal 
     */
    public FamilleArticle(String code, String intitule, UniteAchat unitevente, UniteGestion unite, FamilleArticle centralisateur, String codefiscal) {
        this.code = code;
        this.intitule = intitule;
        this.unitevente = unitevente;
        this.unite = unite;
        this.centralisateur = centralisateur;
        this.codefiscal = codefiscal;
    }

    /**
     * 
     * @param code
     * @param intitule
     * @param unitevente
     * @param unite
     * @param centralisateur
     * @param categorie
     * @param pays
     * @param codefiscal
     * @param id
     * @param designation
     * @param moduleName 
     */
    public FamilleArticle(String code, String intitule, UniteAchat unitevente, UniteGestion unite, FamilleArticle centralisateur, String codefiscal, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.intitule = intitule;
        this.unitevente = unitevente;
        this.unite = unite;
        this.centralisateur = centralisateur;
        this.codefiscal = codefiscal;
    }

    public FamilleArticle() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UniteAchat getUnitevente() {
        return unitevente;
    }

    public void setUnitevente(UniteAchat unitevente) {
        this.unitevente = unitevente;
    }

    public UniteGestion getUnite() {
        return unite;
    }

    public void setUnite(UniteGestion unite) {
        this.unite = unite;
    }

    public FamilleArticle getCentralisateur() {
        return centralisateur;
    }

    public void setCentralisateur(FamilleArticle centralisateur) {
        this.centralisateur = centralisateur;
    }

    public String getSuivistock() {
        return suivistock;
    }

    public void setSuivistock(String suivistock) {
        this.suivistock = suivistock;
    }

    public Short getGarantie() {
        return garantie;
    }

    public void setGarantie(Short garantie) {
        this.garantie = garantie;
    }

    public Double getCoutstockage() {
        return coutstockage;
    }

    public void setCoutstockage(Double coutstockage) {
        this.coutstockage = coutstockage;
    }

    public Double getCouttransport() {
        return couttransport;
    }

    public void setCouttransport(Double couttransport) {
        this.couttransport = couttransport;
    }

    public Short getDelailivraison() {
        return delailivraison;
    }

    public void setDelailivraison(Short delailivraison) {
        this.delailivraison = delailivraison;
    }

   
    public String getCodefiscal() {
        return codefiscal;
    }

    public void setCodefiscal(String codefiscal) {
        this.codefiscal = codefiscal;
    }

    @Override
    public String getDesignation() {
        return intitule; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "familles.articles"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
       return "famille.articles"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(FamilleArticle o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
    
}
