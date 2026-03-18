/**
 * 
 * A diferencia de los otros displays, este muestra TODA la
 * información disponible: temperatura, humedad y presión,
 * en un formato más compacto tipo "tarjeta de datos".
 * 
 */
public class ThirdPartyDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    /**
     * Constructor: se registra automáticamente como observador
     * en el Subject recibido.
     *
     */
    public ThirdPartyDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * Recibe todos los datos actualizados del Subject
     * y refresca el display.
     */
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    /**
     * Muestra un resumen completo de todas las mediciones
     * en formato de panel externo.
     */
    @Override
    public void display() {
        System.out.println("=== [Third-Party Weather Panel] ===");
        System.out.printf("  Temp     : %.1f C%n", temperature);
        System.out.printf("  Humidity : %.1f %%%n", humidity);
        System.out.printf("  Pressure : %.1f hPa%n", pressure);
        System.out.println("===================================");
    }

    /**
     * Desregistra este display del Subject.
     * Útil para simular la desconexión de un servicio externo.
     */
    public void unsubscribe() {
        weatherData.removeObserver(this);
        System.out.println("[Third-Party Panel] Unsubscribed from WeatherData.");
    }
}
