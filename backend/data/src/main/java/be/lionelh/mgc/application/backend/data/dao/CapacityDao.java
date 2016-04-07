package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Capacity;
import java.util.List;

/**
 * @author lh
 */
public interface CapacityDao {
    Capacity create(Capacity inCapacity);
    Capacity findById(Long inId);
    Capacity findByName(String inName);
    Capacity findByNom(String inNom);
    List<Capacity> findAll();
    Capacity update(Capacity inCapacity);
    boolean delete(Capacity inCapacity);
}
