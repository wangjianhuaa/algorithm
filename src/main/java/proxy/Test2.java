package proxy;

import java.lang.reflect.Method;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/12/012 20:10
 **/
public class Test2 {
    public static void main(String[] args) throws Exception {
        Class<UserApiImpl> aClass = UserApiImpl.class;
        Method userInfo = aClass.getMethod("queryUserInfo");
        Object invoke = userInfo.invoke(aClass.newInstance());
        System.out.println(invoke);
    }
}
