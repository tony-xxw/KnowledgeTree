package com.wynne.knowledge.tree.service;

import com.wynne.knowledge.tree.service.base.IAccountService;
import com.wynne.knowledge.tree.service.empty.EmptyAccountServiceImpl;

/**
 * @author xxw
 */
public class ServiceFactory {
    private IAccountService mIAccountService;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return Inner.serviceFactory;
    }

    private static class Inner {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }

    public void setAccountService(IAccountService iAccountService) {
        mIAccountService = iAccountService;
    }


    public IAccountService getAccountService() {
        if (mIAccountService == null) {
            mIAccountService = new EmptyAccountServiceImpl();
        }
        return mIAccountService;
    }


}
