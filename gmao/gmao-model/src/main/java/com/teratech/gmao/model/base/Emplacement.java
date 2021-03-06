/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.gmao.model.base;

import com.core.base.BaseElement;
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
@Table(name = "T_EMPL")
public class Emplacement extends BaseElement implements Serializable,Comparable<Emplacement>{

    @Predicate(label = "Nom de l'emplacement",optional = false,unique = true,search = true)
    private String code ;
    
    @ManyToOne
    @JoinColumn(name = "ENTR_ID")
    @Predicate(label = "Entrepôt parent",type = Entrepot.class,target = "many-to-one",search = true)
    private Entrepot edepot ;
    
    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    @Predicate(label = "Emplacement parent",type = Emplacement.class,target = "many-to-one",search = true)
    private Emplacement parent ;
    
    
    @Predicate(label = "Type d'emplacement",target = "combobox",values = "Emplacement fournisseur;Vue;Emplacement interne;Emplacement client;Inventaire;Approvisionnement;Production;Emplacement de transit",search = true)
    private String type = "0";
    
    @Predicate(label = "Couloir(X)",group = true,groupName = "group1",groupLabel = "Complement")
    private String couloir ;
    
    @Predicate(label = "Rayon(Y)",group = true,groupName = "group1",groupLabel = "Complement")
    private String rayon  ;
    
    @Predicate(label = "Hauteur(X)",group = true,groupName = "group1",groupLabel = "Complement")
    private String hauteur  ;
    
    @Predicate(label = "Code barre de l'emplacement",group = true,groupName = "group1",groupLabel = "Complement")
    private String codebarre ;
    
    @Predicate(label = "Emplacement de rebus?",type = Boolean.class,group = true,groupName = "group1",groupLabel = "Complement")
    private Boolean rebus = Boolean.FALSE;
    
    @Predicate(label = "Actif?",type = Boolean.class,group = true,groupName = "group1",groupLabel = "Complement")
    private Boolean actif =Boolean.TRUE;
    
    @Predicate(label = " ",target = "textarea",group = true,groupName = "group2",groupLabel = "Commentaire")
    private String commentaire ;

    /**
     * 
     * @param code
     * @param edepot
     * @param parent
     * @param proprio
     * @param couloir
     * @param rayon
     * @param hauteur
     * @param codebarre
     * @param commentaire 
     */
    public Emplacement(String code, Entrepot edepot, Emplacement parent, String couloir, String rayon, String hauteur, String codebarre, String commentaire) {
        this.code = code;
        this.edepot = edepot;
        this.parent = parent;
        this.couloir = couloir;
        this.rayon = rayon;
        this.hauteur = hauteur;
        this.codebarre = codebarre;
        this.commentaire = commentaire;
    }

    /**
     * 
     * @param code
     * @param edepot
     * @param parent
     * @param proprio
     * @param couloir
     * @param rayon
     * @param hauteur
     * @param codebarre
     * @param commentaire
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Emplacement(String code, Entrepot edepot, Emplacement parent, String couloir, String rayon, String hauteur, String codebarre, String commentaire, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.edepot = edepot;
        this.parent = parent;
        this.couloir = couloir;
        this.rayon = rayon;
        this.hauteur = hauteur;
        this.codebarre = codebarre;
        this.commentaire = commentaire;
    }
    
    public Emplacement(Emplacement empl) {
        super(empl.id, empl.designation, empl.moduleName,empl.compareid);
        this.code = empl.code;
        if(empl.getEdepot()!=null){
            this.edepot = new Entrepot(empl.edepot);
        }
        if(empl.parent!=null){
            this.parent = new Emplacement(empl.parent);
        }
        this.couloir = empl.couloir;
        this.rayon = empl.rayon;
        this.hauteur = empl.hauteur;
        this.codebarre = empl.codebarre;
        this.commentaire = empl.commentaire;
    }

    public Emplacement() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Entrepot getEdepot() {
        return edepot;
    }

    public void setEdepot(Entrepot edepot) {
        this.edepot = edepot;
    }

    public Emplacement getParent() {
        return parent;
    }

    public void setParent(Emplacement parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }   

    public String getCouloir() {
        return couloir;
    }

    public void setCouloir(String couloir) {
        this.couloir = couloir;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getHauteur() {
        return hauteur;
    }

    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public Boolean getRebus() {
        return rebus;
    }

    public void setRebus(Boolean rebus) {
        this.rebus = rebus;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "empl200221081308"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechgmao"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "EMPLACEMENTS"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "EMPLACEMENT";  //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(Emplacement o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
