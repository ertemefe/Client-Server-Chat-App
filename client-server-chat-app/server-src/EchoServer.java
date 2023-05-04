import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        DataInputStream din;
        ServerSocket serverSocket;
        DataOutputStream dout;
        BufferedReader br;
        long sec;

        try {
            //setting timeout duration
            System.out.println("Please enter timeout time:");
            BufferedReader time = new BufferedReader(new InputStreamReader(System.in));
            sec = (Long.parseLong(time.readLine()) * 1000);
            //setting the port number
            System.out.println("Please enter port number:");
            BufferedReader port = new BufferedReader(new InputStreamReader(System.in));

            //creating the server socket
            serverSocket = new ServerSocket(Integer.parseInt(port.readLine()));
            if (sec != 0) serverSocket.setSoTimeout((int) sec);

            //waits for a client
            System.out.println("Waiting for client");
            //founds a client
            Socket socket = serverSocket.accept();
            System.out.println("Connected");

            //input #from client to server
            din = new DataInputStream(socket.getInputStream());

            //output #from server to client
            OutputStream outputStream = socket.getOutputStream();
            dout = new DataOutputStream(outputStream);

            //buffered reader for the input
            br = new BufferedReader(new InputStreamReader(System.in));

            //showing the local port
            System.out.println("Local port: " + serverSocket.getLocalPort());

            String strFromClient = "", strToServer = "";

            /*
            --Time for the chat app to timeout
            --As part 1 of the project does not want us to use threads,
            -I came up with a solution like this.
            -the timeout occurs after trying to write a message
            */

            //Chat continues until someone sends "goodby" or time exceeds
            while (!strToServer.equals("goodby")) {
                //initiates the chat with receiving from the client
                strFromClient = din.readUTF();
                System.out.println("client says: " + strFromClient);
                //ends if the message is "goodby"
                if (strFromClient.equals("goodby")) break;

                //reads the server's message
                System.out.print("Server says: ");
                strToServer = br.readLine();

                //sends server's message
                dout.writeUTF(strToServer);
                dout.flush();

            }
            //closes the chat and socket
            System.out.println("Chat ended");
            br.close();
            din.close();
            dout.close();
            serverSocket.close();
        } catch (Exception exe) {
            System.out.println("chat ended");
            //exe.printStackTrace();
        }
    }
}