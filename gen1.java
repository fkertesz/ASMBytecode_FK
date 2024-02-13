import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

public class gen1{

    public static void main(String[] args){

        //Write class "program1"
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program1", null, "java/lang/Object",null);
        
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

            //DOUBLE MUL

            //Push 2 numbers onto stack and store them
            mv.visitLdcInsn((Double) 1.3);
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitLdcInsn((Double) 50.2);
            mv.visitVarInsn(Opcodes.DSTORE, 3);

            //Load and multiply numbers
            mv.visitVarInsn(Opcodes.DLOAD, 1);
            mv.visitVarInsn(Opcodes.DLOAD, 3);
            mv.visitInsn(Opcodes.DMUL);

            //Store result
            mv.visitVarInsn(Opcodes.DSTORE, 5);

            //Load and print result
        
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

            //FLOAT MUL
            
            //Push 2 numbers onto stack and store them
            mv.visitLdcInsn((float) 3.14);
            mv.visitVarInsn(Opcodes.FSTORE,13);
            mv.visitLdcInsn((float) 2.5);
            mv.visitVarInsn(Opcodes.FSTORE, 15);

            //Load and multiply numbers
            mv.visitVarInsn(Opcodes.FLOAD, 13);
            mv.visitVarInsn(Opcodes.FLOAD, 15);
            mv.visitInsn(Opcodes.FMUL);

            //Store result
            mv.visitVarInsn(Opcodes.FSTORE, 17);

            //Load and print result
        
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.FLOAD, 17);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);
            
            //INT MUL
            
            //Push 2 numbers onto stack and store them
            mv.visitLdcInsn((int) 4);
            mv.visitVarInsn(Opcodes.ISTORE,7);
            mv.visitLdcInsn((int) 7);
            mv.visitVarInsn(Opcodes.ISTORE,9);

            //Load and multiply numbers
            mv.visitVarInsn(Opcodes.ILOAD,7);
            mv.visitVarInsn(Opcodes.ILOAD, 9);
            mv.visitInsn(Opcodes.IMUL);

            //Store result
            mv.visitVarInsn(Opcodes.ISTORE, 11);

            //Load and print result
        
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 11);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            //LONG MUL
            
            //Push 2 numbers onto stack and store them
            mv.visitLdcInsn((long) 13);
            mv.visitVarInsn(Opcodes.LSTORE,19);
            mv.visitLdcInsn((long) 14);
            mv.visitVarInsn(Opcodes.LSTORE,21);

            //Load and multiply numbers
            mv.visitVarInsn(Opcodes.LLOAD,19);
            mv.visitVarInsn(Opcodes.LLOAD, 21);
            mv.visitInsn(Opcodes.LMUL);

            //Store result
            mv.visitVarInsn(Opcodes.LSTORE, 23);

            //Load and print result
        
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.LLOAD, 23);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            
            //END VISIT
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program1.class");
        
        System.out.println("Done!");
    }//end main
}//end class
