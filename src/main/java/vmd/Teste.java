package vmd;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Teste {
	 public static void main(String[] args) throws IOException {
		 
		 
		 // TABELA DE IGUALDADE
		 
		 // VISUAL BASIC 6    |    JAVA
		 // LONG              |    Integer
		 //
		 
		 
		 //2 bytes - short 
		 
		   ByteBuffer buffer = ByteBuffer.allocate(10000);
		   buffer.order(ByteOrder.LITTLE_ENDIAN);
		   byte[] a = "2".getBytes();
		   buffer.put(1, (byte)1 );
		   
		  System.out.println(buffer.array());
		   
		   
	 }
	 
}
