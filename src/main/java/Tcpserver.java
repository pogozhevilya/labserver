import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.ArrayList;


public class Tcpserver {
    public static final int PORT = 7050;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("TCP сервер запущен на " + PORT + " порту");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Обработка запроса");
                InputStream reader = clientSocket.getInputStream();
                byte[] buffer = new byte[64 * 1024];
                reader.read(buffer);
                String string = new String(buffer);
                char[] charArray = string.toCharArray();
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < charArray.length; i++) {
                    int value = Character.getNumericValue(charArray[i]);
                    if (value > 0 && value % 2 != 0) {
                        list.add(value);
                    }
                }
                String out = new String("Ваш массив: " + list.toString());
                OutputStream writer = clientSocket.getOutputStream();
                writer.write(out.getBytes());
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
