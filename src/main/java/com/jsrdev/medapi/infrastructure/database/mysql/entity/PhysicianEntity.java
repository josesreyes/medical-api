package com.jsrdev.medapi.infrastructure.database.mysql.entity;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Specialty;
import com.jsrdev.medapi.infrastructure.database.mysql.converter.EmailJpaConverter;
import com.jsrdev.medapi.infrastructure.database.mysql.converter.PhoneNumberJpaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Table(name = "physicians")
@Entity(name = "Physician")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PhysicianEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private String avatar;
    @Convert(converter = EmailJpaConverter.class)
    private Email email;
    private String document;
    @Convert(converter = PhoneNumberJpaConverter.class)
    @Column(name = "phone_number")
    private PhoneNumber phoneNumber;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Column(name = "is_active")
    private Boolean isActive;
    @Embedded
    private AddressEntity address;

    public void update(
            String name,
            String avatar,
            PhoneNumber phoneNumber,
            String street,
            String stateOrProvince,
            String municipalityOrDelegation,
            String country,
            String city,
            String zipCode,
            String externalNumber,
            String internalNumber,
            String complement
    ) {
        if (name != null) this.name = name;
        if (avatar != null) this.avatar = avatar;
        if (phoneNumber != null) this.phoneNumber = phoneNumber;
        if (address != null) {
            this.address.update(
                    street,
                    stateOrProvince,
                    municipalityOrDelegation,
                    country,
                    city,
                    zipCode,
                    externalNumber,
                    internalNumber,
                    complement
            );
        }
    }
}
