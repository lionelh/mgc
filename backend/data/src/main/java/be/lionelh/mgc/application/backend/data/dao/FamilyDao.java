package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Family;
import java.util.List;

/**
 * @author lh
 */
public interface FamilyDao {
    Family create(Family inFamily);
    Family findById(Long inId);
    Family findByName(String inName);
    Family findByNom(String inNom);
    List<Family> findAll();
    Family update(Family inFamily);
    void delete(Family inFamily);
}
