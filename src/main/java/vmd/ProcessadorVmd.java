package vmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ProcessadorVmd {

	private defconf config;
	private Escritor escritor;
	File file;
	
	public ProcessadorVmd(defconf a) {
	   this.config = a;
		
	}
	
	public void gerarData(ByteBuffer bufferDados) throws FileNotFoundException, IOException {
		
		
		try (FileOutputStream fos = new FileOutputStream(file = new File("C:\\VisualStore\\lj000001\\vmix\\dataisp\\linux\\prodtemp\\java.vmd"))) {
			escritor = new Escritor(fos);
			
			escritor.escreverLong(config.RegistroAtual);
			escritor.escreverByte(config.QtdeCampos);
			escritor.escreverInteger(config.TamanhoTotal);
			
		for(int i = 0; i < 201 ; i++) {
				escritor.escreverString(config.Campos[i].Nome.toString());
				escritor.escreverInteger(config.Campos[i].Inicio);
				escritor.escreverByte(config.Campos[i].tipo);
				escritor.escreverByte(config.Campos[i].Tamanho);
			}
			
		   for (int i = 0; i <= 9; i++) {
			 escritor.escreverString(config.Indices[i].toString());
		    }   
		   
		   for (int i = 0; i <= 9; i++) {
			   escritor.escreverByte(config.ChaveDuplicada[i]);
			   }
		   
		   //config sempre tem que ter o tamanho de 12371, depois desse valor vem os dados
		   
		   escritor.escreverBytes(bufferDados.array());
		   
		   
		   
		   
		   
		   
		   
		   fos.close();
		   System.out.print(file.length());
		}
		
	}
	
public StringBuilder preencherTipoString(StringBuilder str) {
		
		for (int i = 0; i < str.capacity(); i++) {
			str.append(".");
		}
		
		return str;
	
	}

}
