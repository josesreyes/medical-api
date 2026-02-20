package com.jsrdev.medapi.infrastructure.database.mysql.adapter;

import com.jsrdev.medapi.domain.common.Email;
import com.jsrdev.medapi.domain.common.PhoneNumber;
import com.jsrdev.medapi.domain.exception.EntityNotFoundException;
import com.jsrdev.medapi.domain.model.address.Address;
import com.jsrdev.medapi.domain.model.physician.Physician;
import com.jsrdev.medapi.domain.repository.PhysicianRepositoryPort;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.AddressEntity;
import com.jsrdev.medapi.infrastructure.database.mysql.entity.PhysicianEntity;
import com.jsrdev.medapi.infrastructure.database.mysql.mapper.PhysicianMapper;
import com.jsrdev.medapi.infrastructure.database.mysql.repository.PhysicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PhysicianRepositoryAdapter implements PhysicianRepositoryPort {

    private final PhysicianRepository physicianRepository;

    @Override
    public Physician create(Physician physician) {
        PhysicianEntity entity = PhysicianMapper.fromPhysicianToPhysicianEntity(physician);
        PhysicianEntity saved = physicianRepository.save(entity);
        return PhysicianMapper.fromPhysicianEntityToPhysician(saved);
    }

    @Override
    public Optional<Physician> findById(UUID id) {
        return physicianRepository.findById(id)
                .map(PhysicianMapper::fromPhysicianEntityToPhysician);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return physicianRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocument(String document) {
        return physicianRepository.existsByDocument(document);
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return physicianRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Page<Physician> findActivePhysicians(Pageable pageable) {
        return physicianRepository.findByIsActiveTrue(pageable)
                .map(PhysicianMapper::fromPhysicianEntityToPhysician);
    }

    @Override
    public Physician update(Physician physician) {

        PhysicianEntity entity = physicianRepository.findById(physician.getUuid())
                        .orElseThrow(() -> new EntityNotFoundException("Physician not found"));

        mapDomainToExistingEntity(physician, entity);

        return PhysicianMapper.fromPhysicianEntityToPhysician(entity);
    }

    private void mapDomainToExistingEntity(
            Physician domain,
            PhysicianEntity entity
    ) {
        entity.setName(domain.getName());
        entity.setAvatar(domain.getAvatar());
        entity.setPhoneNumber(domain.getPhoneNumber());
        entity.setIsActive(domain.getIsActive());
        // update address
        mapAddress(domain.getAddress(), entity.getAddress());
    }

    private void mapAddress(Address domainAddress, AddressEntity entityAddress) {
        entityAddress.setStreet(domainAddress.getStreet());
        entityAddress.setStateOrProvince(domainAddress.getStateOrProvince());
        entityAddress.setMunicipalityOrDelegation(domainAddress.getMunicipalityOrDelegation());
        entityAddress.setCountry(domainAddress.getCountry());
        entityAddress.setCity(domainAddress.getCity());
        entityAddress.setZipCode(domainAddress.getZipCode());
        entityAddress.setExternalNumber(domainAddress.getExternalNumber());
        entityAddress.setInternalNumber(domainAddress.getInternalNumber());
        entityAddress.setComplement(domainAddress.getComplement());
    }
}
