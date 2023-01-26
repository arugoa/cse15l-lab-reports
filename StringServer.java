import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    String names = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {

            return String.format("Welcome to the String Server! \n" +
            "Please use the path /add-message?s=[string] to add a new string to the " +
            "message! \n");

        } else {
            System.out.println("Path: " + url.getPath());

            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");

                if (parameters[0].equals("s")) {

                    //names.concat(parameters[1]);
                    names = names + (parameters[1]);
                    names = names + ("\n");
                    return String.format("" + names);
                }
            }
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}