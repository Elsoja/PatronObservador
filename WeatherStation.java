/**
 * 
 * Punto de entrada del programa. Crea el Subject (WeatherData),
 * registra todos los displays como observadores y simula
 * cambios en las mediciones meteorológicas.
 * 
 */
public class WeatherStation {

    public static void main(String[] args) {

        // 1. Crear el Subject principal
        WeatherData weatherData = new WeatherData();

        // 2. Crear los displays — cada uno se auto-registra como Observer
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        // 3. Simular cambios de mediciones (temperatura en °F, humedad en %, presión en
        // inHg)
        // Cada setMeasurements() notifica automáticamente a todos los Observers.
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
