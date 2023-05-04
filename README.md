<a name="br1"></a>COMP 416: COMPUTER NETWORKS

PROJECT 1 PART 1

REPORT

EFE ERTEM 71739




<a name="br2"></a>README

Project description:

This project is made for the Comp416 course in Koç University. The goal is tocreate a client-server chat app using TCP sockets. There are two main classes,Chat Server and Chat Client which can communicate with each other on a localmachine through a TCP socket. The objective of the project is to get familiarwith the Java Socket class and its functionality to develop skills on socketprogramming. In this part, we develop a simple chat app, after passing the portand timeout values using the console, client starts the chat and server responds,and the communication continues one message at a time.

How to run the project:

To run the chat-app, first you need to run the echoServer.java file as shown below:

-$ java echoServer.java

Then enter desired integer for the duration before timeout, for example:

-Please enter timeout time:-10

Then enter the port number, for example:-Please enter port number:-9999

Then server waits for a client socket

To run the client socket, you need to run the echoClient.java file with another terminal, as shown below:

-$ java echoClient.java

The rest is the same with the server socket. After passing the same port numberthe chat app initiates with entering a message from the client side.Under normal circumstances, the app ends in two occasions:1- either the server or client writes the message “goodby”2- timeout duration exceeds



<a name="br3"></a>OVERVIEW

This overview will include snapshot of the app and explaining the processeswith these snapshots step-by-step.

Step 1: Run the program with $java EchoServer.javaStep 2: Enter a value for duration before timeout (in seconds)Step 3: Enter a value for port number (integer)Step 4: Waits for the client socket

With these inputs the program takes the values creates a server socket as seen inthe snapshot below.

Step 4: Do steps 1-3 for client file (using $java EchoClient.java to run theprogram)

Step 5: Once the connection is established, local ports are shown on the console.Step 6: The app waits for the client to start messaging.




<a name="br4"></a>Step 7: The messages are sent one-by-one as seen on the figure above.

Step 8: Chat ends with the message “goodby”

\*\*Timeout cases are shown below




<a name="br5"></a>-standard implementation for socket timeout.-works well for server socket

-does not work for the client socket

-In the case below, the client socket had trouble with timeout so I implementeda different kind of solution for this. After the timeout duration is exceeded,when you try to send a message it fails and the app closes.

-snapshot of the implementation is below
