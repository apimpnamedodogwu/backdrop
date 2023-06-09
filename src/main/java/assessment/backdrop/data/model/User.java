package assessment.backdrop.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Table(name = "users")
@Entity
@Data
@RequiredArgsConstructor

public class User implements Serializable {
    private String accountName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private boolean isVerified;
    private String bankCode;

}
