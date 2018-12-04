package com.wynne.knowledge.tree.service;

import com.wynne.knowledge.tree.service.base.IBaseService;
import com.wynne.knowledge.tree.service.empty.EmptyBaseServiceImpl;

/**
 * @author xxw
 */
public class ServiceFactory {
    private IBaseService mIBaseService;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return Inner.serviceFactory;
    }

    private static class Inner {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }

    public void setAccountService(IBaseService iBaseService) {
        mIBaseService = iBaseService;
    }


    public IBaseService getAccountService() {
        if (mIBaseService == null) {
            mIBaseService = new EmptyBaseServiceImpl();
        }
        return mIBaseService;
    }


}
