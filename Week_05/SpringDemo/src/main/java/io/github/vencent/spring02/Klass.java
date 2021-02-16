package io.github.vencent.spring02;

import io.github.vencent.spring01.Student;
import lombok.Data;

import java.util.List;

@Data
public class Klass {
    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }
}
