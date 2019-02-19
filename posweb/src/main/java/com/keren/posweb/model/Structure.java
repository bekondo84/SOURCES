/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_SOCIETE")
@XmlRootElement
public class Structure extends BaseElement implements Serializable,Comparable<Structure>{
    
    @Predicate(label = "image",search = false,target = "image")
     private String image ;
    
     @Predicate(label = "nom" ,optional = false,unique = true,search = true)
     private String code ;
     
     @Predicate(label = "slogan",search = true)
     private String intitule;
     
     @Predicate(label = "adresse",group = true,groupName = "infosgenerales",groupLabel = "informations.generales",search = true)
     private String adresse;
     
     @Predicate(label = "telephone",target = "tel",group = true,groupName = "infosgenerales",groupLabel = "informations.generales",search = true)
     private String telephone;    
     
     @Predicate(label = "ville",group = true,groupName = "infosgenerales",groupLabel = "informations.generales")
     private String ville;
     
     @Predicate(label = "fax" , target = "tel",group = true,groupName = "infosgenerales",groupLabel = "informations.generales")
     private String fax ;
     
     @Predicate(label = "code.postal",group = true,groupName = "infosgenerales",groupLabel = "informations.generales",search = true)
     private String codePostal;
     
     @Predicate(label = "courriel",group = true,groupName = "infosgenerales",groupLabel = "informations.generales",search = true)
     private String courriel ;     
   
     @Predicate(label = "numero.fiscal",group = true,groupName = "infosgenerales",groupLabel = "informations.generales")
     private String numFiscal ;
     
     @Predicate(label = "site.web",target = "url",group = true,groupName = "infosgenerales",groupLabel = "informations.generales")
     private String siteWeb ;
     
     @Predicate(label = "registre.du.commerce",group = true,groupName = "infosgenerales",groupLabel = "informations.generales")
     private String registre ;
     
     @Predicate(label = "devise" ,type = POSDevise.class,group = true,groupName = "infosgenerales",groupLabel = "informations.generales")
     @ManyToOne
     @JoinColumn(name = "DEV_ID")
     private POSDevise devise ;
     
    @Predicate(label = "societe.mere" , type = Structure.class,target="many-to-one")
    @ManyToOne
    @JoinColumn(name = "SOCP_ID")
     private Structure societeMere ;

     
     /**
      * 
      */
    public Structure() {
    }

   /**
    * 
    * @param entity 
    */
    public Structure(Structure entity) {
        this.image = entity.image;
        this.code = entity.code;
        this.intitule = entity.designation;
        this.adresse = entity.adresse;
        this.telephone = entity.telephone;
        this.ville = entity.ville;
        this.fax = entity.fax;
        this.codePostal = entity.codePostal;
        this.courriel = entity.courriel;
        this.numFiscal = entity.numFiscal;
        this.siteWeb = entity.siteWeb;
        this.registre = entity.registre;
        this.devise = entity.devise;
        this.societeMere = entity.societeMere;
    }

   
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesignation() {
        return code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getNumFiscal() {
        return numFiscal;
    }

    public void setNumFiscal(String numFiscal) {
        this.numFiscal = numFiscal;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getRegistre() {
        return registre;
    }

    public void setRegistre(String registre) {
        this.registre = registre;
    }

    public POSDevise getDevise() {
        return devise;
    }

    public void setDevise(POSDevise devise) {
        this.devise = devise;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Structure getSocieteMere() {
        return societeMere;
    }

    public void setSocieteMere(Structure societeMere) {
        this.societeMere = societeMere;
    }

   
    
    

//    public Societe getSocieteMere() {
//        return societeMere;
//    }
//
//    public void setSocieteMere(Societe societeMere) {
//        this.societeMere = societeMere;
//    }

    @Override
    public String getListTitle() {
        return "SOCIETES"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "SOCIETE"; //To change body of generated methods, choose Tools | Templates.
    }
     
     @Override
    public String getModuleName() {
         //To change body of generated methods, choose Tools | Templates.
        return "posweb";
    }
    
    

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
    
     
    @Override
    public int compareTo(Structure o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
