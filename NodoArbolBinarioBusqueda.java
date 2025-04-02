package mazmorra;

public class NodoArbolBinarioBusqueda
{

	private int clave;
	private Sala dato;
	private NodoArbolBinarioBusqueda izquierdo, derecho;

	public NodoArbolBinarioBusqueda ( int clave, Sala dato )
	{
		this.clave = clave;
		this.dato = dato;
		this.izquierdo = null;
		this.derecho = null;
	}

	public Sala getDato () { return dato; }

	public void setDato ( Sala dato ) { this.dato = dato; }

	public int getClave () {return clave; }

	public void setClave ( int clave ) { this.clave = clave; }

	public NodoArbolBinarioBusqueda getIzquierdo () { return izquierdo; }

	public void setIzquierdo ( NodoArbolBinarioBusqueda izquierdo ) { this.izquierdo = izquierdo; }

	public NodoArbolBinarioBusqueda getDerecho () { return derecho; }

	public void setDerecho ( NodoArbolBinarioBusqueda derecho ) { this.derecho = derecho;}

}