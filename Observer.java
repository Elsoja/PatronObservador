/**
 * Interfaz Observer (Observador)
 *
 * Define el contrato que deben implementar todos los
 * objetos que deseen recibir notificaciones del Subject.
 *
 * El método update() es llamado por el Subject cada vez
 * que su estado interno cambia.
 *
 * Parte del Patrón de Diseño: Observer
 */
public interface Observer {

    /**
     * Método invocado por el Subject cuando sus datos cambian.
     * Recibe los nuevos valores directamente como parámetros
     * (modelo "push": el Subject empuja los datos al Observer).
     *
     * @param temperature La temperatura actual en grados Celsius.
     * @param humidity    La humedad relativa actual (porcentaje).
     * @param pressure    La presión atmosférica actual (hPa).
     */
    void update(float temperature, float humidity, float pressure);
}