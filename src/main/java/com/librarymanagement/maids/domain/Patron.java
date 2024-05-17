package com.librarymanagement.maids.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patron implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Unique identifier for each patron

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    @Column(name="name")
    private String name; // Name of the patron

    private LocalDate dateOfBirth;

    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Pattern(regexp="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email; // Email address of the patron

    private String phoneNumber; // Phone number of the patron

    private String libraryCardNumber; // Unique identifier for the patron's library card

    @Enumerated(EnumType.STRING)
    private PatronStatus membershipStatus; // Membership status of the patron (active/inactive)


}
