package com.starvincci.barcodeprint.Config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages= {"com.starvincci.barcodeprint.read"},sqlSessionFactoryRef="readSqlSessionFactory")
public class ReadConfig {

	//指定使用的数据源
	@Bean(name="readDataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.read.datasource")
	public DataSource readDataSource() {
		return new HikariDataSource();
	}
	
	@Primary
	@Bean(name="readSqlSessionFactory")
	public SqlSessionFactory readSqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	    bean.setDataSource(dataSource);
	   // ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    //bean.setMapperLocations(resolver.getResources("classpath*:com/starvincci/barcodeprint/readmapper/*.xml"));
		return bean.getObject();
	}
	
	@Primary
	@Bean(name="readSqlSessionTemplate")
	 public SqlSessionTemplate readSqlSessionTemplate(@Qualifier("readSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory);
	    return template;
	}
}
