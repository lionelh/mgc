package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Card;
import be.lionelh.mgc.application.backend.data.domain.Color;
import be.lionelh.mgc.application.backend.data.domain.TypeCard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@DataSet("/datasets/card.xml")
public class CardDaoTest {

    @TestedObject
    @SpringBeanByType
    private CardDao cardDao;

    @SpringBeanByType
    private ColorDao colorDao;

    @SpringBeanByType
    private TypeCardDao typeCardDao;

    @Test
    public void testCreate() {
        Card c = new Card();
        c.setName("Card 001");
        Color co = this.colorDao.findById(1L);
        c.setColor(co);
        TypeCard tc = this.typeCardDao.findById(2L);
        c.setTypeCard(tc);
        Card newCard = this.cardDao.create(c);
        assertNotNull(newCard);
        assertNotNull(newCard.getId());
        assertEquals(3, this.cardDao.findAll().size());
        assertEquals("Card 001", newCard.getName());
    }

    @Test
    public void testFindAll() {
        List<Card> l = this.cardDao.findAll();
        assertNotNull(l);
        assertEquals(2, l.size());
    }

    @Test
    public void testFindById() {
        Card c = this.cardDao.findById(2L);
        assertNotNull(c);
        assertEquals("Swamp", c.getName());
    }

    @Test
    public void testFindByName() {
        Card c = this.cardDao.findByName("Swamp");
        assertNotNull(c);
        assertEquals(new Long(2), c.getId());
    }

    @Test
    public void testFindByNom() {
        Card c = this.cardDao.findByNom("Chevalier noir");
        assertNotNull(c);
        assertEquals("Black knight", c.getName());
        assertEquals(new Long(1), c.getId());
    }

    @Test
    public void testUpdate() {
        Card c = this.cardDao.findById(2L);
        assertEquals("Swamp", c.getName());
        Card oldCard = new Card();
        oldCard.setId(c.getId());
        oldCard.setName(c.getName());
        oldCard.setNom(c.getNom());
        oldCard.setCreationDate(c.getCreationDate());
        oldCard.setLastUpdateDate(c.getLastUpdateDate());

        c.setNom("Marais");
        Card updatedCard = this.cardDao.update(c);
        assertEquals("Marais", updatedCard.getNom()); // Was null
        
        assertNotEquals(oldCard.getLastUpdateDate(), updatedCard.getLastUpdateDate()); // This date must have change !
        assertEquals(oldCard.getCreationDate(), updatedCard.getCreationDate()); // This date must not have changed !
    }

    @Test
    public void testDelete() {
        Card c = this.cardDao.findById(2L);

        this.cardDao.delete(c);
        assertNull(this.cardDao.findById(2L));
        assertEquals(1, this.cardDao.findAll().size());
    }
}
