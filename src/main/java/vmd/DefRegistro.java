package vmd;

public class DefRegistro {
	public DefCodigos[] codigos = new DefCodigos[100];

	public DefRegistro() {
		for (int i = 0; i < this.codigos.length; i++) {
			this.codigos[i] = new DefCodigos();
		}
	}
}
