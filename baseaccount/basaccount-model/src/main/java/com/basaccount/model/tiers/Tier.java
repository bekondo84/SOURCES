/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.tiers;

import com.basaccount.model.banques.CompteBancaire;
import com.basaccount.model.comptabilite.Compte;
import com.core.base.BaseElement;
import com.core.referentiels.Societe;
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
 * @author Commercial_2
 */
@Entity
@Table(name = "T_TIER")
public class Tier extends BaseElement implements Serializable,Comparable<Tier>{

    @Predicate(label = "imgae",target = "image")
    private String image ;
    
    @Predicate(label = "actif",type = Boolean.class,search = false)
    private Boolean active = true;
     
    @Predicate(label = "numero.compte",unique = true,optional = false,updatable = false,search = true)
    private String code ;
    
    @Predicate(label = "type",unique = true,optional = false,updatable = false,target = "combobox",values = "Client;Fournisseur;Salarié;Autre",search = false)
    private String type ;
    
    @Predicate(label = "intitule",search = true)
    private String label ;
    
    @Predicate(label = "societe",type = Societe.class,target = "many-to-one",search = true)
    @ManyToOne
    @JoinColumn(name = "SOC_ID")
    private Societe societe;
    
   
    @Predicate(label = "classement",group = true,groupName = "group1",groupLabel = "identification",search = false,sequence = 1)
    private String classe;
    
    @Predicate(label = "adresse",group = true,groupName = "group1",groupLabel = "identification",search = false,sequence = 1)
    private String adresse;
    
    @Predicate(label = "poste.occupe",group = true,groupName = "group1",groupLabel = "identification",search = false,sequence = 1)
    private String poste;
    
    @Predicate(label = "telephone",group = true,groupName = "group1",groupLabel = "identification",target = "tel",sequence = 1,search = true)
    private String tel;
    
    @Predicate(label = "mobile",group = true,groupName = "group1",groupLabel = "identification",target = "tel",sequence = 1)
    private String mobile;
    
    @Predicate(label = "fax",group = true,groupName = "group1",groupLabel = "identification",sequence = 1)
    private String fax;
    
    @Predicate(label = "courriel",group = true,groupName = "group1",groupLabel = "identification",target = "email",sequence = 1,search = true)
    private String courriel;
    
    @Predicate(label = "civilite",target = "many-to-one",type = Civilite.class,values = "",group = true,groupName = "group1",groupLabel = "identification",sequence = 1,search = true)
    @ManyToOne
    @JoinColumn(name = "CV_ID")
    private Civilite civilite;    
    
    @Predicate(label = "compte.associe",type = Compte.class,group = true,groupName = "group2",groupLabel = "comptabilite",target = "many-to-one",search = false,sequence = 2)
    @ManyToOne
    @JoinColumn(name = "CPT_ID")
    private  Compte compte ;
    
    @Predicate(label = "niveau.confiance",target = "combobox",values = "Bon débiteur;Débiteur normal;Mauvais débiteur",group = true,groupName = "group2",groupLabel = "comptabilite",search = false,sequence = 2)
    private String confiance ="0";
    
    @Predicate(label = "condition.paiement.client",type = ConditionPaiement.class,target = "many-to-one",group = true,groupName = "group2",groupLabel = "comptabilite",search = false,sequence = 2)
    @ManyToOne
    @JoinColumn(name = "PAYCLI_ID")
    private ConditionPaiement paiementclient ;
    
    @Predicate(label = "condition.paiement.fournisseur",type = ConditionPaiement.class,target = "many-to-one",group = true,groupName = "group2",groupLabel = "comptabilite",search = false,sequence = 2)
    @ManyToOne
    @JoinColumn(name = "PAYFOU_ID")
    private ConditionPaiement paiementfourn ;
    
     
    @Predicate(label = "comptes.bancaire",group = true,groupName = "group3",groupLabel = "comptes.bancaires",target = "one-to-many",type = CompteBancaire.class,search = false,sequence = 3,edittable = true)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "CPT_ID")
    private List<CompteBancaire> comptesbancaire = new ArrayList<CompteBancaire>();
    
    @Predicate(label = "contacts.adresses",group = true,groupName = "group4",groupLabel = "contacts.adresses",type = Contact.class,target = "one-to-many",search = false,sequence = 4,edittable = true)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "CON_ID")
    private List<Contact> contacts = new ArrayList<Contact>();
    
    @Predicate(label = " ",group = true,groupName = "group5",groupLabel = "notes.internes",target = "textarea",search = false,sequence = 5)
    private String note;
    

    /**
     * 
     */
    public Tier() {
    }

    /**
     *
     * @param tier
     */
    public Tier(Tier tier) {
        super(tier.id, tier.designation, tier.moduleName,tier.compareid);
        this.image = tier.image;
        this.active = tier.getActive();
        this.code = tier.code;
        this.type = tier.type;
        this.label = tier.label;
        this.societe = tier.societe;
        this.classe = tier.classe;
        this.adresse = tier.adresse;
        this.poste = tier.poste;
        this.tel = tier.tel;
        this.mobile = tier.mobile;
        this.fax = tier.fax;
        this.courriel = tier.courriel;
        this.civilite = tier.civilite;
        if(tier.compte!=null){
            this.compte = new Compte(tier.compte);
        }
        this.confiance = tier.getConfiance();
        this.paiementclient = tier.paiementclient;
        this.paiementfourn = tier.paiementfourn;
        this.note = tier.note;
    }
    
     public Tier(String image, String code, String type, String label, String classe, String adresse, String poste, String tel, String mobile, String fax, String courriel, Civilite civilite, Compte compte, long id, String designation, String moduleName) {
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
        this.compte = compte;
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

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
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

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getConfiance() {
        return confiance;
    }

    public void setConfiance(String confiance) {
        this.confiance = confiance;
    }

    public ConditionPaiement getPaiementclient() {
        return paiementclient;
    }

    public void setPaiementclient(ConditionPaiement paiementclient) {
        this.paiementclient = paiementclient;
    }

    public ConditionPaiement getPaiementfourn() {
        return paiementfourn;
    }

    public void setPaiementfourn(ConditionPaiement paiementfourn) {
        this.paiementfourn = paiementfourn;
    }

    public List<CompteBancaire> getComptesbancaire() {
        return comptesbancaire;
    }

    public void setComptesbancaire(List<CompteBancaire> comptesbancaire) {
        this.comptesbancaire = comptesbancaire;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public String getListTitle() {
        return "Partenaires"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Partenaire"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCompareid() {
        return super.getCompareid(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
 
    @Override
    public int compareTo(Tier o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
    
}
