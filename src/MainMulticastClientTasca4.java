import java.io.IOException;

public class MainMulticastClientTasca4 {
    public static void main(String[] args) throws IOException {
        MulticastClientTasca4 clientMulticast = new MulticastClientTasca4(5557,"224.1.1.1");
        clientMulticast.runClient();
    }
}
