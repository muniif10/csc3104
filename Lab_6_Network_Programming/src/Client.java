import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    static Socket socket;
    static OutputStream out;
    static InputStream in;

    public Client() throws IOException {
    }

    public static void main(String[] args) {
        // Create thread to receive the server IP.
        System.out.println("Client started...");
        new Thread(()->{
            try {
                Client cli = new Client();
                cli.startConnection();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }).start();



    }
    public void startConnection() throws IOException, ClassNotFoundException {
        String serverIP;
        int serverPort;

        try(MulticastSocket mult = new MulticastSocket(4545)){ // Create a socket to receive multicast packet
            InetAddress group = InetAddress.getByName("224.0.0.1"); // Create the multicast address group
            byte[] buf = new byte[1024]; // Data to be transfered inside the packet, can be smaller
            mult.joinGroup(group); // Assign the multicast socket to the multicast address group
            DatagramPacket packet = new DatagramPacket(buf,buf.length); // The packet to be used to store the multicast packet
            mult.receive(packet); // Awaits the packet
             serverIP = packet.getAddress().getHostAddress(); // Store the server
            mult.close(); // Close
//            System.out.println("Connecting...");
            socket = new Socket(serverIP, 5454);
            System.out.println("Connected.");




        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(System.in);
//        System.out.println("Attempting to get streams.");
        in = socket.getInputStream();
        out = socket.getOutputStream();
        var textToServer = new OutputStreamWriter(out);
        BufferedReader textFromServer = new BufferedReader(new InputStreamReader(in));
        ObjectInputStream objectFromServer = new ObjectInputStream(in);
        Player curPlayer;
        while (true){

             curPlayer = (Player) objectFromServer.readUnshared();
            curPlayer.currentStatus();
            System.out.println("Select your next move, \n'a','w','s','d' OR enter 'x' to exit.");
            String input = sc.next();


            if (input.equalsIgnoreCase("x")){
                break;
            } else {

                textToServer.write(input);
                textToServer.flush();

            }

        }
        socket.close();



    }

}
