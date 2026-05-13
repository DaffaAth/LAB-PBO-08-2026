import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LibraryLogger {
    private List<String> logs;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LibraryLogger() {
        this.logs = new ArrayList<>();
    }

    public void logActivity(String activity) {
        String timestamp = LocalDateTime.now().format(formatter);
        logs.add(timestamp + " | " + activity);
    }

    public String getLogs() {
        if (logs.isEmpty()) return "Log kosong.";
        return String.join("\n", logs);
    }

    public void clearLogs() {
        logs.clear();
    }
}