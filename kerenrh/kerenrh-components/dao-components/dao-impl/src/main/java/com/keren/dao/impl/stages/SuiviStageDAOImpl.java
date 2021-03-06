
package com.keren.dao.impl.stages;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.dao.ifaces.stages.SuiviStageDAOLocal;
import com.keren.dao.ifaces.stages.SuiviStageDAORemote;
import com.keren.model.stages.SuiviStage;

@Stateless(mappedName = "SuiviStageDAO")
public class SuiviStageDAOImpl
    extends AbstractGenericDAO<SuiviStage, Long>
    implements SuiviStageDAOLocal, SuiviStageDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public SuiviStageDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SuiviStage> getManagedEntityClass() {
        return (SuiviStage.class);
    }

}
