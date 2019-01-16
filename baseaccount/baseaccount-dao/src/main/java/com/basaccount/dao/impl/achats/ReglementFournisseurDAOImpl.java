
package com.basaccount.dao.impl.achats;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.achats.ReglementFournisseurDAOLocal;
import com.basaccount.dao.ifaces.achats.ReglementFournisseurDAORemote;
import com.basaccount.model.achats.ReglementFournisseur;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ReglementFournisseurDAO")
public class ReglementFournisseurDAOImpl
    extends AbstractGenericDAO<ReglementFournisseur, Long>
    implements ReglementFournisseurDAOLocal, ReglementFournisseurDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ReglementFournisseurDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ReglementFournisseur> getManagedEntityClass() {
        return (ReglementFournisseur.class);
    }

}
