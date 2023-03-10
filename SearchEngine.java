import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    ArrayList<String> sites = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {

            return String.format("Welcome to the Search Engine! \n" +
            "Please use the path /add?s=[site-name] to add a new website to the " +
            "list! \nIf you want to search for a site use the path /search?s=[site]");

        } else {
            System.out.println("Path: " + url.getPath());

            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");

                if (parameters[0].equals("s")) {

                    sites.add(parameters[1]);
                    return String.format("Site %s added to database!", parameters[1]);
                }
            }
            else if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");

                if (parameters[0].equals("s")) {

                    ArrayList<String> sitesfound = new ArrayList<String>();

                    for (int i = 0; i < sites.size(); i++) {
                        if (sites.get(i).contains(parameters[1])) {
                            sitesfound.add(sites.get(i));
                        }
                    }
                    
                    return String.format("Found the sites %s!", sitesfound.toString());
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}