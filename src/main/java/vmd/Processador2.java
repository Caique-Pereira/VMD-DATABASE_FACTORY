package vmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Processador2 {
	private defconf config;
	private Escritor escritor;
	private ByteBuffer buffer;
	private StringBuilder strBuilder;
	File file;
	
	public Processador2(defconf a, ByteBuffer buffer) {
	   this.config = a;
	   this.buffer = buffer;
		
	}
	
	public Processador2(ByteBuffer buffer) {
		   this.buffer = buffer;
			
		}
	
	public Processador2(StringBuilder strBuilder) {
		   this.strBuilder = strBuilder;
		}
	
	public void gerarData() throws FileNotFoundException, IOException {
		try (FileOutputStream fos = new FileOutputStream(file = new File("C:\\VisualStore\\lj000001\\vmix\\dataisp\\linux\\prodtemp\\dados.vmd"))) {
			escritor = new Escritor(fos);
			
	
			escritor.escreverBytes(buffer.array());
		   
		   fos.close();
		   System.out.print(file.length());
		}
		
	}


}
