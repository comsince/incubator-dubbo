package org.apache.dubbo.demo.consumer;

import org.apache.dubbo.rpc.*;

/**
 * @author comsicne
 * Copyright (c) [2019] [Meizu.inc]
 * @Time 19-5-10 下午2:32
 **/
public class TestFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("test filter");
        return invoker.invoke(invocation);
    }

    @Override
    public Result onResponse(Result result, Invoker<?> invoker, Invocation invocation) {
        return result;
    }
}
