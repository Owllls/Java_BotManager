package LikeDataBase;

import java.util.ArrayList;

public class UsersList {

    String Adrees;
    long chatID;
    String username;
    static ArrayList<User> User = new ArrayList<User>();

    public void addUser() {
        if (Adrees != null && username != null)
            User.add(new User(Adrees, chatID, username));

        if (username != null){
            User.add(new User(chatID, username));
        }else {

            User.add(new User(chatID));
        }

    }

    public static ArrayList<UsersList.User> getUser() {
        return User;
    }

    public static void setUser(ArrayList<UsersList.User> user) {
        User = user;
    }




    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdrees() {
        return Adrees;
    }

    public void setAdrees(String adrees) {
        Adrees = adrees;
    }

     class User{

          public User(String Adrees, long chatID, String username){
              this.Adrees = Adrees;
              this.chatID = chatID;
              this.username = username;

          }
         public User(long chatID, String username){

             this.chatID = chatID;
             this.username = username;

         }
         public User(long chatID){
             this.chatID = chatID;


         }
        String Adrees;
        long chatID;
        String username;
    }
}
