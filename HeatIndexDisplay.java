/**
 * HeatIndexDisplay - Display del Índice de Calor (Heat Index)
 *
 * Calcula y muestra el "Heat Index" (sensación térmica real),
 * que combina temperatura y humedad para indicar cómo percibe
 * el cuerpo humano el calor ambiente.
 *
 * Utiliza la fórmula de regresión polinomial de Rothfusz,
 * que trabaja con temperatura en grados Fahrenheit (°F)
 * y humedad relativa en porcentaje (%).
 *
 * Implementa: Observer, DisplayElement
 */
public class HeatIndexDisplay implements Observer, DisplayElement {

    private float heatIndex = 0.0f;
    private Subject weatherData;

    /**
     * Constructor: se registra automáticamente como observador.
     *
     * @param weatherData El Subject al que se suscribe.
     */
    public HeatIndexDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * Recibe temperatura (°F) y humedad del Subject,
     * calcula el Heat Index y actualiza el display.
     *
     * @param temperature Temperatura en grados Fahrenheit (°F).
     * @param humidity    Humedad relativa (%).
     * @param pressure    Presión atmosférica — no usada en este display.
     */
    @Override
    public void update(float temperature, float humidity, float pressure) {
        heatIndex = computeHeatIndex(temperature, humidity);
        display();
    }

    /**
     * Muestra el valor actual del Heat Index en pantalla.
     */
    @Override
    public void display() {
        System.out.println("Heat index is " + heatIndex);
    }

    /**
     * Calcula el Heat Index usando la fórmula de Rothfusz.
     *
     * Fórmula:
     *   HI = 16.923
     *        + 1.85212×10⁻¹ × T
     *        + 5.37941       × RH
     *        - 1.00254×10⁻¹ × T  × RH
     *        + 9.41695×10⁻³ × T²
     *        + 7.28898×10⁻³ × RH²
     *        + 3.45372×10⁻⁴ × T² × RH
     *        - 8.14971×10⁻⁴ × T  × RH²
     *        + 1.02102×10⁻⁵ × T² × RH²
     *        - 3.86460×10⁻⁵ × T³
     *        + 2.91583×10⁻⁵ × RH³
     *        + 1.42721×10⁻⁶ × T³ × RH
     *        + 1.97483×10⁻⁷ × T  × RH³
     *        - 2.18429×10⁻⁸ × T³ × RH²
     *        + 8.43296×10⁻¹⁰× T² × RH³
     *        - 4.81975×10⁻¹¹× T³ × RH³
     *
     * @param t  Temperatura en °F.
     * @param rh Humedad relativa en %.
     * @return   El índice de calor calculado (en °F).
     */
    private float computeHeatIndex(float t, float rh) {
        return (float)(
             16.923
            + (1.85212e-1  * t)
            + (5.37941     * rh)
            - (1.00254e-1  * t  * rh)
            + (9.41695e-3  * t  * t)
            + (7.28898e-3  * rh * rh)
            + (3.45372e-4  * t  * t  * rh)
            - (8.14971e-4  * t  * rh * rh)
            + (1.02102e-5  * t  * t  * rh * rh)
            - (3.8646e-5   * t  * t  * t)
            + (2.91583e-5  * rh * rh * rh)
            + (1.42721e-6  * t  * t  * t  * rh)
            + (1.97483e-7  * t  * rh * rh * rh)
            - (2.18429e-8  * t  * t  * t  * rh * rh)
            + (8.43296e-10 * t  * t  * rh * rh * rh)
            - (4.81975e-11 * t  * t  * t  * rh * rh * rh)
        );
    }
}
