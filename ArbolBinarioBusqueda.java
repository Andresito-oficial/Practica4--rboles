package mazmorra;

public class ArbolBinarioBusqueda
{
	private NodoArbolBinarioBusqueda raiz;
	private int numElementos;

	public ArbolBinarioBusqueda ()
	{
		raiz = null;
		numElementos = 0;
	}

	public boolean vacio ()
	{
		return raiz == null;
	}

	public int getNumElementos ()
	{
		return numElementos;
	}

	public Sala getElemento ( int clave )
	{
		return this.getElementoRec ( raiz, clave );
	}

	private Sala getElementoRec ( NodoArbolBinarioBusqueda nodo, int clave )
	{
		Sala resultado = null;
		if ( nodo == null )
		{
			resultado = null;
		}
		else if ( nodo.getClave () == clave )
		{
			resultado = nodo.getDato ();
		}
		else if ( nodo.getClave () > clave )
		{
			resultado = this.getElementoRec ( nodo.getIzquierdo (), clave );
		}
		else
		{
			resultado = this.getElementoRec ( nodo.getDerecho (), clave );
		}
		return resultado;
	}

	public boolean contiene ( int clave )
	{
		return ( getElemento ( clave ) != null );
	}


	public boolean insertar ( int clave, Sala dato )
	{
		int previousNumElementos = numElementos;
		raiz = this.insertarRec ( raiz, clave, dato );
		return ( previousNumElementos < numElementos );
	}

	private NodoArbolBinarioBusqueda insertarRec ( NodoArbolBinarioBusqueda nodo, int clave, Sala dato )
	{
		if ( nodo == null )
		{     // Crear nuevo nodo
			nodo = new NodoArbolBinarioBusqueda ( clave, dato );
			numElementos++;
		}
		else if ( clave < nodo.getClave () )
		{    // Subárbol izquierdo
			NodoArbolBinarioBusqueda nuevoIzq = this.insertarRec ( nodo.getIzquierdo (), clave, dato );
			nodo.setIzquierdo ( nuevoIzq );
		}
		else if ( clave > nodo.getClave () )
		{    // Subárbol derecho
			NodoArbolBinarioBusqueda nuevoDer = this.insertarRec ( nodo.getDerecho (), clave, dato );
			nodo.setDerecho ( nuevoDer );
		}
		else
		{      // Clave repetida
			System.out.println ( "Error. La clave " + clave + " ya existe" );
		}
		return nodo;    // Devolver la nueva raíz del subárbol
	}


	public boolean borrar ( int clave )
	{
		int previousNumElementos = numElementos;
		raiz = this.borrarRec ( raiz, clave );
		return ( numElementos < previousNumElementos );
	}

	private NodoArbolBinarioBusqueda borrarRec ( NodoArbolBinarioBusqueda nodo, int clave )
	{
		if ( nodo == null )
		{
			System.out.println ( "la clave buscada no existe" );
		}
		else if ( nodo.getClave () > clave )
		{  // Subarbol izquierdo
			NodoArbolBinarioBusqueda nuevoIzq = this.borrarRec ( nodo.getIzquierdo (), clave );
			nodo.setIzquierdo ( nuevoIzq );
		}
		else if ( nodo.getClave () < clave )
		{  // Subarbol derecho
			NodoArbolBinarioBusqueda nuevoDer = this.borrarRec ( nodo.getDerecho (), clave );
			nodo.setDerecho ( nuevoDer );
		}
		else
		{  // Borrar elemento en nodo
			if ( nodo.getIzquierdo () == null && nodo.getDerecho () == null )
			{
				nodo = null;  // Caso 1
			}
			else if ( nodo.getDerecho () == null )
			{  // Caso 2
				nodo = nodo.getIzquierdo ();
			}
			else if ( nodo.getIzquierdo () == null )
			{  // Caso 2
				nodo = nodo.getDerecho ();
			}
			else
			{    // Caso 3
				NodoArbolBinarioBusqueda nuevoIzq = this.cambiarPorMenor ( nodo,
						nodo.getIzquierdo () );
				nodo.setIzquierdo ( nuevoIzq );
			}
			numElementos--;
		}
		return nodo;
	}

	private NodoArbolBinarioBusqueda cambiarPorMenor(NodoArbolBinarioBusqueda nodoBorrar, NodoArbolBinarioBusqueda nodoMenor) {
		if (nodoMenor.getDerecho() != null) {   // Subárbol derecho
			NodoArbolBinarioBusqueda nuevoDer = this.cambiarPorMenor(nodoBorrar, nodoMenor.getDerecho());
			nodoMenor.setDerecho(nuevoDer);
			return nodoMenor;
		} else {  // Encontrado nodo menor inmediato
			nodoBorrar.setClave(nodoMenor.getClave()); // Cambiar datos
			nodoBorrar.setDato(nodoMenor.getDato());
			return nodoMenor.getIzquierdo();
			// Devolver subarbol izquierdo de menor inmediato
		}
	}

	
	private NodoArbolBinarioBusqueda rotarDerecha(NodoArbolBinarioBusqueda nodo) {
		NodoArbolBinarioBusqueda res = null;
		if (nodo != null) {
			NodoArbolBinarioBusqueda temp = nodo.getIzquierdo();
			if (temp == null) res = nodo;
			else {
				nodo.setIzquierdo(temp.getDerecho());
				temp.setDerecho(nodo);
				res = temp;
			}
		}
		return res;
	}

	private NodoArbolBinarioBusqueda rotarIzquierda(NodoArbolBinarioBusqueda nodo) {
		NodoArbolBinarioBusqueda res = null;
		if (nodo != null) {
			NodoArbolBinarioBusqueda temp = nodo.getDerecho();
			if (temp == null) {
				res = nodo;
			} else {
				nodo.setDerecho(temp.getIzquierdo());
				temp.setIzquierdo(nodo);
				res = temp;
			}
		}
		return res;
	}

	private int altura(NodoArbolBinarioBusqueda nodo) {
		int res ;
		if (nodo == null) res = 0;
		else {
			int aIzq = this.altura(nodo.getIzquierdo());
			int aDech = this.altura(nodo.getDerecho());
			res = Math.max(aIzq, aDech) + 1;
		}
		return res;
	}

	/**
	 * TODO estudiantes
	 */
	public String toString() {
		return null;
	}

	/**
	 * TODO estudiantes
	 */
	public void mostrar2D() {
	}

	/**
	 * TODO estudiantes
	 */
	public ArbolBinarioBusqueda[] partir(int clave) {
		return null;
	}

}
