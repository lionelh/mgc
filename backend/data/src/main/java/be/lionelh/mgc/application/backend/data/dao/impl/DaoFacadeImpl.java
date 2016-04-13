package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.CapacityDao;
import be.lionelh.mgc.application.backend.data.dao.CardDao;
import be.lionelh.mgc.application.backend.data.dao.ColorDao;
import be.lionelh.mgc.application.backend.data.dao.DaoFacade;
import be.lionelh.mgc.application.backend.data.dao.FamilyDao;
import be.lionelh.mgc.application.backend.data.dao.TypeCardDao;
import be.lionelh.mgc.application.backend.data.domain.Capacity;
import be.lionelh.mgc.application.backend.data.domain.Card;
import be.lionelh.mgc.application.backend.data.domain.Color;
import be.lionelh.mgc.application.backend.data.domain.Family;
import be.lionelh.mgc.application.backend.data.domain.TypeCard;
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
    private CardDao cardDao;
    
    @Autowired
    private ColorDao colorDao;

    @Autowired
    private FamilyDao familyDao;

    @Autowired
    private TypeCardDao typeCardDao;

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
    public void deleteCapacity(Capacity inCapacity) {
        this.capacityDao.delete(inCapacity);
    }

    @Override
    public Card createCard(Card inCard) {
        return this.cardDao.create(inCard);
    }

    @Override
    public Card findCardById(Long inId) {
        return this.cardDao.findById(inId);
    }

    @Override
    public Card findCardByName(String inName) {
        return this.cardDao.findByName(inName);
    }

    @Override
    public Card findCardByNom(String inNom) {
        return this.cardDao.findByNom(inNom);
    }

    @Override
    public List<Card> findAllCards() {
        return this.cardDao.findAll();
    }

    @Override
    public Card updateCard(Card inCard) {
        return this.cardDao.update(inCard);
    }

    @Override
    public void deleteCard(Card inCard) {
        this.cardDao.delete(inCard);
    }

    @Override
    public Color createColor(Color inColor) {
        return this.colorDao.create(inColor);
    }

    @Override
    public Color findColorById(Long inId) {
        return this.colorDao.findById(inId);
    }

    @Override
    public Color findColorByName(String inName) {
        return this.colorDao.findByName(inName);
    }

    @Override
    public Color findColorByNom(String inNom) {
        return this.colorDao.findByNom(inNom);
    }

    @Override
    public List<Color> findAllColors() {
        return this.colorDao.findAll();
    }

    @Override
    public Color updateColor(Color inColor) {
        return this.colorDao.update(inColor);
    }

    @Override
    public void deleteColor(Color inColor) {
        this.colorDao.delete(inColor);
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
    public void deleteFamily(Family inFamily) {
        this.familyDao.delete(inFamily);
    }

    @Override
    public TypeCard createTypeCard(TypeCard inTypeCard) {
        return this.typeCardDao.create(inTypeCard);
    }

    @Override
    public TypeCard findTypeCardById(Long inId) {
        return this.typeCardDao.findById(inId);
    }

    @Override
    public TypeCard findTypeCardByName(String inName) {
        return this.typeCardDao.findByName(inName);
    }

    @Override
    public TypeCard findTypeCardByNom(String inNom) {
        return this.typeCardDao.findByNom(inNom);
    }

    @Override
    public List<TypeCard> findAllTypeCards() {
        return this.typeCardDao.findAll();
    }

    @Override
    public TypeCard updateTypeCard(TypeCard inTypeCard) {
        return this.typeCardDao.update(inTypeCard);
    }

    @Override
    public void deleteTypeCard(TypeCard inTypeCard) {
        this.typeCardDao.delete(inTypeCard);
    }
}
