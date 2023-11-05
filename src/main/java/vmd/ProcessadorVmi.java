package vmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ProcessadorVmi {
	private Escritor escritor;
	File file;
	
	
	public void gerarData(ByteBuffer bufferDados) throws FileNotFoundException, IOException {
		
		try (FileOutputStream fos = new FileOutputStream(file = new File("C:\\VisualStore\\lj000001\\vmix\\dataisp\\linux\\prodtemp\\java.vi1"))) {
			escritor = new Escritor(fos);
			
			bufferDados.order(ByteOrder.LITTLE_ENDIAN);
			bufferDados.putDouble(-999999999999999.0);
			bufferDados.putDouble(-999999999999999.0);
			bufferDados.putDouble(-999999999999999.0);
			bufferDados.putShort((short)0);
			bufferDados.putInt(0);
			bufferDados.put((byte)1);
			//31 bytes
			
			bufferDados.putDouble(1.0);
			bufferDados.putDouble(1.0);
			bufferDados.putDouble(1.0);
			bufferDados.putShort((short)0);
			bufferDados.putInt(1);
			bufferDados.put((byte)1);
			
			for(int i = 0 ; i <= 97 ; i++) {
				bufferDados.putDouble(0);
				bufferDados.putDouble(0);
				bufferDados.putDouble(0);
				bufferDados.putShort((short)0);
				bufferDados.putInt(0);
				bufferDados.put((byte)0);
			}
			
			
			
			
			
		
			fos.write(bufferDados.array());
		}
		
	}
}

