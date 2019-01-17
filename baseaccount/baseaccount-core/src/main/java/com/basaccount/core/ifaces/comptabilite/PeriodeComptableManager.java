
package com.basaccount.core.ifaces.comptabilite;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import java.util.List;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Wed Jan 16 14:16:27 WAT 2019
 * 
 */
public interface PeriodeComptableManager
    extends GenericManager<PeriodeComptable, Long>
{

    public final static String SERVICE_NAME = "PeriodeComptableManager";
    
    /**
     * 
     * @return 
     */
    public List<ExerciceComptable> getExercices();

}
