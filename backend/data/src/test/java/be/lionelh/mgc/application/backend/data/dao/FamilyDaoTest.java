package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Family;
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
@DataSet("/datasets/family.xml")
public class FamilyDaoTest {

    @TestedObject
    @SpringBeanByType
    private FamilyDao FamilyDao;

    @Test
    public void testCreate() {
        Family c = new Family();
        c.setName("Family 001");

        Family newFamily = this.FamilyDao.create(c);
        assertNotNull(newFamily);
        assertNotNull(newFamily.getId());
        assertEquals(8, this.FamilyDao.findAll().size());
        assertEquals("Family 001", newFamily.getName());
    }

    @Test
    public void testFindAll() {
        List<Family> l = this.FamilyDao.findAll();
        assertNotNull(l);
        assertEquals(7, l.size());
    }

    @Test
    public void testFindById() {
        Family c = this.FamilyDao.findById(50L);
        assertNotNull(c);
        assertEquals("Goblin", c.getName());
    }

    @Test
    public void testFindByName() {
        Family c = this.FamilyDao.findByName("Swamp");
        assertNotNull(c);
        assertEquals(new Long(98), c.getId());
    }

    @Test
    public void testFindByNom() {
        Family c = this.FamilyDao.findByNom("Soldat");
        assertNotNull(c);
        assertEquals("Soldier", c.getName());
        assertEquals(new Long(11), c.getId());
    }

    @Test
    public void testUpdate() {
        Family c = this.FamilyDao.findById(33L);
        assertEquals("Spirit", c.getName());
        Family oldFamily = new Family();
        oldFamily.setId(c.getId());
        oldFamily.setName(c.getName());
        oldFamily.setNom(c.getNom());
        oldFamily.setCreationDate(c.getCreationDate());
        oldFamily.setLastUpdateDate(c.getLastUpdateDate());

        c.setNom("Esprit");
        Family updatedFamily = this.FamilyDao.update(c);
        assertEquals("Esprit", updatedFamily.getNom()); // Was null
        
        assertNotEquals(oldFamily.getLastUpdateDate(), updatedFamily.getLastUpdateDate()); // This date must have change !
        assertEquals(oldFamily.getCreationDate(), updatedFamily.getCreationDate()); // This date must not have changed !
    }

    @Test
    public void testDelete() {
        Family c = this.FamilyDao.findById(24L);

        this.FamilyDao.delete(c);
        assertNull(this.FamilyDao.findById(24L));
        assertEquals(6, this.FamilyDao.findAll().size());
    }
}
