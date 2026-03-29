import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public abstract class Animal
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The animal's age.
    //Carlton - added the age field to make it easier to implement the breeding behaviour of rabbits and foxes.
    private int age;
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }

//Carlton - added the following methods to make it easier to implement the breeding behaviour of rabbits and foxes.
    /**
     * An animal can breed if it has reached the breeding age.
     * @return true if the animal can breed and false otherwise.
     */
    public boolean canBreed()
    {
        return getAge() >= getBreedingAge();
    }
 //Carlton - added getAge() and setAge() methods
    /**
     * Return the age of this animal.
     * @return The age of this animal.
     */
    protected int getAge()
    {
        return age;
    }

    /**
     * Set the age of this animal.
     * @param age The new age of this animal.
     */
    protected void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Return the breeding age of this animal.
     * @return The breeding age of this animal.
     */
    abstract protected int getBreedingAge();

    /**
     * Return the maximum age of this animal.
     * @return The maximum age of this animal.
     */
    abstract protected int getMaxAge();

    /**
     * Return the breeding probability of this animal.
     * @return The breeding probability of this animal.
     */
    abstract protected double getBreedingProbability();

    /**
     * Return the maximum litter size of this animal.
     * @return The maximum litter size of this animal.
     */
    abstract protected int getMaxLitterSize();

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > getMaxAge()) {
            setDead();
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }
}
