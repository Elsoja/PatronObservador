/**
 * Compara la presión atmosférica actual con la última medición
 * y hace una predicción simple del clima:
 * - Presión subiendo → Mejorará el clima
 * - Presión bajando → Podría venir lluvia/frío
 * - Presión estable → Igual que ahora
 */
public class ForecastDisplay implements Observer, DisplayElement {

    private float currentPressure = 1013.0f; // presión inicial promedio (hPa)
    private float lastPressure;

    private Subject weatherData;

    /**
     * Constructor: se registra automáticamente como observador.
     */
    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * Guarda la presión anterior, actualiza con la nueva
     * y llama a display() para mostrar el pronóstico.
     */
    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    /**
     * Muestra un pronóstico basado en la tendencia de la presión.
     */
    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same.");
        } else {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }
}
