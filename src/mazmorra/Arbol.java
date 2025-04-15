package mazmorra;

import java.util.ArrayDeque;
import java.util.Stack;
import utilidades.Utilidades;

public class Arbol
{
    private NodoArbol raiz;

    private Arbol ()
    {
        raiz = null;
    }

    private Arbol ( Sala dato )
    {
        raiz = new NodoArbol ( dato );
    }

    private Arbol ( Sala dato, Arbol izquierdo, Arbol derecho )
    {
        NodoArbol nodoIzq = null;
        NodoArbol nodoDer = null;
        if ( izquierdo != null )
        {
            nodoIzq = izquierdo.raiz;
        }
        if ( derecho != null )
        {
            nodoDer = derecho.raiz;
        }
        raiz = new NodoArbol ( dato, nodoIzq, nodoDer );
    }

    private Arbol ( NodoArbol nodo )
    {
        raiz = nodo;
    }

    /**
     * Recorrido en preorden
     */
    public void preOrden ()
    {
        System.out.print ( "Preorden: " );
        this.preOrdenRec ( raiz );
        System.out.println ();
    }

    private void preOrdenRec ( NodoArbol nodo )
    {
        if ( nodo != null )
        {
            System.out.print ( nodo.getDato () + "  " );
            this.preOrdenRec ( nodo.getIzquierdo () );
            this.preOrdenRec ( nodo.getDerecho () );
        }
    }

    /**
     * Recorrido en orden central
     */
    public void ordenCentral ()
    {
        System.out.print ( "Orden Central: " );
        this.ordenCentralRec ( raiz );
        System.out.println ();
    }

    private void ordenCentralRec ( NodoArbol nodo )
    {
        if ( nodo != null )
        {
            this.ordenCentralRec ( nodo.getIzquierdo () );
            System.out.print ( nodo.getDato () + "  " );
            this.ordenCentralRec ( nodo.getDerecho () );
        }
    }

    /**
     * Recorrido en postorden
     */
    public void postOrden ()
    {
        System.out.print ( "Postorden: " );
        this.postOrdenRec ( raiz );
        System.out.println ();
    }

    private void postOrdenRec ( NodoArbol nodo )
    {
        if ( nodo != null )
        {
            this.postOrdenRec ( nodo.getIzquierdo () );
            this.postOrdenRec ( nodo.getDerecho () );
            System.out.print ( nodo.getDato () + "  " );
        }
    }

    /**
     * Recorrido en amplitud con una cola de nodos del árbol
     */
    public void amplitud ()
    {
        final ArrayDeque<NodoArbol> cola = new ArrayDeque<> ();
        System.out.print ( "Amplitud: " );
        if ( raiz != null )
        {
            cola.add ( raiz );
            while ( !cola.isEmpty () )
            {
                final NodoArbol nodo = cola.remove ();
                System.out.print ( nodo.getDato () + "  " );
                if ( nodo.getIzquierdo () != null ) 
                {
                    cola.add ( nodo.getIzquierdo () );
                }
                if ( nodo.getDerecho () != null )
                {
                    cola.add ( nodo.getDerecho () );
                }
            }
        }
        System.out.println ();
    }

    /**
     * Recorrido en amplitud con una cola proporcionada por la clase ArrayDeque.
     * Escribe tambiémn el nivel del nodo
     */
    public void amplitudConNiveles ()
    {
        final ArrayDeque<NodoArbol> cola = new ArrayDeque<NodoArbol> ();
        final ArrayDeque<Integer> colaNiveles = new ArrayDeque<Integer> ();
        System.out.println ( "Amplitud con niveles: " );
        final int nivel = 1;
        if ( raiz != null )
        {
            cola.add ( raiz );
            colaNiveles.add ( nivel );
            while ( !cola.isEmpty () )
            {
                final NodoArbol nodo = cola.remove ();
                final int n = colaNiveles.remove ();
                System.out.println ( "Nivel " + n + ": " + nodo.getDato () );
                if ( nodo.getIzquierdo () != null )
                {
                    cola.add ( nodo.getIzquierdo () );
                    colaNiveles.add ( n+1 );
                }
                if ( nodo.getDerecho () != null )
                {
                    cola.add ( nodo.getDerecho () );
                    colaNiveles.add ( n+1 );
                }
            }
        }
        System.out.println ();
    }

    public void preOrdenNivel ()
    {
        System.out.println ( "Preorden con niveles: " );
        preOrdenNivelRec ( raiz, 1 );
    }

    private void preOrdenNivelRec ( NodoArbol nodo, int nivel )
    {
        if ( nodo != null )
        {
            System.out.println ( nodo.getDato () + " en el nivel " + nivel );
            preOrdenNivelRec ( nodo.getIzquierdo (), nivel + 1 );
            preOrdenNivelRec ( nodo.getDerecho (), nivel + 1 );
        }
    }



