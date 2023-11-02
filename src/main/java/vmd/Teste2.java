package vmd;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class Teste2 {

	
	public static void main(String[] args) throws IOException {
		
		//StringBuilder Buffer =preencherTipoStringValor(" ",10000);
		ByteBuffer buffer = ByteBuffer.allocate(38);
		buffer.order(ByteOrder.LITTLE_ENDIAN);

			StringBuilder fixedLengthString = new StringBuilder(50);

			// Preenche com espaços em branco
			// for (int i = 0; i < 50; i++) {
			// fixedLengthString.append(" ");
			// }

			// Substitui os espaços em branco por "."
			// for (int i = 0; i < fixedLengthString.length(); i++) {
			// if (fixedLengthString.charAt(i) == ' ') {
			// fixedLengthString.setCharAt(i, '.');
			// }
			// }

			// Converta para String quando necessário
			// String output = preencherTipoString(fixedLengthString);

//			escritor.escreverLong(394);
//			escritor.escreverByte((byte)32);
//			escritor.escreverInteger((short)98);
//			escritor.escreverString(output);

			// Segundo testes
			// defConf config = new defConf();

			// for (int i = 0; i <= 9; i++) {
			// config.Indices[i] = preencherTipoString(new StringBuilder(50));
			// }

			// for (int i = 0; i <= 9; i++) {
			// escritor.escreverString(config.Indices[i].toString());
			// }

			// terceito testes

			defconf config = new defconf();

			//inicializa os objetos campos dentro do objeto config e atribui valores padrões
			//é necessario setar valores padrões para evitar inconsistencias
			initialize(config);
			
			
			//initialize do vb
			config.RegistroAtual = 0;
			config.Campos[0].Nome =  preencherTipoStringValor("__Status__",50);
			config.Campos[0].Inicio = 1;
			config.Campos[0].tipo = 2 ;
			config.Campos[0].Tamanho = 1;
			
			
			for (int i = 1; i < 201; i++) {
				config.Campos[i].Nome = preencherTipoStringValor(" ", 50);
			}
			
			for (int i = 0; i <= 9; i++) {
				config.Indices[i] = preencherTipoStringValor(" ",150);
				config.ChaveDuplicada[i] = 1;
			  }
			
			config.QtdeCampos =0;
			config.TamanhoTotal = 1;
			
			// a parte a cima deu tudo certo
			
			//TFields_New do vb
			TFields_New(config,"PRODUTO_ID",Byte.valueOf("7"),Byte.valueOf("8"));
			TFields_New(config,"SEQUENCIA",Byte.valueOf("2"),Byte.valueOf("1"));
			TFields_New(config,"TABELA",Byte.valueOf("2"),Byte.valueOf("1"));
			TFields_New(config,"PRECO",Byte.valueOf("5"),Byte.valueOf("8"));
			TFields_New(config,"ALTERADO",Byte.valueOf("1"),Byte.valueOf("1"));
			TFields_New(config,"TIPO_PRECO",Byte.valueOf("2"),Byte.valueOf("1"));
			//TFields_New(config,"DATA_INICIAL",Byte.valueOf("8"),Byte.valueOf("8"));
			//TFields_New(config,"DATA_FINAL",Byte.valueOf("8"),Byte.valueOf("8"));
			
			
			//inserir dados das tabelas 
			//TFields_SetNew(buffer,config,1, 1.0);
			//TFields_SetNew(buffer,config,2,  (byte) 1);
			//TFields_SetNew(buffer,config,3,  (byte)1);
			//TFields_SetNew(buffer,config,4,(double) 1);
			//TFields_SetNew(buffer,config,5,  false);
			//TFields_SetNew(buffer,config,6, (byte)1);
			//TFields_SetNew(buffer,config,0,  (byte)1);
			
			
			
			
			ProcessadorVmd processadorVmd = new ProcessadorVmd(config);
			processadorVmd.gerarData();
			//Processador2 processador2 = new Processador2(config,buffer);
			//processador2.gerarData();		
			
			long tamanho = 12371 + 1;
			
			
	}
	
	public static void TFields_SetNew(ByteBuffer buffer,defconf conf, int indice, Object value) {
		
		switch (conf.Campos[indice].tipo) {
        case 1:  // Logico
            byte logico = (byte) ((boolean) value ? 1 : 0);
            buffer.position(conf.Campos[indice].Inicio);
            buffer.putShort(logico);
            break;
        case 2:  // Baite
            byte baite = (byte) value;
            buffer.position(conf.Campos[indice].Inicio == 1 ?  (byte) 0 : conf.Campos[indice].Inicio);
            buffer.put(baite);
            break;
        case 3:  // Inteiro
            int inteiro = (int) value;
           buffer.position(conf.Campos[indice].Inicio);;
            buffer.putShort((short) inteiro);
            break;
        case 4:  // Longo
            long longo = (long) value;
            buffer.position(conf.Campos[indice].Inicio);
            buffer.putInt((int) longo);
            break;
        case 5:  // Dinheiro
            double dinheiro = (double) value;
            buffer.position(conf.Campos[indice].Inicio);
            buffer.putDouble(dinheiro);
            break;
        case 6:  // Simples
            float simples = (float) value;
            buffer.position(conf.Campos[indice].Inicio);
            buffer.putFloat(simples);
            break;
        case 7:  // Duplo
            double duplo = (double) value;
            buffer.position(conf.Campos[indice].Inicio);
            buffer.putDouble(duplo);
            break;
        case 8:  // Data
            // Implementação depende do tipo 'Data' e como você quer lidar com ele.
            break;
        case 10:  // Texto
            String texto = (String) value;
            byte[] textoBytes = texto.getBytes(StandardCharsets.UTF_8);
            buffer.position(conf.Campos[indice].Inicio);
            buffer.put(textoBytes, 0, conf.Campos[indice].Tamanho);
            break;
        default:
            throw new IllegalArgumentException("Tipo desconhecido: " + conf.Campos[indice].tipo);
    }
		
	}
	
	public static int TFields_New(defconf config,String nomeCampo, byte tipo, byte tamanho) {
		
		switch (tipo) {
		case 1: // Logico
			tamanho = 2;
			break;
		case 2: // Baite
			tamanho = 1;
			break;
		case 3: // Inteiro
			tamanho = 2;
			break;
		case 4: // Longo
			tamanho = 4;
			break;
		case 5: // Dinheiro
			tamanho = 8;
			break;
		case 6: // Simples
			tamanho = 4;
			break;
		case 7: // Duplo
			tamanho = 8;
			break;
		case 8: // Data
			tamanho = 8;
			break;
		case 10: // Texto
			tamanho = tamanho;
			break;
		default:
		}
		
		config.QtdeCampos++;
		config.Campos[config.QtdeCampos].Nome = preencherTipoStringValor(nomeCampo.toUpperCase(),50) ;
		config.Campos[config.QtdeCampos].Inicio = (short) (config.TamanhoTotal + 1);
		config.Campos[config.QtdeCampos].tipo = tipo;
		config.Campos[config.QtdeCampos].Tamanho = tamanho;
	    config.TamanhoTotal += tamanho;
	   
	    return 0;
		
	}
	
	
	public static void initialize(defconf config) {
		for (int i = 0; i < 201; i++) {
			config.Campos[i] = new defCampos();
			config.Campos[i].Nome = preencherTipoStringValorPadrao(new StringBuilder(50));
		}
		
		for (int i = 0; i <= 9; i++) {
			config.Indices[i] = preencherTipoStringValorPadrao(new StringBuilder(150));
		  }
		
		  for (int i = 0; i <= 9; i++) {
			config.ChaveDuplicada[i] = 0;
	     }
	}

	public static StringBuilder preencherTipoStringValorPadrao(StringBuilder str) {

		for (int i = 0; i < str.capacity(); i++) {
			str.append(".");
		}

		return str;

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
