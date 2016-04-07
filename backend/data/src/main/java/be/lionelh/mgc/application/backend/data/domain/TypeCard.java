package be.lionelh.mgc.application.backend.data.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author lh
 */
@Entity(name = "TypeCard")
@EntityListeners({DateEntityListener.class})
@Table(name = "mgc_type_card")
@NamedQueries({
    @NamedQuery(name = "TypeCard.FIND_ALL", query = "SELECT new be.lionelh.mgc.application.backend.data.domain.TypeCard(tc.id,tc.name,tc.nom,tc.creationDate,tc.lastUpdateDate) FROM TypeCard tc"),
    @NamedQuery(name = "TypeCard.FIND_BY_NAME", query = "SELECT new be.lionelh.mgc.application.backend.data.domain.TypeCard(tc.id,tc.name,tc.nom,tc.creationDate,tc.lastUpdateDate) FROM TypeCard tc WHERE tc.name = :name"),
    @NamedQuery(name = "TypeCard.FIND_BY_NOM", query = "SELECT new be.lionelh.mgc.application.backend.data.domain.TypeCard(tc.id,tc.name,tc.nom,tc.creationDate,tc.lastUpdateDate) FROM TypeCard tc WHERE tc.nom = :nom"),
})
@SuppressWarnings({"SerializableClass", "PersistenceUnitPresent"})
public class TypeCard implements Persistable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TC_ID", insertable = true, nullable = false, precision = 20, scale = 0, unique = true, updatable = true)
    private Long id;

    @Column(name = "TC_NAME", insertable = true, nullable = false, length = 255, unique = true, updatable = true)
    private String name;

    @Column(name = "TC_NAME_FR", insertable = true, nullable = true, length = 255, unique = false, updatable = true)
    private String nom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TC_CREATION_DATE", insertable = true, nullable = false, unique = false, updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TC_LAST_UPDATE_DATE", insertable = true, nullable = false, unique = true, updatable = true)
    private Date lastUpdateDate;

    public TypeCard() {}

    public TypeCard(Long inId, String inName, String inNom, Date inCreationDate, Date inLastUpdateDate) {
        this.creationDate = inCreationDate;
        this.id = inId;
        this.lastUpdateDate = inLastUpdateDate;
        this.name = inName;
        this.nom = inNom;
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
        final TypeCard other = (TypeCard) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeCard{" + "id=" + id + ", name=" + name + ", nom=" + nom + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate + '}';
    }
}
