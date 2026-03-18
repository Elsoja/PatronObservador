/**
 * 
 * Define el contrato para cualquier clase que pueda
 * mostrar información en pantalla.
 * 
 */
public interface DisplayElement {

    /**
     * Muestra la información actual del elemento en pantalla.
     * Cada implementación decide cómo y qué datos mostrar.
     */
    void display();
}