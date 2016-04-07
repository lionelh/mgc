package be.lionelh.mgc.application.backend.data.domain;

import java.util.Date;

/**
 * @author lh
 */
public interface Persistable {
    void setCreationDate(Date inDate);
    void setLastUpdateDate(Date inDate);
}
