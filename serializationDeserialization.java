import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.Arrays;

public class serializationDeserialization {

    //@Test
    void POJOToJSON() throws Exception {

        User user = new User();
        user.setName("Hammad");
        user.setEmail("Hammad@pw.live");
        user.setLocation("Seelampur");
        user.setSkills(Arrays.asList("QA", "Automation", "Rest Assured"));

        ObjectMapper mapper = new ObjectMapper();

        String data = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(user);

        System.out.println(data);
    }

    @Test
    void JSONToPOJO() throws JsonProcessingException {
        String data = """
          {
          "name": "Hammad",
          "email": "Hammad@pw.live",
          "location": "Seelampur",
          "skills": [
            "QA",
            "Automation",
            "Rest Assured"
          ]
        }
                """;
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(data, User.class);
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Location: " + user.getLocation());
        System.out.println("Skills: " + user.getSkills().get(0));
    }
}