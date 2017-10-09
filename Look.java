
/*
 * @author marcoedoardopalma
 */
public class Look
{

    private String string;

    public Look(String string)
    {
        this.string = string;
    }

    public String find(String context)
    {
        String response = "";

        if (this.string.contains(context))
        {
            int indexReference = this.string.lastIndexOf(context);

            char tmp = this.string.charAt(indexReference);
            
            boolean openTag = false;
            boolean startedReading = false;
            for(int i = indexReference + context.length(); i<this.string.length(); i++)
            {
                if(this.string.charAt(i) == '<')
                {
                    openTag = true;
                    if(startedReading)
                        break;
                }
                else if(this.string.charAt(i) == '>')
                {
                    openTag = false;
                    if(startedReading)
                        break;
                }
                else if(openTag == false & (int)this.string.charAt(i) >= 48 & (int)this.string.charAt(i) <= 125 )
                {
                    //I am reading the thing
                    startedReading = true;
                    response+=this.string.charAt(i);
                }
                else if(openTag == false & startedReading == true)
                {
                    response+=this.string.charAt(i);
                }
                else
                {
                    ;
                }
            }

        }

        return response;
    }

}
