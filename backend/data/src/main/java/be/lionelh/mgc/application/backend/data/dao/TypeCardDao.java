package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.TypeCard;
import java.util.List;

/**
 * @author lh
 */
public interface TypeCardDao {
    TypeCard create(TypeCard inTypeCard);
    TypeCard findById(Long inId);
    TypeCard findByName(String inName);
    TypeCard findByNom(String inNom);
    List<TypeCard> findAll();
    TypeCard update(TypeCard inTypeCard);
    void delete(TypeCard inTypeCard);
}
