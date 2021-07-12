package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wangjianhua
 * @Description cglib代理样例
 * @date 2021/7/12/012 20:26
 **/
public class CglibProxy implements MethodInterceptor {
    /*
    cglib底层使用ASM字节码在类中修改指令码实现代理
    得益于字节码的使用 这种代理方式会比jdk代理快1-2倍
     */
    public Object newInstall(Object object){
        return Enhancer.create(object.getClass(),  this);
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这是cglib代理");
        return methodProxy.invokeSuper(o,objects);
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        UserApiImpl userApi = (UserApiImpl)cglibProxy.newInstall(new UserApiImpl());
        String invoke = userApi.queryUserInfo();
        System.out.println("测试结果"+invoke);
    }
}
