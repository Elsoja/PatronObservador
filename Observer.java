/**
 * 
 * Define el contrato que deben implementar todos los
 * objetos que deseen recibir notificaciones del Subject.
 * 
 */
public interface Observer {

    /**
     * Método invocado por el Subject cuando sus datos cambian.
     * Recibe los nuevos valores directamente como parámetros
     * (modelo "push": el Subject empuja los datos al Observer).
     */
    void update(float temperature, float humidity, float pressure);
}