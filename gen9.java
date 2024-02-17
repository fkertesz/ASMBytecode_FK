/**
 * Generates program9 class that gets integer input with scanner, from the user, runs a loop
 * that adds that number to an accumulator, and then prints the result.
 * @author Fanni Kertesz
 * CS322 Assignment3 gen9
 */
import static utils.Utilities.writeFile;
import org.objectweb.asm.*;

public class gen9{

    public static void main(String[] args){

        //Write class "program9"
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"program9", null, "java/lang/Object",null);
        
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
            
            //Create new scanner with System.in input
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
            mv.visitInsn(Opcodes.DUP);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");//static field from system.in
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);//Scanner constructor with system.in argument
            mv.visitVarInsn(Opcodes.ASTORE, 1);

            //Declare accumulator variable with value 0 and a constant 0 variable
            mv.visitLdcInsn((int) 0);
            mv.visitVarInsn(Opcodes.ISTORE, 3);
            mv.visitLdcInsn((int) 0);
            mv.visitVarInsn(Opcodes.ISTORE, 7);

            //Labels for start and end of loop
            Label start = new Label();
            Label end = new Label();

            //Visit start of loop
            mv.visitLabel(start);

            //Print out prompt and store input
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("What number would you like to add? Type 0 to end.");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/util/Scanner", "nextInt", "()I", false);
            mv.visitVarInsn(Opcodes.ISTORE, 5);

            //Load input variable and 0 and ends loop if they're equal
            mv.visitVarInsn(Opcodes.ILOAD, 5);
            mv.visitVarInsn(Opcodes.ILOAD, 7);
            mv.visitJumpInsn(Opcodes.IF_ICMPEQ, end);

            //Loads accumulator and input, adds them, stores new value, and restarts loop if input isn't 0
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitVarInsn(Opcodes.ILOAD, 5);
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE, 3);
            mv.visitJumpInsn(Opcodes.GOTO, start);

            //End of loop
            mv.visitLabel(end);

            //Prints result
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            //END VISIT
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"program9.class");
        
        System.out.println("Done!");
    }//end main
}//end class
