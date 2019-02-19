/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.ventes;

import com.basaccount.model.achats.*;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.tiers.Tier;
import com.core.base.State;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.KHeader;
import com.megatim.common.annotations.KHeaders;
import com.megatim.common.annotations.KValue;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BEKO
 */
@KHeaders(statubar = true
        ,value = {
           @KHeader(type = "button",name = "work1",label = "valider",target = "workflow",roles = {"administrateur","gestionnaire"},states = {"etabli"},pattern = "btn btn-danger"
                , value = @KValue("{'model':'baseaccount','entity':'notefraisvente','method':'validate','critical':true,'alert':'cette action est irreversible \nÊtes-vous sûr de vouloir continuer ?'}")
            ),
            @KHeader(type = "button",name = "work2",label = "imprimer.note",target = "report",roles = {"administrateur","gestionnaire"},pattern = "btn btn-default"
                , value = @KValue("{'name':'notedefrais_report01','model':'baseaccount','entity':'notefraisvente','method':'imprime'}")
            )
        })
@Entity
@DiscriminatorValue("VTE")
public class NoteFraisVente extends NoteFraisTMP implements Serializable{
    
     @ManyToOne
    @JoinColumn(name = "FOUR_ID")
    @Predicate(label = "client",type = Tier.class,target = "many-to-one",optional = false,search = true)
     @Filter(value = "[{\"fieldName\":\"type\",\"value\":\"0\"}]")
    private Tier fournisseur ;
     
    @ManyToOne
    @JoinColumn(name = "JOUR_ID")
    @Predicate(label = "journal.comptable",type = JournalComptable.class,target = "many-to-one",optional = false,search = true)
    private JournalComptable journal ;    
    
    @Temporal(TemporalType.DATE)
//    @Predicate(label = "date.echeance",type = Date.class,target = "date",optional = false,search = true)
    private Date decheance;
    
    @Predicate(label = "memo",search = true)
    private String memo;   
    
     @ManyToOne
    @JoinColumn(name = "COMP_ID")
    @Predicate(label = "compte",type = Compte.class,target = "many-to-one",optional = false,search = true)
    private Compte comptecompens ;
    
    @OneToMany(orphanRemoval = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "NOFRVTE_ID")
    @Predicate(label = " ",type = LigneNoteFrais.class,target = "one-to-many",group = true,groupName = "group1",groupLabel = "notes.frais",edittable = true)
    private List<LigneNoteFrais> notes = new ArrayList<LigneNoteFrais>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "NFV_ID")
    @Predicate(label = " ",type = EcheanceReglement.class,target = "one-to-many",group = true,groupName = "group2",groupLabel = "echeances",edittable = true)
    private List<EcheanceReglement> echeances = new ArrayList<EcheanceReglement>();
    
    @Predicate(label = " ",target = "textarea",group = true,groupName = "group3",groupLabel = "notes")
    private String commentaire ;    
    
    @Predicate(label = "total.ht",type = Double.class,search = true,hide = true,editable = false)
    private Double totalht = 0.0;
    
    @Predicate(label = "total.taxes",type = Double.class,hide = true,search = true,editable = false,compute = true,values = "")
    private Double totaltaxes = 0.0;
    
    @Predicate(label = "total.ttc",type = Double.class,hide = true,search = true,editable = false)
    private Double totalttc = 0.0;
    
    @Predicate(label = " ",target = "state",hide = true,search = true)
    private String state ="etabli";
    

        
    /**
     * 
     * @param note 
     */
    public NoteFraisVente(NoteFraisVente note) {
        super(note);
         if(note.fournisseur!=null){
            this.fournisseur = new Tier(note.fournisseur);
        }
        if(note.comptecompens!=null){
            this.comptecompens = new Compte(note.comptecompens);
        }
        
        this.decheance = note.decheance;
        this.memo = note.memo;
        this.journal = note.journal;
        this.commentaire = note.commentaire;
        this.state = note.state;
        this.totalht = note.totalht;
        this.totaltaxes=note.totaltaxes;
        this.totalttc = note.totalttc;
    }

    public NoteFraisVente() {
    }

   

    public Tier getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Tier fournisseur) {
        this.fournisseur = fournisseur;
    }

   
    public Compte getComptecompens() {
        return comptecompens;
    }

    public void setComptecompens(Compte compte) {
        this.comptecompens = compte;
    }

    public Date getDecheance() {
        return decheance;
    }

    public void setDecheance(Date decheance) {
        this.decheance = decheance;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }

   
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }    
    

    public List<LigneNoteFrais> getNotes() {
        return notes;
    }

    public void setNotes(List<LigneNoteFrais> notes) {
        this.notes = notes;
    }

    public Double getTotalht() {
        return totalht;
    }

    public void setTotalht(Double totalht) {
        this.totalht = totalht;
    }

    public Double getTotaltaxes() {
        return totaltaxes;
    }

    public void setTotaltaxes(Double totaltaxes) {
        this.totaltaxes = totaltaxes;
    }

    public Double getTotalttc() {
        return totalttc;
    }

    public void setTotalttc(Double totalttc) {
        this.totalttc = totalttc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }   

    public List<EcheanceReglement> getEcheances() {
        return echeances;
    }

    public void setEcheances(List<EcheanceReglement> echeances) {
        this.echeances = echeances;
    }

     @Override
    public List<State> getStates() {
        List<State> states = new ArrayList<State>();
        State stat = new State("etabli", "brouillon");
        stat.setCouleur("#b22222");
        states.add(stat);
        stat = new State("valide", "valide");
        stat.setCouleur("#008b8b");
        states.add(stat);
        return states; //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean isActivatefollower() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }   

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "160320180913NF"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Reçus d'achats"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Reçu d'achat";//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return state.trim().equalsIgnoreCase("valide"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return state.trim().equalsIgnoreCase("valide"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return state.trim().equalsIgnoreCase("valide"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
