package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import server.MeinServer;

public class MeinClient {

	public static void main(String[] args) {

		MeinClient client = new MeinClient("localhost", 8000);
		client.sendMessage("Guten Tag, Frau Server! Wie gehts es Ihnen?");
	}
	
	
	private InetSocketAddress address;
	
	public MeinClient (String hostname, int port) {
		
		address = new InetSocketAddress(hostname, port);
    }
	
	public void sendMessage(String msg) {
		
		try {
		System.out.println("[Client] Verbinde zu Server....");
		Socket socket = new Socket();
		socket.connect(address, 5000);
		System.out.println("[Client] Verbunden.");
		
		System.out.println("[Client] Sende Nachricht....");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.println(msg);
		pw.flush();
		
		System.out.println("[Client] Nachricht gesendet.");
		
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
		if (s.hasNextLine()) {
			System.out.println("[Client] Antwort vom Client: " + s.nextLine());
		}
		
		//Verbindung schlie�en
		pw.close();
		s.close();
		socket.close();
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
