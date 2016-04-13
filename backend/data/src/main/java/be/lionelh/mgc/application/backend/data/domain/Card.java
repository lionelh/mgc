package be.lionelh.mgc.application.backend.data.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author lh
 */
@Entity(name = "Card")
@EntityListeners({DateEntityListener.class})
@Table(name = "mgc_card")
@NamedQueries({
    @NamedQuery(name = "Card.FIND_ALL", query = "SELECT new be.lionelh.mgc.application.backend.data.domain.Card(c.id,c.name,c.nom,c.creationDate,c.lastUpdateDate,c.manacost,c.power,c.toughness,c.loyalty,c.color,c.typeCard) FROM Card c"),
    @NamedQuery(name = "Card.FIND_BY_NAME", query = "SELECT new be.lionelh.mgc.application.backend.data.domain.Card(c.id,c.name,c.nom,c.creationDate,c.lastUpdateDate,c.manacost,c.power,c.toughness,c.loyalty,c.color,c.typeCard) FROM Card c WHERE c.name = :name"),
    @NamedQuery(name = "Card.FIND_BY_NOM", query = "SELECT new be.lionelh.mgc.application.backend.data.domain.Card(c.id,c.name,c.nom,c.creationDate,c.lastUpdateDate,c.manacost,c.power,c.toughness,c.loyalty,c.color,c.typeCard) FROM Card c WHERE c.nom = :nom"),
})
@SuppressWarnings({"SerializableClass", "PersistenceUnitPresent"})
public class Card implements Persistable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CA_ID", insertable = true, nullable = false, precision = 20, scale = 0, unique = true, updatable = true)
    private Long id;

    @Column(name = "CA_NAME", insertable = true, nullable = false, length = 255, unique = true, updatable = true)
    private String name;

    @Column(name = "CA_NAME_FR", insertable = true, nullable = true, length = 255, unique = false, updatable = true)
    private String nom;

    @Column(name = "CA_MANA_COST", insertable = true, nullable = true, length = 255, unique = false, updatable = true)
    private String manacost;

    @Column(name = "CA_POWER", insertable = true, nullable = true, length = 50, unique = false, updatable = true)
    private String power;

    @Column(name = "CA_TOUGHNESS", insertable = true, nullable = true, length = 50, unique = false, updatable = true)
    private String toughness;

    @Column(name = "CA_LOYALTY", insertable = true, nullable = true, length = 50, unique = false, updatable = true)
    private String loyalty;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "CA_TEXT_ENG", insertable = true, nullable = true,unique = false, updatable = true)
    private String textEng;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "CA_TEXT_FR", insertable = true, nullable = true, unique = false, updatable = true)
    private String textFr;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "CA_FLAVOR_ENG", insertable = true, nullable = true, unique = false, updatable = true)
    private String flavorEng;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "CA_FLAVOR_FR", insertable = true, nullable = true, unique = false, updatable = true)
    private String flavorFr;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CA_CREATION_DATE", insertable = true, nullable = false, unique = false, updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CA_LAST_UPDATE_DATE", insertable = true, nullable = false, unique = true, updatable = true)
    private Date lastUpdateDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "CA_CO_ID", referencedColumnName = "CO_ID", insertable = true, nullable = false, unique = false, updatable = true)
    private Color color;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "CA_TC_ID", referencedColumnName = "TC_ID", insertable = true, nullable = false, unique = false, updatable = true)
    private TypeCard typeCard;

    public Card() {}

    public Card(Long inId, String inName, String inNom, Date inCreationDate, Date inLastUpdateDate, String manacost, String power, String toughness, String loyalty, Color color, TypeCard typeCard) {
        this.creationDate = inCreationDate;
        this.id = inId;
        this.lastUpdateDate = inLastUpdateDate;
        this.name = inName;
        this.nom = inNom;
        this.loyalty = loyalty;
        this.manacost = manacost;
        this.power = power;
        this.toughness = toughness;
        this.color = color;
        this.typeCard = typeCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getManacost() {
        return manacost;
    }

    public void setManacost(String manacost) {
        this.manacost = manacost;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public String getTextEng() {
        return textEng;
    }

    public void setTextEng(String textEng) {
        this.textEng = textEng;
    }

    public String getTextFr() {
        return textFr;
    }

    public void setTextFr(String textFr) {
        this.textFr = textFr;
    }

    public String getFlavorEng() {
        return flavorEng;
    }

    public void setFlavorEng(String flavorEng) {
        this.flavorEng = flavorEng;
    }

    public void setFlavorFr(String flavorFr) {
        this.flavorFr = flavorFr;
    }

    public String getFlavorFr() {
        return flavorFr;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public TypeCard getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(TypeCard typeCard) {
        this.typeCard = typeCard;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Card{" + "id=" + id + ", name=" + name + ", nom=" + nom + ", manacost=" + manacost + ", power=" + power + ", toughness=" + toughness + ", loyalty=" + loyalty + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate + ", typeCard=" + typeCard + '}';
    }
}
