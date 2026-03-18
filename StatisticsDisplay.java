/**
 * 
 * Lleva un historial de todas las temperaturas recibidas
 * y muestra estadísticas: mínima, máxima y promedio.
 * 
 */
public class StatisticsDisplay implements Observer, DisplayElement {

    private float maxTemperature = Float.MIN_VALUE;
    private float minTemperature = Float.MAX_VALUE;
    private float temperatureSum = 0.0f;
    private int numReadings = 0;

    private Subject weatherData;

    /**
     * Constructor: se registra automáticamente como observador.
     *
     */
    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * Actualiza las estadísticas con la nueva temperatura
     * y llama a display() para mostrar los resultados.
     *
     * Nota: humidity y pressure no son usadas en este display.
     */
    @Override
    public void update(float temperature, float humidity, float pressure) {
        temperatureSum += temperature;
        numReadings++;

        if (temperature > maxTemperature)
            maxTemperature = temperature;
        if (temperature < minTemperature)
            minTemperature = temperature;

        display();
    }

    /**
     * Muestra el promedio, máximo y mínimo de temperatura
     * calculados desde el inicio de las observaciones.
     */
    @Override
    public void display() {
        float average = temperatureSum / numReadings;
        System.out.println("Avg/Max/Min temperature = " + average + "/" + maxTemperature + "/" + minTemperature);
    }
}