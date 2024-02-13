import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class gen4{

    public static void main(String[] args){

        //Write class "program1"
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program4", null, "java/lang/Object",null);
        
        //Constructor
        {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}

        //Main method
        {
            //VISIT METHOD
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            //INT COMP
            
            //Push 2 numbers onto stack, store and load them
            mv.visitLdcInsn((int) 4);
            mv.visitVarInsn(Opcodes.ISTORE,1);
            mv.visitLdcInsn((int) 7);
            mv.visitVarInsn(Opcodes.ISTORE,3);
            mv.visitVarInsn(Opcodes.ILOAD,1);
            mv.visitVarInsn(Opcodes.ILOAD, 3);

            //Create labels for jumps
            Label intComp = new Label();
            Label intEnd = new Label();

            //Compare the 2 ints
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, intComp);//jumps to intComp if 1st int greater than 2nd

            //if 2nd int>1st int, print following, and jump to intEnd
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, intEnd);

            //if 1st int>2nd int, jumps here to intComp and prints following
            mv.visitLabel(intComp);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitLabel(intEnd);

            //LONG COMP
            
            //Push 2 numbers onto stack, store and load them
            mv.visitLdcInsn((long) 14);
            mv.visitVarInsn(Opcodes.LSTORE,5);
            mv.visitLdcInsn((long) 13);
            mv.visitVarInsn(Opcodes.LSTORE,7);
            mv.visitVarInsn(Opcodes.LLOAD,5);
            mv.visitVarInsn(Opcodes.LLOAD, 7);

            //Create labels for jumps
            Label longComp = new Label();
            Label longEnd = new Label();

            //Compare 2 longs and pushes result onto stack
            mv.visitInsn(Opcodes.LCMP);

            //If 2nd long>1st long, print following, and jump to longEnd
            mv.visitJumpInsn(Opcodes.IFGE, longComp);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 7);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, longEnd);

            //If 1st long>2nd long, jump here to longComp and print following
            mv.visitLabel(longComp);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);

            mv.visitLabel(longEnd);

            //SHORT COMP
            //Push 2 shorts onto stack, store and load them as ints
            mv.visitIntInsn(Opcodes.SIPUSH, 1);
            mv.visitVarInsn(Opcodes.ISTORE,9);
            mv.visitIntInsn(Opcodes.SIPUSH, 10);
            mv.visitVarInsn(Opcodes.ISTORE,11);
            mv.visitVarInsn(Opcodes.ILOAD,9);
            mv.visitVarInsn(Opcodes.ILOAD, 11);

            //Create labels for jumps
            Label shortComp = new Label();
            Label shortEnd = new Label();

            //Compare the 2 shorts as ints
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, shortComp);//jumps to shortComp if 1st short greater than 2nd

            //if 2nd short>1st short, print following, and jump to shortEnd
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 11);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, shortEnd);

            //if 1st int>2nd int, jumps here to intComp and prints following
            mv.visitLabel(shortComp);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 9);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitLabel(shortEnd);
            
            //END VISIT
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program4.class");
        
        System.out.println("Done!");
    }//end main
}//end class
