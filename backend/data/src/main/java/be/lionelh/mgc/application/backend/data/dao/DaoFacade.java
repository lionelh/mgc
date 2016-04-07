package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Capacity;
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
}
