package network;

import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) throws Exception {

		String serverSentence;
		String csentence;

		Socket clientSocket = new Socket("hostname hna hanekteb el ip adress", 6789);

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		csentence = "elmovement bta3et player 2 aka client";
		outToServer.writeBytes(csentence + '\n');

		serverSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + serverSentence); // El movement bta3et player 1 aka server
		clientSocket.close();
	}

}
