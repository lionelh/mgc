package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.ColorDao;
import be.lionelh.mgc.application.backend.data.domain.Color;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Lionel
 */
@Repository("colorDao")
public class ColorDaoImpl implements ColorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Color create(Color inColor) {
        this.em.persist(inColor);
        this.em.flush();
        return inColor;
    }

    @Override
    public Color findById(Long inId) {
        return this.em.find(Color.class, inId);
    }

    @Override
    public Color findByName(String inName) {
        TypedQuery<Color> q = this.em.createNamedQuery("Color.FIND_BY_NAME", Color.class);
        q.setParameter("name", inName);
        List<Color> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Capacity found for name (" + inName + ") !");
        }
    }

    @Override
    public Color findByNom(String inNom) {
        TypedQuery<Color> q = this.em.createNamedQuery("Color.FIND_BY_NOM", Color.class);
        q.setParameter("nom", inNom);
        List<Color> l = q.getResultList();
        if (l == null || l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            throw new RuntimeException("Too many Capacity found for nom (" + inNom + ") !");
        }
    }

    @Override
    public List<Color> findAll() {
        return this.em.createNamedQuery("Color.FIND_ALL", Color.class).getResultList();
    }

    @Override
    public Color update(Color inColor) {
        Color c = this.findById(inColor.getId());
        this.em.merge(c);
        this.em.flush();
        return c;
    }

    @Override
    public void delete(Color inColor) {
        Color c = this.findById(inColor.getId());
        this.em.remove(c);
    }
}
