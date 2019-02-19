/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.tiers.Tier;
import com.basaccount.model.ventes.NoteFraisTMP;
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
            @KHeader(type = "button",name = "work2",label = "imprimer.note",target = "report",roles = {"administrateur","gestionnaire"},pattern = "btn btn-default"
                , value = @KValue("{'name':'notedefrais_report01','model':'baseaccount','entity':'notefrais','method':'imprime'}")
            ),
            @KHeader(type = "button",name = "work1",label = "valider",target = "workflow",roles = {"administrateur","gestionnaire"},states = {"etabli"},pattern = "btn btn-danger"
                , value = @KValue("{'model':'baseaccount','entity':'notefrais','method':'validate','critical':true,'alert':'cette action est irreversible \nÊtes-vous sûr de vouloir continuer ?'}")
            )
        })
@Entity
@DiscriminatorValue("ACH")
public class NoteFrais extends NoteFraisTMP implements Serializable{
    
   @ManyToOne
    @JoinColumn(name = "FOUR_ID")
    @Predicate(label = "fournisseur",type = Tier.class,target = "many-to-one",optional = false,search = true)
    @Filter("[{\"fieldName\":\"type\",\"value\":\"1\"}]")
    private Tier fournisseur ;
    
     @ManyToOne
    @JoinColumn(name = "COMP_ID")
    @Predicate(label = "compte",type = Compte.class,target = "many-to-one",optional = false,search = true)
    private Compte comptecompens ;
    
    @Temporal(TemporalType.DATE)
//    @Predicate(label = "Date échéance",type = Date.class,optional = false,search = true)
    private Date decheance;
    
    @Predicate(label = "memo",search = true)
    private String memo;
    
    @ManyToOne
    @JoinColumn(name = "JOUR_ID")
    @Predicate(label = "journal.comptable",type = JournalComptable.class,target = "many-to-one",optional = false,search = true)
    @Filter("[{\"fieldName\":\"type\",\"value\":\"1\"}]")
    private JournalComptable journal ;    
    
    
    @OneToMany(orphanRemoval = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "NOFRACH_ID")
    @Predicate(label = " ",type = LigneNoteFrais.class,target = "one-to-many",group = true,groupName = "group1",groupLabel = "notes.frais",edittable = true)
    private List<LigneNoteFrais> notes = new ArrayList<LigneNoteFrais>();

     @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "NFA_ID")
    @Predicate(label = " ",type = EcheanceReglement.class,target = "one-to-many",group = true,groupName = "group2",groupLabel = "echeances",edittable = true)
    private List<EcheanceReglement> echeances = new ArrayList<EcheanceReglement>();
     
    @Predicate(label = " ",target = "textarea",group = true,groupName = "group3",groupLabel = "notes")
    private String commentaire ;
    
    @Predicate(label = "total.ht",type = Double.class,search = true,hide = true,editable = false)
    private Double totalht = 0.0;
    
    @Predicate(label = "total.taxes",type = Double.class,hide = true,search = true,editable = false)
    private Double totaltaxes = 0.0;
    
    @Predicate(label = "total.ttc",type = Double.class,hide = true,search = true,editable = false)
    private Double totalttc = 0.0;
    
    @Predicate(label = " ",target = "state",hide = true,search = true)
    private String state ="etabli";
    /**
     * 
     * @param code
     * @param fournisseur
     * @param date
     * @param compte
     * @param decheance
     * @param memo
     * @param journal 
     */
    public NoteFrais(String code, Tier fournisseur, Date date, Compte compte, Date decheance, String memo, JournalComptable journal) {
        this.code = code;
        this.fournisseur = fournisseur;
        this.date = date;
        this.comptecompens = compte;
        this.decheance = decheance;
        this.memo = memo;
        this.journal = journal;
    }

   
    
    /**
     * 
     * @param note 
     */
    public NoteFrais(NoteFrais note) {
        super(note);
        this.code = note.code;
        if(note.fournisseur!=null){
            this.fournisseur = new Tier(note.fournisseur);
        }
        this.date = note.date;
        if(note.comptecompens!=null){
            this.comptecompens = new Compte(note.comptecompens);
        }
        this.state = note.state;
        this.decheance = note.decheance;
        this.memo = note.memo;
        this.journal = note.journal;
        this.commentaire = note.commentaire;
        this.totalht = note.totalht;
        this.totaltaxes = note.totaltaxes;
        this.totalttc = note.totalttc;
    }

    public NoteFrais() {
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

    public void setComptecompens(Compte comptecompens) {
        this.comptecompens = comptecompens;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public List<EcheanceReglement> getEcheances() {
        return echeances;
    }

    public void setEcheances(List<EcheanceReglement> echeances) {
        this.echeances = echeances;
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
    
    

    @Override
    public boolean isDesableupdate() {
         return state.equalsIgnoreCase("valide"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return state.equalsIgnoreCase("valide"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return super.isDesablecreate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public boolean isActivatefollower() {
        return true; //To change body of generated methods, choose Tools | Templates.
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
        return "recus.achats"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "recu.achat";//To change body of generated methods, choose Tools | Templates.
    }
    
    
    
//    @Override
//    public int compareTo(NoteFrais o) {
//        return 0; //To change body of generated methods, choose Tools | Templates.
//    }
    
}
