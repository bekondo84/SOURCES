
package com.kerenedu.personnel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.kerenedu.configuration.Classe;
import com.kerenedu.configuration.ClasseDAOLocal;
import com.kerenedu.notes.CoefMatiereDetail;
import com.kerenedu.notes.CoefMatiereDetailDAOLocal;
import com.megatim.common.annotations.OrderType;

@TransactionAttribute
@Stateless(mappedName = "ProfesseurManager")
public class ProfesseurManagerImpl
    extends AbstractGenericManager<Professeur, Long>
    implements ProfesseurManagerLocal, ProfesseurManagerRemote
{

    @EJB(name = "ProfesseurDAO")
    protected ProfesseurDAOLocal dao;
    
    @EJB(name = "CoefMatiereDetailDAO")
    protected CoefMatiereDetailDAOLocal daocoefmat;
    
    @EJB(name = "ClasseDAO")
    protected ClasseDAOLocal daoclasse;

    public ProfesseurManagerImpl() {
    }

    @Override
    public GenericDAO<Professeur, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }
    @Override
   	public List<Professeur> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties,
   			int firstResult, int maxResult) {
   		// TODO Auto-generated method stub
   		List<Professeur> datas = super.filter(predicats, orders, properties, firstResult, maxResult);
   		List<Professeur> result = new ArrayList<Professeur>();
   		for(Professeur elev:datas){
   			result.add(new Professeur(elev));
   		}
   		return result;
   	}

   	@Override
   	public Professeur find(String propertyName, Long entityID) {
   		// TODO Auto-generated method stub
   		Professeur elev = super.find(propertyName, entityID);
   		Professeur inscrip = new Professeur(elev);
		
   		return inscrip;
   	}

   	@Override
   	public List<Professeur> findAll() {
   		// TODO Auto-generated method stub
   		List<Professeur> datas = super.findAll();
   		List<Professeur> result = new ArrayList<Professeur>();
   		for(Professeur elev:datas){
   			result.add(new Professeur(elev));
   		}
   		return result;
   	}
   	
   	

   	@Override
   	public Professeur delete(Long id) {
   		// TODO Auto-generated method stub
   		Professeur elev = super.delete(id);
   		return new Professeur(elev);
   	}

	public List<Professeur> findprofclasse(Long id) {
		List<CoefMatiereDetail> datas = new ArrayList<CoefMatiereDetail>() ;
		 List<Professeur> result = new ArrayList<Professeur>();
		 RestrictionsContainer container = RestrictionsContainer.newInstance();
			if(id>0){
				// recherche l'objet classe
				container = RestrictionsContainer.newInstance();
				container.addEq("id",id);
				Classe classe = daoclasse.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1).get(0);
				
				container = RestrictionsContainer.newInstance();
				 container.addEq("classe.id",id);
				 datas = daocoefmat.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
				 if(datas!=null||datas.size()!=0){
					 for(CoefMatiereDetail cmdlt :datas){
						 result.add(new Professeur(cmdlt.getProffesseur()));
					 }
					 // triatement des doublons 
					 Set<Professeur> set = new HashSet<Professeur>();
					 set.addAll(result);
					 List<Professeur> listDistinct = new ArrayList<Professeur>(set);
					 result=listDistinct;
				 }// fin  if(datas!=null||datas.size()!=0)
			}// fin if(id>0)
		return result;
}
	
}
