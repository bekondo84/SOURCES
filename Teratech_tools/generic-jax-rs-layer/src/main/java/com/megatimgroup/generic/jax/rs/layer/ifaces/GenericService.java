/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.ifaces;

import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.annotations.OrderType;
import com.megatimgroup.generic.jax.rs.layer.impl.FilterPredicat;
import com.megatimgroup.generic.jax.rs.layer.impl.ImportData;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.megatimgroup.generic.jax.rs.layer.impl.RSNumber;
import com.megatimgroup.generic.jax.rs.layer.impl.TreeNode;
import com.megatimgroup.mgt.commons.tools.ValidatorError;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Commercial_2
 * @param <T>
 * @param <PK>
 */
public interface GenericService<T ,PK extends Serializable> {
    
    /**
	 * Methode d'obtention de la DAO
	 * @return	DAO
	 */        
	public GenericManager<T, PK> getManager();
	
	/**
	 * Methode generique d'enregistrement d'une entite
     * @param headers
	 * @param entity	Entite a enregistrer
	 * @return	Entite enregistree
	 */
        @POST
        @Consumes({MediaType.APPLICATION_JSON})
        @Produces({MediaType.APPLICATION_JSON})
	public T save(@Context HttpHeaders headers , T entity);

        /**
         * 
     * @param headers
         * @param entities 
         */
        @POST
        @Consumes({MediaType.APPLICATION_JSON})
        @Path("list")
        public void save(@Context HttpHeaders headers , List<T> entities);
        
