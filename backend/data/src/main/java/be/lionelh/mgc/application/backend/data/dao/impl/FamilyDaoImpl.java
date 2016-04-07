package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.FamilyDao;
import be.lionelh.mgc.application.backend.data.domain.Capacity;
import be.lionelh.mgc.application.backend.data.domain.Family;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Lionel
 */
@Repository("familyyDao")
public class FamilyDaoImpl implements FamilyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Family create(Family inFamily) {
        this.em.persist(inFamily);
        this.em.flush();
        return inFamily;
    }

    @Override
    public Family findById(Long inId) {
        return this.em.find(Family.class, inId);
    }

    @Override
    public Family findByName(String inName) {
        TypedQuery<Family> q = this.em.createNamedQuery("Family.FIND_BY_NAME", Family.class);
        q.setParameter("name", inName);
        List<Family> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Family found for name (" + inName + ") !");
        }
    }

    @Override
    public Family findByNom(String inNom) {
        TypedQuery<Family> q = this.em.createNamedQuery("Family.FIND_BY_NOM", Family.class);
        q.setParameter("nom", inNom);
        List<Family> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Family found for nom (" + inNom + ") !");
        }
    }

    @Override
    public List<Family> findAll() {
        return this.em.createNamedQuery("Family.FIND_ALL", Family.class).getResultList();
    }

    @Override
    public Family update(Family inFamily) {
        Family f = this.findById(inFamily.getId());
        this.em.merge(f);
        this.em.flush();
        return f;
    }

    @Override
    public void delete(Family inFamily) {
        Family f = this.findById(inFamily.getId());
        this.em.remove(f);
    }
}
