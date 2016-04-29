package py.pol.una.ii.pw.util;



import java.io.Reader;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;


@Singleton
public class MyBatisUtil {
	
	private static SqlSessionFactory factory;

	 private MyBatisUtil() {
	 }

	 static
	 {
	  Reader reader = null;
	  try {
	   String resource="mybatis-config.xml";
	   reader = Resources.getResourceAsReader(resource);
	  } catch (IOException e) {
	   throw new RuntimeException(e.getMessage());
	  }
	  factory = new SqlSessionFactoryBuilder().build(reader);
	 }

	 public static SqlSessionFactory getSqlSessionFactory() 
	 {
	  return factory;
	 }

}
