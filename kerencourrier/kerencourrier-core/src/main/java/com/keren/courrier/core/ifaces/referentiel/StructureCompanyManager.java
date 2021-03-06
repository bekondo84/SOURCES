
package com.keren.courrier.core.ifaces.referentiel;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.courrier.model.referentiel.StructureCompany;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Wed Jul 18 10:58:43 GMT+01:00 2018
 * 
 */
public interface StructureCompanyManager
    extends GenericManager<StructureCompany, Long>
{

    public final static String SERVICE_NAME = "StructureCompanyManager";

}
