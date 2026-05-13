import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private List<LibraryItem> items;
    private List<Member> members;
    private LibraryLogger logger;

    public Library() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.logger = new LibraryLogger();
    }

    public String addItem(LibraryItem item) {
        items.add(item);
        return item.getTitle() + " berhasil ditambahkan";
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public LibraryItem findItemById(int itemId) {
        return items.stream()
                .filter(i -> i.getItemId() == itemId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item dengan ID " + itemId + " tidak ditemukan."));
    }

    public Member findMemberById(String memberId) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Member tidak ditemukan."));
    }

    public String getLibraryStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-----+-----------------------+------------+\n");
        sb.append("| ID  | Judul                 | Status     |\n");
        sb.append("+-----+-----------------------+------------+\n");
        for (LibraryItem item : items) {
            String status = item.isBorrowed() ? "Dipinjam" : "Tersedia";
            sb.append(String.format("| %-3d | %-21s | %-10s |\n", 
                      item.getItemId(), item.getTitle(), status));
        }
        sb.append("+-----+-----------------------+------------+");
        return sb.toString();
    }

    public String getAllLogs() {
        return logger.getLogs();
    }

    public LibraryLogger getLogger() { return logger; }
}