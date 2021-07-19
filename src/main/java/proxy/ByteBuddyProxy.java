package proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author wangjianhua
 * @Description ByteBuddy代理
 * @date 2021/7/12/012 21:34
 **/
public class ByteBuddyProxy {

    public static <T>T getProxy(Class clazz) throws Exception{
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(clazz)
                .method(ElementMatchers.<MethodDescription>named("queryUserInfo"))
                .intercept(MethodDelegation.to(InvocationHandler.class))
                .make();
        return (T)dynamicType.load(Thread.currentThread().getContextClassLoader()).getLoaded().newInstance();
    }



    public static void main(String[] args) throws Exception{
        UserApi userApi = ByteBuddyProxy.getProxy(UserApiImpl.class);
        String invoke = userApi.queryUserInfo();
        System.out.println("调试结果:"+invoke);

    }
}
