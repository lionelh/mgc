package be.lionelh.mgc.application.backend.data.dao;

import be.lionelh.mgc.application.backend.data.domain.Capacity;
import be.lionelh.mgc.application.backend.data.domain.Family;
import be.lionelh.mgc.application.backend.data.domain.TypeCard;
import java.util.List;

/**
 * @author lh
 */
public interface DaoFacade {
    Capacity createCapacity(Capacity inCapacity);
    Capacity findCapacityById(Long inId);
    Capacity findCapacityByName(String inName);
    Capacity findCapacityByNom(String inNom);
    List<Capacity> findAllCapacities();
    Capacity updateCapacity(Capacity inCapacity);
    void deleteCapacity(Capacity inCapacity);
    
    Family createFamily(Family inFamily);
    Family findFamilyById(Long inId);
    Family findFamilyByName(String inName);
    Family findFamilyByNom(String inNom);
    List<Family> findAllFamilies();
    Family updateFamily(Family inFamily);
    void deleteFamily(Family inFamily);

    TypeCard createTypeCard(TypeCard inTypeCard);
    TypeCard findTypeCardById(Long inId);
    TypeCard findTypeCardByName(String inName);
    TypeCard findTypeCardByNom(String inNom);
    List<TypeCard> findAllTypeCards();
    TypeCard updateTypeCard(TypeCard inTypeCard);
    void deleteTypeCard(TypeCard inTypeCard);
}
