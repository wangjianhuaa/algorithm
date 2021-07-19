package proxy;

import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

/**
 * @author wangjianhua
 * @Description ASM代理方式 字节码插桩
 * @date 2021/7/12/012 21:02
 **/
public class ASMProxy extends ClassLoader{

    /*
    - 场景：全链路监控、破解工具包、CGLIB、Spring获取类元数据等
    - 点评：这种代理就是使用字节码编程的方式进行处理，它的实现方式相对复杂，而且需要了解Java虚拟机规范相关的知识。
    因为你的每一步代理操作，都是在操作字节码指令，例如：`Opcodes.GETSTATIC`、`Opcodes.INVOKEVIRTUAL`，
    除了这些还有小200个常用的指令。但这种最接近底层的方式，也是最快的方式。所以在一些使用字节码插装的全链路监控中，会非常常见。
     */

    public static <T> T getProxy(Class clazz) throws Exception{
        ClassReader classReader = new ClassReader(clazz.getName());
        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS);
        classReader.accept(new ClassVisitor(ASM5,classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, final String name, String descriptor, String signature, String[] exceptions) {
                //方法过滤
                if(!("queryUserInfo").equals(name))
                {
                    return super.visitMethod(access, name, descriptor, signature, exceptions);
                }

                    final MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

                    return new AdviceAdapter(ASM5,methodVisitor,access,name,descriptor) {
                        @Override
                        protected void onMethodEnter() {
                            //执行指令 获取静态属性
                            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out","Ljava/io/PrintStream;");
                            //加载常量 load constant
                            methodVisitor.visitLdcInsn(name+"你被代理了 ASM代理！");
                            //调用方法
                            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/io/PrintStream", "println","(Ljava/lang/String;)V",false);
                            super.onMethodEnter();
                        }
                    };
                }

        },ClassReader.EXPAND_FRAMES);
        byte[] bytes = classWriter.toByteArray();
        return (T) new ASMProxy().defineClass(clazz.getName(),bytes,0,bytes.length).newInstance();
        }

    public static void main(String[] args) throws Exception{
        UserApi userApi = ASMProxy.getProxy(UserApiImpl.class);
        String invoke = userApi.queryUserInfo();
        System.out.println("测试结果"+invoke);
    }
}
