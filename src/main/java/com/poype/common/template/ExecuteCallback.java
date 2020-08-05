package com.poype.common.template;

public abstract class ExecuteCallback {

    /**
     * 参数检查回调
     */
    public void check() {}

    /**
     * 业务执行回调
     */
    public abstract void doService();

}
