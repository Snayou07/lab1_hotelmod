import java.util.Objects;

public class Guest {
    private int id;
    private String name;
    private String email;
    private boolean isVip;

    public Guest() {}

    public Guest(int id, String name, String email, boolean isVip) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isVip = isVip;
    }

    // Business logic method
    public double getDiscountPercent() {
        return isVip ? 10.0 : 0.0;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isVip() { return isVip; }
    public void setVip(boolean vip) { isVip = vip; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return id == guest.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Guest{id=%d, name='%s', email='%s', vip=%s}",
                id, name, email, isVip);
    }
}