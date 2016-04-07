package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.CapacityDao;
import be.lionelh.mgc.application.backend.data.domain.Capacity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Lionel
 */
@Repository("capacityDao")
public class CapacityDaoImpl implements CapacityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Capacity create(Capacity inCapacity) {
        this.em.persist(inCapacity);
        this.em.flush();
        return inCapacity;
    }

    @Override
    public Capacity findById(Long inId) {
        return this.em.find(Capacity.class, inId);
    }

    @Override
    public Capacity findByName(String inName) {
        TypedQuery<Capacity> q = this.em.createNamedQuery("Capacity.FIND_BY_NAME", Capacity.class);
        q.setParameter("name", inName);
        List<Capacity> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Capacity found for name (" + inName + ") !");
        }
    }

    @Override
    public Capacity findByNom(String inNom) {
        TypedQuery<Capacity> q = this.em.createNamedQuery("Capacity.FIND_BY_NOM", Capacity.class);
        q.setParameter("nom", inNom);
        List<Capacity> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Capacity found for nom (" + inNom + ") !");
        }
    }

    @Override
    public List<Capacity> findAll() {
        return this.em.createNamedQuery("Capacity.FIND_ALL", Capacity.class).getResultList();
    }

    @Override
    public Capacity update(Capacity inCapacity) {
        Capacity c = this.findById(inCapacity.getId());
        this.em.merge(c);
        this.em.flush();
        return c;
    }

    @Override
    public void delete(Capacity inCapacity) {
        Capacity c = this.findById(inCapacity.getId());
        this.em.remove(c);
    }
}
