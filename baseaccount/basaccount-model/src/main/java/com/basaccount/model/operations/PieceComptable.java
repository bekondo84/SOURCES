/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.achats.Facture;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.ventes.FactureVente;
import com.core.base.State;
import com.megatim.common.annotations.KHeader;
import com.megatim.common.annotations.KHeaders;
import com.megatim.common.annotations.KValue;
import com.megatim.common.annotations.Predicate;
import com.megatim.common.annotations.TableFooter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Commercial_2
 */
@KHeaders(statubar = true
        ,value = {
           @KHeader(type = "button",name = "work1",label = "valider",target = "workflow",roles = {"administrateur","gestionnaire"},states = {"etabli"},pattern = "btn btn-danger"
                , value = @KValue("{'model':'baseaccount','entity':'piececomptable','method':'validate','critical':true,'alert':'Vous ne pourriez plus modifier cette pièce comptable \nÊtes-vous sûr de vouloir continuer ?'}")
            ),
            @KHeader(type = "button",name = "work2",label = "imprimer.note",target = "report",roles = {"administrateur","gestionnaire"},pattern = "btn btn-default"
                , value = @KValue("{'name':'piececomptable_report01','model':'baseaccount','entity':'piececomptable','method':'imprime'}")
            )
        })
@Entity
@DiscriminatorValue("PCB")
public class PieceComptable extends PieceComptableTmp implements Serializable{
    
    @ManyToOne
    @JoinColumn(name = "JRN_ID")
    @Predicate(label = "jouranl.comptable",type = JournalComptable.class,target = "many-to-one",optional = false,editable = false,search = true)
    private JournalComptable journal;
    
    @Predicate(label = "debit",type = Double.class,updatable = false,search = true,editable = false,hide = true)
    private Double debit = 0.0;
    
