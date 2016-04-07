package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Capacity;
import be.lionelh.mgc.application.backend.data.domain.Family;
import java.util.List;

/**
 * @author lh
 */
public interface DaoFacade {
    Capacity createCapacity(Capacity inCapacity);
    Capacity findCapacityById(Long inId);
    Capacity findCapacityByName(String inName);
    Capacity findCapacityByNom(String inNom);
    List<Capacity> findAllCapacities();
    Capacity updateCapacity(Capacity inCapacity);
    void removeCapacity(Capacity inCapacity);
    
    Family createFamily(Family inFamily);
    Family findFamilyById(Long inId);
    Family findFamilyByName(String inName);
    Family findFamilyByNom(String inNom);
    List<Family> findAllFamilies();
    Family updateFamily(Family inFamily);
    void removeFamily(Family inFamily);
}
