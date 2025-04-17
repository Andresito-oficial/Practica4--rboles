import mazmorra.Arbol;
import mazmorra.ArbolBinarioBusqueda;
import utilidades.Utilidades;


public class Main
{
    public static void main ( String[] args )
    {
        /* TODO estudiantes */
        final int[][] mazmorra = Utilidades.leerMazmorra ( "src/mazmorra1.txt" );
        final Arbol mazmorra1 = Arbol.construirMazmorra(mazmorra);
        final ArbolBinarioBusqueda arbolBinario = mazmorra1.abbMonstruos();
        arbolBinario.mostrar2D();
    }
}
