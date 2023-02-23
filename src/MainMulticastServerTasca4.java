import java.io.IOException;

public class MainMulticastServerTasca4 {
    public static void main(String[] args) throws IOException {
        MulticastServerTasca4 serverMulticast = new MulticastServerTasca4(5557,"224.1.1.1");
        serverMulticast.runServer();
    }
}