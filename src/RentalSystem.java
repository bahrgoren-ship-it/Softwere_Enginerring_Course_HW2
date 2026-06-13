
public class RentalSystem {

    /**
     * The maximum number of movies the system can handle.
     */
    public static final int MAX_NUMBER_OF_MOVIES = 30;


    /**
     * The movie list.
     */
    private Movie[] movieList;

    /**
     * The index at which to write to movieList.
     */
    private int movieIndex = 0;

    /**
     * The active customer list
     */
    private Customer[] activeCustomers;

    /**
     * The index at which to write to activeCustomers
     */
    private int customerIndex = 0;

    /**
     * The maximum number of customers the system can handle.
     */
    public static final int MAX_NUMBER_OF_CUSTOMERS = 30;

    /**
     * The directors that have movies in the system.
     */
    private Director[] directorList;

    /**
     * The index at which to write to directorList
     */
    private int directorIndex = 0;


    /**
     * Constructs a new Rental System.
     */
    public RentalSystem() {
        this.movieList = new Movie[MAX_NUMBER_OF_MOVIES];
        for (int i = 0; i < MAX_NUMBER_OF_MOVIES; i++) {//init movie list
            this.movieList[i] = null;
        }
        this.activeCustomers = new Customer[MAX_NUMBER_OF_CUSTOMERS];
        for (int i = 0; i < MAX_NUMBER_OF_CUSTOMERS; i++) {//init active customer list
            this.activeCustomers[i] = null;
        }
        this.directorList = new Director[MAX_NUMBER_OF_MOVIES];// cannot be more directors than movies
        for (int i = 0; i < MAX_NUMBER_OF_MOVIES; i++) {
            this.directorList[i] = null;
        }
    }

    /**
     *Adds movie to system or displays that movie cannot be added.
     *
     * @param movieName    name of the movie we wish to add.
     * @param genre        of the movie.
     * @param releaseYear  of the movie.
     * @param directorName of the movie.
     * @param biography    of the director.
     */
    public void addMovie(String movieName, Genre genre, int releaseYear, String directorName, String biography) {
        if (this.movieIndex >= MAX_NUMBER_OF_MOVIES) {
            System.out.println("System is full. Cannot add more movies.");
            return;
        }

        boolean isNewDirector = true;
        Director directorToAdd = null;
        for (int i = 0; i < directorIndex; i++) {
            if (directorName.equals(directorList[i].getName())) {// instance already exists
                isNewDirector = false;
                directorToAdd = directorList[i];
                break;
            }
        }
        if (isNewDirector) {
            directorToAdd = new Director(directorName, biography);
            // directorIndex is less or equal to movie index which is less than max so were in bounds.
            directorList[directorIndex] = directorToAdd;
            directorIndex++;
        }
        Movie movieToAdd = new Movie(movieName, genre, releaseYear, directorToAdd);
        for (int i = 0; i < movieIndex; i++) {//not first movie so we must check for duplicates
            if (movieToAdd.equals(movieList[i])) {//movie already exists
                System.out.println("Movie is already in the system.");
                return;
            }
        }
        movieList[movieIndex] = movieToAdd;
        movieIndex++;
    }

    /**
     * Removes movie from the system.
     *
     * @param movieName    name of the movie we wish to remove.
     * @param releaseYear  of the movie.
     * @param directorName of the movie.
     */
    public void removeMovie(String movieName, int releaseYear, String directorName) {

        // Finds the director of the movie as a Director object.
        Director director = null;
        int targetDirectorIndex;
        for (targetDirectorIndex = 0; targetDirectorIndex < directorIndex; targetDirectorIndex++) {
            if ((directorList[targetDirectorIndex].getName()).equals(directorName)) {
                director = directorList[targetDirectorIndex];
                break;
            }
        }

        if (director == null) {
            System.out.println("No such movie exists.");
            return;
        }

        // Finds the movie to remove in movieList.
        Movie targetMovie = new Movie(movieName, null, releaseYear, director);
        boolean movieFound = false;
        int targetMovieIndex;
        for (targetMovieIndex = 0; targetMovieIndex < movieIndex; targetMovieIndex++) {
            if (movieList[targetMovieIndex].equals(targetMovie)) {
                targetMovie = movieList[targetMovieIndex];
                movieFound = true;
                break;
            }
        }

        if (!movieFound) {
            System.out.println("No such movie exists.");
            return;
        }

        if (targetMovie.getRentCounter() > 0) {
            System.out.println("Cannot remove rented movie.");
            return;
        }

        // Remove movie from movieList.
        movieList[targetMovieIndex] = movieList[movieIndex - 1];
        movieList[movieIndex - 1] = null;
        movieIndex--;

        // Removes director if he has no movies to his name in the system.
        boolean directorStillHasMovies = false;
        for (int i = 0; i < movieIndex; i++) {
            if ((movieList[i].getDirector()).equals(director)) {
                directorStillHasMovies = true;
                break;
            }
        }
        if (!directorStillHasMovies) {
            directorList[targetDirectorIndex] = directorList[directorIndex - 1];
            directorList[directorIndex - 1] = null;
            directorIndex--;
        }

    }

