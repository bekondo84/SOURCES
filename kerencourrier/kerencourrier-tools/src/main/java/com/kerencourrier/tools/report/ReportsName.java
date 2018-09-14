/**
 *
 */
package com.kerencourrier.tools.report;

/**
 * @author <a href="mailto:ntchuenna@yahoo.fr">nadège Tchuente</a>
 * 22 sept. 2016
 */
public enum ReportsName {


    BORDERO("cbordereau.jasper");

    /**
     * Nom de l'etat sans extension
     */
    private final String name;

    /**
     * Constructeur parametre
     *
     * @param name Nom de l'état
     */
    private ReportsName(String name) {

        this.name = name;
    }

    /**
     * Methode d'obtention du nom du fichier de l'état (sans extension)
     *
     * @return Nom de l'etat
     */
    public String getName() {

        return name;
    }
}
