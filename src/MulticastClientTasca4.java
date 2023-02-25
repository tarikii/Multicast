import java.io.IOException;
import java.net.*;

public class MulticastClientTasca4 {
    private boolean continueRunning = true;
    private MulticastSocket socket;
    private int port;
    private InetAddress multicastIP;
    private NetworkInterface netIf;
    private InetSocketAddress group;


    public MulticastClientTasca4(int port, String multicastIP) throws IOException {
        socket = new MulticastSocket(port);
        this.multicastIP = InetAddress.getByName(multicastIP);
        this.port = port;
        netIf = socket.getNetworkInterface();
        group = new InetSocketAddress(multicastIP,port);
    }

    public void runClient() throws IOException {
        DatagramPacket packet;
        byte[] receivedData = new byte[1024];

        socket.joinGroup(group, netIf);
        System.out.printf("Connectat a %s:%d%n", group.getAddress(), group.getPort());

        while (continueRunning) {
            packet = new DatagramPacket(receivedData, receivedData.length);
            socket.setSoTimeout(5000);
            try {
                socket.receive(packet);
                continueRunning = getData(packet.getData(), packet.getLength());
            } catch (SocketTimeoutException e) {
                System.out.println("S'ha perdut la connexió amb el servidor.");
                continueRunning = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean getData(byte[] data,int length) {
        boolean ret = true;

        String frase = new String(data, 0, length);

        int numPalabras = comptaParaules(frase);
        if (numPalabras > 8) {
            System.out.println(frase);
        }

        return ret;
    }

    private static int comptaParaules(String text) {
        String[] words = text.trim().split(" ");
        return words.length;
    }

}