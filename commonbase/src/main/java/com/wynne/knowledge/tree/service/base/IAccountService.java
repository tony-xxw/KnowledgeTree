package com.wynne.knowledge.tree.service.base;

/**
 * @author xxw
 */
public interface IAccountService {

    /**
     * 是否登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 登录用户id
     *
     * @return
     */
    String getAccountId();
}
