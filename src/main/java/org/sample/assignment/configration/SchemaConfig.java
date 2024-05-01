package org.sample.assignment.configration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.sample.assignment.exception.ApplicationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author RahulPure
 */
@Slf4j
@Component
public class SchemaConfig implements BeanPostProcessor
{
    @Value("${spring.liquibase.liquibaseSchema:liquibase}")
    private String schemaName;
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!StringUtils.isEmpty(schemaName) && bean instanceof DataSource dataSource) {
            try (Connection conn = dataSource.getConnection();
                 Statement statement = conn.createStatement()) {
                log.info("Going to create DB schema '{}' if not exists.", schemaName);
                statement.execute("create schema if not exists " + schemaName);
            } catch (SQLException e) {
                throw new ApplicationException("Failed to create schema '" + schemaName + "'", ApplicationErrorCode.UNEXPECTED_ERROR, HttpStatus.OK);
            }
        }
        return bean;
    }

}
