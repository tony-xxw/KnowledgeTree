package com.wynne.knowledge.tree.service.empty;

import com.wynne.knowledge.tree.service.base.IAccountService;

/**
 * @author xxw
 */
public class EmptyAccountServiceImpl implements IAccountService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }
}
