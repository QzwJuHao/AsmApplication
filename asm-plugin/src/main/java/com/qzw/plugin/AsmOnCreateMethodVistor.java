package com.qzw.plugin;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.POP;

/**
 * @auther: qizewei
 * @date: 2019-08-28
 * @e-mail: qizewei@koolearn.com
 * @description：
 */
public class AsmOnCreateMethodVistor extends MethodVisitor {

    public AsmOnCreateMethodVistor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        //方法执行前插入
        mv.visitLdcInsn("AsmApplication");
        mv.visitLdcInsn("insert Start");
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(POP);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN) {
        //方法执行后插入
            mv.visitLdcInsn("AsmApplication");
            mv.visitLdcInsn("insert Finish");
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);
        }
        super.visitInsn(opcode);
    }
}
