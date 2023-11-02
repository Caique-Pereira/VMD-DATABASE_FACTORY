package vmd;

import java.io.Serializable;

public class defconf  implements Serializable {
	
	public Integer RegistroAtual =0;
	public byte QtdeCampos = 0;
	public short TamanhoTotal = 0;
	public defCampos[] Campos = new defCampos[201];
	public StringBuilder[] Indices = new StringBuilder[10];
	public byte[] ChaveDuplicada = new byte[10];

}
