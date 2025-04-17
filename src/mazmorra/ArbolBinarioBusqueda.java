package mazmorra;

import java.util.ArrayDeque;

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
		final int previousNumElementos = numElementos;
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
		return reequilibrar ( nodo );    // Devolver la nueva raíz del subárbol
	}

	private NodoArbolBinarioBusqueda reequilibrar ( NodoArbolBinarioBusqueda nodo )
	{
		final NodoArbolBinarioBusqueda nodoIzq = nodo.getIzquierdo();
		final NodoArbolBinarioBusqueda nodoDer = nodo.getDerecho();
		int aIzq = NodoArbolBinarioBusqueda.getAltura( nodoIzq );
		final int aDer = NodoArbolBinarioBusqueda.getAltura( nodoDer );
		
		if ( aIzq > aDer + 1 )
		{  // Rotar derecha
			nodo = rotarDerecha( nodo );
		}
		else if ( aDer > aIzq + 1 || nodoIzq == null && nodoDer != null )
		{  // Rotar izquierda
			nodo = rotarIzquierda( nodo );
		}
		nodo.recalcularAltura();

		/*
		 * if ( nodoDer != null && nodoIzq == null ) nodo.setIzquierdo ( rotarIzquierda ( nodo ) );
		 * else if ( aIzq > aDer + 1 ) nodo = rotarDerecha ( nodo );
		 * else if ( aDer > aIzq + 1 ) nodo = rotarIzquierda ( nodo );
		 */
		return nodo;
	}


	public boolean borrar ( int clave )
	{
		final int previousNumElementos = numElementos;
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

	private NodoArbolBinarioBusqueda cambiarPorMenor ( NodoArbolBinarioBusqueda nodoBorrar, NodoArbolBinarioBusqueda nodoMenor )
	{
		if ( nodoMenor.getDerecho () != null )
		{   // Subárbol derecho
			NodoArbolBinarioBusqueda nuevoDer = this.cambiarPorMenor ( nodoBorrar, nodoMenor.getDerecho () );
			nodoMenor.setDerecho ( nuevoDer );
			return nodoMenor;
		}
		else
		{  // Encontrado nodo menor inmediato
			nodoBorrar.setClave ( nodoMenor.getClave () ); // Cambiar datos
			nodoBorrar.setDato ( nodoMenor.getDato () );
			return nodoMenor.getIzquierdo ();
			// Devolver subarbol izquierdo de menor inmediato
		}
	}

	
	private NodoArbolBinarioBusqueda rotarDerecha ( NodoArbolBinarioBusqueda nodo )
	{
		NodoArbolBinarioBusqueda res = null;
		if ( nodo != null )
		{
			NodoArbolBinarioBusqueda temp = nodo.getIzquierdo();
			if ( temp == null ) res = nodo;
			else
			{
				nodo.setIzquierdo( temp.getDerecho() );
				temp.setDerecho( nodo );
				nodo.recalcularAltura();
				res = temp;
			}
		}
		return res;
	}

	private NodoArbolBinarioBusqueda rotarIzquierda ( NodoArbolBinarioBusqueda nodo )
	{
		NodoArbolBinarioBusqueda res = null;
		if ( nodo != null )
		{
			NodoArbolBinarioBusqueda temp = nodo.getDerecho ();
			if ( temp == null )
			{
				res = nodo;
			}
			else
			{
				nodo.setDerecho ( temp.getIzquierdo () );
				temp.setIzquierdo ( nodo );
				nodo.recalcularAltura ();
				res = temp;
			}
		}
		return res;
	}

	private int altura ( NodoArbolBinarioBusqueda nodo )
	{
		int res ;
		if ( nodo == null ) res = 0;
		else
		{
			int aIzq = this.altura ( nodo.getIzquierdo () );
			int aDech = this.altura ( nodo.getDerecho () );
			res = Math.max ( aIzq, aDech ) + 1;
		}
		return res;
	}

	/**
	 * TODO estudiantes
	 */
	@Override
	public String toString ()
	{
		StringBuilder sb = new StringBuilder ();
		toStringRec ( raiz, sb, 1 );
		return sb.toString ();
	}

	private void toStringRec ( NodoArbolBinarioBusqueda nodo, StringBuilder sb, int nivel )
	{
		if ( nodo != null )
		{
			toStringRec ( nodo.getIzquierdo(), sb, nivel + 1 );
			sb.append ("Sala ").append ( nodo.getDato().getId() )
			  .append (": Valor(" ).append ( nodo.getClave() ).append (") " )
			  .append ("[Nivel " ).append ( nivel ).append ("]\n" );
			toStringRec ( nodo.getDerecho(), sb, nivel + 1 );
		}
	}

	/**
	 * TODO estudiantes
	 */
	public void mostrar2D ()
	{
		final ArrayDeque<NodoArbolBinarioBusqueda> cola = new ArrayDeque<NodoArbolBinarioBusqueda> ();
        final ArrayDeque<Integer> colaNiveles = new ArrayDeque<Integer> ();
		final int nivel = 1;
		final int altura = altura(raiz); 
		if ( raiz != null )
        {
			int nivelAnterior;
			final NodoArbolBinarioBusqueda nulo = new NodoArbolBinarioBusqueda ( -1, null );
			nivelAnterior = 0;
            cola.add ( raiz );
            colaNiveles.add ( nivel );
            while ( !cola.isEmpty () )
            {
                final NodoArbolBinarioBusqueda nodo = cola.remove ();
                final int n = colaNiveles.remove ();
				int nEspacios = (int) Math.pow ( 2, altura - n + 2 );
				if ( n > nivelAnterior )
				{
					System.out.println ();
					nivelAnterior = n;
					System.out.printf("%-" + nEspacios/2 + "s", " " );
				}
				
				if ( nodo != nulo )
				{
					System.out.printf("%-" + nEspacios + "s", nodo.getClave() );
					if ( nodo.getIzquierdo() != null)
					{
						cola.add ( nodo.getIzquierdo () );
					}
					else
					{
						cola.add ( nulo );
					}
                   	colaNiveles.add ( n+1 );
					if ( nodo.getDerecho() != null)
					{
						cola.add ( nodo.getDerecho () );
					}
					else
					{
						cola.add ( nulo );
					}
                   	colaNiveles.add ( n+1 );
				}
				else
				{
					System.out.printf("%-" + nEspacios + "s", " " );
				}
				
            }
			System.out.println ();
        }
	}

	/**
	 * TODO estudiantes
	 */
	public ArbolBinarioBusqueda[] partir ( int clave )
	{
		final ArbolBinarioBusqueda[] resultado = new ArbolBinarioBusqueda[2];
		/* final Sala corte = this.getElementoRec ( raiz, clave );
		final NodoArbolBinarioBusqueda nodo = new NodoArbolBinarioBusqueda ( clave, corte );
		if ( nodo != null )
		{
			resultado[0] = new ArbolBinarioBusqueda ();
			resultado[1] = new ArbolBinarioBusqueda ();
			resultado[0].raiz = nodo.getIzquierdo ();
			resultado[1].raiz = nodo.getDerecho ();
			resultado[0].numElementos = this.numElementos;
			resultado[1].numElementos = this.numElementos;
		} */
		resultado[0] = new ArbolBinarioBusqueda ();
		resultado[1] = new ArbolBinarioBusqueda ();
		if ( raiz != null )
		{
			partirRec ( raiz, clave, resultado );

			return resultado;
		}
		else
		{
			System.out.println ( "El árbol está vacío" );
			return null;
		}
	}

	private void partirRec ( NodoArbolBinarioBusqueda nodo, int clave, ArbolBinarioBusqueda[] resultado )
	{
		if ( nodo != null )
		{
			if ( nodo.getClave () < clave )
			{
				resultado[0].insertar ( nodo.getClave (), nodo.getDato () );
				
			}
			else if ( nodo.getClave () > clave )
			{
				resultado[1].insertar ( nodo.getClave (), nodo.getDato () );
				
			}
			partirRec ( nodo.getIzquierdo (), clave, resultado );
			partirRec ( nodo.getDerecho (), clave, resultado );
		}
	}


}
