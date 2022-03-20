package parkinglot.entity.account;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Account {
    private String accountId;
    private String userName;
    private String password;
    private String email;
    private LocalDateTime lastAccessDate;
    private Contact contact;
}