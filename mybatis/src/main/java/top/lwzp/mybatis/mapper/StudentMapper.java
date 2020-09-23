package top.lwzp.mybatis.mapper;

import top.lwzp.mybatis.vo.Student;
import java.util.Map;

public interface StudentMapper {
    Student selectStudentById(int stuNo);
    long updateStudentState(Map<String,Integer> params);
}
