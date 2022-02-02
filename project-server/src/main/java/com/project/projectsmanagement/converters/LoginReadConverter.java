package com.project.projectsmanagement.converters;

import com.project.projectsmanagement.model.Login;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class LoginReadConverter implements Converter<Row, Login> {
    @Override
    public Login convert(Row source) {

        Login login = new Login();
        login.setEmail(source.get("email",String.class));
        login.setLoginId(source.get("login_id",Integer.class));

        return login;
    }
}
