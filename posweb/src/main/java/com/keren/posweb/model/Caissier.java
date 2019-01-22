/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.KHeader;
import com.megatim.common.annotations.KHeaders;
import com.megatim.common.annotations.KValue;
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
@KHeaders(value={
   @KHeader(type = "button",name = "work1",label = "enable",target = "workflow",roles = {"administrateur","gestionnaire"},states = {"desable"},pattern = "btn btn-success"
       , value = @KValue("{'model':'posweb','entity':'caissier','method':'enable'}")
   ),@KHeader(type = "button",name = "work1",label = "desable",target = "workflow",roles = {"administrateur","gestionnaire"},states = {"enable"},pattern = "btn btn-danger"
       , value = @KValue("{'model':'posweb','entity':'caissier','method':'desable'}")
   )
},statubar = true)
@Entity
@Table(name = "T_CASHIER_POS")
public class Caissier extends  BaseElement implements Serializable,Comparable<Caissier>{

    @Predicate(label = "img",target = "image")
    private String image ="avatar.png";
    
    @Predicate(label = "reference",optional = false,unique = true,search = true)
    private String code;
    
    @Predicate(label = "nnom.prenom",optional = false,search = true)
    private String intitule ;
    
    @Predicate(label = "link.account",type = UserAccount.class,target = "many-to-one",unique = true,optional = false,search = true)
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserAccount compte ;
    
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

    @ManyToMany(mappedBy = "cashiers")
    @Predicate(label = " ",type = PointVente.class,target = "many-to-many-list",group = true,groupLabel = "points.of.sales",groupName = "group2",edittable = true,editable = false)
    private List<PointVente> pointsofsales = new ArrayList<PointVente>() ;
    
    @Predicate(label = " ",search = true,hide=true)
    private String state ="enable";
    
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
    public Caissier(String code, String intitule, UserAccount compte, PointVente pos, String classe, String adresse, String poste, String tel, String mobile, String fax, String courriel, Civilite civilite, long id, String designation, String moduleName, long comparedid) {
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
            this.compte = entity.compte;
        }       
        this.image = entity.image;
        this.classe = entity.classe;
        this.adresse = entity.adresse;
        this.poste = entity.poste;
        this.tel = entity.tel;
        this.mobile =entity.mobile;
        this.fax = entity.fax;
        this.courriel = entity.courriel;
        this.civilite = entity.civilite;
        this.state = entity.state;
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

    public UserAccount getCompte() {
        return compte;
    }

    public void setCompte(UserAccount compte) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    

    @Override
    public String getOwnermodule() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return super.isDesableupdate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivatefollower() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
        states = new ArrayList<State>();
        states.add(new State("enable", "enable"));
        states.add(new State("desable", "desable"));
        return states; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "210120191425cashier"; //To change body of generated methods, choose Tools | Templates.
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
        return intitule; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "caissiers"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "caissier"; //To change body of generated methods, choose Tools | Templates.
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
