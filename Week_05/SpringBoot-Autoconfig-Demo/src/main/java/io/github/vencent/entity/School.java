package io.github.vencent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class School implements ISchool {
    @Autowired
    private Klass klass;
    @Resource(name = "zhangsan")
    Student student;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student);
    }
}
