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

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_TIER")
public class Client extends BaseElement implements Serializable,Comparable<Client>{

    @Predicate(label = "image",target = "image")
    private String image ;
    
    @Predicate(label = "actif",type = Boolean.class,search = false)
    private Boolean active = true;
     
    @Predicate(label = "numero.compte",unique = true,optional = false,updatable = false,search = true)
    private String code ;
    
    @Predicate(label = "type",unique = true,optional = false,updatable = false,target = "combobox",values = "Client;Fournisseur;Salarié;Autre",search = false)
    private String type ;
    
    @Predicate(label = "intitule",search = true)
    private String label ;
   
    @Predicate(label = "classement",group = true,groupName = "group1",groupLabel = "Identification",search = false,sequence = 1)
    private String classe;
    
    @Predicate(label = "adresse",group = true,groupName = "group1",groupLabel = "Identification",search = false,sequence = 1)
    private String adresse;
    
    @Predicate(label = "poste.occupe",group = true,groupName = "group1",groupLabel = "Identification",search = false,sequence = 1)
    private String poste;
    
    @Predicate(label = "telephone",group = true,groupName = "group1",groupLabel = "Identification",target = "tel",sequence = 1,search = true)
    private String tel;
    
    @Predicate(label = "mobile",group = true,groupName = "group1",groupLabel = "Identification",target = "tel",sequence = 1)
    private String mobile;
    
    @Predicate(label = "fax",group = true,groupName = "group1",groupLabel = "Identification",sequence = 1)
    private String fax;
    
    @Predicate(label = "courriel",group = true,groupName = "group1",groupLabel = "Identification",target = "email",sequence = 1,search = true)
    private String courriel;
    
    @Predicate(label = "civilite",target = "many-to-one",type = Civilite.class,values = "",group = true,groupName = "group1",groupLabel = "Identification",sequence = 1,search = true)
    @ManyToOne
    @JoinColumn(name = "CV_ID")
    private Civilite civilite;    
    
    @Predicate(label = "niveau.confiance",target = "combobox",values = "Bon débiteur;Débiteur normal;Mauvais débiteur",group = true,groupName = "group2",groupLabel = "Comptabilité",search = false,sequence = 2)
    private String confiance ="0";
    
    @Predicate(label = "notes.internes",group = true,groupName = "group5",groupLabel = "Notes internes",target = "textarea",search = false,sequence = 5)
    private String note;
    

    /**
     * 
     */
    public Client() {
    }

    /**
     *
     * @param tier
     */
    public Client(Client tier) {
        super(tier.id, tier.designation, tier.moduleName,tier.compareid);
        this.image = tier.image;
        this.active = tier.getActive();
        this.code = tier.code;
        this.type = tier.type;
        this.label = tier.label;
        this.classe = tier.classe;
        this.adresse = tier.adresse;
        this.poste = tier.poste;
        this.tel = tier.tel;
        this.mobile = tier.mobile;
        this.fax = tier.fax;
        this.courriel = tier.courriel;
        this.civilite = tier.civilite;
        this.confiance = tier.getConfiance();
        this.note = tier.note;
    }
    
     public Client(String image, String code, String type, String label, String classe, String adresse, String poste, String tel, String mobile, String fax, String courriel, Civilite civilite, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.image = image;
        this.code = code;
        this.type = type;
        this.label = label;
        this.classe = classe;
        this.adresse = adresse;
        this.poste = poste;
        this.tel = tel;
        this.mobile = mobile;
        this.fax = fax;
        this.courriel = courriel;
        this.civilite = civilite;       
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public String getConfiance() {
        return confiance;
    }

    public void setConfiance(String confiance) {
        this.confiance = confiance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getModuleName() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public String getListTitle() {
        return "clients"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "clients"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCompareid() {
        return super.getCompareid(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code+" - "+label; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
 
    @Override
    public int compareTo(Client o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
    
}
