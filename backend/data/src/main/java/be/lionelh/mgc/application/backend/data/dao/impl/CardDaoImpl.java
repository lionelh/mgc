package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.CardDao;
import be.lionelh.mgc.application.backend.data.domain.Card;
import be.lionelh.mgc.application.backend.data.domain.Color;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Lionel
 */
@Repository("cardDao")
public class CardDaoImpl implements CardDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Card create(Card inCard) {
        this.em.persist(inCard);
        this.em.flush();
        return inCard;
    }

    @Override
    public Card findById(Long inId) {
        return this.em.find(Card.class, inId);
    }

    @Override
    public Card findByName(String inName) {
        TypedQuery<Card> q = this.em.createNamedQuery("Card.FIND_BY_NAME", Card.class);
        q.setParameter("name", inName);
        List<Card> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Card found for name (" + inName + ") !");
        }
    }

    @Override
    public Card findByNom(String inNom) {
        TypedQuery<Card> q = this.em.createNamedQuery("Card.FIND_BY_NOM", Card.class);
        q.setParameter("nom", inNom);
        List<Card> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Card found for nom (" + inNom + ") !");
        }
    }

    @Override
    public List<Card> findAll() {
        return this.em.createNamedQuery("Card.FIND_ALL", Card.class).getResultList();
    }

    @Override
    public Card update(Card inCard) {
        Card c = this.findById(inCard.getId());
        this.em.merge(c);
        this.em.flush();
        return c;
    }

    @Override
    public void delete(Card inCard) {
        Card c = this.findById(inCard.getId());
        this.em.remove(c);
    }
}
