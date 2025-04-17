import mazmorra.Arbol;
import mazmorra.ArbolBinarioBusqueda;
import utilidades.Utilidades;


public class Main
{
    public static void main ( String[] args )
    {
        /* TODO estudiantes */
        final int[][] mazmorra = Utilidades.leerMazmorra ( "/home/andres/OneDrive/Prácticas estructura de datos/ED 4 Practica. Arboles/Practica4-arboles/mazmorra1.txt" );
        final Arbol mazmorra1 = Arbol.construirMazmorra(mazmorra);
        System.out.println("Arbol de mazmorras\n" + mazmorra1.toString() ); 
        final ArbolBinarioBusqueda arbolBinario = mazmorra1.abbMonstruos();
        arbolBinario.mostrar2D();

    }
}
