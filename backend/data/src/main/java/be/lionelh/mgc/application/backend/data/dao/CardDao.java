package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Card;
import java.util.List;

/**
 * @author lh
 */
public interface CardDao {
    Card create(Card inCard);
    Card findById(Long inId);
    Card findByName(String inName);
    Card findByNom(String inNom);
    List<Card> findAll();
    Card update(Card inCard);
    void delete(Card inCard);
}
