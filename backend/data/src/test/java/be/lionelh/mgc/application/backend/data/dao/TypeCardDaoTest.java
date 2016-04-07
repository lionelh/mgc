package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.TypeCard;

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
@DataSet("/datasets/type_card.xml")
public class TypeCardDaoTest {

    @TestedObject
    @SpringBeanByType
    private TypeCardDao typeCardDao;

    @Test
    public void testCreate() {
        TypeCard tc = new TypeCard();
        tc.setName("TypeCard 001");

        TypeCard newTypeCard = this.typeCardDao.create(tc);
        assertNotNull(newTypeCard);
        assertNotNull(newTypeCard.getId());
        assertEquals(13, this.typeCardDao.findAll().size());
        assertEquals("TypeCard 001", newTypeCard.getName());
    }

    @Test
    public void testFindAll() {
        List<TypeCard> l = this.typeCardDao.findAll();
        assertNotNull(l);
        assertEquals(12, l.size());
    }

    @Test
    public void testFindById() {
        TypeCard tc = this.typeCardDao.findById(1L);
        assertNotNull(tc);
        assertEquals("Artifact", tc.getName());
    }

    @Test
    public void testFindByName() {
        TypeCard tc = this.typeCardDao.findByName("Instant");
        assertNotNull(tc);
        assertEquals(new Long(3), tc.getId());
    }

    @Test
    public void testFindByNom() {
        TypeCard tc = this.typeCardDao.findByNom("Rituel");
        assertNotNull(tc);
        assertEquals("Sorcery", tc.getName());
        assertEquals(new Long(7), tc.getId());
    }

    @Test
    public void testUpdate() {
        TypeCard tc = this.typeCardDao.findById(8L);
        assertEquals("Land", tc.getName());
        TypeCard oldTypeCard = new TypeCard();
        oldTypeCard.setId(tc.getId());
        oldTypeCard.setName(tc.getName());
        oldTypeCard.setNom(tc.getNom());
        oldTypeCard.setCreationDate(tc.getCreationDate());
        oldTypeCard.setLastUpdateDate(tc.getLastUpdateDate());

        tc.setNom("Terrain");
        TypeCard updatedTypeCard = this.typeCardDao.update(tc);
        assertEquals("Terrain", updatedTypeCard.getNom()); // Was null
        
        assertNotEquals(oldTypeCard.getLastUpdateDate(), updatedTypeCard.getLastUpdateDate()); // This date must have change !
        assertEquals(oldTypeCard.getCreationDate(), updatedTypeCard.getCreationDate()); // This date must not have changed !
    }

    @Test
    public void testDelete() {
        TypeCard tc = this.typeCardDao.findById(10L);

        this.typeCardDao.delete(tc);
        assertNull(this.typeCardDao.findById(10L));
        assertEquals(11, this.typeCardDao.findAll().size());
    }
}
