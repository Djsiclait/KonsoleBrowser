import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Step 1: asking and capturing the URL
        System.out.print("URL: ");

        Scanner input = new Scanner(System.in); // setting input to "stdin"

        String url = input.nextLine(); // saving user input


    }

    // Browsing function
    public static void BrowseData(String url)
    {
        try {
            Document doc = Jsoup.connect(url).get();
        }
        catch (IOException error)
        {
            System.out.println("Error!: " + error.toString());
        }
    }
}


