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
        System.out.println("\tTitle: " + doc.title());
        System.out.println("\tHTML lines: " + CountLines(doc.html()));
        System.out.println("\tHTML <p>: " + CountBy(doc, 1));
        System.out.println("\tImages: " + CountBy(doc, 2));
        System.out.println("\tForms: " + CountBy(doc, 3));

        // Processing forms
        ProcessForms(doc, url);
    }

    public static void ProcessForms(Document doc, String url)
    {
        Elements forms = doc.getElementsByTag("form");

        if(forms.size() > 0) {
            System.out.println("Processing Forms...");

            int countF = 0;

            for (Element form : forms) {
                System.out.println("\tProcessing Forms " + ++countF + ": ");

                Elements inputs = form.getElementsByTag("input");

                int countI = 0;

                for(Element input: inputs)
                {
                    System.out.println("\t\tProcessing Input " + ++countI + ": ");

                    System.out.println("\t\t\tClass Name: " + input.className());
                    System.out.println("\t\t\tID: " + input.id());
                    System.out.println("\t\t\tCSS Selector: " + input.cssSelector());
                }
            }
        }
        else
            System.out.println("There are no forms to process.");
    }


    // Auxilary Functions
    public static int CountBy(Document doc, int opt)
    {
        String tag;

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

    public static int CountLines(String content)
    {
        String[] paper = content.split("\n");

        return paper.length;
    }

}


