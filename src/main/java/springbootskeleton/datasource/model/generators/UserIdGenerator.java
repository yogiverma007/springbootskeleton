package springbootskeleton.datasource.model.generators;

import springbootskeleton.datasource.model.User;

import java.io.Serializable;

public class UserIdGenerator extends AbstractIdentityGenerator {

    @Override
    public Serializable getId(Object user) {
        return ((User)user).getId();
    }
}
