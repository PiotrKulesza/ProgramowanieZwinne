package com.project.projectsmanagement.converters;


import com.project.projectsmanagement.model.Lecturer;
import com.project.projectsmanagement.model.Login;
import com.project.projectsmanagement.model.Project;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@ReadingConverter
public class ProjectReadConverter implements Converter<Row, Project> {
    @Override
    public Project convert(Row source) {



        Project project = new Project();
        project.setDataczasEdycji(source.get("dataczasedycji", LocalDateTime.class));
        project.setDataczasUtworzenia(source.get("dataczasutworzenia", LocalDateTime.class));
        project.setDataOddania(source.get("dataoddania", LocalDate.class));
        project.setNazwa(source.get("nazwa",String.class));
        project.setOpis(source.get("opis",String.class));
        project.setProjectId(source.get("project_id",Integer.class));

        return project;
    }
}
