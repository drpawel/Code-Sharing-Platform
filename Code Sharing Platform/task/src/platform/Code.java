package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Code {
    private String code;
    private LocalDateTime date;
    private long time, views;

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id = UUID.randomUUID();

    public Code() {
    }

    public Code(String code) {
        this.code = code;
        this.date = setDate();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this.date = setDate();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private LocalDateTime setDate(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now().withNano(0);
        LocalDateTime dateTime = LocalDateTime.of(localDate,localTime);
        return dateTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
