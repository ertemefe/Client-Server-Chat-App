import java.io.*;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) {
        Socket clientSocket;
        DataInputStream din;
        DataOutputStream dout;
        long milisec;


        try {

            //setting the duration for timeout
            System.out.println("Please enter timeout time:");
            BufferedReader time = new BufferedReader(new InputStreamReader(System.in));
            milisec = (Long.parseLong(time.readLine()) * 1000);

            //setting the port number
            System.out.println("Please enter port number:");
            BufferedReader port = new BufferedReader(new InputStreamReader(System.in));

            //creating the client socket
            clientSocket = new Socket("localhost", Integer.parseInt(port.readLine()));
            clientSocket.setSoTimeout((int) milisec);

            //input #from server socket to client socket
            din = new DataInputStream(clientSocket.getInputStream());

            //output #from client to server socket
            OutputStream outputStream = clientSocket.getOutputStream();
            dout = new DataOutputStream(outputStream);

            //buffered reader for the input on the console that will be passed to the server socket
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            //connection established
            System.out.println("Connected");
            //showing the local port
            System.out.println("Local port: " + clientSocket.getLocalPort());

            String strFromServer = "", strToClient = "";


            /*
            --Time for the chat app to timeout
            --As part 1 of the project does not want us to use threads,
            -I came up with a solution like this.
            -the timeout occurs after trying to write a message
            */
            long timeoutTime = System.currentTimeMillis() + milisec;

            //Chat continues until someone sends "goodby" or time exceeds
            while (!strFromServer.equals("goodby")) {
                //Client initiates the chat
                System.out.print("Client says: ");
                strFromServer = br.readLine();

                //checks for the timeout, breaks or adds time
                if (System.currentTimeMillis() > timeoutTime) {
                    System.out.println("message failed - chat timed out");
                    break;
                } else timeoutTime = System.currentTimeMillis() + milisec;

                //sends client's message
                dout.writeUTF(strFromServer);
                dout.flush();

                //receives the server's message
                strToClient = din.readUTF();
                System.out.println("Server says: " + strToClient);

                //ends if server says goodby
                if (strToClient.equals("goodby")) break;
            }
            //closes the chat and socket
            System.out.println("Chat ended");
            br.close();
            din.close();
            dout.close();
            clientSocket.close();


        } catch (Exception exe) {
            System.out.println("chat ended");
            //exe.printStackTrace();
        }
    }
}