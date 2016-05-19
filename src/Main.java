import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        System.out.println("HTML lines: " + CountBy(doc, 0));
        System.out.println("HTML <p>: " + CountBy(doc, 1));
        System.out.println("Images : " + CountBy(doc, 2));
        System.out.println("Forms: " + CountBy(doc, 3));
    }

    public static int CountBy(Document doc, int opt)
    {
        String tag;

        //Element content = doc.getElementById("content");

        switch(opt)
        {
            case 1: // paragraphs
                tag = "p";
                break;

            case 2: // images
                tag = "img";
                break;

            case 3: // forms
                tag = "form";
                break;

            default:
                return 0;
        }

        Elements tags = doc.getElementsByTag(tag);

        return tags.size();
    }
}


