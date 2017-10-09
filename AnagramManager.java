import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class AnagramManager
{

    public AnagramManager()
    {
        ;
    }

    public ArrayList<String> getSolutions(String myInput)
    {

        ArrayList<String> solutions = new ArrayList<String>();

        try {
            String s0 = "https://new.wordsmith.org/anagram/anagram.cgi?anagram=";
            String s1 = "&t=500&a=n";

            //int space = 32;
            //int plus = 43;

            char[] chars = myInput.toCharArray();
            String newMyInput = "";

            for (int i = 0; i < myInput.length(); i++) {
                int x = ((int) (myInput.charAt(i)));
                if (x<=90 & x>= 65)
                {
                    newMyInput += myInput.charAt(i);
                }
                else if(x<=122 & x>= 97)
                {
                    newMyInput += myInput.charAt(i);
                }
                else if(x == 32)
                {
                    newMyInput += '+';
                }
            }

            myInput = newMyInput;

            s0 = s0 + myInput + s1;

            System.out.println(s0);

            URL url = new URL(s0);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String webSource = "";
            String tmp;
            while ((tmp = in.readLine()) != null) {
                webSource += tmp;
            }


            //Thread.sleep(1000);

            //System.out.println(webSource);

            in.close();

            String searchObject = "Displaying";
            Look l = new Look(webSource);
            while (true) {
                String temp = l.find(searchObject);
                if (temp.contains("document.body.style")) {
                    break;
                } else {
                    solutions.add(temp);
                    searchObject = temp;
                }
            }

            solutions.add("No solutions for this anagram");


            solutions.remove(0);

            return solutions;

        }catch(Exception a)
        {
            solutions.add("No anagrams found!");
            return solutions;
        }

    }

}
