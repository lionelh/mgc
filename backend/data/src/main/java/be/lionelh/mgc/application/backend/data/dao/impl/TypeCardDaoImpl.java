package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.TypeCardDao;
import be.lionelh.mgc.application.backend.data.domain.TypeCard;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Lionel
 */
@Repository("typeCardDao")
public class TypeCardDaoImpl implements TypeCardDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public TypeCard create(TypeCard inTypeCard) {
        this.em.persist(inTypeCard);
        this.em.flush();
        return inTypeCard;
    }

    @Override
    public TypeCard findById(Long inId) {
        return this.em.find(TypeCard.class, inId);
    }

    @Override
    public TypeCard findByName(String inName) {
        TypedQuery<TypeCard> q = this.em.createNamedQuery("TypeCard.FIND_BY_NAME", TypeCard.class);
        q.setParameter("name", inName);
        List<TypeCard> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many TypeCard found for name (" + inName + ") !");
        }
    }

    @Override
    public TypeCard findByNom(String inNom) {
        TypedQuery<TypeCard> q = this.em.createNamedQuery("TypeCard.FIND_BY_NOM", TypeCard.class);
        q.setParameter("nom", inNom);
        List<TypeCard> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many TypeCard found for nom (" + inNom + ") !");
        }
    }

    @Override
    public List<TypeCard> findAll() {
        return this.em.createNamedQuery("TypeCard.FIND_ALL", TypeCard.class).getResultList();
    }

    @Override
    public TypeCard update(TypeCard inTypeCard) {
        TypeCard f = this.findById(inTypeCard.getId());
        this.em.merge(f);
        this.em.flush();
        return f;
    }

    @Override
    public void delete(TypeCard inTypeCard) {
        TypeCard f = this.findById(inTypeCard.getId());
        this.em.remove(f);
    }
}
