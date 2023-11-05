package vmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

public class vi1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Substitua pelo caminho onde deseja criar o arquivo MDB
		
		ByteBuffer bufferDados = ByteBuffer.allocate(3100);
		ProcessadorVmi a = new ProcessadorVmi();
		a.gerarData(bufferDados);

		File file = new File("c:\\visualstore\\lj000001\\vmix\\Dataisp\\Linux\\prodtemp\\java.mdb");

		try {
			// Cria o banco de dados
			Database db = new DatabaseBuilder(file).setFileFormat(Database.FileFormat.V2000).create();

			// Cria a tabela "Codigos"
			Table newTable = new TableBuilder("Codigos").addColumn(new ColumnBuilder("Codigo1", DataType.DOUBLE))
					.addColumn(new ColumnBuilder("Codigo2", DataType.DOUBLE))
					.addColumn(new ColumnBuilder("Codigo3", DataType.DOUBLE))
					.addColumn(new ColumnBuilder("Registro", DataType.LONG)).toTable(db);

			// Insira dados de exemplo na nova tabela
			newTable.addRow(1, 1, 1, 1); // Adiciona uma linha com dados de exemplo

		
			
			boolean ChaveDuplicada = true;
			int reg = 0;
			int campo1 = 1;
			int campo2 = 2;
			int campo3 = 3;
			
			StringBuilder tabela = preencherTipoStringValor("",38);
			int lenconf = 	12371;
			
			DefRegistro[] niveis = new DefRegistro[3];
			
			for(int i = 0; i <= 2; i++) {
				niveis[i] = new DefRegistro();
				niveis[i].codigos[0].codigo = -999999999999999.0;
				niveis[i].codigos[0].codigo1 = -999999999999999.0;
				niveis[i].codigos[0].codigo2 = -999999999999999.0;
				niveis[i].codigos[0].filho = 0;
				niveis[i].codigos[0].registro = 0;
				niveis[i].codigos[0].usado = 1; 
			}
			
		
			System.out.println("Banco de dados MDB criado com sucesso em: " + file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
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