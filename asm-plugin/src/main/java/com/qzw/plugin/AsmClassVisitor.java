package com.qzw.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @auther: qizewei
 * @date: 2019-08-28
 * @e-mail: qizewei@koolearn.com
 * @description：
 */
public class AsmClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    public AsmClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        // 此处可以根据 mClassName 过滤需要处理的类
        if ("onCreate".equals(name)) {
            //处理onCreate
            System.out.println("AsmClassVisitor : change method:" + name);
            return new AsmOnCreateMethodVistor(mv);
        } else if ("onClick".equals(name)) {
            System.out.println("AsmClassVisitor : change method:" + name);
            mv = new AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {
                // visitCode 方法插入的代码在 onMethodEnter之后
                @Override
                public void visitCode() {
                    super.visitCode();
                    mv.visitLdcInsn("AsmApplication");
                    mv.visitLdcInsn("insert 02");
                    mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                    mv.visitInsn(POP);
                }

                // visitCode 方法插入的代码在 onMethodExit之后
                @Override
                public void visitInsn(int opcode) {
                    if (opcode == Opcodes.RETURN) {
                        //方法执行后插入
                        mv.visitLdcInsn("AsmApplication");
                        mv.visitLdcInsn("insert 03");
                        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                        mv.visitInsn(POP);
                    }
                    super.visitInsn(opcode);
                }

                // 获取 onClick 的第一个参数，执行外部方法 isDoubleClick，获取方法返回值
                @Override
                protected void onMethodEnter() {
                    super.onMethodEnter();
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitMethodInsn(INVOKESTATIC, "com/example/asmapplication/Utils", "isDoubleClick", "(Landroid/view/View;)Z", false);
                    Label l1 = new Label();
                    mv.visitJumpInsn(IFEQ, l1);
                    Label l2 = new Label();
                    mv.visitLabel(l2);
                    mv.visitLineNumber(17, l2);
                    mv.visitInsn(RETURN);
                    mv.visitLabel(l1);
                    mv.visitLineNumber(19, l1);
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

                    mv.visitLdcInsn("AsmApplication");
                    mv.visitLdcInsn("insert 01");
                    mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                    mv.visitInsn(POP);
                }

                @Override
                protected void onMethodExit(int opcode) {
                    super.onMethodExit(opcode);
                    if (opcode == Opcodes.RETURN) {
                        //方法执行后插入
                        mv.visitLdcInsn("AsmApplication");
                        mv.visitLdcInsn("insert 04");
                        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                        mv.visitInsn(POP);
                    }
                }
            };
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
