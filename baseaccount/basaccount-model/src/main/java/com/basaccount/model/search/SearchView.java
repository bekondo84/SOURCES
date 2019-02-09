/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.search;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;

/**
 *
 * @author BEKO
 */
public class SearchView extends BaseElement implements Serializable,Comparable<SearchView>{
    
    @Predicate(label = "exercice.comptable",type = ExerciceComptable.class,target = "many-to-one",optional = false)
    private ExerciceComptable exercice ;
    
    @Predicate(label = "periode.comptable",type = PeriodeComptable.class,target = "many-to-one",optional = false)
    @Filter(value = "[{\"fieldName\":\"exercice\",\"value\":\"object.exercice\",\"searchfield\":\"code\",\"optional\":false,\"message\":\"baseaccount.journalsaisie.exercice.null\"}]")
    private PeriodeComptable periode ;
    
    

    public SearchView() {
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
        return "search.view"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(SearchView o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
