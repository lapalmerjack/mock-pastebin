package platform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="code")
@ToString
@AllArgsConstructor
public class Code {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
    @JsonIgnore
    private String id;

    @Column(name= "code", columnDefinition = "VARCHAR(1000)")
    private String code;
    @Column(name = "date")
    private String date = setDateAndTime();
    @Column(name = "time")
    private Long time;
    @Column(name = "views")
    private Integer views;

    @JsonIgnore
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @JsonIgnore
    @Column(name = "restriction")
    private Boolean restriction = false;

    @Transient
    @JsonIgnore
    private Integer lastNumber = 0;

    @Transient
    @JsonIgnore
    private boolean timeUp = false;



    public Code(String code) {
        this.code = code;

    }



    private String setDateAndTime () {

        String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        return localDateTime.format(dateTimeFormatter);
    }










}
