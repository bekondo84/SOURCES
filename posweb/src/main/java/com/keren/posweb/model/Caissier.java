/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
import com.core.base.State;
import com.core.securites.Utilisateur;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_CASHIER_POS")
public class Caissier extends  BaseElement implements Serializable,Comparable<Caissier>{

    @Predicate(label = "reference",optional = false,unique = true,search = true)
    private String code;
    
    @Predicate(label = "nnom.prenom",optional = false,search = true)
    private String intitule ;
    
    @Predicate(label = "link.account",type = Utilisateur.class,target = "many-to-one",unique = true,optional = false,search = true)
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Utilisateur compte ;
    
    @ManyToMany(mappedBy = "cashiers")
    @Predicate(label = " ",type = PointVente.class,target = "many-to-many-list",group = true,groupLabel = "points.of.sales",groupName = "group1",edittable = true,editable = false)
    private List<PointVente> pointsofsales = new ArrayList<PointVente>() ;
    
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

    public Caissier() {
    }

    /**
     * 
     * @param code
     * @param intitule
     * @param compte
     * @param pos
     * @param classe
     * @param adresse
     * @param poste
     * @param tel
     * @param mobile
     * @param fax
     * @param courriel
     * @param civilite
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public Caissier(String code, String intitule, Utilisateur compte, PointVente pos, String classe, String adresse, String poste, String tel, String mobile, String fax, String courriel, Civilite civilite, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.intitule = intitule;
        this.compte = compte;
//        this.pointsofsales = pos;
        this.classe = classe;
        this.adresse = adresse;
        this.poste = poste;
        this.tel = tel;
        this.mobile = mobile;
        this.fax = fax;
        this.courriel = courriel;
        this.civilite = civilite;
    }
    
    /**
     * 
     * @param entity 
     */
    public Caissier(Caissier entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.code;
        this.intitule = entity.intitule;
        if(entity.compte!=null){
            this.compte = new Utilisateur(entity.compte);
        }       
        this.classe = entity.classe;
        this.adresse = entity.adresse;
        this.poste = entity.poste;
        this.tel = entity.tel;
        this.mobile =entity.mobile;
        this.fax = entity.fax;
        this.courriel = entity.courriel;
        this.civilite = entity.civilite;
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

    public Utilisateur getCompte() {
        return compte;
    }

    public void setCompte(Utilisateur compte) {
        this.compte = compte;
    }

    public List<PointVente> getPointsofsales() {
        return pointsofsales;
    }

    public void setPointsofsales(List<PointVente> pointsofsales) {
        this.pointsofsales = pointsofsales;
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

    @Override
    public String getOwnermodule() {
        return super.getOwnermodule(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return super.isDesableupdate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivatefollower() {
        return super.isActivatefollower(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
        return super.getStates(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivefilelien() {
        return super.isActivefilelien(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return super.getSerial(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return super.isDesabledelete(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return super.isDesablecreate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return super.isCreateonfield(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return super.getModuleName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return super.getListTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return super.getEditTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(Caissier o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
