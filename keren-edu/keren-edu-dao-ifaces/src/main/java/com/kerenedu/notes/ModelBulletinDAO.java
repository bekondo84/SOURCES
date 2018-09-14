
package com.kerenedu.notes;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Apr 24 13:23:40 WAT 2018
 * 
 */
public interface ModelBulletinDAO
    extends GenericDAO<ModelBulletin, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ModelBulletinDAO";

}
