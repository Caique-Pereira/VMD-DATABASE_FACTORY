package vmd;

import java.io.RandomAccessFile;
import java.io.Serializable;


public class GeracaoVmd {
	
	private static class defConf implements Serializable {
		public int RegistroAtual;
		public int QtdeCampos;
		public int TamanhoTotal;
		public defCampos[] Campos = new defCampos[201];
		public String[] Indices = new String[10];
		public byte[] ChaveDuplicada = new byte[10];
	}
	
	private static class DefNavegacao {
		byte x;
		Integer reg;
		byte filho;
	}

	private static class defCampos implements Serializable {
		public String Nome;
		public int Inicio;
		public byte tipo;
		public byte Tamanho;
	}
	
	private final static String CAMINHODOARQUIVO = "C:\\VisualStore\\lj000001\\vmix\\dataisp\\linux\\prodtemp\\";
	private final static String ARQUIVO = "nomeDoArquivo";
	private final static String CAMINHOCOMPLETO = CAMINHODOARQUIVO + ARQUIVO + ".vmd";
	
	private static int EventoQtde = 0;
	private static double sizeLines = 0; 
	private static StringBuilder BufLinhas = new StringBuilder();
	
	private static String ArquivoAtual;
	private static String IndiceAtual;
	private static byte Operacao; // 1-Incluir 2-Editar
	private static StringBuilder buffer = new StringBuilder(10000);
	private static Long RegistroAtual = 0L;
	private static byte  NavIndex;
	private static byte NavIndice;
	private static DefNavegacao[] navegacao = new DefNavegacao[100]; 
	
	private static defConf config;


	
	
	public static void initialize() {
		 EventoQtde = 1000;
		 sizeLines = 1024.0 * 1024.0;
		 BufLinhas = preencheComEspacos(BufLinhas,sizeLines);
	}
	
	
	public static int TClose() {
		 byte x;
		 
		 ArquivoAtual = "";
		 IndiceAtual = "";
		 Operacao = 0;
		 buffer = preencheComEspacos(buffer, 1000.0);
		 RegistroAtual = 0L;
		 NavIndex = 0;
		 NavIndice =0;
		 
		 for (int i = 0; i < 100; i++) {
			    navegacao[i] = new DefNavegacao();
			    navegacao[i].reg = 0;
			    navegacao[i].x = 0;
			    navegacao[i].filho = 0;
			}
		 return 0;
	}
	
	public static StringBuilder preencheComEspacos(StringBuilder variavel,Double qntdDeEspacos) {
		for(int i = 0; i < qntdDeEspacos; i++) {
			variavel.append(' ');
		}	
		return variavel;
	}
	
	
	public static void Tinitialize() {
		 initialize();
		 TClose();
		 
		  byte x;
		  
		  config = new defConf();
		  
		  // Inicializar array de campos
		    for (int i = 0; i < 201; i++) {
		        config.Campos[i] = new defCampos();
		    }
	 
		  config.RegistroAtual = 0;  
		  config.Campos[0].Nome = "__Status__";
		  config.Campos[0].Inicio = 1;
		  config.Campos[0].tipo = 2;
		  config.Campos[0].Tamanho = 1;
		  
		  for (int i = 1; i <= 200; i++) {
		        config.Campos[i].Nome = String.format("%-50s", " ");  // 50 espaços
		        config.Campos[i].Inicio = 0;
		        config.Campos[i].tipo = 0;
		        config.Campos[i].Tamanho = 0;
		    }
		  
		  for (int i = 0; i < 10; i++) {
		        config.Indices[i] = String.format("%-150s", " ");  // 150 espaços
		        config.ChaveDuplicada[i] = 1;
		    }
		  
		   config.QtdeCampos = 0;
		    config.TamanhoTotal = 1;
		  
		    String TConfig = String.format("%-" + Integer.toString(config.TamanhoTotal) + "s", ".");
		  
	}
	
