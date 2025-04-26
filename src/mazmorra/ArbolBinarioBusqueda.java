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
		/*System.out.println( "Reequilibrando el nodo con clave " + nodo.getClave() );
		this.mostrar2D();*/
		NodoArbolBinarioBusqueda nodoIzq = nodo.getIzquierdo();
		NodoArbolBinarioBusqueda nodoDer = nodo.getDerecho();
		int aIzq = NodoArbolBinarioBusqueda.getAltura( nodoIzq );
		final int aDer = NodoArbolBinarioBusqueda.getAltura( nodoDer );
		//System.out.println( "Altura izquierda: " + aIzq + " Altura derecha: " + aDer );
		if ( aIzq > aDer + 1 )
		{  // Rotar derecha
			if ( NodoArbolBinarioBusqueda.getAltura ( nodoIzq.getDerecho () ) > NodoArbolBinarioBusqueda.getAltura ( nodoIzq.getIzquierdo () ) )
			{
				nodoIzq = rotarIzquierda ( nodoIzq );
				nodoIzq.recalcularAltura();
			}
			nodo = rotarDerecha( nodo );
			
		}
		else if ( aDer > aIzq + 1 || nodoIzq == null && nodoDer != null )
		{  // Rotar izquierda
			if ( NodoArbolBinarioBusqueda.getAltura( nodoDer.getIzquierdo () ) > NodoArbolBinarioBusqueda.getAltura ( nodoDer.getDerecho () ) )
			{
				nodoDer = rotarDerecha ( nodoDer );
				nodoDer.recalcularAltura();
			}
			nodo = rotarIzquierda( nodo );
		}
		nodo.recalcularAltura();

		//this.mostrar2D();
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
			//System.out.println( nodo.getIzquierdo() );
		}
		else if ( nodo.getClave () < clave )
		{  // Subarbol derecho
			NodoArbolBinarioBusqueda nuevoDer = this.borrarRec ( nodo.getDerecho (), clave );
			nodo.setDerecho ( nuevoDer );
			//System.out.println( nodo.getDerecho() );
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
			else
			{    // Caso 3
				NodoArbolBinarioBusqueda nuevoDer = this.cambiarPorMenor ( nodo,
						nodo.getDerecho () );
				nodo.setDerecho ( nuevoDer );
				//nodo.setIzquierdo ( nuevoIzq );
				//System.out.println( nodo.getIzquierdo() );
			}
			numElementos--;
		}
		return switch ( nodo )
		{
			case null -> null;
			default -> reequilibrar ( nodo );
		};
	}

	/*private NodoArbolBinarioBusqueda cambiarPorMenor ( NodoArbolBinarioBusqueda nodoBorrar, NodoArbolBinarioBusqueda nodoMenor )
	{

	}	*/
	private NodoArbolBinarioBusqueda cambiarPorMenor ( NodoArbolBinarioBusqueda nodoBorrar, NodoArbolBinarioBusqueda nodoMenor )
	{
		if ( nodoMenor.getIzquierdo () == null )
		{
			nodoBorrar.setClave ( nodoMenor.getClave () ); // Cambiar datos
			nodoBorrar.setDato ( nodoMenor.getDato () );
			/*System.out.println( "voy a reequilibrar el nodo con clave " + nodoMenor.getClave() );
			System.out.println( nodoMenor + " " + nodoMenor.getClave() ); */
			nodoMenor = null;
			return nodoMenor; 
		}
		else
		{
			NodoArbolBinarioBusqueda nuevoIzq = this.cambiarPorMenor ( nodoBorrar, nodoMenor.getIzquierdo () );
			nodoMenor.setIzquierdo ( nuevoIzq );
			//System.out.println( nodoMenor  + " " + nodoMenor.getClave() );
			nodoMenor = reequilibrar ( nodoMenor );
			return nodoMenor;
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
			  .append ("[Nivel " ).append ( nivel ).append ("]" ).append ( " Altura: " + nodo.getAltura () + "\n" );
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
					if ( n < altura )
					{
						for ( int i = 0; i < 2; i++ )
						{
							cola.add ( nulo );
							colaNiveles.add ( n + 1 );	
						}
					}
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
		/*
		 * Partir el árbol en dos subárboles, uno con los elementos menores o iguales a la clave
		 * y otro con los elementos mayores a la clave.
		 * El método devuelve un array de dos elementos, el primero con el subárbol menor o igual
		 * y el segundo con el subárbol mayor.
		 * Si el árbol está vacío, se devuelve null.
		 * Si la clave es mayor que la mayor clave del árbol, se devuelve un array con el
		 * árbol original en la primera posición y un árbol vacío en la segunda.
		 * Si la clave es menor que la menor clave del árbol, se devuelve un array con un
		 * árbol vacío en la primera posición y el árbol original en la segunda.
		 * Si la clave está entre la menor y la mayor clave del árbol, se devuelve un array con
		 * el subárbol menor o igual a la clave en la primera posición y el subárbol mayor en
		 * la segunda.
		 */
		final ArbolBinarioBusqueda[] resultado = new ArbolBinarioBusqueda[2];
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
		/*
		 * Si el nodo es nulo, no se hace nada.
		 * Si la clave del nodo es menor o igual a la clave, se inserta en el subárbol de la primera posición del array.
		 * Si la clave del nodo es mayor que la clave, se inserta en el subárbol de la segunda posición del array.
		 * Se llama recursivamente a la función para los subárboles izquierdo y derecho.
		 */
		if ( nodo != null )
		{
			if ( nodo.getClave () <= clave )
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

	public ArbolBinarioBusqueda[] partir2 ( int clave )
	{
        if ( this.raiz == null )
		{
            // Caso base: si el árbol está vacío, devuelve dos árboles vacíos
            return new ArbolBinarioBusqueda[] { new ArbolBinarioBusqueda(), new ArbolBinarioBusqueda() };
        }

        // Creación de dos nuevos árboles binarios de búsqueda
        ArbolBinarioBusqueda arbolMenorOIgual = new ArbolBinarioBusqueda ();
        ArbolBinarioBusqueda arbolMayor = new ArbolBinarioBusqueda  ();

        // Partición del árbol
        partirRecursivo ( this.raiz, clave, arbolMenorOIgual, arbolMayor );

        return new ArbolBinarioBusqueda[] { arbolMenorOIgual, arbolMayor };
    }
	private void partirRecursivo ( NodoArbolBinarioBusqueda nodo, int clave, 
                                 ArbolBinarioBusqueda arbolMenorOIgual, ArbolBinarioBusqueda arbolMayor )
	{
        if ( nodo == null )
		{
            return; // Caso base: nodo nulo
        }

        if ( nodo.getClave() <= clave )
		{
            // Agrega el nodo al árbol de menores o iguales
            arbolMenorOIgual.insertar ( nodo.getClave(), nodo.getDato() );
            // Continúa la partición con el subárbol derecho
            partirRecursivo ( nodo.getDerecho() , clave, arbolMenorOIgual, arbolMayor );
			partirRecursivo ( nodo.getIzquierdo(), clave, arbolMenorOIgual, arbolMayor );
        }
		else
		{
            // Agrega el nodo al árbol de mayores
            arbolMayor.insertar ( nodo.getClave(), nodo.getDato() );
            // Continúa la partición con el subárbol izquierdo
            partirRecursivo ( nodo.getIzquierdo(), clave, arbolMenorOIgual, arbolMayor );
			partirRecursivo ( nodo.getDerecho(), clave, arbolMenorOIgual, arbolMayor );
        }
    }

}
