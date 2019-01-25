/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.achat.model.comptabilite;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import com.teratech.achat.model.base.Societe;
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
@Table(name = "T_CANALYTIQUE")
public class CompteAnalytique extends BaseElement implements Serializable,Comparable<CompteAnalytique>{

    @Predicate(label = "numero.compte",unique = true,optional = false,updatable = false,search = true)
    private String code ;
    
    @Predicate(label = "intitule",search = true)
    private String label ;
    
    @Predicate(label = "type.compte",target ="combobox" ,values ="Détail;Total" ,search = false)
    private String type ="0";
    
    @Predicate(label = "classement",search = true)
    private String classe;
    
    @Predicate(label = "report.a.nouveau" , type = Boolean.class,search = false)
    private Boolean reportANouveau = false;
    
    @Predicate(label = "actif" , type = Boolean.class,search = false)
    private Boolean active = true ;
    
    @Predicate(label = "niveau.analyse",type = CompteAnalytique.class,target = "many-to-one",search = true)
    @ManyToOne
    @JoinColumn(name = "NA_ID")
    private NiveauAnalyse niveau ;
    
    @ManyToOne
    @JoinColumn(name = "SOC_ID")
    @Predicate(label = "societe",updatable = false,type = Societe.class,target = "many-to-one",search = true)
    private Societe societe ;
    
    @Predicate(label = " ",group = true,groupLabel = "bloc.notes",groupName = "group2",target = "textarea",search = false)
    private String note ;

    /**
     * 
     */
    public CompteAnalytique() {
    }
    
     public CompteAnalytique(CompteAnalytique compte) {
        super(compte.id, compte.designation, compte.moduleName,compte.compareid);
        this.code = compte.code;
        this.label = compte.label;
        type = compte.type;
        this.classe = compte.classe;
        reportANouveau = compte.reportANouveau;
        this.niveau = compte.niveau;
        this.active = compte.active;
        this.societe = compte.societe;
        this.note = compte.note;
    }

    /**
     * 
     * @param code
     * @param label
     * @param classe
     * @param niveau
     * @param id
     * @param designation
     * @param moduleName 
     */
    public CompteAnalytique(String code, String label, String classe, NiveauAnalyse niveau, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.label = label;
        this.classe = classe;
        this.niveau = niveau;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public boolean isReportANouveau() {
        return reportANouveau;
    }

    public void setReportANouveau(boolean reportANouveau) {
        this.reportANouveau = reportANouveau;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public NiveauAnalyse getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauAnalyse niveau) {
        this.niveau = niveau;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    
    
     @Override
    public String getModuleName() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code+" - "+label; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "plan.analytiques"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "compte.analytique"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(CompteAnalytique o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
