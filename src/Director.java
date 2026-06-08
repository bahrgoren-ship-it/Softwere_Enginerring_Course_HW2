public class Director {
    public static final int directorNotFound = -1;

    String name;
    Movie[] moviesDirected = new Movie[Main.MAX_NUMBER_OF_MOVIES];
    int numberOfMoviesDirected = 0;

    Director(String directorName){
        this.name = directorName;
    }

    public boolean equals(Director otherDirector) {
        return this.name.equals(otherDirector.name);
    }

    void addMovie(Movie movie){

    }

    static Director getDirector(String directorName) {

        Director existingDirector = RentalSystem.directorExists(directorName);

        if (existingDirector != null) {
            return existingDirector;
        }

        return new Director(directorName);
    }

    String show(){
        return this.name;
    }
}