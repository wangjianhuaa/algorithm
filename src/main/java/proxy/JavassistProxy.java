package proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author wangjianhua
 * @Description Javassist代理
 * @date 2021/7/12/012 21:51
 **/
public class JavassistProxy  extends ClassLoader{
        /*
        `Javassist` 是一个使用非常广的字节码插装框架，
        几乎一大部分非入侵的全链路监控都是会选择使用这个框架。因为它不想ASM那样操作字节码导致风险，
        同时它的功能也非常齐全。另外，这个框架即可使用它所提供的方式直接编写插装代码，
        也可以使用字节码指令进行控制生成代码，所以综合来看也是一个非常不错的字节码框架。
         */
    public static <T> T getProxy(Class clazz) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        //获取类
        CtClass ctClass = pool.get(clazz.getName());
        //获取方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("queryUserInfo");
        //方法前加强
        ctMethod.insertBefore("{System.out.println(\""+ctMethod.getName()+"你被代理了，By Javassist代理\");}");
        byte[] bytes = ctClass.toBytecode();
        return (T) new JavassistProxy().defineClass(clazz.getName(),bytes,0,bytes.length).newInstance();
    }

    public static void main(String[] args) throws Exception {
        UserApi userApi= JavassistProxy.getProxy(UserApiImpl.class);
        String invoke = userApi.queryUserInfo();
        System.out.println("测试结果："+invoke);
    }
}
