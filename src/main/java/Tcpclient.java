import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Tcpclient {
    final static String HOST = "localhost";
    final static int PORT = 7050;
    public static void main(String[] args) {
        if (args.length > 0) {
            try (Socket socket = new Socket(HOST, PORT)) {
                OutputStream out = socket.getOutputStream();
                out.write(args[0].getBytes());
                InputStream in = socket.getInputStream();
                byte[] buf = new byte[64 * 1024];
                in.read(buf);
                System.out.println(new String(buf));
                socket.close();
            } catch (UnknownHostException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Необходимо передать массив букв латинского алфавита в виде строки");
        }
    }
}

