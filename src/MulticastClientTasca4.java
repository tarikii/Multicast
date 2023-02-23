import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClientTasca4 {
    public static void main(String[] args) {
        try {
            InetAddress multicastIP = InetAddress.getByName("224.1.1.1"); // adreça IP multicast del servidor
            int port = 5557; // port del servidor
            MulticastSocket socket = new MulticastSocket(port); // creem el socket multicast
            socket.joinGroup(multicastIP); // ens unim al grup multicast del servidor

            byte[] receivingFrases = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(receivingFrases, receivingFrases.length);
                socket.receive(packet); // esperem a rebre una frase del servidor
                String frase = new String(packet.getData(), 0, packet.getLength());
                if (countWords(frase) > 8) { // si la frase conté més de 8 paraules
                    System.out.println(frase); // la imprimim
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static int countWords(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

}