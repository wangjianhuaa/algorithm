package proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/12/012 21:46
 **/
public class InvocationHandler {


    @RuntimeType
    public static Object intercept(@Origin Method method, @AllArguments Object[] args,
                                   @SuperCall Callable<?> callable) throws Exception{
        System.out.println(method.getName()+"被代理成功"+"By byteBuddy");
        return callable.call();
    }


}
