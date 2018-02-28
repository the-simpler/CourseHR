package HRInformationSystem.controller;

import HRInformationSystem.model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientConnection {
    public static Socket socket = null;
    public static PrintStream ps;
    public static BufferedReader br;
    public static ObjectInputStream deserializer;
    public static ObjectOutputStream serializer;

    public static void connection() throws UnknownHostException, IOException {

        socket = new Socket(InetAddress.getLocalHost(), 8888);
        ps = new PrintStream(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        serializer = new ObjectOutputStream(socket.getOutputStream());
        deserializer = new ObjectInputStream(socket.getInputStream());
    }

    public static List<Skills> getSkills() {
        List<Skills> skills = new ArrayList<>();
        try {
            serializer.writeObject("GetSkills");
            System.out.println("ShowsKILLs");
            skills = (List<Skills>) deserializer.readObject();
            System.out.println(1234);
            System.out.println(skills.get(1).name);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return skills;
    }

    public static String regUserAdm(User user, Object person)throws IOException{
        try {
            serializer.writeObject("RegistrationAdmin");
            serializer.writeObject(user);
            serializer.writeObject(person);
            serializer.flush();

            String input = (String) deserializer.readObject();
            return input;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "UNSUCCESS";
    }
    public static String regUserClient(User user, Object person){
        try {
            serializer.writeObject("RegistrationClient");
            serializer.writeObject(user);
            serializer.writeObject(person);
            serializer.flush();

            String input = (String) deserializer.readObject();
            return input;
        }catch(Exception ex){
           // ex.printStackTrace();
            System.out.println(ex);
        }
        return "UNSUCCESS";
    }


    public static User checkUser(String login, String password)throws IOException{
        try {
            serializer.writeObject("Authorization");
            serializer.writeObject(login);
            serializer.writeObject(password);
            serializer.flush();//gjghj
            User user = (User) deserializer.readObject();
           // System.out.println(user.login+user.id);
            return user;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new User();
    }

    public static List<Request> getRequests(){
        List<Request> requests = new ArrayList<>();
        try {
            serializer.writeObject("ShowRequests");
            System.out.println("Admin Panel");
            requests = (List<Request>) deserializer.readObject();
            //System.out.println(requests);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return requests;
    }
    public static List <RequestJoin> getRequestsJoin(){
        List<RequestJoin> newRequests = new ArrayList<>();
        try {
            serializer.writeObject("ShowRequestsJoin");
            newRequests = (List<RequestJoin>) deserializer.readObject();//System.out.println(requests);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return newRequests;
    }

    public static List <User> getUsers(){
        List<User> shwUsers = new ArrayList<>();
        try {
            serializer.writeObject("ShowUser");
            shwUsers = (List<User>) deserializer.readObject();
            //System.out.println(requests);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return shwUsers;
    }
    public static List <Client> getClients(){
        List<Client> shwClient = new ArrayList<>();
        try {
            serializer.writeObject("ShowClients");
            shwClient = (List<Client>) deserializer.readObject();
            //System.out.println(requests);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return shwClient;
    }


    public static List <Manager> getManagers(){
        List<Manager> shwManager = new ArrayList<>();
        try {
            serializer.writeObject("ShowManagers");
            shwManager = (List<Manager>) deserializer.readObject();
            //System.out.println(requests);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return shwManager;
    }
    public static List <Position> getPositions(){
        List<Position> shwPositions = new ArrayList<>();
        try {
            serializer.writeObject("ShowPositions");
            shwPositions = (List<Position>) deserializer.readObject();
            System.out.println(shwPositions);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return shwPositions;
    }

    public static String addDeleteEditTable(Object entity, String action){
        try {
            serializer.writeObject(action);
            serializer.writeObject(entity);
            serializer.flush();
            //System.out.println(user.id);
            String input = (String) deserializer.readObject();
            //String sac = (String) deserializer.readObject();
            //System.out.println(sac);
            return input;

        }catch(Exception ex){
            // ex.printStackTrace();
            System.out.println(ex);
        }
        return "UNSUCCESS";
    }

    public Client getClientByID(int user_id) throws IOException, ClassNotFoundException {
        serializer.writeObject("GetClientByUser");
        serializer.writeObject(user_id);
        serializer.flush();
        Client client = (Client) deserializer.readObject();
        return client;
    }

    public static List <RequestJoin> getRequestsJoinByClient(int user_id){
        List<RequestJoin> newRequests = new ArrayList<>();
        try {
            serializer.writeObject("ShowRequestsJoinByClient");
            serializer.writeObject(user_id);
            newRequests = (List<RequestJoin>) deserializer.readObject();//System.out.println(requests);
            serializer.flush();
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return newRequests;
    }


}



