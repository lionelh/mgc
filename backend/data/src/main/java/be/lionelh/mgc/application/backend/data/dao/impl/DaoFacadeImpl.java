package be.lionelh.mgc.application.backend.data.dao.impl;

import be.lionelh.mgc.application.backend.data.dao.CapacityDao;
import be.lionelh.mgc.application.backend.data.dao.DaoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lionel
 */
@Component("daoFacade")
public class DaoFacadeImpl implements DaoFacade {

    @Autowired
    private CapacityDao capacityDao;

    @Override
    public CapacityDao getCapacityDao() {
        return this.capacityDao;
    }
    
}
