package cat.rank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import mark.core.DataAgentInterface;
import mark.core.DataAgentProfile;
import mark.core.RawData;
import mark.core.ServerInterface;

public class Sink implements DataAgentInterface {

    public void run(DataAgentProfile profile, ServerInterface datastore)
            throws Throwable {

        ServerSocket serverSocket = new ServerSocket(1555);
        serverSocket.setSoTimeout(10000);

        while (!Thread.interrupted()) {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println("Welcome to Cat Rank! Tell me what's wrong?");

                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] parts = inputLine.split(":");
                    Cat cat = new Cat();
                    cat.name = parts[0].trim();

                    RawData<Cat> data = new RawData<Cat>();
                    data.subject = cat;
                    data.data = parts[1].trim();
                    data.time = (int) (System.currentTimeMillis() / 1000);
                    data.label = "data.cat.server";

                    datastore.addRawData(data);
                }
            } catch (java.net.SocketTimeoutException timeout) {

            }
        }
    }
}
