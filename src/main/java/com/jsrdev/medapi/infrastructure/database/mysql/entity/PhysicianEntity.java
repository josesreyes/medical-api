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

import java.util.UUID;

@Table(name = "physicians")
@Entity(name = "Physician")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PhysicianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String avatar;
    @Convert(converter = EmailJpaConverter.class)
    private Email email;
    private String document;
    @Convert(converter = PhoneNumberJpaConverter.class)
    private PhoneNumber phoneNumber;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private AddressEntity address;
}
