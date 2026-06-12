
public class RentalSystem {

    /** The maximum number of movies the system can handle.*/
    public static final int MAX_NUMBER_OF_MOVIES = 30;


    /** The movie list.*/
    private Movie[] movieList;

    /**The index at which to write to movieList.*/
    private int movieIndex=0;

    /** The active cutomer list*/
    private  Customer[] activeCustomers;

    /**The index at which to write to activeCustomers*/
    private int customerIndex=0;

    /** The maximum number of customers the system can handle.*/
    public static final int MAX_NUMBER_OF_CUSTOMERS = 30;

    /**
     * Constructs a new Rental System.
     */
    public RentalSystem(){
        this.movieList= new Movie[MAX_NUMBER_OF_MOVIES];
        for(int i=0;i<MAX_NUMBER_OF_MOVIES;i++){//init movie list
            this.movieList[i]=null;
        }
        this.activeCustomers= new Customer[MAX_NUMBER_OF_CUSTOMERS];
        for(int i=0;i<MAX_NUMBER_OF_CUSTOMERS;i++){//init active customer list
            this.activeCustomers[i]=null;
        }
    }

    /**
     *
     * @param movieName name of the movie we wish to add.
     * @param genre of the movie.
     * @param releaseYear of the movie.
     * @param directorName of the movie.
     * @param biography of the director.
     * Adds movie to system or displays that movie cannot be added.
     */
    public void addMovie(String movieName,Genre genre, int releaseYear,String directorName,String biography){
        if(this.movieIndex>=30){
            System.out.println("System is full, Cannot add more movies.");
            return;
        }
        if(this.movieIndex==0){//
            Director directorToAdd= new Director(directorName, biography);
            Movie movieToAdd= new Movie(movieName,genre,releaseYear,directorToAdd);
            movieList[0]=movieToAdd;
            return;
        }
        for(int i=0;i<movieIndex)

    }

}
