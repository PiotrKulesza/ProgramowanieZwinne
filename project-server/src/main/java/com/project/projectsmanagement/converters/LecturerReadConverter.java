package com.project.projectsmanagement.converters;

import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.model.Login;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class LecturerReadConverter implements Converter<Row, Lecturer> {
    @Override
    public Lecturer convert(Row source) {

        Login login = new Login();
        login.setEmail(source.get("email",String.class));
        login.setLoginId(source.get("login_id",Integer.class));

        Lecturer lecturer = new Lecturer();

        lecturer.setLogin(login);
        lecturer.setLecturerId(source.get("lecturer_id",Integer.class));
        lecturer.setImie(source.get("imie",String.class));
        lecturer.setNazwisko(source.get("nazwisko",String.class));

        return lecturer;
    }
}
