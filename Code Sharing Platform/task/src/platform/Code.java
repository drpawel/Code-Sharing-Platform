package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Code {
    private String code;
    private LocalDateTime date;
    private long time, views;
    private boolean timeRestriction, viewsRestriction;

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id = UUID.randomUUID();

    public Code() {
    }

    public Code(String code) {
        this.code = code;
        this.date = setDate();
        this.timeRestriction = false;
        this.viewsRestriction = false;
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
        return date.withNano(0);
    }

    private LocalDateTime setDate(){
        LocalDateTime dateTime = LocalDateTime.now();
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

    @JsonIgnore
    public boolean isTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(boolean timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    @JsonIgnore
    public boolean isViewsRestriction() {
        return viewsRestriction;
    }

    public void setViewsRestriction(boolean viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }
}
