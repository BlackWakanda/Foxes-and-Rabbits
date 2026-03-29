
import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    
    // Individual characteristics (instance fields).
    
    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        setAge(0);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
        }
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Animal> newRabbits)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newRabbits);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    protected void giveBirth(List<Animal> newRabbits)
    {
        super.giveBirth(newRabbits);
    }

    /**
     * Create a new young rabbit.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return A new young rabbit.
     */
    protected Animal createYoung(Field field, Location location)
    {
        return new Rabbit(false, field, location);
    }
        
    /**
     * Return the age at which a rabbit starts to breed.
     * @return The age at which a rabbit starts to breed.
     */
    protected int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * Return the maximum age of a rabbit.
     * @return The maximum age of a rabbit.
     */
    protected int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * Return the breeding probability of a rabbit.
     * @return The breeding probability of a rabbit.
     */
    protected double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * Return the maximum litter size of a rabbit.
     * @return The maximum litter size of a rabbit.
     */
    protected int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }
}