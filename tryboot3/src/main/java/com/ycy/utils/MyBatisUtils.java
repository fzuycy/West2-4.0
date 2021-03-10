package com.ycy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
    //这里是一个MyBatis的自定义工具类
    public static SqlSessionFactory sqlSessionFactory;
    static {
        try {
//            String resource = "org/mybatis/example/mybatis-config.xml";
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSession(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession;
    }

}
