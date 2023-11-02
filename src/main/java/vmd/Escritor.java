package vmd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Escritor {
	
	private FileOutputStream fos;
	private  ByteBuffer buffer;
	
	public Escritor(FileOutputStream fos) {
		this.fos = fos;
	}
	
	public Escritor(FileOutputStream fos,ByteBuffer buffer) {
		this.fos = fos;
		this.buffer = buffer;
	}
	
	public ByteBuffer getBuffer() {
		return this.buffer;
	}
	
	public void setBuffer(ByteBuffer buffer) {
	   this.buffer = buffer;
	}
	
	public void SetarBufferPosicao(int posicao) {
		buffer.position(posicao);
	}
	
	public void escreverBufferFixoInteger(short a) throws IOException {
		buffer.putShort(a);
	}
	
	
	
	public void escreverInteger(short a) throws IOException {
	// Criar um ByteBuffer com espaço para um short (2 bytes)
     ByteBuffer buffer = ByteBuffer.allocate(2);
     buffer.order(ByteOrder.LITTLE_ENDIAN);
     buffer.putShort(a);
     fos.write(buffer.array());
	}
	
	public void escreverLong(Integer a) throws IOException {
		// Criar um ByteBuffer com espaço para um short (2 bytes)
	     ByteBuffer buffer = ByteBuffer.allocate(4);
	     buffer.order(ByteOrder.LITTLE_ENDIAN);
	     buffer.putInt(a);
	     fos.write(buffer.array());
		}
	
	
	public void escreverByte(Byte a) throws IOException {
		// Criar um ByteBuffer com espaço para um short (2 bytes)
	     ByteBuffer buffer = ByteBuffer.allocate(1);
	     buffer.order(ByteOrder.LITTLE_ENDIAN);
	     buffer.put(a);
	     fos.write(buffer.array());
		}
	
	public void escreverBytes(byte[] a) throws IOException {
		// Criar um ByteBuffer com espaço para um short (2 bytes)
	     ByteBuffer buffer = ByteBuffer.allocate(a.length);
	     buffer.order(ByteOrder.LITTLE_ENDIAN);
	     buffer.put(a);
	     fos.write(buffer.array());
		}
	
	
	  
	
	
	public void escreverByteVazio() throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1);  // Aloca 1 byte
		buffer.order(ByteOrder.LITTLE_ENDIAN);
	    buffer.put((byte) 0);  // Coloca um byte vazio (0x00)
	    fos.write(buffer.array()); 
		}
	
	public void escreverString(String str) throws IOException {
		byte[] bytes = new byte[str.length()];
		for (int i = 0; i < str.length(); i++) {
		    char letra = str.charAt(i);
		    bytes[i] = letra == '.' ? (byte) 0 : (byte) letra;
		}
		
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
	    buffer.put(bytes);  // Coloca os bytes da string no buffer
	    fos.write(buffer.array());  // Escreve o array de bytes no FileOutputStream
	}

		



}
