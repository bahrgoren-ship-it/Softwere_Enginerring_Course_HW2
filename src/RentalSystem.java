
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

    /** The directors that have movies in the system and thus instancized*/
    private Director[] directorList;

    /** The index at which to write to directorList*/
    private int directorIndex=0;

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
        this.directorList=new Director[MAX_NUMBER_OF_MOVIES];// cannot be more directors than movies
        for(int i=0;i<MAX_NUMBER_OF_MOVIES;i++){
            this.directorList[i]=null;
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
        if(this.movieIndex>=MAX_NUMBER_OF_MOVIES){
            System.out.println("System is full. Cannot add more movies.");
            return;
        }

        boolean isNewDirector=true;
        Director directorToAdd=null;
        for(int i=0;i<directorIndex;i++){
            if(directorName.equals(directorList[i].getName())){// instance already exists
                isNewDirector=false;
                directorToAdd=directorList[i];
                break;
            }
        }
        if(isNewDirector){
            directorToAdd=new Director(directorName,biography);
            // directorIndex is less or equal to movie index which is less than max so were in bounds.
            directorList[directorIndex]=directorToAdd;
            directorIndex++;
        }
        Movie movieToAdd=new Movie(movieName,genre,releaseYear,directorToAdd);
        for(int i=0;i<movieIndex;i++){//not first movie so we must check for duplicates
            if(movieToAdd.equals(movieList[i])){//movie already exists
                System.out.println("Movie is already in the system.");
                return;
            }
        }
        movieList[movieIndex]=movieToAdd;
        movieIndex++;
    }
    public void printMovies(){
        for(int i=0;i<)
    }
}
