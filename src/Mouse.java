import java.util.List;
import java.util.Random;
import java.util.Iterator;


public class Mouse extends Animal
{
    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 2;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 20;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
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
        // New mice are born into adjacent locations.
        // Get a list of adjacent free locations.
        /*List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();*/
        super.giveBirth;
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Mouse young = new Mouse(getField(), loc, false);
            newMice.add(young);
        }
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
