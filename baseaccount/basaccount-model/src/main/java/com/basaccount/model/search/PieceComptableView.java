/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.search;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.ManyToOne;

/**
 *
 * @author BEKO
 */
public class PieceComptableView extends BaseElement implements Serializable,Comparable<PieceComptableView>{

    @ManyToOne
    @Predicate(label = "journal.comptable",type = JournalComptable.class,target = "many-to-one",optional = false,sequence = 1)
    private JournalComptable journal ;
    
    @Predicate(label = "exercice.comptable",type = ExerciceComptable.class,target = "many-to-one",optional = false,sequence = 3)
    private ExerciceComptable exercice ;
    
    @Predicate(label = "periode.comptable",type = PeriodeComptable.class,target = "many-to-one",optional = false,sequence = 2)
    @Filter(value = "[{\"fieldName\":\"exercice\",\"value\":\"object.exercice\",\"searchfield\":\"code\",\"optional\":false,\"message\":\"baseaccount.journalsaisie.exercice.null\"}]")
    private PeriodeComptable periode ;

    public PieceComptableView() {
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }

    public ExerciceComptable getExercice() {
        return exercice;
    }

    public void setExercice(ExerciceComptable exercice) {
        this.exercice = exercice;
    }

    public PeriodeComptable getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeComptable periode) {
        this.periode = periode;
    }

    @Override
    public String getOwnermodule() {
        return super.getOwnermodule(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "journaldesaisie.title"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(PieceComptableView o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
