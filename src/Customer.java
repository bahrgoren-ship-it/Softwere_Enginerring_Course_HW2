/**
 * Holds the name of each customer, id and the movies he rented.
 */

public class Customer {
    /**
     * The maximum number of movies a customer can rent.
     */
    public static final int MAX_MOVIES_PER_CUSTOMER = 5;

    /**
     * Tell if the movie is not rented by the customer.
     */
    public static final int MOVIE_NOT_RENTED_BY_CUSTOMER = -1;

    /**
     * Customer name.
     */
    private String name;

    /**
     * Customer id.
     */
    private String id;

    /**
     * Movies the customer rented.
     */
    private Movie[] rentedMovies = new Movie[MAX_MOVIES_PER_CUSTOMER];

    /**
     * Counts movies rented and points to list end.
     */
    private int rentedMoviesNumber = 0;

    /**
     * Constructs a new Customer.
     *
     * @param newCustomerName name of new customer
     * @param newCustomerId   id of new customer
     */
    public Customer(String newCustomerName, String newCustomerId) {
        this.name = newCustomerName;
        this.id = newCustomerId;
    }

    /**
     * Rents a movie - add it to rented movies list.
     *
     * @param movieToRent the movie to rent
     * @return true if added successfully and false if not
     */
    public boolean rentMovie(Movie movieToRent) {
        if (movieToRent == null) return false;
        if (this.rentedMoviesNumber >= MAX_MOVIES_PER_CUSTOMER) {
            System.out.println("The customer has reached the limit");
            return false;
        }
        for (int i = 0; i < rentedMoviesNumber; i++) {
            if (rentedMovies[i].equals(movieToRent)) {
                System.out.println("Customer already has this movie.");
                return false;
            }
        }
        this.rentedMovies[this.rentedMoviesNumber] = movieToRent;
        movieToRent.increaseRentCounter();
        rentedMoviesNumber++;
        return true;
    }


    /**
     * Finds a rented movie's index in rentedMovies.
     *
     * @param movie the rented movie to find the index of
     * @return the index of the rented movie in rentedMovies
     */
    private int rentedMovieIndex(Movie movie) {
        for (int i = 0; i < rentedMoviesNumber; i++) {
            if (this.rentedMovies[i].equals(movie)) return i;
        }

        return MOVIE_NOT_RENTED_BY_CUSTOMER;
    }

    /**
     * Returns a movie - delete it from rented movies list.
     * Adjust movie indexes.
     *
     * @param movieToReturn the movie to return
     * @return true if returned successfully and false if not
     */
    public boolean returnMovie(Movie movieToReturn) {
        int index = rentedMovieIndex(movieToReturn);
        if (movieToReturn == null || index == MOVIE_NOT_RENTED_BY_CUSTOMER) return false;

        movieToReturn.decreaseRentCounter();
        this.rentedMovies[index] = this.rentedMovies[rentedMoviesNumber - 1];
        rentedMovies[rentedMoviesNumber - 1] = null;
        rentedMoviesNumber--;

        return true;
    }

    /**
     * Gets the customer's name.
     *
     * @return the name of the customer
     */
    public String getCustomerName() {
        return this.name;
    }


    /**
     * Gets the customer's id.
     *
     * @return the customer's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Equals for customers - checks only id's.
     *
     * @param otherCustomer the other customer to compare to
     * @return true if both customers have the same id, false otherwise
     */
    public boolean equals(Customer otherCustomer) {
        if (otherCustomer == null) return false;
        return this.id.equals(otherCustomer.id);
    }

    /**
     * Find a movie that the customer rented by its details.
     *
     * @param targetMovieName The name of the target movie.
     * @param targetMovieReleaseYear The release year of the target movie.
     * @param targetDirector The director of the target movie.
     * @return The movie from rentedMovies as a Movie object.
     */
    public Movie getRentedMovie(String targetMovieName, int targetMovieReleaseYear, Director targetDirector){
        Movie targetMovie = new Movie(targetMovieName, null, targetMovieReleaseYear, targetDirector);
        for(int i = 0; i < rentedMoviesNumber; i++){
            if(rentedMovies[i].equals(targetMovie)){
                return rentedMovies[i];
            }
        }
        return null;
    }
}
