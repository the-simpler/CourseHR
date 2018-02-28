package HRInformationSystem;

import HRInformationSystem.DAO.*;
import HRInformationSystem.model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ServerThread extends Thread {
    private Socket socket;
    private PrintStream os;
    private BufferedReader is;
    private InetAddress address;

    public ServerThread(Socket s)  {
        try {
            socket = s;
            address = s.getInetAddress();
            os = new PrintStream(s.getOutputStream());
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
        }catch(IOException e){

        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        int i = 0;
        String str;
        try {

            ObjectOutputStream serializer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream deserializer = new ObjectInputStream(socket.getInputStream());
            String url = "jdbc:mysql://localhost:3306/course?useSSL=false";
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "root");
            prop.put("autoReconnect", "true");
            prop.put("characterEncoding", "UTF-8");
            prop.put("useUnicode", "true");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection conn = DriverManager.getConnection(url, prop);
            System.out.println("DB connected!");
           //

            while ((str = (String)deserializer.readObject()) != null) {

                if (str.equals("Authorization")) {
                    UserDAO reg = new UserDAO(conn);
                    User user = new User();
                    user.login = (String) deserializer.readObject();
                    user.password = (String) deserializer.readObject();
                    //System.out.println(user.login+' '+user.password);
                    User account = reg.check(user);
                    //System.out.println(account.login+' '+account.password+ ' '+account.id );
                    serializer.writeObject(account);
                } else if (str.equals("ShowRequests")) {
                    RequestDAO req = new RequestDAO(conn);
                    List<Request> requestList;
                    requestList = req.showRequests();
                    serializer.writeObject(requestList);
                } else if (str.equals("GetSkills")) {
                    SkillsDAO req = new SkillsDAO(conn);
                    List<Skills> skillsList;
                    skillsList = req.getSkills();
                    System.out.println(skillsList.get(0).name);
                    serializer.writeObject(skillsList);
                }else if (str.equals("RegistrationAdmin")) {
                    UserDAO regUsr = new UserDAO(conn);
                    ManagerDAO regMng = new ManagerDAO(conn);
                    User user = (User) deserializer.readObject();
                    Manager manager = (Manager) deserializer.readObject();
                    if (regUsr.create(user)){
                        System.out.println("user '"+user.login+"' created with passsword: '"+user.password+"'");
                        manager.user_id = regUsr.getId(user);
                        if(regMng.create(manager)) {
                            System.out.println("manager '"+manager.name+"' created");
                            serializer.writeObject("SUCCESS");
                        }else{serializer.writeObject("UNSUCCESS");}
                    }

                }else if (str.equals("RegistrationClient")) {
                        UserDAO regUsr = new UserDAO(conn);
                        ClientDAO regClnt = new ClientDAO(conn);
                        User user = (User) deserializer.readObject();
                        Client client = (Client) deserializer.readObject();
                        System.out.println(client.skills);
                        if (regUsr.create(user)) {
                            System.out.println("user '" + user.login + "' created with passsword: '" + user.password + "'");
                            client.user_id = regUsr.getId(user);
                            if (regClnt.create(client)) {
                                System.out.println("client '" + client.name + "' created");
                                serializer.writeObject("SUCCESS");
                            }else{serializer.writeObject("UNSUCCESS");}
                        }

                }else if(str.equals("ShowRequestsJoin")) {
                    RequestDAO req = new RequestDAO(conn);
                    List<RequestJoin> requestJoin;
                    requestJoin = req.joinRequests();
                    serializer.writeObject(requestJoin);
                }else if(str.equals("ShowUser")) {
                    UserDAO usr = new UserDAO(conn);
                    List<User> shwUsers;
                    shwUsers = usr.findAll();
                    serializer.writeObject(shwUsers);
                }else if(str.equals("ShowClients")) {
                    ClientDAO clnt = new ClientDAO(conn);
                    List<Client> shwClient;
                    shwClient = clnt.showClient();
                    serializer.writeObject(shwClient);
                }else if(str.equals("ShowManagers")) {
                    ManagerDAO mngr = new ManagerDAO(conn);
                    List<Manager> shwManager;
                    shwManager = mngr.showManager();
                    serializer.writeObject(shwManager);
                }else if(str.equals("ShowPositions")) {
                    PositionDAO mngr = new PositionDAO(conn);
                    List<Position> shwPosition;
                    shwPosition = mngr.showPosition();
                    System.out.println(shwPosition);
                    serializer.writeObject(shwPosition);
                }else if(str.equals("AddUser")) {
                    UserDAO regUsr = new UserDAO(conn);
                    User user = (User) deserializer.readObject();
                    if (regUsr.create(user)==true) {
                        System.out.println("user '" + user.login + "' created with passsword: '" + user.password + "'");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("EditUser")) {
                    UserDAO editUsr = new UserDAO(conn);
                    User user = (User) deserializer.readObject();
                    if (editUsr.edit(user)==true) {
                        System.out.println("user '" + user.id + "' edited success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("AddPosition")) {
                    PositionDAO regPstn = new PositionDAO(conn);
                    Position position = (Position) deserializer.readObject();
                    if (regPstn.create(position)==true) {
                        System.out.println("position '" + position.name + "' created ");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("EditPosition")) {
                    PositionDAO regPstn = new PositionDAO(conn);
                    Position position = (Position) deserializer.readObject();
                    if (regPstn.edit(position) == true) {
                        System.out.println("position '" + position.id + "' edited success");
                        serializer.writeObject("SUCCESS");
                    } else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("AddSkills")) {
                    SkillsDAO regSklls = new SkillsDAO(conn);
                    Skills skills = (Skills) deserializer.readObject();
                    if (regSklls.create(skills)==true) {
                        System.out.println("skill '" + skills.name + "' created ");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("EditSkills")) {
                    SkillsDAO regSklls = new SkillsDAO(conn);
                    Skills skills = (Skills) deserializer.readObject();
                    if (regSklls.edit(skills)==true) {
                        System.out.println("skill '" + skills.id + "' edited success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("AddRequest")) {
                    RequestDAO regRqst = new RequestDAO(conn);
                    Request request = (Request) deserializer.readObject();
                    if (regRqst.create(request)==true) {
                        System.out.println("request '" + request.description + "' created ");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("EditRequest")) {
                    RequestDAO regRqst = new RequestDAO(conn);
                    Request request = (Request) deserializer.readObject();
                    if (regRqst.edit(request)==true) {
                        System.out.println("request '" + request.id + "' edited success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("AddManager")) {
                    ManagerDAO regMng = new ManagerDAO(conn);
                    Manager manager = (Manager) deserializer.readObject();
                    if (regMng.create(manager)==true) {
                        System.out.println("manager '" + manager.surname + "' created ");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("EditManager")) {
                    ManagerDAO regMng = new ManagerDAO(conn);
                    Manager manager = (Manager) deserializer.readObject();
                    if (regMng.edit(manager)==true) {
                        System.out.println("manager '" + manager.surname + "' edited success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("AddClient")) {
                    ClientDAO regClnt = new ClientDAO(conn);
                    Client client = (Client) deserializer.readObject();
                    if (regClnt.create(client)==true) {
                        System.out.println("client '" + client.surname + "' created ");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("EditClient")) {
                    ClientDAO regClnt = new ClientDAO(conn);
                    Client client = (Client) deserializer.readObject();
                    if (regClnt.edit(client)==true) {
                        System.out.println("client '" + client.surname + "' edited success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("DeleteClient")) {
                    ClientDAO regClnt = new ClientDAO(conn);
                    Client client = (Client) deserializer.readObject();
                    if (regClnt.delete(client)==true) {
                        System.out.println("client '" + client.surname + "' deleted success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("DeleteManager")) {
                    ManagerDAO regMng = new ManagerDAO(conn);
                    Manager manager = (Manager) deserializer.readObject();
                    if (regMng.delete(manager)==true) {
                        System.out.println("manager '" + manager.surname + "' deleted success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("DeleteRequest")) {
                    RequestDAO regRqst = new RequestDAO(conn);
                    Request request = (Request) deserializer.readObject();
                    if (regRqst.delete(request)==true) {
                        System.out.println("request '" + request.id + "' deleted success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("DeleteSkills")) {
                    SkillsDAO regSklls = new SkillsDAO(conn);
                    Skills skills = (Skills) deserializer.readObject();
                    if (regSklls.delete(skills)==true) {
                        System.out.println("skill '" + skills.id + "' deleted success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("DeletePosition")) {
                    PositionDAO regPstn = new PositionDAO(conn);
                    Position position = (Position) deserializer.readObject();
                    if (regPstn.delete(position) == true) {
                        System.out.println("position '" + position.id + "' deleted success");
                        serializer.writeObject("SUCCESS");
                    } else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("DeleteUser")) {
                    UserDAO editUsr = new UserDAO(conn);
                    User user = (User) deserializer.readObject();
                    if (editUsr.delete(user)==true) {
                        System.out.println("user '" + user.id + "' deleted success");
                        serializer.writeObject("SUCCESS");
                    }else {
                        System.out.println("UNSEC");
                        serializer.writeObject("UNSUCCESS");
                    }
                }else if(str.equals("GetClientByUser")) {
                    ClientDAO getClient = new ClientDAO(conn);
                    int user_id = (int) deserializer.readObject();
                    Client client = getClient.getClient(user_id);
                    serializer.writeObject(client);
                }else if(str.equals("ShowRequestsJoinByClient")) {
                    RequestDAO req = new RequestDAO(conn);
                    List<RequestJoin> requestJoin;
                    int user_id = (int) deserializer.readObject();
                    requestJoin = req.joinRequestsByUser(user_id);
                    serializer.writeObject(requestJoin);
                }


                serializer.flush();
            }
        }catch (IOException e) {
            System.err.println(e);
        }catch (ClassNotFoundException e) {
            System.err.println(e);
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        finally {
            disconnect();
        }
    }

    public void disconnect() {
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            System.out.println("Закончил работу: "+address.getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}
