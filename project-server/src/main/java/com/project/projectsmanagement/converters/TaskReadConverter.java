package com.project.projectsmanagement.converters;

import com.project.projectsmanagement.model.Task;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class TaskReadConverter implements Converter<Row, Task> {
    @Override
    public Task convert(Row source) {
        Task task = new Task();
        task.setTaskId(source.get("task_id",Integer.class));
        task.setOpis(source.get("opis",String.class));
        task.setNazwa(source.get("nazwa",String.class));
        task.setKanban(source.get("kanban",String.class));
        return task;
    }
}
