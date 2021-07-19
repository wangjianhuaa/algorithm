package proxy;

import java.lang.reflect.Proxy;

/**
 * @author wangjianhua
 * @Description jdk代理模拟
 * @date 2021/7/12/012 20:17
 **/
public class JdkProxy {

    public static <T> T getProxy(Class clazz) throws Exception{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        return (T)Proxy.newProxyInstance(classLoader, new Class[]{clazz}, (proxy, method, args) -> {
            System.out.println(method.getName()+":jdk代理");
            return "好的 代理成功";
        });
    }

    public static void main(String[] args) throws Exception{
        UserApi userApi  =JdkProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        System.out.println("代理成功:"+invoke);

    }
}
