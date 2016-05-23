import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to KonsoleBrowser");

        do {
            // Step 1: asking and capturing the URL
            System.out.print("URL: ");

            Scanner input = new Scanner(System.in); // setting input to "stdin"

            // Step 2: Using the Browser
            BrowseData(input.nextLine());

            System.out.print("\nWould you like to search another site? (Y/N): ");

            if( input.nextLine().toUpperCase().equals("N") )
                break;

        }while(true);

        // Step 4: Goodbye!
        System.out.println("\nThank you for the using KonsoleBrowser!");
        System.out.println("----Created by Djidjelly P. Siclait");

    }

    // Browsing function
    public static void BrowseData(String url)
    {
        Document doc = null;

        try {
            System.out.print("\nFetching data... ");

            doc = Jsoup.connect(url).get();

            System.out.println("Ping!"); // Success

        }
        catch (IOException error)
        {
            System.out.println("\nError!: " + error.toString());
        }

        // Showing metadata
        System.out.println("\n\tTitle: " + doc.title());
        System.out.println("\tHTML lines: " + doc.html().split("\n").length);
        System.out.println("\tHTML <p>: " + doc.getElementsByTag("p").size());
        System.out.println("\tImages: " + doc.getElementsByTag("img").size());
        System.out.println("\tForms: " + doc.getElementsByTag("form").size());

        // Step 3: Processing forms
        ProcessForms(doc.getElementsByTag("form"));
    }

    public static void ProcessForms(Elements tags)
    {
        Elements forms = tags;

        if(forms.size() > 0) {
            System.out.println("\nProcessing Forms...");

            int countF = 0;

            for (Element form : forms) {
                System.out.println("\n\tProcessing Forms " + ++countF + ": ");

                Elements inputs = form.getElementsByTag("input");

                int countI = 0;

                for(Element input: inputs)
                {
                    System.out.println("\n\t\tProcessing Input " + ++countI + ": ");
                    System.out.println("\t\t\tType: " + input.attr("type"));
                    System.out.println("\t\t\tName: " + input.attr("name"));
                }
            }
        }
        else
            System.out.println("\nThere are no forms to process.");
    }
}


