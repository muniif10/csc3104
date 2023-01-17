import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

//    NOTE FOR DATABASE: root account, password is muniifroot, using MySQL
//    database named test;
//    table structure is

//    username varchar(20),
//    pass varchar (20),
//    xCoordinate integer(3),
//    yCoordinate(3)
//
//
//
//

    static ArrayList<Socket> clients = new ArrayList<>();
    static Map map;

    public static void main(String[] args) {
        Server server = new Server();
        System.out.println("Server started...");
        Map map = new Map();
        map.createRoom();
//        for (int y = 0; y < 10; y++) {
//            for (int x = 0; x < 10; x++) {
//                var o = map.roomStruct.get(y).get(x);
//                if (o instanceof Move wall) {
////                    System.out.println(wall.moveHere());
//                }
//            }
//        }
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

        Thread continuousConnect = new Thread(() -> {
            while (true) {
                try (ServerSocket serverSocket = new ServerSocket(5454)) {
                    Socket client = serverSocket.accept();
                    clients.add(client);
                    System.out.println("Connected to the client " + client.getInetAddress().getHostAddress());
                    new Thread(()->{
                        try {

                            Player pl = new Player(map,1,1);
                            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                            while(true){
                                out.writeUnshared(pl);
                                switch (in.read()) {
                                    case 'a' -> {
                                        if (pl.getX() - 1 >= 0) {
                                            System.out.println("player choose left");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()).get(pl.getX()-1) instanceof Map.Wall)){
                                                pl.setX(pl.getX() - 1);
                                            }
                                        }
                                    }
                                    case 'w' -> {
                                        if (pl.getY() - 1 >= 0) {
                                            System.out.println("Player chose top");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()-1).get(pl.getX()) instanceof Map.Wall)){
                                                pl.setY(pl.getY() - 1);
                                            }
                                        }
                                    }
                                    case 's' -> {
                                        if (pl.getY() + 1 < 10) {
                                            System.out.println("Player chose down");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()+1).get(pl.getX()) instanceof Map.Wall)){

                                                pl.setY(pl.getY() + 1);
                                            }
                                        }
                                    }
                                    case 'd' -> {
                                        if (pl.getX() + 1 < 10) {
                                            System.out.println("Player chose right");
                                            if(!(pl.getMap().roomStruct.get(pl.getY()).get(pl.getX()+1) instanceof Map.Wall)){
                                                pl.setX(pl.getX() + 1);

                                            }
                                        }
                                    }
                                }

                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();
                    Thread.sleep(5000);

                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        continuousConnect.start();




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