	/**
	 * Methode generique de mise a jour d'une entite
     * @param headers
	 * @param id	Identifiant de l'objet a mettre a jour
	 * @param entity	Entite a mettre a jour
	 * @return	Entite mise a jour
	 */
        @PUT
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})  
        @Path("{id}")
	public T update(@Context HttpHeaders headers , @PathParam("id") PK id, T entity);
	
        /**
         * 
     * @param headers
         * @param entity :Entity describing the import meta data
     * @return 
         */
        @PUT
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})  
        @Path("import")
        public Map<Long,Map<String,List<ValidatorError>>> importData(@Context HttpHeaders headers , ImportData entity);
        
        @PUT
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})  
        @Path("validateimport")
        public Map<Long,Map<String,List<ValidatorError>>> validateImportData(@Context HttpHeaders headers , ImportData entity);
        
        /**
         * 
     * @param headers
         * @param entity
         * @return 
         */
        @PUT
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})  
        @Path("export")
        public Response exportData(@Context HttpHeaders headers ,ImportData entity);
        
        /**
	 * Methode generique de mise a jour d'une entite
     * @param headers
         * @param entities
	 */
        @PUT
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})  
        @Path("map")
	public void update(@Context HttpHeaders headers , Map<PK,T> entities);
        
               
	/**
	 * Methode generique de suppression d'une entite
     * @param headers
	 * @param id	Identifiant de l'entite a supprimer
     * @return 
	 */
        @DELETE
        @Produces({MediaType.APPLICATION_JSON})
        //@Consumes({MediaType.APPLICATION_JSON})  
        @Path("{id}")
	public T delete(@Context HttpHeaders headers , @PathParam("id") PK id);
        
        /**
	 * Methode generique de suppression d'une entite
     * @param headers 
	 */
        @DELETE
        @Produces({MediaType.APPLICATION_JSON})
        //@Consumes({MediaType.APPLICATION_JSON})  
        //@Path("{many}")
	public void deleteAll(@Context HttpHeaders headers);
	
	/**
	 * Methode de recherche d'une entite par son ID
     * @param headers
         * @param propertyName
	 * @param id	ID de l'entite
	 * @return Entite recherchee
	 */
        @GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("byid/{property}/{id}")
	public T find(@Context HttpHeaders headers , @PathParam("property") String propertyName ,@PathParam("id") PK id );
        
        /**
         * 
     * @param headers
         * @param propertyName
     * @param value
         * @return 
         */
        @GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("unique/{property}/{value}")
        public boolean unique(@Context HttpHeaders headers , @PathParam("property") String propertyName 
                  ,@PathParam("value") String value );
        
         /**
         * 
     * @param headers
         * @return 
         */
        @GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("unique")
        public List<FilterPredicat> uniqueProperties(@Context HttpHeaders headers);
        
        /**
         * Constructeur de METAData
         * @param headers
         * @return 
         */
	@GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("meta")
        public MetaData getMetaData(@Context HttpHeaders headers);
        
        
        /**
         * Constructeur de METADATA de l'Import
         * @param headers
         * @return 
         */
       
	/**
	 * Methode de chargement de tous les objets
     * @param headers
	 * @return	Liste de tous les objets
	 */
        @GET
        @Produces({MediaType.APPLICATION_JSON})
	public List<T> findAll(@Context HttpHeaders headers);
	
	
	/**
	 * Methode de recherche d'une entite par une propriete unique
     * @param headers
	 * @param propertyName	Nom de la propriete Unique
	 * @param propertyValue	Valeur de la propriete Unique
	 * @return	Objet recherche
	 */
         @GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("bylongproperty/{property}/{value}")
	public List<T> findByUniqueProperty(@Context HttpHeaders headers , @PathParam("property") String propertyName, @PathParam("value") long propertyValue);
    
        /**
	 * Methode de recherche d'une entite par une propriete unique
     * @param headers
	 * @param propertyName	Nom de la propriete Unique
	 * @param propertyValue	Valeur de la propriete Unique
	 * @return	Objet recherche
	 */
         @GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("bystringproperty/{property}/{value}")
	public List<T> findByUniqueProperty(@Context HttpHeaders headers , @PathParam("property") String propertyName, @PathParam("value") String propertyValue);
        
        /**
	 * Methode de recherche d'une entite par une propriete unique
     * @param headers
	 * @param propertyName	Nom de la propriete Unique
	 * @param propertyValue	Valeur de la propriete Unique
	 * @return	Objet recherche
	 */
         @GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("byintproperty/{property}/{value}")
	public List<T> findByUniqueProperty(@Context HttpHeaders headers , @PathParam("property") String propertyName, @PathParam("property") int propertyValue);
    
        /**
	 * Methode de recherche d'une entite par une propriete unique
     * @param headers
	 * @param propertyName	Nom de la propriete Unique
	 * @param propertyValue	Valeur de la propriete Unique
	 * @return	Objet recherche
	 */
         @GET
        @Produces({MediaType.APPLICATION_JSON})
        @Path("byshortproperty/{property}/{value}")
	public List<T> findByUniqueProperty(@Context HttpHeaders headers , @PathParam("property") String propertyName, @PathParam("property") short propertyValue);
    
    /**
     * methode de filtre des entites d'une classe en fonction des prédicats de filtres
     * @param headers
     * @param  firstResult : int index du premier resultat
     * @param  maxResult : int nbre maximal de données à charges par defaut -1 : charge tous les données
     * @return 
     * 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("filter/{first}/{max}")
    public List<T> filter(@Context HttpHeaders headers ,@PathParam("first") int firstResult, @PathParam("max") int maxResult);
    
    /**
     * Build the tree node data
     * to override by the user
     * @param headers
     * @param firstResult
     * @param maxResult
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("tree/{first}/{max}")
    public List<TreeNode> treefilter(@Context HttpHeaders headers ,@PathParam("first") int firstResult, @PathParam("max") int maxResult);
    
    /**
     * methode de filtre des entites d'une classe en fonction des prédicats de filtres
     * @param headers
     * @param predicats : List des prédicats (condition de selection)
     * @param orders : Map ordres de tri des resultats
     * @param properties : Set ensemble des proprietes à charges
     * @param hints
     * @param  firstResult : int index du premier resultat
     * @param  maxResult : int nbre maximal de données à charges par defaut -1 : charge tous les données
     * @return 
     * 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("hintfilter")
    public List<T> filter(@Context HttpHeaders headers , List<Predicat> predicats , Map<String,OrderType> orders , Set<String> properties , Map<String,Object> hints , int firstResult , int maxResult);
    
    /**
     * Retourne le nombre d'enregistrements dans une requete
     * @param headers
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("count")
    public RSNumber count(@Context HttpHeaders headers );    
     
    /**
	 * Methode de filtre sur la base du critere d'egalite et de conjonction des proprietes
	 * @param criteriasProperties	MAP des proprietes de recheche
	 * @param properties	Ensemble des proprietes a charger immediatement
	 * @param firstResult Index du premoier
	 * @return	Liste des entites trouvees
	 
	public <Y extends Comparable<Y>> List<T> filter(Map<String, Y> criteriasProperties, Set<String> properties, int firstResult, int maxResult);
	
	*/
    
	/**
	 * Methode de nettoyage des entites
	 */
	public void clean();
        
        /**
	 * Methode vidage du contexte de persistence en BD
	 */
	public void flush();
        
      
    
}
