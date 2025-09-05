package ticket.booking.services;
import ticket.booking.entities.User;
import ticket.booking.entities.Train;
import ticket.booking.services.TrainService;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import ticket.booking.utils.UserServiceUtil;

public class UserBookingService {

    private User user;
    private List<User> userList;
    //used to serialize and deserialize data found in jackson.data library
    private  static final ObjectMapper objectMapper=new ObjectMapper();
    //final keyword makes sure nobody can change its value further
    private static final String USERS_PATH="../localDb/users.json";

    public UserBookingService(User user1) throws IOException{
        this.user=user1;
        loadUsers();
    }
    public UserBookingService() throws IOException{
        loadUsers();
    }
    public List<User> loadUsers() throws IOException{
        File users= new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>(){});        
    }
    public Boolean loginUser(){
        Optional<User> foundUser=userList.stream().filter(user1->{
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch(IOException ex){
            return Boolean.FALSE;
        }
    }
    public void saveUserListToFile() throws IOException{
        File usersFile=new File(USERS_PATH);
        ObjectMapper.writeValue(usersFile, userList);//serialization is happening
    }
    //when you take object from json and map it -> deserialization
    //opposite is serialization
    
    public void fetchBooking(){
        user.printTickets();
    }
    //abhi krna h
    public void cancelBooking(String TicketId){

    }
    public List<Train> getTrains(String source,String destination){
        try{
            TrainService trainService =new TrainService();
            return trainService.searchTrains(source,destination);
        }catch(IOException e){
            return new ArrayList<>();
        }
    }
}