
import java.io.Serializable;


/*
 * @author marcoedoardopalma
 */
public class Person implements Serializable
{
    private String name, email, id, position, tutor, homepage, dPA_Visibility, eCS_Member_ID, role_ID_Number;

    public Person(String name, String email, String id, String position, String tuton, String homepage, String dPA_Visibility, String eCS_Member_ID, String role_ID_Number)
    {
        this.name = name;
        this.email = email;
        this.id = id;
        this.position = position;
        this.tutor = tuton;
        this.homepage = homepage;
        this.dPA_Visibility = dPA_Visibility;
        this.eCS_Member_ID = eCS_Member_ID;
        this.role_ID_Number = role_ID_Number;
    }

    public Person()
    {
        this.name = "unavailable";
        this.email = "unavailable";
        this.id = "unavailable";
        this.position = "unavailable";
        this.tutor = "unavailable";
        this.homepage = "unavailable";
        this.dPA_Visibility = "unavailable";
        this.eCS_Member_ID = "unavailable";
        this.role_ID_Number = "unavailable";
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getId()
    {
        return id;
    }

    public String getPosition()
    {
        return position;
    }

    public String getTutor()
    {
        return tutor;
    }

    public String getHomepage()
    {
        return homepage;
    }

    public String getdPA_Visibility()
    {
        return dPA_Visibility;
    }

    public String geteCS_Member_ID()
    {
        return eCS_Member_ID;
    }

    public String getRole_ID_Number()
    {
        return role_ID_Number;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public void setTutor(String tuton)
    {
        this.tutor = tuton;
    }

    public void setHomepage(String homepage)
    {
        this.homepage = homepage;
    }

    public void setdPA_Visibility(String dPA_Visibility)
    {
        this.dPA_Visibility = dPA_Visibility;
    }

    public void seteCS_Member_ID(String eCS_Member_ID)
    {
        this.eCS_Member_ID = eCS_Member_ID;
    }

    public void setRole_ID_Number(String role_ID_Number)
    {
        this.role_ID_Number = role_ID_Number;
    }

}
