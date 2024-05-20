package de.dhbw.softwareengineering.person;

import de.dhbw.softwareengineering.person.values.Address;
import de.dhbw.softwareengineering.person.values.Gender;
import de.dhbw.softwareengineering.person.values.Name;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "person")
@Builder
public class Person {
    @Id
    private UUID id;
    private Name name;
    private Address address;

    private LocalDate birthDate;
    private Gender gender;

    public Person(UUID id, Name name, Address address, LocalDate birthDate, Gender gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Person(Name name, Address address, LocalDate birthDate, Gender gender) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}