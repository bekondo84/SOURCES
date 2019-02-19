
package com.basaccount.dao.impl.achats;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.achats.LigneReglementFournisseurDAOLocal;
import com.basaccount.dao.ifaces.achats.LigneReglementFournisseurDAORemote;
import com.basaccount.model.achats.LigneReglementFournisseur;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "LigneReglementFournisseurDAO")
public class LigneReglementFournisseurDAOImpl
    extends AbstractGenericDAO<LigneReglementFournisseur, Long>
    implements LigneReglementFournisseurDAOLocal, LigneReglementFournisseurDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public LigneReglementFournisseurDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<LigneReglementFournisseur> getManagedEntityClass() {
        return (LigneReglementFournisseur.class);
    }

}
