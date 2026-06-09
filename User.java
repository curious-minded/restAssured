import java.util.List;

public class User {

    private String name;
    private String email;
    private String location;
    private List<String> skills;

    public User() {
    }

    public User(String name, String email, String location, List<String> skills) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}