    /**
     * TODO estudiantes
     */
    public static Arbol construirMazmorra ( int[][] datosSalas )
    {
        /*
         * Si la matriz es nula o esta vaciá, se devuelve un elemento nulo.
         * En cualquier otro caso, se devuelve un árbol construida de la siguiente manera:
         * 1. Se crea un array de nodos de tamaño n+2, donde n es el número de filas de la matriz.
         * 2. Se inicializan los dos primeros nodos como nulos. De esta manera, cuando un nodo apunta a otro nodo
         *    (como noodo izquierdo o derecho) utilizando el índice -1, se le puede dar el valor nulo almacenado en la posición 0
         *    del array (-1 + 1 = 0 ).
         * 3. Se recorre la matriz de abajo hacia arriba, creando un nodo para cada sala y asignando los nodos izquierdo y derecho
         *    correspondientes a los valores ubicados en la fila correspondiente al nodo actual y la columna 1 (nodo izquierdo) o la columna 2
         *    (nodo derecho) de la matriz.
         *    Cada nodo está formado por una sala (con id, que es el número de línea que ocupa el nodo en el fichero .txt que contiene la mazmorra
         *    y valor, el cual es el valor almacenado en la columna 3 de la fila que ocupa el nodo en la matriz ) y las referencias a los nodos izquierdo y derecho.
         * Finalmente, se devuelve el árbol construido a partir del nodo raíz, que es el nodo 2 del array (nodo ubicado en la primera fila de la matriz).
         */

        if ( datosSalas == null || datosSalas.length == 0 )
        {
            return null;
        }

        final NodoArbol[] nodos = new NodoArbol [ datosSalas.length + 2 ];
        nodos[0] = nodos[1] = null;
        for ( int i = datosSalas.length - 1; i >= 0; i-- )
        {
            final int indiceIzquierdo = datosSalas[i][0] + 2;
            final int indicederecho = datosSalas[i][1] + 2;
            final int valor = datosSalas[i][2];
            final Sala sala = new Sala ( i + 1, valor );
            nodos[i + 2] = new NodoArbol ( sala, nodos[indiceIzquierdo], nodos[indicederecho] );
        }
        return new Arbol ( nodos[2]);
    }

    /**
     * TODO estudiantes
     */
    public String rutaMejorTesoro ()
    {
        // Recorrer el árbol en preorden y buscar la ruta con el mejor tesoro
        // La ruta con el mejor tesoro es aquella que tiene la suma de los tesoros
        // más alta. Si hay varias rutas con la misma suma, se debe devolver la primera
        // que se encuentre.
        // La ruta se debe devolver como una cadena de caracteres con el formato
        // "Sala 0 -> Sala 1 -> Sala 2 -> ... -> Sala n"
        // donde n es el número de salas en la ruta.
        // Si no hay tesoros, se debe devolver "No hay tesoros".
        // Si la raíz es nula, se debe devolver "No hay tesoros".

        //si tesoroDerecha < tesoroIzquierda, entonces se añade la ruta de la izquierda
        //si tesoroIzquierda < tesoroDerecha, entonces se añade la ruta de la derecha
        //si tesoroIzquierda == tesoroDerecha, entonces se añade la ruta de la izquierda
        //si tesoroIzquierda == 0 && tesoroDerecha == 0, entonces se añade la ruta de la izquierda
        //hay que añadir al string la ruta seleccionada en cada disyunción
        

        //si la raíz no tiene nodos izuquierdo y derecho, se devuelve el valor de la raíz.
        final String inicio = "-> ";
        final Ruta ruta = rutaMejorTesoro ( raiz );
        final String rutaStr = inicio + ruta.ruta + "(" + ruta.valor + ") X";
        return rutaStr;
    }
    
    final char[] direcciones = { Utilidades.getGiroDerecha (), Utilidades.getGiroIzquierda (), Utilidades.getLineaRecta () };
    final int derecha = 0;
    final int izquierda = 1;
    final int recta = 2;

    private Ruta rutaMejorTesoro ( NodoArbol nodo )
    {
        if ( nodo == null )
        {
            return null;
        }

        final Ruta rutaIzquierda = rutaMejorTesoro ( nodo.getIzquierdo () );
        final Ruta rutaDerecha = rutaMejorTesoro ( nodo.getDerecho () );

        if ( rutaIzquierda == null && rutaDerecha == null )
        {
            return new Ruta ( "" + nodo.getDato ().getId (), nodo.getDato ().getValor () );
        }
        else if ( rutaIzquierda == null ) 
        {
            return new Ruta ( nodo.getDato ().getId () + " " + direcciones[recta] + " " + rutaDerecha.ruta + " ",
                    rutaDerecha.valor );
        }
        else if ( rutaDerecha == null )
        {
            return new Ruta ( nodo.getDato ().getId () + direcciones[recta] + rutaIzquierda.ruta,
                    rutaIzquierda.valor );
        }
        else
        {
            if ( rutaIzquierda.valor > rutaDerecha.valor )
            {
                return new Ruta ( nodo.getDato ().getId () + direcciones[izquierda] + rutaIzquierda.ruta,
                        rutaIzquierda.valor );
            }
            else
            {
                return new Ruta ( nodo.getDato ().getId () + direcciones[derecha] + rutaDerecha.ruta,
                        rutaDerecha.valor );
            }
        }
    }

    private class Ruta
    {
        public final String ruta;
        public final int valor;

        public Ruta ( String ruta, int valor )
        {
            this.ruta = ruta;
            this.valor = valor;
        }
    }

    /**
     * TODO estudiantes
     */
    public String rutaMasFacil ()
    {
        
        return null;
    }

    /**
     * TODO estudiantes
     */
    public ArbolBinarioBusqueda abbMonstruos ()
    {
        return null;
    }

    /**
     * TODO estudiantes
     * @return
     */
    public ArbolBinarioBusqueda abbTesoros ()
    {
        return null;
    }

}
