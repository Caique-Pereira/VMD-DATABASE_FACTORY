package vmd;

import java.io.RandomAccessFile;

public class Test {
	
    private static class defConf {
        public int RegistroAtual;
        public int QtdeCampos;
        public int TamanhoTotal;
        public defCampos[] Campos = new defCampos[201];
        public String[] Indices = new String[11];
        public byte[] ChaveDuplicada = new byte[11];
    }

    private static class defCampos {
        public String Nome;
        public int Inicio;
        public byte tipo;
        public byte Tamanho;
    }

    public static void main(String[] args) {
        String diretorio = "C:\\Users\\User\\Desktop\\diferencia\\";
        String arquivo = "nomeDoArquivo";
        String caminhoCompleto = diretorio + arquivo + ".vmd";

        try (RandomAccessFile file = new RandomAccessFile(caminhoCompleto, "rw")) {
            defConf config = new defConf();

            // Preencha os campos do objeto 'config' conforme necessário
            config.RegistroAtual = 0;
            config.QtdeCampos = 0;
            config.TamanhoTotal = 0;

            for (int i = 0; i < 201; i++) {
                defCampos campo = new defCampos();
                if(i == 0) {
                	campo.Nome = "__Status__";
                	campo.tipo = 2;
                	campo.Tamanho = 1;
                }else if(i == 1) {
                	campo.Nome = "ID";
                	campo.tipo = 4;
                	campo.Tamanho = 4;
                }else if(i == 2) {
                	campo.Nome = "DATA";
                	campo.tipo = 8;
                	campo.Tamanho = 8;
                }else if(i == 3) {
                	campo.Nome = "HORA";
                	campo.tipo = 8;
                	campo.Tamanho = 8;
                }else if(i == 4) {
                	campo.Nome = "TAMANHO";
                	campo.tipo = 4;
                	campo.Tamanho = 4;
                }else if(i == 5) {
                	campo.Nome = "ORIGEM";
                	campo.tipo = 10;
                	campo.Tamanho = 6;
                }else if(i == 6) {
                	campo.Nome = "DATAVENDA";
                	campo.tipo = 8;
                	campo.Tamanho = 8;
                }
                else {
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