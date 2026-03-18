/**
 * CurrentConditionsDisplay - Display de Condiciones Actuales
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private Subject weatherData; // referencia al Subject (para desregistro futuro)

    /**
     * Constructor: se registra automáticamente como observador
     * en el Subject recibido.
     * 
     */
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * Recibe los nuevos datos del Subject y llama a display()
     * para refrescar la pantalla inmediatamente.
     */
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    /**
     * Muestra las condiciones actuales: temperatura y humedad.
     * (La presión no se muestra en este display intencionalmente.)
     */
    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}