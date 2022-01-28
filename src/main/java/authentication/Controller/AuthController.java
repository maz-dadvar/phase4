package authentication.Controller;

import authentication.View.LogInFormEvent;
import authentication.View.RegistrationFormEvent;
import controller.Tweet.NewTweet.SendDataToServer;
import controller.UserDB;
import controller.UserWorker;
import interfaces.Dimensions;
import model.RequestToServer;
import model.User;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class AuthController extends controller.Controller implements Dimensions {

    public static User currentUser;
    public static UserDB userDB = new UserDB();
    private UserWorker userWorker;
    public static Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean hasConnectedToServer;

    public AuthController(){
        this.hasConnectedToServer = false;
    }

    public void register(RegistrationFormEvent e) {
        User user = null;
        if (!hasConnectedToServer){
            try {
                Toolkit.getDefaultToolkit().beep();
                socket = new Socket(host, port);
                setSocket(socket);
                hasConnectedToServer = true;
                userWorker = new UserWorker(socket);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                e.setServerFound(true);
            } catch (IOException ioException) {
                System.out.println("Server not found!");
                e.setServerFound(false);
            }
        }

        if (hasConnectedToServer){
            user = new User(e.getName(), e.getLastname(), e.getEmailAddress(), e.getUsername(), e.getPassword(), true);
            user.phoneNumber = e.getPhoneNumber();
            user.dateOfBirth = e.getDateOfBirth();
            user.bio = e.getBio();
            currentUser = user;
            sendUserDataToServer(user);
            if (checkValidInputs(user)){
                userDB.add(user);
                e.setValid(true);
                currentUser = userDB.get(userDB.getId(e.getUsername()));
                user = currentUser;
                System.out.println(user);
                System.out.println("Hi " + e.getUsername() + " !");
                System.out.println("Your Id is : " + user.id);
            } else {
                e.setValid(false);
                System.out.println("Zeki !");
            }
        }
        System.out.println("Hi " + e.getName() + " !");
    }

    private boolean checkValidInputs(User user) {
        int i = 0;
        if (validName(user.getName(), user.getLastname())){
            i++;
        }
        if (validEmail(user.getEmailAddress())){
            i++;
        }
        if (validUsername(user.getUsername())){
            i++;
        }
        if (validPassword(user.getPassword())){
            i++;
        }
        return i == 4;
    }

    private boolean validName(String name, String lastname){
        boolean validName = true;
        for (int i = 0; i < name.length(); i++){
            if (name.charAt(i) < 65 || name.charAt(i) > 122 || (name.charAt(i) > 90 && name.charAt(i) < 97)){
                validName = false;
                break;
            }
        }
        for (int i = 0; i < lastname.length(); i++){
            if (lastname.charAt(i) < 65 || lastname.charAt(i) > 122 || (lastname.charAt(i) > 90 && lastname.charAt(i) < 97)){
                validName = false;
                break;
            }
        }
        return validName;
    }

    private boolean validEmail(String emailAddress){
        boolean flag = true;
        if (!emailAddress.contains("@")){
            flag = false;
        }
        else {
            int index = emailAddress.indexOf('@');
            String temp = emailAddress.substring(index, emailAddress.length());
            if (!temp.contains(".")){
                flag = false;
            }
        }
        if (flag){
            return userDB.emailExists(emailAddress);
        } else {
            return false;
        }
    }

    private boolean validUsername(String username){
        return !userDB.usernameExists(username);
    }

    private boolean validPassword(String password){
        boolean flag1 = false, flag2 = false;
        for (int i = 0; i < password.length(); i++){
            if (Character.isLetter(password.charAt(i))){
                flag1 = true;
            }
            else if (Character.isDigit(password.charAt(i))){
                flag2 = true;
            }
        }
        if (password.length() < 8){
            flag1 = false;
        }
        if (!flag1 || !flag2){
            System.out.println("invalid password");
        }
        return flag1 && flag2;
    }

    public void logIn(LogInFormEvent e) {
        if (!hasConnectedToServer){
            try {
                Toolkit.getDefaultToolkit().beep();
                socket = new Socket(host, port);
                setSocket(socket);
                hasConnectedToServer = true;
                userWorker = new UserWorker(socket);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                e.setServerFound(true);
            } catch (IOException ioException) {
                System.out.println("Server not found!");
                e.setServerFound(false);
            }
        }
        if (hasConnectedToServer){
            User user = new User(e.getUsername(), e.getPassword());
            currentUser = user;
            sendUserDataToServer(user);
            if (isValidUser(user)){
                e.setValid(true);
                currentUser = userDB.get(userDB.getId(e.getUsername()));
                user = currentUser;
                System.out.println(user);
                System.out.println("Hi " + e.getUsername() + " !");
                System.out.println("Your Id is : " + user.id);
            } else {
                e.setValid(false);
                System.out.println("Zeki !");
            }
        }

    }

    public boolean isValidUser(User user) {
        boolean isValid = false;
        LinkedList<User> users = new UserDB().all();
        for (User user1 : users) {
            if (user1.username.equals(user.username) && user1.password.equals(user.password)){
                isValid = true;
                break;
            }
        }
        users.clear();
        return isValid;
    }

    public void setSocket(Socket socket) {
        AuthController.socket = socket;
    }

    private void sendUserDataToServer(User user){
        try {
            System.out.println(user.getUsername());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(user);
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("User data hasn't sent to server!");
        }
    }

    public static void submitLastSeen(RequestToServer request){
        SendDataToServer sendDataToServer = new SendDataToServer();
        sendDataToServer.sendToServer(request);
    }
}
