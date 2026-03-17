/**
 * Interfaz DisplayElement (Elemento de Visualización)
 *
 * Define el contrato para cualquier clase que pueda
 * mostrar información en pantalla.
 *
 * Separar esta responsabilidad en su propia interfaz
 * sigue el Principio de Segregación de Interfaces (ISP),
 * permitiendo que no todo Observer sea necesariamente
 * un elemento de visualización.
 *
 * Parte del Patrón de Diseño: Observer
 */
public interface DisplayElement {

    /**
     * Muestra la información actual del elemento en pantalla.
     * Cada implementación decide cómo y qué datos mostrar.
     */
    void display();
}