	public static int TFields_New(String nomeCampo, byte tipo, byte tamanho) {
		TClose();

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
		config.Campos[config.QtdeCampos].Nome = nomeCampo.toUpperCase();
		config.Campos[config.QtdeCampos].Inicio = config.TamanhoTotal + 1;
		config.Campos[config.QtdeCampos].tipo = tipo;
		config.Campos[config.QtdeCampos].Tamanho = tamanho;
	    config.TamanhoTotal += tamanho;

	    double maxLinhas = (Math.floor(sizeLines / config.TamanhoTotal) - 1) * config.TamanhoTotal;
	    
	    return 0;
		
	}
	
	public static int TOpenNew() {
		TClose();
		
		 byte x;
		 Integer Arq;
		
		return 0;
		
	}
	
	public static void main(String args[]) {

		Tinitialize();
		TFields_New("ID",Byte.valueOf("4"),Byte.valueOf("4"));
		TFields_New("DATA",Byte.valueOf("8"),Byte.valueOf("8"));
		TFields_New("HORA",Byte.valueOf("8"),Byte.valueOf("8"));
		TFields_New("TAMANHO",Byte.valueOf("4"),Byte.valueOf("4"));
		TFields_New("ORIGEM",Byte.valueOf("10"),Byte.valueOf("6"));
		TFields_New("DATAVENDA",Byte.valueOf("8"),Byte.valueOf("8"));
		
		
		try (RandomAccessFile file = new RandomAccessFile(CAMINHOCOMPLETO, "rw")) {
			defConf config = new defConf();


			config.RegistroAtual = 0;
			config.QtdeCampos = 0;
			config.TamanhoTotal = 0;

			for (int i = 0; i < 201; i++) {
				defCampos campo = new defCampos();
				if (i == 0) {
					campo.Nome = "__Status__";
					campo.tipo = 2;
					campo.Tamanho = 1;
				} else if (i == 1) {
					campo.Nome = "ID";
					campo.tipo = 4;
					campo.Tamanho = 4;
				} else if (i == 2) {
					campo.Nome = "DATA";
					campo.tipo = 8;
					campo.Tamanho = 8;
				} else if (i == 3) {
					campo.Nome = "HORA";
					campo.tipo = 8;
					campo.Tamanho = 8;
				} else if (i == 4) {
					campo.Nome = "TAMANHO";
					campo.tipo = 4;
					campo.Tamanho = 4;
				} else if (i == 5) {
					campo.Nome = "ORIGEM";
					campo.tipo = 10;
					campo.Tamanho = 6;
				} else if (i == 6) {
					campo.Nome = "DATAVENDA";
					campo.tipo = 8;
					campo.Tamanho = 8;
				} else {
					campo.Nome = padRight("", 50);
					campo.tipo = 0;
					campo.Tamanho = 0;
					campo.Inicio = 0;
					config.Campos[i] = campo;
					continue;
				}
				campo.Inicio = config.TamanhoTotal + 1;
				config.Campos[i] = campo;
				config.QtdeCampos++;
				config.TamanhoTotal = config.TamanhoTotal + campo.Tamanho;
			}

			for (int i = 1; i < 11; i++) {
				config.Indices[i] = padRight("", 150);
			}

			for (int i = 1; i < 11; i++) {
				config.ChaveDuplicada[i] = 1;
			}

			// Escrever dados no arquivo
			file.writeInt(config.RegistroAtual);
			file.writeByte(config.QtdeCampos - 1);
			file.writeShort(config.TamanhoTotal);

			for (int i = 0; i < 201; i++) {
				defCampos campo = config.Campos[i];
				file.writeBytes(padRight(campo.Nome, 50));
				file.writeShort(campo.Inicio);
				file.writeByte(campo.tipo);
				file.writeByte(campo.Tamanho);
			}

			for (int i = 1; i < 11; i++) {
				file.writeBytes(padRight(config.Indices[i], 150));
			}

			file.write(config.ChaveDuplicada);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String padRight(String input, int length) {
		StringBuilder sb = new StringBuilder(input);
		while (sb.length() < length) {
			sb.append(" ");
		}
		return sb.toString();
	}

}
