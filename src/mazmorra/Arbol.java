package mazmorra;

import java.util.ArrayDeque;
import java.util.Stack;

public class Arbol
{
    private NodoArbol raiz;

    public Arbol ()
    {
        raiz = null;
    }

    public Arbol ( Sala dato )
    {
        raiz = new NodoArbol ( dato );
    }

    public Arbol ( Sala dato, Arbol izquierdo, Arbol derecho )
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
        ArrayDeque<NodoArbol> cola = new ArrayDeque<> ();
        System.out.print ( "Amplitud: " );
        if ( raiz != null )
        {
            cola.add ( raiz );
            while ( !cola.isEmpty () )
            {
                NodoArbol nodo = cola.remove ();
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
        ArrayDeque<NodoArbol> cola = new ArrayDeque<NodoArbol> ();
        ArrayDeque<Integer> colaNiveles = new ArrayDeque<> ();
        System.out.println ( "Amplitud con niveles: " );
        int nivel = 1;
        if ( raiz != null )
        {
            cola.add ( raiz );
            colaNiveles.add ( nivel );
            while ( !cola.isEmpty () )
            {
                NodoArbol nodo = cola.remove ();
                int n = colaNiveles.remove ();
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
        /*if ( datosalas[i][j] ==-1) -> no existe la sala;
        */
        // TODO estudiantes
        // Crear un árbol binario de búsqueda con los datos de las salas
        // y devolverlo.
        // La raíz del árbol será la sala 0.
        // Las salas se numeran de 0 a n-1, donde n es el número de salas.
        // La sala i tiene como hijo izquierdo la sala 2*i+1 y como hijo derecho la sala 2*i+2.
        // Si la sala i no tiene hijo izquierdo o derecho, se debe poner -1 en su lugar.

        if ( datosSalas == null || datosSalas.length == 0 )
        {
            return null;
        }

        NodoArbol[] nodos = new NodoArbol [ datosSalas.length ];
        for ( int i = 0; i < datosSalas.length; i++ )
        {
            if ( datosSalas[i][0] != -1 )
            {
            nodos[i] = new NodoArbol ( new Sala ( datosSalas[i][0] ) );
            }
        }

        for ( int i = 0; i < datosSalas.length; i++ )
        {
            if ( nodos[i] != null )
            {
            int hijoIzquierdo = 2 * i + 1;
            int hijoDerecho = 2 * i + 2;

            if ( hijoIzquierdo < datosSalas.length && datosSalas[hijoIzquierdo][0] != -1 )
            {
                nodos[i].setIzquierdo ( nodos[hijoIzquierdo] );
            }

            if ( hijoDerecho < datosSalas.length && datosSalas[hijoDerecho][0] != -1 )
            {
                nodos[i].setDerecho ( nodos[hijoDerecho] );
            }
            }
        }

        return new Arbol ( nodos[0].getDato (), null, null );
    }

    /**
     * TODO estudiantes
     */
    public String rutaMejorTesoro ()
    {
        return null;
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
