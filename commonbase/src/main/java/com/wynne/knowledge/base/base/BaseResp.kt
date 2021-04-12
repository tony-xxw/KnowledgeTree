package com.wynne.knowledge.base.base


data class BaseResp<T>(val data: T, val errorCode: Int, val errorMsg: String)