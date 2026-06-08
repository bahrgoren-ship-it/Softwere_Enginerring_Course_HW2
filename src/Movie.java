
public class Movie {
    private String movieName;
    private Genre genre;
    private int releaseYear;
    private Director director;

    /**
     * Constructor.
     */

    public Movie(String movieName, Genre genre, int releaseYear, String directorName) {
        this.movieName = movieName;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = Director.getDirector(directorName);//get a director by name or creates new one
    }

    /**
     * Displays movie information.
     */
    public void showMovie() {
        System.out.print("Title: " + this.movieName + ", Genre: " + this.genre);
        System.out.print(", Year: " + this.releaseYear + ", director: ");
        this.director.showDirector();
        System.out.println(".");
    }

    /**
     * Returns whether true if both movies are the same.
     */
    public boolean equals(Movie movie) {
        if (this.movieName.equals(movie.movieName) && this.genre == movie.genre) {
            if(this.releaseYear == movie.releaseYear && this.director.equals(movie.director)){
                return true;
            }
        }
        return false;
    }

}
