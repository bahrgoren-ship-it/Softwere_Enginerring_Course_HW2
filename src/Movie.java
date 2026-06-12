
public class Movie {

    private String movieName;
    private Genre genre;
    private int releaseYear;
    private Director director;
    private int rentCounter=0;

    /**
     * Constructor.
     *
     * @param movieName the name of the movie
     * @param genre the genre of the movie
     * @param releaseYear the release year of the movie
     * @param director the director of the movie
     */

    public Movie(String movieName, Genre genre, int releaseYear, Director director) {
        this.movieName = movieName;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
    }
    /**
     * Constructor, creates movie without a genre.
     *
     * @param movieName the name of the movie
     * @param releaseYear the release year of the movie
     * @param director the director of the movie
     */

    public Movie(String movieName, int releaseYear, Director director) {
        this(movieName,Genre.ACTION,releaseYear,director);
    }

    /**
     * Displays movie information.
     */

    public void showMovie() {
        System.out.print("Title: " + this.movieName + ", Genre: " + this.genre);
        System.out.print(", Year: " + this.releaseYear + ", director: " + this.director.getName());
        System.out.println(".");
    }

    /**
     * Checks if both movies are the same.
     * A movie is identified by its name, release year, and director.
     *
     * @param movie the movie to compare to
     * @return true if same and false otherwise
     */
    public boolean equals(Movie movie) {
        if(movie == null) return false;
        if (this.movieName.equals(movie.movieName) && this.releaseYear == movie.releaseYear
                && this.director.equals(movie.director)){
                return true;
        }
        return false;
    }

    /**
     * Gets the movie's name.
     *
     * @return the name of the movie
     */
    public String getMovieName() {
        return this.movieName;
    }

    public void increaseRentCounter(){
        this.rentCounter++;
    }
    public void decreaseRentCounter(){
        this.rentCounter--;
    }
    public int getRentCounter(){
        return this.rentCounter;
    }
    /**
     * Gets the movie's release year.
     *
     * @return the release year
     */
    public int getReleaseYear() {
        return this.releaseYear;
    }

    /**
     * Gets the movie's director.
     *
     * @return the director of the movie
     */
    public Director getDirector() {
        return this.director;
    }

}
