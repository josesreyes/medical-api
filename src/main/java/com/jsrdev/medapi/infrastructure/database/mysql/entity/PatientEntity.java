package com.jsrdev.medapi.infrastructure.database.mysql.entity;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.infrastructure.database.mysql.converter.EmailJpaConverter;
import com.jsrdev.medapi.infrastructure.database.mysql.converter.PhoneNumberJpaConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Setter
    private String name;
    @Setter
    private String avatar;
    @Convert(converter = EmailJpaConverter.class)
    private Email email;
    @Column(name = "identity_document")
    private String identityDocument;
    @Setter
    @Convert(converter = PhoneNumberJpaConverter.class)
    @Column(name = "phone_number")
    private PhoneNumber phoneNumber;
    @Setter
    @Column(name = "is_active")
    private Boolean isActive;
    @Embedded
    private AddressEntity address;
}
