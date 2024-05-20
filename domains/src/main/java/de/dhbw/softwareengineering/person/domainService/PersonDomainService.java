package de.dhbw.softwareengineering.person.domainService;

import org.springframework.stereotype.Component;

@Component
public class PersonDomainService {
    public boolean validateZipCode(String zipCode){
        return zipCode != null && zipCode.matches("\\d{5}");
    }
}
