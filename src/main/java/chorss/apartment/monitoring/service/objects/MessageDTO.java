package chorss.apartment.monitoring.service.objects;

import java.time.LocalDateTime;
import java.util.Map;

public class MessageDTO {

    private final Map<String, String> messages;
    private final String path;
    private final String date;

    public MessageDTO(Map<String, String> messages, String path) {
        this.messages = messages;
        this.path = path;
        this.date = LocalDateTime.now().toString();
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public String getPath() {
        return path;
    }

    public String getDate() {
        return date;
    }
}