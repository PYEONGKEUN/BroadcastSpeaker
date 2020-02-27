package com.poseungcar.broadcastspeaker.config;



import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration

@ComponentScan(basePackages = { "com.poseungcar.broadcastspeaker.service" })
@ComponentScan(basePackages = {"com.poseungcar.broadcastspeaker.serviceImpl"})
@MapperScan(basePackages = { "mapper" })

@EnableScheduling
@EnableTransactionManagement
public class RootConfig {
	
	@Bean
	public DataSource dataSource() {
		DataSource ds=new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://itbuddy.iptime.org:8233/codehouse"); 
		ds.setUsername("yuhan"); 
		ds.setPassword("yuhan1234"); 
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setMaxIdle(10);

		
		//ValidationQuery
		// 일정시간마다 DB와 연결 확인
		// mysql은 기본값으로 8시간동안
		//DB에 요청이 없으면 DB 연결을 끊어 버린다

		ds.setTestWhileIdle(true);
		ds.setValidationQuery("select 1");
		ds.setMinEvictableIdleTimeMillis(60000*3);
		ds.setTimeBetweenEvictionRunsMillis(10*1000);
		ds.setValidationQueryTimeout(2880);
		
		
		return ds;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSession sqlSession() {
		SqlSessionTemplate template=null;
		try {
			template = new SqlSessionTemplate(sqlSessionFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;
	}

	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	



}	
