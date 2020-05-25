package FunctionLayer;

/**
 * Formålet med denne klasse er at skabe et user objekt og sætte attributter på dette. Enten ved login eller
 * oprettelse af en ny bruger. Metoder til oprettelse/log-in ligger i UserMapper klassen. Attributten id
 * sættes auto genereret i databasen.
 * @author claes
 */
public class User {

    private int id;
    private String email;
    private String password;

    //Constructor, skaber et user objekt med 2 parametre
    public User( String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Gettere og settere
    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
