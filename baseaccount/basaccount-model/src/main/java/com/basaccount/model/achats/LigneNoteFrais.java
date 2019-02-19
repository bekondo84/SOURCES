/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.Taxe;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_LINOFR_ACH")
public class LigneNoteFrais extends BaseElement implements Serializable,Comparable<LigneNoteFrais>{

    @ManyToOne
    @JoinColumn(name = "COMP_ID")
    @Predicate(label = "compte",type = Compte.class,target = "many-to-one",optional = false,search = true)
    private Compte compte ;
    
    @Predicate(label = "description",search = true)
    private String libelle ;
    
    @Predicate(label = "montant",type = Double.class,optional = false,search = true)
    private Double montant =0.0;
    
    @ManyToMany
    @JoinTable(name = "NOF_TAX_ACH",joinColumns = @JoinColumn(name = "NOF_ID"),inverseJoinColumns = @JoinColumn(name = "TAX_ID"))
    @Predicate(label = "taxes",type = Taxe.class,target = "many-to-many",search = true)
    @Filter("[{\"fieldName\":\"porte\",\"value\":\"1\"}]")
    private List<Taxe> taxes =new ArrayList<Taxe>();
    
    @Predicate(label = "total.taxes",type = Double.class,search = true,editable = false)
    private Double taxe = 0.0;
    
    @Predicate(label = "total.ttc",type = Double.class,search = true,editable = false)
    private Double total =0.0;    
    

    public LigneNoteFrais(Compte compte, String libelle) {
        this.compte = compte;
        this.libelle = libelle;
    }

    public LigneNoteFrais(Compte compte, String libelle, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.compte = compte;
        this.libelle = libelle;
    }
    
    /**
     * 
     * @param ligne 
     */
    public LigneNoteFrais(LigneNoteFrais ligne) {
        super(ligne.id, ligne.designation, ligne.moduleName,ligne.compareid);
        if(ligne.compte!=null){
            this.compte = new Compte(ligne.compte);
        }
        this.libelle = ligne.libelle;
        this.montant = ligne.montant;
        this.total = ligne.total;
        this.taxe = ligne.taxe;
    }

    /**
     * 
     */
    public LigneNoteFrais() {
    }
    
    

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getTaxe() {
        return taxe;
    }

    public void setTaxe(Double taxe) {
        this.taxe = taxe;
    }
    
    

    @Override
    public String getDesignation() {
        return compte.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "note.frais"; //To change body of generated methods, choose Tools | Templates.
    }

    public List<Taxe> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Taxe> taxes) {
        this.taxes = taxes;
    }

    

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    
    @Override
    public int compareTo(LigneNoteFrais o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
