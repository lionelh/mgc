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

public class DaoFacadeTest {

    @TestedObject
    @SpringBeanByType
    private DaoFacade bean;

    @Test
    @DataSet("/datasets/capacity.xml")
    public void testCreateCapacity() {
        Capacity c = new Capacity();
        c.setName("Capacity 001");

        Capacity newCapacity = this.bean.createCapacity(c);
        assertNotNull(newCapacity);
        assertNotNull(newCapacity.getId());
        assertEquals(3, this.bean.findAllCapacities().size());
        assertEquals("Capacity 001", newCapacity.getName());
    }

    @Test
    @DataSet("/datasets/capacity.xml")
    public void testFindAllCapacities() {
        List<Capacity> l = this.bean.findAllCapacities();
        assertNotNull(l);
        assertEquals(2, l.size());
    }

    @Test
    @DataSet("/datasets/capacity.xml")
    public void testFindCapacityById() {
        Capacity c = this.bean.findCapacityById(1L);
        assertNotNull(c);
        assertEquals("Haste", c.getName());
    }

    @Test
    @DataSet("/datasets/capacity.xml")
    public void testFindCapacityByName() {
        Capacity c = this.bean.findCapacityByName("Haste");
        assertNotNull(c);
        assertEquals(new Long(1), c.getId());
    }

    @Test
    @DataSet("/datasets/capacity.xml")
    public void testFindCapacityByNom() {
        Capacity c = this.bean.findCapacityByNom("Initiative");
        assertNotNull(c);
        assertEquals("First strike", c.getName());
        assertEquals(new Long(2), c.getId());
    }

    @Test
    @DataSet("/datasets/capacity.xml")
    public void testUpdateCapacity() {
        Capacity c = this.bean.findCapacityById(1L);
        assertEquals("Haste", c.getName());
        Capacity oldCapacity = new Capacity();
        oldCapacity.setId(c.getId());
        oldCapacity.setName(c.getName());
        oldCapacity.setNom(c.getNom());
        oldCapacity.setCreationDate(c.getCreationDate());
        oldCapacity.setLastUpdateDate(c.getLastUpdateDate());

        c.setNom("Celerite");
        Capacity updatedCapacity = this.bean.updateCapacity(c);
        assertEquals("Celerite", updatedCapacity.getNom()); // Was null
        
        assertNotEquals(oldCapacity.getLastUpdateDate(), updatedCapacity.getLastUpdateDate()); // This date must have change !
        assertEquals(oldCapacity.getCreationDate(), updatedCapacity.getCreationDate()); // This date must not have changed !
    }

    @Test
    @DataSet("/datasets/capacity.xml")
    public void testRemoveCapacity() {
        Capacity c = this.bean.findCapacityById(2L);

        this.bean.removeCapacity(c);
        assertNull(this.bean.findCapacityById(2L));
        assertEquals(1, this.bean.findAllCapacities().size());
    }
}
