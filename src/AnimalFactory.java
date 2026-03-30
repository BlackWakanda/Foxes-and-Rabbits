import java.util.*;
import java.util.function.BiFunction;

/**
 * Carlton created this class to serve as a factory for creating animals in the simulation.
 * This design allows for dynamic registration of new animal types without modifying the core logic of the Simulator.
 * <p>
 * The AnimalFactory class provides a centralized way to create animal instances based on their type names,
 * eliminating direct class instantiation and promoting loose coupling.
 * </p>
 *
 * @author Carlton - Implemented factory pattern for decoupling animal creation
 * @version 1.0
 */
public class AnimalFactory {

    // Map of animal type names to their creation functions
    // The function takes (Field, Location) and returns an Animal with random age
    private static final Map<String, BiFunction<Field, Location, Animal>> animalCreators = new HashMap<>();

    // Static initialization block to register all animal types
    static {
        // Register creation functions for each animal type
        // All animals are created with randomAge = true for initial population
        animalCreators.put("fox", (field, location) -> new Fox(true, field, location));
        animalCreators.put("rabbit", (field, location) -> new Rabbit(true, field, location));
        animalCreators.put("mouse", (field, location) -> new Mouse(field, location, true));
    }

    /**
     * Create an animal of the specified type at the given location.
     * @param typeName The name of the animal type to create (e.g., "fox", "rabbit", "mouse")
     * @param field The field where the animal will be placed
     * @param location The location where the animal will be placed
     * @return A new instance of the specified animal type, or null if type not found
     * @author Carlton - Core factory method for decoupled animal creation
     */
    public static Animal createAnimal(String typeName, Field field, Location location) {
        BiFunction<Field, Location, Animal> creator = animalCreators.get(typeName.toLowerCase());
        if (creator != null) {
            return creator.apply(field, location);
        }
        return null;
    }

    /**
     * Register a new animal type with its creation function.
     * @param typeName The name of the animal type
     * @param creator The function that creates instances of this animal type
     *               Takes (Field, Location) and returns an Animal
     */
    public static void registerAnimalType(String typeName, BiFunction<Field, Location, Animal> creator) {
        animalCreators.put(typeName.toLowerCase(), creator);
    }

    /**
     * Check if an animal type is registered.
     * @param typeName The name of the animal type to check
     * @return true if the type is registered, false otherwise
     */
    public static boolean isAnimalTypeRegistered(String typeName) {
        return animalCreators.containsKey(typeName.toLowerCase());
    }

    /**
     * Get all registered animal type names.
     * @return Set of all registered animal type names
     */
    public static Set<String> getRegisteredTypes() {
        return animalCreators.keySet();
    }

    private Class<?> getAnimalClass(String typeName) {
        try {
            switch (typeName.toLowerCase()) {
                case "fox": return Class.forName("Fox");
                case "rabbit": return Class.forName("Rabbit");
                case "mouse": return Class.forName("Mouse");
                default: return null;
            }
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Unregister an animal type.
     * @param typeName The name of the animal type to unregister
     * @return true if the type was unregistered, false if it wasn't registered
     */
    public static boolean unregisterAnimalType(String typeName) {
        return animalCreators.remove(typeName.toLowerCase()) != null;
    }
}