package top.lwzp.mybatis.main;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.lwzp.mybatis.mapper.StudentMapper;
import top.lwzp.mybatis.vo.Person;
import top.lwzp.mybatis.vo.Student;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class MainClass {
    public static void main(String[] args) throws SQLException {
        String uname = "root";
        String pwd = "1234qwer";
        String url = "jdbc:mysql://10.0.251.50:3306/lwz_jcapp_api?autoReconnect=true&zeroDateTimeBehavior=round&charset=utf8";



        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = getConn(uname, pwd, url);
            ps = conn.prepareStatement("select * from api_detail where api_detail_id=25");
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("cname"));
            }
        } finally {
            conn.close();
        }
    }

    private static Connection getConn(String uname,String pwd, String url){
//        String driver="com.mysql.jdbc.Driver";
//        String driver="sgcc.nds.jdbc.driver.NdsDriver";
        //其中test为数据库名称
        Connection conn=null;
        try{
//            Class.forName(driver);
            conn= DriverManager.getConnection(url,uname,pwd);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    private static void mybatis() throws IOException {
        //加载MyBatis配置文件
        Reader r = Resources.getResourceAsReader("mybatis.xml");
        //SqlSession对象 其作用类似于JDBC中的connection
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(r);
        SqlSession sqlSession = sessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectStudentById(1);

        sqlSession.commit();
        sqlSession.close();

        System.out.println(student);
    }

    private static void updateStudentStateById(SqlSession session,int state){
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        Map<String,Integer> params = new HashMap<>();
        params.put("stuNo",1);
        params.put("state",state);
        long i = mapper.updateStudentState(params);
        System.out.println(i);
    }

    private static void queryStudentById(SqlSession session){
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        Student student = mapper.selectStudentById(1);
        System.out.println(student);
    }

    private static long baseTime = 1281418983252L;
    private static Map<String, Object> getUpdateAddDateParams(int i) {
        Map<String, Object> params = new HashMap<>();
        params.put("stuNo",i);
        baseTime = baseTime + new Random().nextInt(2000);
        params.put("date",new Date(baseTime));
        return params;
    }

    private static Map<String, Object> getUpdateStatedParams(int i) {
        Map<String, Object> params = new HashMap<>();
        params.put("stuNo",i);
        int temp = new Random().nextInt(2);
        String appId = map.get(temp);
        params.put("state",new Random().nextInt(5));
        return params;
    }

    private static Map<String, Object> getUpdateAppIdParams(int i) {
        Map<String, Object> params = new HashMap<>();
        params.put("stuNo",i);
        int temp = new Random().nextInt(2);
        String appId = map.get(temp);
        params.put("appId",appId);
        return params;
    }
    private static Map<Integer,String> map = new HashMap<>();
    static {
        map.put(0,"4a1c1d87-4db9-43a4-a86f-22aeeb1707cc");
        map.put(1,"93442ec2-c833-4520-a8c1-b82e0205540e");
    }

    private static Map<String,Object> getAddStudentParams(int i) {
        Map<String, Object> params = new HashMap<>();
        params.put("stuNo",i);
        params.put("stuName","lisi");
        params.put("stuAge",new Random().nextInt(5) + 10);
        params.put("gradeName","五年三班");
        params.put("appId", UUID.randomUUID().toString());
        params.put("objType",getObjType());
        params.put("objId","" + i);
        params.put("uid","" + (i + 1000000000));
        return params;
    }

    private static String getObjType() {
        if (new Random().nextInt() == 0){
            return "channel";
        }
        return "news";
    }
}
