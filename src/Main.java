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

        // Using the Browser
        BrowseData(url);

    }

    // Browsing function
    public static void BrowseData(String url)
    {
        Document doc = null;

        try {
            System.out.print("Fetching data...");

            doc = Jsoup.connect(url).get();

            System.out.println("Ping!"); // Success

        }
        catch (IOException error)
        {
            System.out.println("Error!: " + error.toString());
        }

        // Showing metadata
        System.out.println("Title: " + doc.title());
        System.out.println("HTML lines: " + CountLines(doc.html()));
    }

    public static int CountLines(String content)
    {
        String[] paper = content.split("\n");

        return paper.length;
    }
}


