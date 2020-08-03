package top.lwzp.mybatis.main;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.lwzp.mybatis.vo.Person;

import java.io.IOException;
import java.io.Reader;

public class MainClass {
    public static void main(String[] args) throws IOException {
        //加载MyBatis配置文件
        Reader r = Resources.getResourceAsReader("mybatis.xml");
        //SqlSession对象 其作用类似于JDBC中的connection
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(r);
        SqlSession sqlSession = sessionFactory.openSession();

        String sqlPath = "top.lwzp.mybatis.vo.personMapping.selectPersonById";
        Person person = sqlSession.selectOne(sqlPath,1);
        System.out.println(person);
        sqlSession.close();
    }
}
