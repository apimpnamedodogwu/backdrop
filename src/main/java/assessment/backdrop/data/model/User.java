package assessment.backdrop.data.model;

import jakarta.persistence.*;
import lombok.Builder;
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
    private String accountNumber;
    private boolean isVerified;

}
