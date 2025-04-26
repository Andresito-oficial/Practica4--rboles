package mazmorra;
import utilidades.Utilidades;

public class Main
{
    public static void main ( String[] args )
    {
        /* TODO estudiantes */
        final int[][] matriz = Utilidades.leerMazmorra ( "/home/andres/OneDrive/Prácticas estructura de datos/ED 4 Practica. Arboles/Practica4-arboles/mazmorra1.txt" );
        final Arbol mazmorra = Arbol.construirMazmorra( matriz );
        System.out.println("Arbol de mazmorras\n" + mazmorra.toString() ); 
        final ArbolBinarioBusqueda arbolBinario = mazmorra.abbMonstruos();
        arbolBinario.mostrar2D();
        final ArbolBinarioBusqueda arbolBinarioTesoro = mazmorra.abbTesoros();
        arbolBinarioTesoro.mostrar2D();
        final ArbolBinarioBusqueda[] arbolBinarios = arbolBinario.partir(4);
        System.out.println("Arbol de mazmorras\n" + arbolBinarios[0].toString() );
        System.out.println("Arbol de mazmorras\n" + arbolBinarios[1].toString() );
        arbolBinario.mostrar2D();
        arbolBinarios[0].mostrar2D();
        arbolBinarios[1].mostrar2D();
        /*final ArbolBinarioBusqueda arbolBinario2 = new ArbolBinarioBusqueda ();
        final int [] claves = { 8, 20, 13, 5, 2, 15, 10, 7, 11, 9, 6, 12 };
        for ( int i = 0; i < claves.length; i++ )
        {
            arbolBinario2.insertar ( claves[i], new Sala() );
            arbolBinario2.mostrar2D();
            System.out.println(arbolBinario2.toString());

        }
        arbolBinario2.borrar ( 20 );
        arbolBinario2.mostrar2D();
        System.out.println ( arbolBinario2.toString () );*/
        final ArbolBinarioBusqueda arbolBinario3 = arbolBinario;
        System.out.println ( "Partir arbolBinario con el algoritmo malo" );
        ArbolBinarioBusqueda arbolBinarios2[] = arbolBinario3.partir2 (4);
        arbolBinarios2[0].mostrar2D();
        arbolBinarios2[1].mostrar2D();
        final ArbolBinarioBusqueda arbolBinario4 = new ArbolBinarioBusqueda ();
        final int [] claves2 = { 8, 20, 13, 5, 2, 15, 10, 7, 11, 9, 6, 12 };
        for ( int i = 0; i < claves2.length; i++ )
        {
            arbolBinario4.insertar ( claves2[i], new Sala() );
            arbolBinario4.mostrar2D();
            System.out.println(arbolBinario4.toString());

        }
        arbolBinario4.borrar ( 20 );
        arbolBinario4.mostrar2D();
        System.out.println ( arbolBinario4.toString () );
        final ArbolBinarioBusqueda[] arbolBinarios3 = arbolBinario4.partir (4);
        arbolBinarios3[0].mostrar2D();
        arbolBinarios3[1].mostrar2D();
        System.out.println ( "Partir arbolBinario4 con el algoritmo malo" );
        final ArbolBinarioBusqueda[] arbolBinarios4 = arbolBinario4.partir2 (4);
        arbolBinarios4[0].mostrar2D();
        arbolBinarios4[1].mostrar2D();
    }
}
