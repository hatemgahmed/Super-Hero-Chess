package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server {

	public static void main(String[] args) throws IOException {

		String clientMovement;
		String sMovement;
		ServerSocket ss = new ServerSocket(6789);
		while (true) {
			Socket s = ss.accept();
			// DataInputStream dis = new DataInputStream(s.getInputStream());
			BufferedScanner inFromClient = new BufferedScanner(s.getInputStream());
			DataOutputStream outToClient = new DataOutputStream(s.getOutputStream());
			clientMovement = inFromClient.nextLine();
			System.out.println("FROM CLIENT: " + clientMovement); // elmovement bta3et player 2 aka client
			sMovement = "El movement bta3et player 1 aka server";
			outToClient.writeBytes(sMovement);
		}
	}

}
