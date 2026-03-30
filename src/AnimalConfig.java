import java.awt.Color;
import java.util.*;

/**
 * Carlton created this class to centralize all configuration for animal types in the simulation.
 * This allows for easier maintenance and extension of the simulation without modifying the core logic in Simulator.
 * <p>
 * Configuration class for animal types in the simulation.
 * This class provides a centralized way to configure all animal properties
 * without hard-coding them in the Simulator.
 * </p>
 *
 * @author Carlton - Architect of the decoupling design pattern implementation
 * @version 1.0
 */
public class AnimalConfig {

    /**
     * Configuration data for a specific animal type.
     */
    public static class AnimalTypeConfig {
        private final String name;
        private final double creationProbability;
        private final Color color;
        private final double breedingProbability;
        private final int breedingAge;
        private final int maxAge;
        private final int maxLitterSize;
// Constructor for animal type configuration
        public AnimalTypeConfig(String name, double creationProbability, Color color,
        double breedingProbability, int breedingAge, int maxAge, int maxLitterSize) {
            this.name = name;
            this.creationProbability = creationProbability;
            this.color = color;
            this.breedingProbability = breedingProbability;
            this.breedingAge = breedingAge;
            this.maxAge = maxAge;
            this.maxLitterSize = maxLitterSize;
        }
// Getters for all properties
        public String getName() { return name; }
        public double getCreationProbability() { return creationProbability; }
        public Color getColor() { return color; }
        public double getBreedingProbability() { return breedingProbability; }
        public int getBreedingAge() { return breedingAge; }
        public int getMaxAge() { return maxAge; }
        public int getMaxLitterSize() { return maxLitterSize; }
    }

    // Configuration for all animal types
    private static final Map<String, AnimalTypeConfig> animalConfigs = new HashMap<>();

    static {
        // Register all animal types with their properties
        animalConfigs.put("fox", new AnimalTypeConfig("fox", 0.02, Color.BLUE, 0.02, 15, 70, 2));
        animalConfigs.put("rabbit", new AnimalTypeConfig("rabbit", 0.08, Color.ORANGE, 0.08, 5, 40, 4));
        animalConfigs.put("mouse", new AnimalTypeConfig("mouse", 0.03, Color.GRAY, 0.15, 2, 20, 6));
    }

    /**
     * Get configuration for a specific animal type.
     * @param typeName The name of the animal type (e.g., "fox", "rabbit", "mouse")
     * @return The configuration for that animal type, or null if not found
     */
    public static AnimalTypeConfig getConfig(String typeName) {
        return animalConfigs.get(typeName.toLowerCase());
    }

    /**
     * Get all registered animal type names.
     * @return Array of animal type names
     */
    public static String[] getAllTypeNames() {
        return animalConfigs.keySet().toArray(new String[0]);
    }

    /**
     * Get all registered animal type names.
     * @return Set of all registered animal type names
     * @author Carlton - Added to support decoupled color setup in Simulator
     */
    public static Set<String> getRegisteredTypes() {
        return new HashSet<>(animalConfigs.keySet());
    }

    /**
     * Get all animal configurations.
     * @return Map of all animal configurations
     */
    public static Map<String, AnimalTypeConfig> getAllConfigs() {
        return Collections.unmodifiableMap(animalConfigs);
    }
}