import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class gen7{

    public static void main(String[] args){

        //Write class "program1"
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program7", null, "java/lang/Object",null);
        
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
            
            //WHILE LOOP
            
            //Variables for while loop
            mv.visitLdcInsn((int) 2);
            mv.visitVarInsn(Opcodes.ISTORE, 1);//changing variable
            mv.visitLdcInsn((int) 1);
            mv.visitVarInsn(Opcodes.ISTORE, 3);//incrementing variable - will keep adding 1 to var1
            mv.visitLdcInsn((int) 3141);
            mv.visitVarInsn(Opcodes.ISTORE, 5);//condition variable - var1=<var7 for loop to run
            
            //Create labels for start and end of loop
            Label start = new Label();
            Label end = new Label();

            //start of loop
            mv.visitLabel(start);

            //Load changing variable and condition variable and check if CV>VR (loop breaks then)
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 5);
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, end);

            //Print var1 inside loop
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            //Multiply var1*(var1+var3) and assign this new value to var1 inside loop
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE, 9);//temp variable=(var1+1)
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 9);
            mv.visitInsn(Opcodes.IMUL);
            mv.visitVarInsn(Opcodes.ISTORE, 1);//store new var1=var1*var9

            //jump to beginning to check if new number is ok
            mv.visitJumpInsn(Opcodes.GOTO, start);

            //end of loop if condition wasn't met
            mv.visitLabel(end);
            
            //Print ending
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Loop ended.");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            
            //END VISIT
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program7.class");
        
        System.out.println("Done!");
    }//end main
}//end class
