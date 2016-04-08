package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Color;
import java.util.List;

/**
 * @author lh
 */
public interface ColorDao {
    Color create(Color inColor);
    Color findById(Long inId);
    Color findByName(String inName);
    Color findByNom(String inNom);
    List<Color> findAll();
    Color update(Color inColor);
    void delete(Color inColor);
}
