package sk.durovic.generators;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.Configurable;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

public class PatientIdGenerator implements IdentifierGenerator, Configurable {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return "ab";
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        IdentifierGenerator.super.configure(type, params, serviceRegistry);
    }

    @Override
    public void configure(Map map) {

    }
}
