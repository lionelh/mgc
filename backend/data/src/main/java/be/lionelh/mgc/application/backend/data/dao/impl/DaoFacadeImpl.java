package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.CapacityDao;
import be.lionelh.mgc.application.backend.data.dao.DaoFacade;
import be.lionelh.mgc.application.backend.data.dao.FamilyDao;
import be.lionelh.mgc.application.backend.data.domain.Capacity;
import be.lionelh.mgc.application.backend.data.domain.Family;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lionel
 */
@Component("daoFacade")
public class DaoFacadeImpl implements DaoFacade {

    @Autowired
    private CapacityDao capacityDao;

    @Autowired
    private FamilyDao familyDao;

    @Override
    public Capacity createCapacity(Capacity inCapacity) {
        return this.capacityDao.create(inCapacity);
    }

    @Override
    public Capacity findCapacityById(Long inId) {
        return this.capacityDao.findById(inId);
    }

    @Override
    public Capacity findCapacityByName(String inName) {
        return this.capacityDao.findByName(inName);
    }

    @Override
    public Capacity findCapacityByNom(String inNom) {
        return this.capacityDao.findByNom(inNom);
    }

    @Override
    public List<Capacity> findAllCapacities() {
        return this.capacityDao.findAll();
    }

    @Override
    public Capacity updateCapacity(Capacity inCapacity) {
        return this.capacityDao.update(inCapacity);
    }

    @Override
    public void removeCapacity(Capacity inCapacity) {
        this.capacityDao.delete(inCapacity);
    }

    @Override
    public Family createFamily(Family inFamily) {
        return this.familyDao.create(inFamily);
    }

    @Override
    public Family findFamilyById(Long inId) {
        return this.familyDao.findById(inId);
    }

    @Override
    public Family findFamilyByName(String inName) {
        return this.familyDao.findByName(inName);
    }

    @Override
    public Family findFamilyByNom(String inNom) {
        return this.familyDao.findByNom(inNom);
    }

    @Override
    public List<Family> findAllFamilies() {
        return this.familyDao.findAll();
    }

    @Override
    public Family updateFamily(Family inFamily) {
        return this.familyDao.update(inFamily);
    }

    @Override
    public void removeFamily(Family inFamily) {
        this.familyDao.delete(inFamily);
    }
}