    @Predicate(label = "credit",type = Double.class,updatable = false , search = true,editable = false,hide = true)
    private Double credit = 0.0;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "PIECO_ID")
    @Predicate(label=" ",group = true,groupLabel = "ecritures.comptable",groupName = "group1",type = LignePieceComptable.class,target = "one-to-many",customfooter = true,edittable = true)
    @TableFooter(value = "<tr style='border:none;'><td></td><td></td><td></td><td></td><td>Total Debit</td><td></td><td>this.debit</td><td></td></tr><tr style='border:none;'><td></td><td></td><td></td><td></td><td>Total Credit</td><td></td> <td>this.credit</td><td></td></tr><tr style='border:none;'><td></td><td></td><td></td><td></td><td>Solde</td><td></td><td>this.debit;-;this.credit</td><td></td></tr>")
    private List<LignePieceComptable> ecritures = new ArrayList<LignePieceComptable>();
    
    
    private Long facturevente ;
    
    private Long factureachat;
    
    
    @Predicate(label = " ",target = "state",search = true,hide = true)
    private String state ="etabli";

    /**
     * 
     * @param libelle
     * @param journal 
     */
    public PieceComptable(String libelle, JournalComptable journal) {
        this.libelle = libelle;
        this.journal = journal;
    }

   /**
    * 
    * @param piece 
    */
    public PieceComptable(PieceComptable piece) {
        super(piece);
        this.code = piece.code;
        this.libelle = piece.libelle;
        if(piece.journal!=null){
            this.journal = new JournalComptable(piece.journal);
        }
//        if(piece.facturevente!=null){
//            this.facturevente = new FactureVente(piece.facturevente);
//        }
        this.datePiece = piece.datePiece;
        this.debit = piece.debit;
        this.credit = piece.credit;     
        this.state = piece.state;
        this.factureachat = piece.factureachat;
        this.facturevente = piece.facturevente;
    }
    
    /**
     * 
     * @param entity
     * @param taxes 
     */
     public PieceComptable(FactureVente entity,Map<Compte , Double> taxes) {
        super(entity);
        this.code = entity.getCode();
        this.libelle = entity.getCodeclient();
        if(entity.getJournal()!=null){
            this.journal = new JournalComptable(entity.getJournal());
        }
        this.facturevente = entity.getId();
        this.datePiece = entity.getDatecommande();
        this.debit =0.0;
        this.credit = 0.0;
        //Ecriture du client
        LignePieceComptable ligneclient = new LignePieceComptable();
        ligneclient.setDateEcriture(entity.getDatecommande());
        ligneclient.setRefPiece(entity.getCode());
        ligneclient.setLibelle(entity.getClient().getCompte().getLibele());
        ligneclient.setCompte(entity.getClient().getCompte());
        ligneclient.setTier(entity.getClient());
        ligneclient.setDebit(entity.getTotalttc());
        ligneclient.setCredit(0.0);
        this.ecritures.add(ligneclient);
        //Ecriture compte ventes
        LignePieceComptable lignevte = new LignePieceComptable();
        lignevte.setDateEcriture(entity.getDatecommande());
        lignevte.setRefPiece(entity.getCode());
        lignevte.setLibelle(entity.getCompte().getLibele());
        lignevte.setCompte(entity.getCompte());
        lignevte.setDebit(0.0);
        lignevte.setCredit(entity.getTotalht());
        this.ecritures.add(lignevte);
        //Ecriture des taxes
        for(Compte compte : taxes.keySet()){
             LignePieceComptable lignetax = new LignePieceComptable();
            lignetax.setDateEcriture(entity.getDatecommande());
            lignetax.setRefPiece(entity.getCode());
            lignetax.setLibelle(compte.getLibele());
            lignetax.setCompte(compte);
            lignetax.setDebit(0.0);
            lignetax.setCredit(taxes.get(compte));
            if(lignetax.getCredit()>0){
                this.ecritures.add(lignetax);
            }//end if(lignetax.getCredit()>0){
        }//end for(Compte compte : taxes.keySet()){
        this.state = "etabli";
    }
     
     /**
      * 
      * @param entity
      * @param taxes 
      */
     public PieceComptable(Facture entity,Map<Compte , Double> taxes) {
        super(entity);
        this.code = entity.getCode();
        this.libelle = entity.getCodefourni();
        if(entity.getJournal()!=null){
            this.journal = new JournalComptable(entity.getJournal());
        }
        this.factureachat = entity.getId();
        this.datePiece = entity.getDatecommande();
        this.debit =0.0;
        this.credit = 0.0;
        //Ecriture compte ventes
        LignePieceComptable lignevte = new LignePieceComptable();
        lignevte.setDateEcriture(entity.getDatecommande());
        lignevte.setRefPiece(entity.getCode());
        lignevte.setLibelle(entity.getCompte().getLibele());
        lignevte.setCompte(entity.getCompte());
        lignevte.setDebit(entity.getTotalht());
        lignevte.setCredit(0.0);
        this.ecritures.add(lignevte);
        //Ecriture du client
        LignePieceComptable ligneclient = new LignePieceComptable();
        ligneclient.setDateEcriture(entity.getDatecommande());
        ligneclient.setRefPiece(entity.getCode());
        ligneclient.setLibelle(entity.getFournisseur().getCompte().getLibele());
        ligneclient.setCompte(entity.getFournisseur().getCompte());
        ligneclient.setTier(entity.getFournisseur());
        ligneclient.setDebit(0.0);
        ligneclient.setCredit(entity.getTotalttc());
        this.ecritures.add(ligneclient);
        //Ecriture des taxes
        for(Compte compte : taxes.keySet()){
             LignePieceComptable lignetax = new LignePieceComptable();
            lignetax.setDateEcriture(entity.getDatecommande());
            lignetax.setRefPiece(entity.getCode());
            lignetax.setLibelle(compte.getLibele());
            lignetax.setCompte(compte);
            lignetax.setDebit(taxes.get(compte));
            lignetax.setCredit(0.0);
            if(lignetax.getDebit()>0){
                this.ecritures.add(lignetax);
            }//end if(lignetax.getCredit()>0){
        }//end for(Compte compte : taxes.keySet()){
        this.state = "etabli";
    }
    /**
     * 
     */
    public PieceComptable() {
    }

    
  

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }

    public List<LignePieceComptable> getEcritures() {
        return ecritures;
    }

    public void setEcritures(List<LignePieceComptable> ecritures) {
        this.ecritures = ecritures;
    }

  
    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getFacturevente() {
        return facturevente;
    }

    public void setFacturevente(Long facturevente) {
        this.facturevente = facturevente;
    }

    public Long getFactureachat() {
        return factureachat;
    }

    public void setFactureachat(Long factureachat) {
        this.factureachat = factureachat;
    }

   

    @Override
    public String getDesignation() {
        return libelle; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "piece.comptable.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "piece.comptable.detail"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return state.equalsIgnoreCase("valide"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
        List<State> etats = new ArrayList<State>();
        etats.add(new State("etabli", "broullion"));
        etats.add(new State("valide", "valide"));
        return etats; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return state.equalsIgnoreCase("valide");  //To change body of generated methods, choose Tools | Templates.
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
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }   

    @Override
    public boolean isActivatefollower() {
         //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "piececomptable01234"; //To change body of generated methods, choose Tools | Templates.
    } 
    
    
}
