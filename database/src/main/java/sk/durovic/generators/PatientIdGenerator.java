package sk.durovic.generators;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.Configurable;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Class for ID generation
 */
public class PatientIdGenerator implements IdentifierGenerator, Configurable {

    private String regex = "[^A-Za-z0-9]";

    /**
     * Generate ID
     * @param sharedSessionContractImplementor
     * @param o
     * @return
     * @throws HibernateException
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String arrayOfString = new String(array, Charset.forName("UTF-8"));

        StringBuilder sb = new StringBuilder();

        String alphaNumeric = arrayOfString.replaceAll(regex, "");

        String id;
        if(alphaNumeric.length() < 10)
            id = (String) generate(sharedSessionContractImplementor, o);
        else
            id = alphaNumeric.substring(0,10).toUpperCase().toString();

        return id;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        IdentifierGenerator.super.configure(type, params, serviceRegistry);
    }

    @Override
    public void configure(Map map) {

    }
}
