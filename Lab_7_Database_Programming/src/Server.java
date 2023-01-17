import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    static ArrayList<Socket> clients = new ArrayList<>();
    static Map map;

    public static void main(String[] args) throws SQLException {
        Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","muniifroot");
        if (!con.isClosed()){
            System.out.println("Connected to database.");
        }
        Server server = new Server();
        System.out.println("Server started...");
        Map map = new Map();
        map.createRoom();
        Thread broadcastThread = new Thread(() -> {
            while (true) {try {
                server.broadcast();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            }
        });
        broadcastThread.start();

        Thread acceptConnection = new Thread(() -> {
            while (true) {
                try (ServerSocket serverSocket = new ServerSocket(5454)) {
                    Socket client = serverSocket.accept();
                    clients.add(client);
                    System.out.println("Connected to the client " + client.getInetAddress().getHostAddress());
                    new Thread(()->{
                        try {
                            boolean goOn = true;

//                            Player pl = new Player(map,1,1);
                            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                            Player pl = (Player) new ObjectInputStream(client.getInputStream()).readUnshared(); // Receive the player object with username and pass
                            // Have a method to check if the user exist in the database:
                            // YES -> Pull data from database and insert into Player
                            if(server.checkExist(pl,con) == 0){
                                pl.setMap(map);
                                pl = server.createPlayer(pl,con);
                            }else{
                                // NO -> Proceed as usual
                                pl.prompt = "A new journey!";
                                pl.setMap(map).setX(1).setY(1);
                            }



                            while(client.isConnected() && goOn){
                                out.writeUnshared(pl);
                                switch (in.read()) {
                                    case 'a' -> {
                                        if (pl.getX() - 1 >= 0) {
//                                            System.out.println("player choose left");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()).get(pl.getX()-1) instanceof Map.Wall)){
                                                pl.setX(pl.getX() - 1);
                                            }
                                        }
                                    }
                                    case 'w' -> {
                                        if (pl.getY() - 1 >= 0) {
//                                            System.out.println("Player chose top");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()-1).get(pl.getX()) instanceof Map.Wall)){
                                                pl.setY(pl.getY() - 1);
                                            }
                                        }
                                    }
                                    case 's' -> {
                                        if (pl.getY() + 1 < 10) {
//                                            System.out.println("Player chose down");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()+1).get(pl.getX()) instanceof Map.Wall)){

                                                pl.setY(pl.getY() + 1);
                                            }
                                        }
                                    }
                                    case 'd' -> {
                                        if (pl.getX() + 1 < 10) {
//                                            System.out.println("Player chose right");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()).get(pl.getX()+1) instanceof Map.Wall)){
                                                pl.setX(pl.getX() + 1);

                                            }
                                        }
                                    }
                                    case 'l' ->{
                                        // Initiate saving of Player content to a database
                                        // What data to store in database? username, password associated to the client, position on the map, map itself?
                                        // Do SQL query to check if username and pass pair exist:
                                        if(server.checkExist(pl,con) == 0){
                                            // YES -> update entry
                                            // Method to update entry given (username, pass, x and y coordinate)
                                            server.updateEntry(pl.username,pl.pass,pl.getX(),pl.getY(),con);
                                        }else {
                                            // NO -> Create new entry
                                            // Method to create entry given (username, pass, x and y coordinate)
                                            server.createEntry(pl.username,pl.pass,pl.getX(),pl.getY(),con);
                                        }


                                        // Terminate socket and connection
                                        System.out.println("Client wants to disconnect.");
                                        serverSocket.close();
                                        goOn = false;
                                    }
                                }

                            }
                        } catch (IOException | ClassNotFoundException | SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Session terminated.");
                    }).start();
                    Thread.sleep(5000);

                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        acceptConnection.start();




    }


    /**
     * @param pl
     * @param con
     * @return 1 if doesn't exist in DB, 0 if existed.
     * @throws SQLException
     */
    public int checkExist(Player pl, Connection con) throws SQLException {
        con.createStatement().execute("use test;");
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("select username, pass from gamesession where username = '" +pl.username + "' and pass = '" + pl.pass + "';");
        if (!res.next()){
            return 1;
        }
        else {
            System.out.println("Session exists, resuming...");
            return 0;
        }
    }

    public void createEntry(String username, String pass, int x, int y, Connection con) throws SQLException {
        con.createStatement().execute("use test;");
        PreparedStatement statement = con.prepareStatement("insert into gameSession(username, pass, xCoordinate, yCoordinate) VALUES('" +
               username+"','"+pass+"',"+x+","+y+ ")");
        statement.execute();
        System.out.println("Saving new session...");
    }
    public void updateEntry(String username, String pass, int x, int y, Connection con) throws SQLException {
        con.createStatement().execute("use test;");
//        PreparedStatement statement =con.prepareStatement("update gamesession " +
//                "set xCoordinate = " + x + ','+ "yCoordinate = " + y+ ',' + " where username = '" + username + "' and pass = '" + pass + "';");
        PreparedStatement statement = con.prepareStatement("update gamesession set xCoordinate = ?, yCoordinate = ? where username = ? and pass = ?;");
        statement.setInt(1,x);
        statement.setInt(2,y);
        statement.setString(3,username);
        statement.setString(4,pass);
        statement.executeUpdate();
        System.out.println("Saving session...");
        System.out.println("DEBUG: UPDATED");
    }
    public Player createPlayer(Player pl, Connection con) throws SQLException {
        con.createStatement().execute("use test;");
        ResultSet res = con.createStatement().executeQuery( "select username, pass, xCoordinate, yCoordinate from gamesession where username = '" +pl.username + "' and pass = '" + pl.pass + "';");
        int x,y;
        res.next();
        x = res.getInt(3);
         y = res.getInt(4);
        System.out.println("Getting player details from DB");
         return pl.setX(x).setY(y);
    }

    public void broadcast() throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName("224.0.0.1");
        DatagramPacket packet;
        byte[] buf = "Packet for client".getBytes();
        packet = new DatagramPacket(buf, buf.length, group, 4545);
        socket.send(packet);
        socket.close();
        Thread.sleep(5000);
    }


}
