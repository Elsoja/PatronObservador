import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Es el núcleo del sistema de la estación meteorológica.
 * Almacena las mediciones actuales (temperatura, humedad, presión)
 * y gestiona la lista de observadores suscritos.
 * 
 */
public class WeatherData implements Subject {

    // Lista de observadores suscritos al Subject
    private List<Observer> observers;

    // Estado interno: mediciones del clima
    private float temperature;
    private float humidity;
    private float pressure;

    /**
     * Constructor: inicializa la lista de observadores vacía.
     */
    public WeatherData() {
        observers = new ArrayList<>();
    }

    /**
     * Registra un observador nuevo en la lista.
     * A partir de este momento, recibirá notificaciones.
     */
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Elimina un observador de la lista.
     * Deja de recibir notificaciones.
     */
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifica a todos los observadores suscritos
     * enviándoles los valores actuales de las mediciones.
     * Usa el modelo "push": los datos se envían como argumentos.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * Llamado cuando hay nuevas mediciones disponibles
     * (por ejemplo, desde sensores físicos o simulación).
     * Actualiza el estado interno y dispara la notificación.
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    /**
     * Indica que las mediciones han cambiado y
     * activa la notificación a los observadores.
     * Separar este método facilita extensiones futuras
     */
    private void measurementsChanged() {
        notifyObservers();
    }

    // ----------------------------------------------------------------
    // Getters opcionales: útiles si en el futuro se implementa el
    // modelo "pull" donde el Observer consulta al Subject por sí mismo.
    // ----------------------------------------------------------------

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}