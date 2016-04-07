package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Capacity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

/**
 * @author Lionel
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("spring-applicationContext-persistence.xml")
@Transactional(value = TransactionMode.ROLLBACK)
@DataSet("/datasets/capacity.xml")
public class CapacityDaoTest {

    @TestedObject
    @SpringBeanByType
    private CapacityDao capacityDao;

    @Test
    public void testCreate() {
        Capacity c = new Capacity();
        c.setName("Capacity 001");

        Capacity newCapacity = this.capacityDao.create(c);
        assertNotNull(newCapacity);
        assertNotNull(newCapacity.getId());
        assertEquals(3, this.capacityDao.findAll().size());
        assertEquals("Capacity 001", newCapacity.getName());
    }

    @Test
    public void testFindAll() {
        List<Capacity> l = this.capacityDao.findAll();
        assertNotNull(l);
        assertEquals(2, l.size());
    }

    @Test
    public void testFindById() {
        Capacity c = this.capacityDao.findById(1L);
        assertNotNull(c);
        assertEquals("Haste", c.getName());
    }

    @Test
    public void testFindByName() {
        Capacity c = this.capacityDao.findByName("Haste");
        assertNotNull(c);
        assertEquals(new Long(1), c.getId());
    }

    @Test
    public void testFindByNom() {
        Capacity c = this.capacityDao.findByNom("Initiative");
        assertNotNull(c);
        assertEquals("First strike", c.getName());
        assertEquals(new Long(2), c.getId());
    }

    @Test
    public void testUpdate() {
        Capacity c = this.capacityDao.findById(1L);
        assertEquals("Haste", c.getName());
        Capacity oldCapacity = new Capacity();
        oldCapacity.setId(c.getId());
        oldCapacity.setName(c.getName());
        oldCapacity.setNom(c.getNom());
        oldCapacity.setCreationDate(c.getCreationDate());
        oldCapacity.setLastUpdateDate(c.getLastUpdateDate());

        c.setNom("Celerite");
        Capacity updatedCapacity = this.capacityDao.update(c);
        assertEquals("Celerite", updatedCapacity.getNom()); // Was null
        
        assertNotEquals(oldCapacity.getLastUpdateDate(), updatedCapacity.getLastUpdateDate()); // This date must have change !
        assertEquals(oldCapacity.getCreationDate(), updatedCapacity.getCreationDate()); // This date must not have changed !
    }

    @Test
    public void testDelete() {
        Capacity c = this.capacityDao.findById(2L);

        this.capacityDao.delete(c);
        assertNull(this.capacityDao.findById(2L));
        assertEquals(1, this.capacityDao.findAll().size());
    }
}
