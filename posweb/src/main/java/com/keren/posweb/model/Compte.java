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
@Table(name = "T_COMPTE")
public class Compte extends BaseElement implements Serializable,Comparable<Compte>{

    @Predicate(label = "numero.compte",unique = true,optional = false,updatable = false,search = true)
    private String code ;
    
    @Predicate(label = "intitule",search = true)
    private String libele ;
    
    @Predicate(label = "type.de.compte",target = "combobox",values = "Detail;Total",search = false)
    private String type ="0";
    
    @Predicate(label = "nature.compte",target = "combobox",values = "Aucune;Client;Fournisseur;Salarié;Banque;Caisse;Amorts/Provision;Resultat de gestion;Immobilisation;Capitaux;Stock;Titre",search = false)
    private String nature ="0";
    
    @ManyToOne
    @JoinColumn(name = "SOC_ID")
    @Predicate(label = "societe",type = Structure.class,target = "many-to-one",search = true,searchfields = "intitule,code,adresse,telephone")
    private Structure societe ;
    
    @Predicate(label = "code.taxe" ,target = "many-to-one",type = Taxe.class,search = false)
    @ManyToOne
    @JoinColumn(name = "TAXE_ID")
    private Taxe taxe ;
    
   @Predicate(label = "report.a.nouveau",target = "combobox",values = "Aucune;Solde;Detail",search = false)
    private String reportdesanouveau = "0";
   
    @Predicate(label = "lettrage",target = "combobox",values = "Automatique;Manuel")
    private String lettrage = "0";
   
    @Predicate(label = " ",group = true,groupLabel = "bloc.notes",groupName = "group2",target = "textarea",search = false)
    private String note ;

    /**
     * 
     */
    public Compte() {
    }

    /**
     * 
     * @param code
     * @param libele
     * @param societe
     * @param taxe
     * @param note
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Compte(String code, String libele, Structure societe, Taxe taxe, String note, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.libele = libele;
        this.societe = societe;
        this.taxe = taxe;
        this.note = note;
    }

    public Compte(Compte compte) {
        super(compte.id, compte.designation, compte.moduleName,compte.compareid);
        this.code = compte.code;
        this.libele = compte.libele;
        this.societe = compte.societe;
        if(compte.getTaxe()!=null){
            this.taxe = new Taxe(compte.getTaxe());
        }
       
        this.note = compte.note;
        this.type = compte.type;
        nature = compte.nature;
        reportdesanouveau = compte.getReportdesanouveau();
        lettrage = compte.getLettrage();
    }
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Structure getSociete() {
        return societe;
    }

    public void setSociete(Structure societe) {
        this.societe = societe;
    }

    public Taxe getTaxe() {
        return taxe;
    }

    public void setTaxe(Taxe taxe) {
        this.taxe = taxe;
    }

    public String getReportdesanouveau() {
        return reportdesanouveau;
    }

    public void setReportdesanouveau(String reportdesanouveau) {
        this.reportdesanouveau = reportdesanouveau;
    }

    public String getLettrage() {
        return lettrage;
    }

    public void setLettrage(String lettrage) {
        this.lettrage = lettrage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getDesignation() {
        return code+" - "+libele; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "plan.comptable"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "compte"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(Compte o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