    /**
     * Displays both rented and unrented movies in the system.
     */
    public void printMovies() {
        boolean isRentedEmpty = true;
        boolean isUnrentedEmpty = true;
        System.out.println("Rented Movies: ");
        for (int i = 0; i < movieIndex; i++) {
            if (this.movieList[i].getRentCounter() > 0) {
                movieList[i].showMovie();
                isRentedEmpty = false;
            }
        }
        if (isRentedEmpty) {
            System.out.println("No Rented movies.");
        }
        System.out.println("Unrented Movies: ");
        for (int i = 0; i < movieIndex; i++) {
            if (this.movieList[i].getRentCounter() == 0) {
                movieList[i].showMovie();
                isUnrentedEmpty = false;
            }
        }
        if (isUnrentedEmpty) {
            System.out.println("No Unrented movies.");
        }
    }

    /**
     * Rents a movie for the customer or Displays that there's been an error.
     *
     * @param customerName the customer's name.
     * @param customerID   the customer's ID.
     * @param movieName    the movie's name
     * @param releaseYear  the release year of the movie.
     * @param directorName the director's name.
     */
    public void rentMovie(String customerName, String customerID, String movieName, int releaseYear, String directorName) {
        Director directorToRent = null;
        for (int i = 0; i < directorIndex; i++) {
            if (directorName.equals(directorList[i].getName())) {// instance already exists
                directorToRent = directorList[i];
                break;
            }
        }
        if (directorToRent == null) {// No director with given name.
            System.out.println("No such movie exists");
            return;
        }

        Movie movieToRent = new Movie(movieName, null, releaseYear, directorToRent);
        boolean movieExists = false;
        for (int i = 0; i < movieIndex; i++) {
            if (movieToRent.equals(movieList[i])) {
                movieToRent = movieList[i];
                movieExists = true;
            }
        }
        if (!movieExists) {
            System.out.println("No such movie exists");
            return;
        }

        boolean isNewCustomer = true;
        Customer customerToRent = null;
        for (int i = 0; i < customerIndex; i++) {
            if (customerID.equals(activeCustomers[i].getId())) {
                isNewCustomer = false;
                customerToRent = activeCustomers[i];
                break;
            }
        }
        if (isNewCustomer && customerIndex >= MAX_NUMBER_OF_CUSTOMERS) {
            System.out.println("No room for new customers");
            return;
        }
        if (isNewCustomer) {
            customerToRent = new Customer(customerName, customerID);
        }
        if (customerToRent.rentMovie(movieToRent)) {
            if (isNewCustomer) {
                activeCustomers[customerIndex] = customerToRent;
                customerIndex++;
            }
        }
    }

    /**
     * Returns a movie from a customer or Displays that there's been an error.
     *
     * @param customerID   the customer's ID.
     * @param movieName    the movie's name
     * @param releaseYear  the release year of the movie.
     * @param directorName the director's name.
     */
    public void returnMovie(String customerID, String movieName, int releaseYear, String directorName) {
        Director director = null;
        for (int i = 0; i < directorIndex; i++) {
            if (directorName.equals(directorList[i].getName())) {// instance already exists
                director = directorList[i];
                break;
            }
        }
        if (director == null) {// No director with given name found.
            System.out.println("Customer cannot return the movie.");
            return;
        }

        int targetCustomerIndex = -1;
        for(int i = 0; i < customerIndex; i++){
            if(activeCustomers[i].getId().equals(customerID)){
                targetCustomerIndex = i;
                break;
            }
        }
        if (targetCustomerIndex == -1) {// No customer with given ID found.
            System.out.println("Customer not found.");
            return;
        }

        Customer targetCustomer = activeCustomers[targetCustomerIndex];

        Movie targetMovie = targetCustomer.getRentedMovie(movieName, releaseYear, director);
        if(targetMovie == null){
            System.out.println("Customer cannot return the movie.");
            return;
        }

        targetCustomer.returnMovie(targetMovie);

        if (targetCustomer.getRentedMoviesNumber() == 0) {
            activeCustomers[targetCustomerIndex] = activeCustomers[customerIndex - 1];
            activeCustomers[customerIndex - 1] = null;
            customerIndex--;
        }

    }
}
