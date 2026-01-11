package com.jsrdev.medapi.infrastructure.database.mysql.converter;

import com.jsrdev.medapi.domain.common.PhoneNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class PhoneNumberJpaConverter implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.value();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        }
        return PhoneNumber.of(dbData);
    }
}

