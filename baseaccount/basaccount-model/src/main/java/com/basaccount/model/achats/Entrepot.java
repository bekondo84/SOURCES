/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.tiers.Contact;
import com.basaccount.model.tiers.Tier;
import com.core.base.BaseElement;
import com.core.referentiels.Pays;
import com.core.referentiels.Region;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_ENTR")
public class Entrepot extends BaseElement implements Serializable,Comparable<Entrepot>{
    
    @Predicate(label = "code.depot",optional = false,unique = true,search = true,colsequence = 1)
    private String code ;
    
    @Predicate(label = "magasin.principal.?" ,type = Boolean.class,search = true,colsequence = 3)
    private Boolean principal = Boolean.FALSE;
    
    @Predicate(label = "intitule",search = true,colsequence = 2)
    private String intitule ;
    
    @Predicate(label = "adresse",group = true,groupName = "group1",groupLabel = "complement")
    private String adresse1 ;
    
    @Predicate(label = "complement",group = true,groupName = "group1",groupLabel = "complement")
    private String adresse2;
    
    @Predicate(label = "boite.postale",group = true,groupName = "group1",groupLabel = "complement")
    private String cp ;
    
    @Predicate(label = "ville",group = true,groupName = "group1",groupLabel = "complement")
    private String ville ;
    
    @ManyToOne
    @JoinColumn(name = "REGI_ID")
    @Predicate(label = "region",type = Region.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "complement")
    private Region region ;
    
    @ManyToOne
    @JoinColumn(name = "PAYS_ID")
    @Predicate(label = "pays",type = Pays.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "complement")
    private Pays pays ;
    
    @ManyToOne
    @JoinColumn(name = "TIER_ID")
    @Predicate(label = "responsable",type = Tier.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "complement")
    private Tier responsable ;   
    
    
    @Predicate(label = "telephone",target = "tel",group = true,groupName = "group1",groupLabel = "complement")
    private String tel;
    
     @Predicate(label = "mobile",target = "tel",group = true,groupName = "group1",groupLabel = "complement")
    private String mobile;
    
    @Predicate(label = "courriel",target = "email",group = true,groupName = "group1",groupLabel = "complement")
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "CONT_ID")
    @Predicate(label = " ",type = Contact.class,target = "one-to-many",group = true,groupName = "group2",groupLabel = "contacts")
    private List<Contact> contacts = new ArrayList<Contact>();
    
    @Predicate(label = " ",target = "textarea",group = true,groupName = "group3",groupLabel = "commentaire")
    private String commentaire ;

    /**
     * 
     * @param code
     * @param intitule
     * @param adresse1
     * @param adresse2
     * @param cp
     * @param ville
     * @param region
     * @param pays
     * @param responsable
     * @param tel
     * @param mobile
     * @param email
     * @param commentaire 
     */
    public Entrepot(String code, String intitule, String adresse1, String adresse2, String cp, String ville, Region region, Pays pays, Tier responsable, String tel, String mobile, String email, String commentaire) {
        this.code = code;
        this.intitule = intitule;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.cp = cp;
        this.ville = ville;
        this.region = region;
        this.pays = pays;
        this.responsable = responsable;
        this.tel = tel;
        this.mobile = mobile;
        this.email = email;
        this.commentaire = commentaire;
    }

    /**
     * 
     * @param code
     * @param intitule
     * @param adresse1
     * @param adresse2
     * @param cp
     * @param ville
     * @param region
     * @param pays
     * @param responsable
     * @param tel
     * @param mobile
     * @param email
     * @param commentaire
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Entrepot(String code, String intitule, String adresse1, String adresse2, String cp, String ville, Region region, Pays pays, Tier responsable, String tel, String mobile, String email, String commentaire, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.intitule = intitule;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.cp = cp;
        this.ville = ville;
        this.region = region;
        this.pays = pays;
        this.responsable = responsable;
        this.tel = tel;
        this.mobile = mobile;
        this.email = email;
        this.commentaire = commentaire;
    }
    
    public Entrepot(Entrepot entr) {
        super(entr.id, entr.designation, entr.moduleName,entr.compareid);
        this.code = entr.code;
        this.principal = entr.principal;
        this.intitule = entr.intitule;
        this.adresse1 = entr.adresse1;
        this.adresse2 = entr.adresse2;
        this.cp = entr.cp;
        this.ville = entr.ville;
        this.region = entr.region;
        this.pays = entr.pays;
        if(entr.responsable!=null){
            this.responsable = new Tier(entr.responsable);
        }//responsable;
        this.tel = entr.tel;
        this.mobile = entr.mobile;
        this.email = entr.email;
        this.commentaire = entr.commentaire;
    }

    public Entrepot() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Tier getResponsable() {
        return responsable;
    }

    public void setResponsable(Tier responsable) {
        this.responsable = responsable;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String getDesignation() {
        return code+" - "+intitule; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "entrepots"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "entrepot"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
   
    @Override
    public int compareTo(Entrepot o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
