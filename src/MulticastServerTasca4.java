import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class MulticastServerTasca4 {
    String[] frases = {
            "Android Studio me amarga la vida",
            "Una frase cierta: El Jonathan no tiene pelo vaya bobo",
            "Los cascos inalambricos de iPhone son un atraco a mano armada",
            "Tenemos que entregar 59 proyectos para marzo, ayudame dios mio",
            "El Miquel Pineda no viene nunca a clase, DEP"
    };
    MulticastSocket socket;
    boolean continueRunning = true;
    int port;
    InetAddress multicastIP;

    public MulticastServerTasca4(int port, String multicastIP) throws IOException {
        socket = new MulticastSocket(port);
        this.port = port;
        this.multicastIP = InetAddress.getByName(multicastIP);
    }

    Random randomFrase = new Random();


    public void runServer(){

        while (continueRunning){
            String frase = frases[randomFrase.nextInt(frases.length)];
            byte[] sendingFrases = frase.getBytes();
            DatagramPacket packet = new DatagramPacket(sendingFrases, sendingFrases.length, multicastIP, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
            try {
                Thread.sleep(10); // espera 1 segon abans d'enviar la següent frase
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        socket.close();
    }
}
