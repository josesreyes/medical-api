package com.jsrdev.medapi.infrastructure.database.mysql.converter;

import com.jsrdev.medapi.domain.common.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmailJpaConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute.value();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return Email.of(dbData);
    }
}
