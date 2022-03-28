package platform.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, java.sql.Date> {


    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate== null ? null : java.sql.Date.valueOf(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date sqlDate) {
        return (sqlDate ==  null ? null : sqlDate.toLocalDate());
    }
}
