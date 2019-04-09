package Data;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    /**
     * Constructor for User
     * @param first, first name
     * @param last, last name
     * @param user, username
     * @param pass, password
     */
    public User(String first, String last, String user, String pass) {
        setFirstName(first);
        setLastName(last);
        setUsername(user);
        setPassword(pass);
    }

    /**
     * Getter method for username
     * @return, username as string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for username
     */
    public void setUsername(String str) {
        this.username = str;
    }

    /**
     * Getter method for password
     * @return, password as string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password
     */
    public void setPassword(String str) {
        this.password = str;
    }

    /**
     * Getter method for firstName
     * @return, firstName as string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for firstName
     */
    public void setFirstName(String str) {
        this.firstName = str;
    }

    /**
     * Getter method for lastName
     * @return, lastName as string
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for lastName
     */
    public void setLastName(String str) {
        this.lastName = str;
    }

}
