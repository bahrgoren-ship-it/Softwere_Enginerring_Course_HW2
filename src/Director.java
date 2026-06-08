/**
* Holds the name of each director, biography and basic methods.
*/

public class Director {

    /** Name of the director. */
    private String name;

    /** Biography of the director. */
    private String biography;

    /** Constructs a new Director only with a name.
     *
     * @param directorName the name of the director
     */
    public Director(String directorName){
        this.name = directorName;
    }

    /**
     * Constructs a new Director with a name and biography.
     *
     * @param directorName the name of the director
     * @param directorBiography the biography of the director
     */
    public Director(String directorName, String directorBiography){
        this.name = directorName;
        this.biography = directorBiography;
    }

    /**
     * Equals for directors - only compares names.
     *
     * @param otherDirector the other director to compare against
     * @return true if both directors have the same name, false otherwise
     */
    public boolean equals(Director otherDirector) {
        if(otherDirector == null) return false;
        return this.name.equals(otherDirector.name);
    }

    /**
     * Gives the director's name.
     *
     * @return the name of the director
     */
    public String getName(){
        return this.name;
    }
}