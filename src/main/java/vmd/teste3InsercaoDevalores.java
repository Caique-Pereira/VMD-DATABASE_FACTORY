package vmd;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class teste3InsercaoDevalores {
	
	public static void main(String[] args) throws IOException {
		
		StringBuilder fixedLengthString = preencherTipoStringValor("", 10000);
		ByteBuffer buffer = ByteBuffer.allocate(22);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		
        BigDecimal dinheiro = new BigDecimal(1);

		for (int i = 0; i < buffer.capacity(); i++) {
		    buffer.put((byte) ' ');
		}
		buffer.rewind();  // Para retornar a posição do buffer ao início
		
		buffer.putDouble(1, 1);
		buffer.put(9,(byte)1);
		buffer.put(10,(byte)1);
		buffer.putLong(11,(1L * 10000));
		buffer.putShort(19,  (short) (false ? 1 : 0));
		buffer.put(21, (byte)1);
		buffer.put(0, (byte)1);
		
	
		
		Processador2 processador2 = new Processador2(buffer);
		processador2.gerarData();
		
	}
	
	
   public static StringBuilder preencherTipoStringValor(String str,int tamanho) {
		
		StringBuilder ab = new StringBuilder(tamanho);
		ab.append(str, 0, str.length());

		for (int i = str.length(); i < ab.capacity(); i++) {
			ab.append(" ");
		}

		return ab;
	}

}
