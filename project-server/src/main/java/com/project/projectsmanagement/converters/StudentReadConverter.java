package com.project.projectsmanagement.converters;


import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.model.Student;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@ReadingConverter
public class StudentReadConverter  implements Converter<Row, Student> {


    @Override
    public Student convert(Row source) {

        Login login = new Login();
        login.setEmail(source.get("email",String.class));
        login.setLoginId(source.get("login_id",Integer.class));

        Student student = new Student();

        student.setLogin(login);
        student.setStudentId(source.get("student_id",Integer.class));
        student.setImie(source.get("imie",String.class));
        student.setNazwisko(source.get("nazwisko",String.class));
        student.setNrIndeksu(source.get("nrindeksu",String.class));
        student.setStacjonarny(source.get("stacjonarny",Boolean.class));

        return student;
    }
}
