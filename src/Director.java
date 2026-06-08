public class Director {
    public static final int directorNotFound = -1;

    String name;
    Movie[] moviesDirected = new Movie[Main.MAX_NUMBER_OF_MOVIES];
    int moviesDirected = 0;

    Director(String directorName){
        this.name = directorName;
    }

    public boolean equals(Director otherDirector) {
        return this.name.equals(otherDirector.name);
    }

}
