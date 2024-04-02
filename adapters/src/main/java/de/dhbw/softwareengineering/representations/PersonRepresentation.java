package de.dhbw.softwareengineering.representations;

import de.dhbw.softwareengineering.values.Address;
import de.dhbw.softwareengineering.values.Gender;
import de.dhbw.softwareengineering.values.Name;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public class PersonRepresentation {

    private UUID id;

    @JsonProperty("name")
    private Name name;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("birthdate")
    private LocalDate birthDate;

    @JsonProperty("gender")
    private Gender gender;

    public PersonRepresentation(final Name name, final Address address, final LocalDate birthDate, final Gender gender) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public PersonRepresentation(final UUID id, final Name name, final Address address, final LocalDate birthDate, final Gender gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }
}
