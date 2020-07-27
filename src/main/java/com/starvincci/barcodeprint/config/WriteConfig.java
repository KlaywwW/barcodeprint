package com.starvincci.barcodeprint.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
@MapperScan(basePackages= {"com.starvincci.barcodeprint.write"},sqlSessionFactoryRef="writeSqlSessionFactory")
public class WriteConfig {

	@Bean(name="writeDataSource")
	@ConfigurationProperties(prefix="spring.write.datasource")
	public DataSource readDataSource() {
		return new HikariDataSource();
	}
	
	@Bean(name="writeSqlSessionFactory")
	public SqlSessionFactory writeSqlSessionFactory(@Qualifier("writeDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	    bean.setDataSource(dataSource);
	   // ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	   //bean.setMapperLocations(resolver.getResources("classpath*:com/starvincci/barcodeprint/writemapper/*.xml"));
		return bean.getObject();
	}
	
	@Bean(name="writeSqlSessionTemplate")
	 public SqlSessionTemplate writeSqlSessionTemplate(@Qualifier("writeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory);
	    return template;
	}
}


