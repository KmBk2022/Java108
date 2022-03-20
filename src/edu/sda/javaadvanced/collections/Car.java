package edu.sda.javaadvanced.collections;

import java.util.Objects;

/**
 * Klasa taka jak ta poniżej : z konstruktorem ale bez setterów
 * to klasa niemutowalna (immutable) - bardzo wskazana przy programowaniu w wątkach
 * bo z każdego miejsca odwołanie do obiektu zwróci to samo, nie ma możliwości
 * zmiany pól klasy
 */
public class Car implements Comparable<Car> {

    private final String name;
    private final String engine;
    private final int maxSpeed;

    public Car(String name, String engine, int maxSpeed) {
        this.name = name;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
    }

    public String getName() {
        return name;
    }

    public String getEngine() {
        return engine;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", engine='" + engine + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    /**
     * Kontrakt equals - hashCode
     * Jeśli dwa obiekty są sobie równe po porównianiu metodą equals to
     * ich hashCode MUSI być taki sam
     *
     * Jeśli hashCode dwóch obiektów jest taki sam to jeszcze nie znaczy że są one sobie równe
     * po porównaniu equals
     */

    @Override
    public boolean equals(Object o) {
        //1 Krok
        //Sprawdzamy czy referencje obiektów są takie same - czy idą do tego samego miejsca w pamięci
        if (this == o) return true;

        //2 Krok
        //Sprawdzamy czy obiekt do porównania nie jest nullem i czy jest tej samej klasy
        //co obiekt z którym go porównujemy (np obiekt klasy Drzewo z obiektem klasy Samochód)
        if (o == null || getClass() != o.getClass()) return false;

        //3 Krok
        //Wiemy że obiekty są tej samej klasy, bo inaczej wyszlibyśmy z metody krok wcześniej
        //W takim razie wyliczamy iloczyn equals() dla poszczególnych wskazanych pól
        Car car = (Car) o; // rzutowanie obiektu o na klasę Car
        return maxSpeed == car.maxSpeed && Objects.equals(name, car.name) && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, engine, maxSpeed);
    }

    /**
     * compareTo zwraca int:
     * wartość mniejsza od zera => "nasz obiekt this" jest "przed" obiektem car przekazanym do metody
     * zero => obiekty są równe
     * wartość większa od zera => obiekt przekazany car jest przed "naszym obiektem this"
     */
    @Override
    public int compareTo(Car car) {
        /**
         * Będziemy sortować po nazwie samochodu alfabetycznie
         * więc wystarzy do compareTo dać porównanie nazw
         * Klasa String też implementuje compareTo więc wykorzystujemy ten fakt
         */
        return this.name.compareTo(car.getName());
//        return car.getName().compareTo(name); => odwrócona kolejność sortowania
    }
}
