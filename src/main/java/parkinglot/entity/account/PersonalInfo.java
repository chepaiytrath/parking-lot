package parkinglot.entity.account;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PersonalInfo {
    private String firstName;
    private String secondName;
    private String initials;
    private LocalDateTime dob;
}
