
package com.kerenedu.inscription;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.kerenedu.configuration.AnneScolaire;
import com.kerenedu.reglement.FichePaiement;
import com.kerenedu.school.Eleve;

@Stateless(mappedName = "InscriptionDAO")
public class InscriptionDAOImpl
    extends AbstractGenericDAO<Inscription, Long>
    implements InscriptionDAOLocal, InscriptionDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;


    public InscriptionDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Inscription> getManagedEntityClass() {
        return (Inscription.class);
    }

	public Inscription getInscriptionEtudiantByAnnee(Eleve eleve, AnneScolaire annee) {
		  try{
			  Inscription inscription = (Inscription) em.createQuery("SELECT a FROM Inscription a "
			  		+ "WHERE a.eleve.matricule ="+eleve.getMatricule()+" and a.anne.code="+annee.getCode()).getSingleResult();
	            return inscription;
	        }catch(Exception ex){
	            return null;
	        }
	}
	
	public long deleteRadPaiement(FichePaiement fp) {
		 long value = 0  ;
		  try{
			 
			  String query ="Delete  from e_p_paie where f_id="+fp.getId()+"";
			  value = em.createNativeQuery(query).executeUpdate();
	
	        }catch(Exception ex){

	        }
		return value;
	}
	
	public long deleteRadReglement(Inscription ins) {
		 long value = 0  ;
		  try{
			 
			  String query ="Delete  from e_p_rgl where el_id="+ins.getId()+"";
			  value = em.createNativeQuery(query).executeUpdate();
	
	        }catch(Exception ex){

	        }
		return value;
	}
	
	public long deleteRadfiche(Inscription ins) {
		 long value = 0  ;
		  try{
			 for(FichePaiement f : ins.getService()){
				 String query ="Delete  from e_p_paie where f_id="+f.getId()+"";
				  value = em.createNativeQuery(query).executeUpdate();
			 }
			 System.out.println("InscriptionDAOImpl.deleteRadfiche() delete paiement ok ");
			  String query ="Delete  from e_p_fiche where fiche_paie_id="+ins.getId()+"";
			  value = em.createNativeQuery(query).executeUpdate();
	
	        }catch(Exception ex){

	        }
		return value;
	}




}
