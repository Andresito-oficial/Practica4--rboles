import mazmorra.Arbol;
import mazmorra.ArbolBinarioBusqueda;
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
        arbolBinarios[0].mostrar2D();
        arbolBinarios[1].mostrar2D();
    }
}
