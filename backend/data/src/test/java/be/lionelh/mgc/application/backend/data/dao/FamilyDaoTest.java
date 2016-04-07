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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
    private FamilyDao familyDao;

    @Test
    public void testCreate() {
        Family f = new Family();
        f.setName("Family 001");

        Family newFamily = this.familyDao.create(f);
        assertNotNull(newFamily);
        assertNotNull(newFamily.getId());
        assertEquals(8, this.familyDao.findAll().size());
        assertEquals("Family 001", newFamily.getName());
    }

    @Test
    public void testFindAll() {
        List<Family> l = this.familyDao.findAll();
        assertNotNull(l);
        assertEquals(7, l.size());
    }

    @Test
    public void testFindById() {
        Family f = this.familyDao.findById(50L);
        assertNotNull(f);
        assertEquals("Goblin", f.getName());
    }

    @Test
    public void testFindByName() {
        Family f = this.familyDao.findByName("Swamp");
        assertNotNull(f);
        assertEquals(new Long(98), f.getId());
    }

    @Test
    public void testFindByNom() {
        Family f = this.familyDao.findByNom("Soldat");
        assertNotNull(f);
        assertEquals("Soldier", f.getName());
        assertEquals(new Long(11), f.getId());
    }

    @Test
    public void testUpdate() {
        Family f = this.familyDao.findById(33L);
        assertEquals("Spirit", f.getName());
        Family oldFamily = new Family();
        oldFamily.setId(f.getId());
        oldFamily.setName(f.getName());
        oldFamily.setNom(f.getNom());
        oldFamily.setCreationDate(f.getCreationDate());
        oldFamily.setLastUpdateDate(f.getLastUpdateDate());

        f.setNom("Esprit");
        Family updatedFamily = this.familyDao.update(f);
        assertEquals("Esprit", updatedFamily.getNom()); // Was null
        
        assertNotEquals(oldFamily.getLastUpdateDate(), updatedFamily.getLastUpdateDate()); // This date must have change !
        assertEquals(oldFamily.getCreationDate(), updatedFamily.getCreationDate()); // This date must not have changed !
    }

    @Test
    public void testDelete() {
        Family f = this.familyDao.findById(24L);

        this.familyDao.delete(f);
        assertNull(this.familyDao.findById(24L));
        assertEquals(6, this.familyDao.findAll().size());
    }
}
