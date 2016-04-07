package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Capacity;
import be.lionelh.mgc.application.backend.data.domain.Family;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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


    @Test
    @DataSet("/datasets/family.xml")
    public void testCreate() {
        Family f = new Family();
        f.setName("Family 001");

        Family newFamily = this.bean.createFamily(f);
        assertNotNull(newFamily);
        assertNotNull(newFamily.getId());
        assertEquals(8, this.bean.findAllFamilies().size());
        assertEquals("Family 001", newFamily.getName());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testFindAll() {
        List<Family> l = this.bean.findAllFamilies();
        assertNotNull(l);
        assertEquals(7, l.size());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testFindById() {
        Family f = this.bean.findFamilyById(50L);
        assertNotNull(f);
        assertEquals("Goblin", f.getName());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testFindByName() {
        Family f = this.bean.findFamilyByName("Swamp");
        assertNotNull(f);
        assertEquals(new Long(98), f.getId());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testFindByNom() {
        Family f = this.bean.findFamilyByNom("Soldat");
        assertNotNull(f);
        assertEquals("Soldier", f.getName());
        assertEquals(new Long(11), f.getId());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testUpdate() {
        Family f = this.bean.findFamilyById(33L);
        assertEquals("Spirit", f.getName());
        Family oldFamily = new Family();
        oldFamily.setId(f.getId());
        oldFamily.setName(f.getName());
        oldFamily.setNom(f.getNom());
        oldFamily.setCreationDate(f.getCreationDate());
        oldFamily.setLastUpdateDate(f.getLastUpdateDate());

        f.setNom("Esprit");
        Family updatedFamily = this.bean.updateFamily(f);
        assertEquals("Esprit", updatedFamily.getNom()); // Was null
        
        assertNotEquals(oldFamily.getLastUpdateDate(), updatedFamily.getLastUpdateDate()); // This date must have change !
        assertEquals(oldFamily.getCreationDate(), updatedFamily.getCreationDate()); // This date must not have changed !
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testDelete() {
        Family f = this.bean.findFamilyById(24L);

        this.bean.removeFamily(f);
        assertNull(this.bean.findFamilyById(24L));
        assertEquals(6, this.bean.findAllFamilies().size());
    }
}
