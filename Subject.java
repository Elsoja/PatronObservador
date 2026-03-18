/**
 * 
 * Define el contrato que debe cumplir cualquier objeto
 * que quiera ser observado. Permite registrar, eliminar
 * y notificar a los observadores suscritos.
 * 
 */
public interface Subject {

    /**
     * Registra un nuevo observador para recibir notificaciones.
     * 
     */
    void registerObserver(Observer o);

    /**
     * Elimina un observador de la lista de notificaciones.
     * 
     */
    void removeObserver(Observer o);

    /**
     * Notifica a todos los observadores registrados
     * que el estado del sujeto ha cambiado.
     */
    void notifyObservers();
}