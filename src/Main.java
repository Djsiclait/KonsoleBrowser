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
            System.out.print("Fetching data... ");

            doc = Jsoup.connect(url).get();

            System.out.println("Ping!"); // Success

        }
        catch (IOException error)
        {
            System.out.println("Error!: " + error.toString());
        }

        // Showing metadata
        System.out.println("Title: " + doc.title());
        System.out.println("HTML lines: " + CountBy(doc.html(), 0));
        System.out.println("HTML <p>: " + CountBy(doc.body().toString(), 1));
        System.out.println("Images : " + CountBy(doc.body().toString(), 2));
        System.out.println("Forms: " + CountBy(doc.body().toString(), 3));
    }

    public static int CountBy(String content, int opt)
    {
        String symbol;

        switch(opt)
        {
            case 0: // lines
                symbol = "\n";
                break;

            case 1: // paragraphs
                symbol = "<p>";
                break;

            case 2: // images
                symbol = "<img";
                break;

            case 3: // forms
                symbol = "<form";
                break;

            default:
                return 0;
        }

        String[] paper = content.split(symbol);

        return paper.length - 1;
    }
}


