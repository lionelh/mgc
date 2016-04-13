package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Capacity;
import be.lionelh.mgc.application.backend.data.domain.Card;
import be.lionelh.mgc.application.backend.data.domain.Color;
import be.lionelh.mgc.application.backend.data.domain.Family;
import be.lionelh.mgc.application.backend.data.domain.TypeCard;

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

        this.bean.deleteCapacity(c);
        assertNull(this.bean.findCapacityById(2L));
        assertEquals(1, this.bean.findAllCapacities().size());
    }

    @Test
    @DataSet("/datasets/card.xml")
    public void testCreateCard() {
        Card c = new Card();
        c.setName("Card 001");
        Color co = this.bean.findColorById(1L);
        c.setColor(co);
        TypeCard tc = this.bean.findTypeCardById(2L);
        c.setTypeCard(tc);
        Card newCard = this.bean.createCard(c);
        assertNotNull(newCard);
        assertNotNull(newCard.getId());
        assertEquals(3, this.bean.findAllCards().size());
        assertEquals("Card 001", newCard.getName());
    }

    @Test
    @DataSet("/datasets/card.xml")
    public void testFindAllCards() {
        List<Card> l = this.bean.findAllCards();
        assertNotNull(l);
        assertEquals(2, l.size());
    }

    @Test
    @DataSet("/datasets/card.xml")
    public void testFindCardById() {
        Card c = this.bean.findCardById(2L);
        assertNotNull(c);
        assertEquals("Swamp", c.getName());
    }

    @Test
    @DataSet("/datasets/card.xml")
    public void testFindCardByName() {
        Card c = this.bean.findCardByName("Swamp");
        assertNotNull(c);
        assertEquals(new Long(2), c.getId());
    }

    @Test
    @DataSet("/datasets/card.xml")
    public void testFindCardByNom() {
        Card c = this.bean.findCardByNom("Chevalier noir");
        assertNotNull(c);
        assertEquals("Black knight", c.getName());
        assertEquals(new Long(1), c.getId());
    }

    @Test
    @DataSet("/datasets/card.xml")
    public void testUpdateCard() {
        Card c = this.bean.findCardById(2L);
        assertEquals("Swamp", c.getName());
        Card oldCard = new Card();
        oldCard.setId(c.getId());
        oldCard.setName(c.getName());
        oldCard.setNom(c.getNom());
        oldCard.setCreationDate(c.getCreationDate());
        oldCard.setLastUpdateDate(c.getLastUpdateDate());

        c.setNom("Marais");
        Card updatedCard = this.bean.updateCard(c);
        assertEquals("Marais", updatedCard.getNom()); // Was null
        
        assertNotEquals(oldCard.getLastUpdateDate(), updatedCard.getLastUpdateDate()); // This date must have change !
        assertEquals(oldCard.getCreationDate(), updatedCard.getCreationDate()); // This date must not have changed !
    }

    @Test
    @DataSet("/datasets/card.xml")
    public void testDeleteCard() {
        Card c = this.bean.findCardById(2L);

        this.bean.deleteCard(c);
        assertNull(this.bean.findCardById(2L));
        assertEquals(1, this.bean.findAllCards().size());
    }

    @Test
    @DataSet("/datasets/color.xml")
    public void testCreateColor() {
        Color c = new Color();
        c.setName("Color 001");

        Color newColor = this.bean.createColor(c);
        assertNotNull(newColor);
        assertNotNull(newColor.getId());
        assertEquals(9, this.bean.findAllColors().size());
        assertEquals("Color 001", newColor.getName());
    }

    @Test
    @DataSet("/datasets/color.xml")
    public void testFindAllColors() {
        List<Color> l = this.bean.findAllColors();
        assertNotNull(l);
        assertEquals(8, l.size());
    }

    @Test
    @DataSet("/datasets/color.xml")
    public void testFindColorById() {
        Color c = this.bean.findColorById(3L);
        assertNotNull(c);
        assertEquals("Blue", c.getName());
    }

    @Test
    @DataSet("/datasets/color.xml")
    public void testFindColorByName() {
        Color c = this.bean.findColorByName("White");
        assertNotNull(c);
        assertEquals(new Long(4), c.getId());
    }

    @Test
    @DataSet("/datasets/color.xml")
    public void testFindColorByNom() {
        Color c = this.bean.findColorByNom("Noir");
        assertNotNull(c);
        assertEquals("Black", c.getName());
        assertEquals(new Long(2), c.getId());
    }

    @Test
    @DataSet("/datasets/color.xml")
    public void testUpdateColor() {
        Color c = this.bean.findColorById(5L);
        assertEquals("Red", c.getName());
        Color oldColor = new Color();
        oldColor.setId(c.getId());
        oldColor.setName(c.getName());
        oldColor.setNom(c.getNom());
        oldColor.setCreationDate(c.getCreationDate());
        oldColor.setLastUpdateDate(c.getLastUpdateDate());

        c.setNom("Rouge");
        Color updatedColor = this.bean.updateColor(c);
        assertEquals("Rouge", updatedColor.getNom()); // Was null
        
        assertNotEquals(oldColor.getLastUpdateDate(), updatedColor.getLastUpdateDate()); // This date must have change !
        assertEquals(oldColor.getCreationDate(), updatedColor.getCreationDate()); // This date must not have changed !
    }

    @Test
    @DataSet("/datasets/color.xml")
    public void testDeleteColor() {
        Color c = this.bean.findColorById(2L);

        this.bean.deleteColor(c);
        assertNull(this.bean.findColorById(2L));
        assertEquals(7, this.bean.findAllColors().size());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testCreateFamily() {
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
    public void testFindAllFamilies() {
        List<Family> l = this.bean.findAllFamilies();
        assertNotNull(l);
        assertEquals(7, l.size());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testFindFamilyById() {
        Family f = this.bean.findFamilyById(50L);
        assertNotNull(f);
        assertEquals("Goblin", f.getName());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testFindFamilyByName() {
        Family f = this.bean.findFamilyByName("Swamp");
        assertNotNull(f);
        assertEquals(new Long(98), f.getId());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testFindFamilyByNom() {
        Family f = this.bean.findFamilyByNom("Soldat");
        assertNotNull(f);
        assertEquals("Soldier", f.getName());
        assertEquals(new Long(11), f.getId());
    }

    @Test
    @DataSet("/datasets/family.xml")
    public void testUpdateFamily() {
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
    public void testDeleteFamily() {
        Family f = this.bean.findFamilyById(24L);

        this.bean.deleteFamily(f);
        assertNull(this.bean.findFamilyById(24L));
        assertEquals(6, this.bean.findAllFamilies().size());
    }

    @Test
    @DataSet("/datasets/type_card.xml")
    public void testCreateTypeCard() {
        TypeCard tc = new TypeCard();
        tc.setName("TypeCard 001");

        TypeCard newTypeCard = this.bean.createTypeCard(tc);
        assertNotNull(newTypeCard);
        assertNotNull(newTypeCard.getId());
        assertEquals(13, this.bean.findAllTypeCards().size());
        assertEquals("TypeCard 001", newTypeCard.getName());
    }

    @Test
    @DataSet("/datasets/type_card.xml")
    public void testFindAllTypeCards() {
        List<TypeCard> l = this.bean.findAllTypeCards();
        assertNotNull(l);
        assertEquals(12, l.size());
    }

    @Test
    @DataSet("/datasets/type_card.xml")
    public void testFindTypeCardById() {
        TypeCard tc = this.bean.findTypeCardById(1L);
        assertNotNull(tc);
        assertEquals("Artifact", tc.getName());
    }

    @Test
    @DataSet("/datasets/type_card.xml")
    public void testFindTypeCardByName() {
        TypeCard tc = this.bean.findTypeCardByName("Instant");
        assertNotNull(tc);
        assertEquals(new Long(3), tc.getId());
    }

    @Test
    @DataSet("/datasets/type_card.xml")
    public void testFindTypeCardByNom() {
        TypeCard tc = this.bean.findTypeCardByNom("Rituel");
        assertNotNull(tc);
        assertEquals("Sorcery", tc.getName());
        assertEquals(new Long(7), tc.getId());
    }

    @Test
    @DataSet("/datasets/type_card.xml")
    public void testUpdateTypeCard() {
        TypeCard tc = this.bean.findTypeCardById(8L);
        assertEquals("Land", tc.getName());
        TypeCard oldTypeCard = new TypeCard();
        oldTypeCard.setId(tc.getId());
        oldTypeCard.setName(tc.getName());
        oldTypeCard.setNom(tc.getNom());
        oldTypeCard.setCreationDate(tc.getCreationDate());
        oldTypeCard.setLastUpdateDate(tc.getLastUpdateDate());

        tc.setNom("Terrain");
        TypeCard updatedTypeCard = this.bean.updateTypeCard(tc);
        assertEquals("Terrain", updatedTypeCard.getNom()); // Was null
        
        assertNotEquals(oldTypeCard.getLastUpdateDate(), updatedTypeCard.getLastUpdateDate()); // This date must have change !
        assertEquals(oldTypeCard.getCreationDate(), updatedTypeCard.getCreationDate()); // This date must not have changed !
    }

    @Test
    @DataSet("/datasets/type_card.xml")
    public void testDeleteTypeCard() {
        TypeCard tc = this.bean.findTypeCardById(10L);

        this.bean.deleteTypeCard(tc);
        assertNull(this.bean.findTypeCardById(10L));
        assertEquals(11, this.bean.findAllTypeCards().size());
    }
}
