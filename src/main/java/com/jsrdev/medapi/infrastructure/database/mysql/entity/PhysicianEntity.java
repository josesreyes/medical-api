package com.jsrdev.medapi.infrastructure.database.mysql.entity;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.model.physician.Specialty;
import com.jsrdev.medapi.infrastructure.database.mysql.converter.EmailJpaConverter;
import com.jsrdev.medapi.infrastructure.database.mysql.converter.PhoneNumberJpaConverter;
import jakarta.persistence.*;
import lombok.*;
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
    @Setter
    private String name;
    @Setter
    private String avatar;
    @Convert(converter = EmailJpaConverter.class)
    private Email email;
    private String document;
    @Setter
    @Convert(converter = PhoneNumberJpaConverter.class)
    @Column(name = "phone_number")
    private PhoneNumber phoneNumber;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Setter
    @Column(name = "is_active")
    private Boolean isActive;
    @Embedded
    private AddressEntity address;
}
