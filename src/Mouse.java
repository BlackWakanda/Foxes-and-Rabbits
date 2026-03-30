import java.util.List;
import java.util.Random;
import java.util.Iterator;

//Dean created this class to represent the mouse animal type in the simulation.
// It extends the abstract Animal class and implements the specific behavior and characteristics of mice,
// such as breeding age, maximum age, breeding probability, and litter size.
// The Mouse class also defines how mice act in each simulation step,
// including aging, giving birth, and moving to new locations in the field.
public class Mouse extends Animal
{
    private static final int BREEDING_AGE = 2;
    private static final int MAX_AGE = 20;
    private static final double BREEDING_PROBABILITY = 0.12;
    private static final int MAX_LITTER_SIZE = 4;
    
    
    public Mouse(Field field, Location location, boolean randomAge)
    {
        super(field, location);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
        }
    }

// This is what the mouse does most of the time - it runs around. Sometimes it will breed or die of old age.
    /*
        @param newMice A list to return newly born mice.
     */

    public void act(List<Animal> newMice)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newMice);            
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
// Check whether or not this mouse is to give birth at this step. New births will be made into free adjacent locations.
     /*
        @param newMice A list to return newly born mice.
     */
    public void giveBirth(List<Animal> newMice)
    {
        super.giveBirth(newMice);
    }

    /**
     * Create a new young mouse.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return A new young mouse.
     */
    protected Animal createYoung(Field field, Location location)
    {
        return new Mouse(field, location, false);
    }

    protected int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    protected int getMaxAge()
    {
        return MAX_AGE;
    }

    protected double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    protected int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }
}
