
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

    /** Displays both rented and unrented movies in the system*/
    public void printMovies(){
        boolean isRentedEmpty=true;
        boolean isUnrentedEmpty=true;
        System.out.println("Rented movies:");
        for(int i=0;i<movieIndex;i++){
            if(this.movieList[i].getRentCounter()>0){
                movieList[i].showMovie();
                isRentedEmpty=false;
            }
        }
        if(isRentedEmpty){
            System.out.println("No Rented movies.");
        }
        System.out.println("Unrented movies:");
        for(int i=0;i<movieIndex;i++){
            if(this.movieList[i].getRentCounter()==0){
                movieList[i].showMovie();
                isUnrentedEmpty=false;
            }
        }
        if(isUnrentedEmpty){
            System.out.println("No Unrented movies.");
        }
    }

    /**
     * Rents a movie for the customer or Displays that there's been an error.
     * @param customerName the customer's name.
     * @param customerID the customer's ID.
     * @param movieName the movie's name
     * @param releaseYear the release year of the movie.
     * @param directorName the director's name.
     */
    public void rentMovie(String customerName, int customerID,String movieName,int releaseYear,String directorName){
        Director directorToRent=null;
        for(int i=0;i<directorIndex;i++){
            if(directorName.equals(directorList[i].getName())){// instance already exists
                directorToRent=directorList[i];
                break;
            }
        }
        if(directorToRent==null){// no director with given name
            System.out.println("No such movie exists");
            return;
        }

        if(customerIndex>=MAX_NUMBER_OF_CUSTOMERS){
            System.out.println("No room for new customers.");
        }

        boolean isNewCustomer=true;
        Customer customerToRent=null;
        for(int i=0;i<customerIndex;i++){
            if(customerID==activeCustomers[i].getId()){
                isNewCustomer=false;
                customerToRent=activeCustomers[i];
            }
        }
        if(isNewCustomer){
            customerToRent=new Customer(customerName,customerID);
            customerIndex++;
        }

        Movie movieToRent=new Movie(movieName,releaseYear,directorToRent);
        boolean movieExists=false;
        for(int i=0;i<movieIndex;i++){
            if(movieToRent.equals(movieList[i])){
                movieToRent=movieList[i];
                movieExists=true;
            }
        }
        if(!movieExists){
            System.out.println("No such movie exists");
            return;
        }
        customerToRent.rentMovie(movieToRent);
    }
}
