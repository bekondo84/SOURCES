/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.jaxrs;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Commercial_2
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.basaccount.jaxrs.impl.achats.EcheanceReglementRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.achats.FactureRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.achats.LigneReglementFournisseurRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.achats.ModeReglementRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.achats.NoteFraisRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.achats.ReglementFournisseurRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.banques.BanqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.banques.CompteBancaireRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.CompteAnalytiqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.CompteRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.ExerciceComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.JournalComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.NiveauAnalyseRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.PeriodeComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.SectionAnalytiqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.TaxeRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.dashboard.ComptaDashboardRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.ClotureExerciceComptableRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.ClotureJournalRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.EcritureAnalytiqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.operations.EcritureBanqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.operations.EcritureComptableRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.EcritureTierRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.JournalSaisieRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.OperationBancaireRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.operations.PieceComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.search.EcritureSearchRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.search.JournalSaisieViewRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.search.OperationBancaireViewRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.search.PieceComptableViewRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.search.SearchViewRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.CiviliteRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.ConditionPaiementRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.ContactRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.TierRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.ventes.FactureVenteRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.ventes.LigneReglementClientRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.ventes.NoteFraisVenteRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.ventes.ReglementClientRSImpl.class);
    }
    
}
