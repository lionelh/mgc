package be.lionelh.mgc.application.backend.data.domain;

import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author lh
 */
public class DateEntityListener {

    @PrePersist
    public void onPersist(Persistable inObj) {
        Date now = new Date();
        inObj.setCreationDate(now);
        inObj.setLastUpdateDate(now);
    }

    @PreUpdate
    public void onUpdate(Persistable inObj) {
        inObj.setLastUpdateDate(new Date());
    }
}
