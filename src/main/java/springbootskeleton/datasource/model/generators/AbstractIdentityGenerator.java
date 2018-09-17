package springbootskeleton.datasource.model.generators;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

public abstract class AbstractIdentityGenerator extends IdentityGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
        if (obj == null) throw new HibernateException(new NullPointerException());
        Serializable id = getId(obj);
        if (id == null) {
            id = super.generate(session, obj);
        }
        return id;
    }

    public abstract Serializable getId(Object obj);
}
