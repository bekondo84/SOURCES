
package com.basaccount.core.ifaces.operations;

import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.operations.PieceComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import java.util.List;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Sat Dec 23 09:07:30 WAT 2017
 * 
 */
public interface PieceComptableManager
    extends GenericManager<PieceComptable, Long>
{

    public final static String SERVICE_NAME = "PieceComptableManager";
    
    /**
     * 
     * @param entity
     * @return 
     */
    public PieceComptable valider(PieceComptable entity);
    
    public List<PieceComptable> priseencompte(Long id,PeriodeComptable periode);

    public List<PieceComptable> priseencompteachat(Long id,PeriodeComptable periode);
}
