import java.util.List;
import java.util.Random;
import java.util.Iterator;


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
