/**
 * Interfaz Subject (Sujeto / Observable)
 * 
 * Define el contrato que debe cumplir cualquier objeto
 * que quiera ser observado. Permite registrar, eliminar
 * y notificar a los observadores suscritos.
 *
 * Parte del Patrón de Diseño: Observer
 */
public interface Subject {

    /**
     * Registra un nuevo observador para recibir notificaciones.
     * 
     * @param o El observador a registrar.
     */
    void registerObserver(Observer o);

    /**
     * Elimina un observador de la lista de notificaciones.
     * 
     * @param o El observador a eliminar.
     */
    void removeObserver(Observer o);

    /**
     * Notifica a todos los observadores registrados
     * que el estado del sujeto ha cambiado.
     */
    void notifyObservers();
}