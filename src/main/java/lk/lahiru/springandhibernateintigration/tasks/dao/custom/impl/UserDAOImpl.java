package lk.lahiru.springandhibernateintigration.tasks.dao.custom.impl;

import lk.ijse.dep8.tasks.dao.CrudDAOImpl;
import lk.ijse.dep8.tasks.dao.custom.UserDAO;
import lk.ijse.dep8.tasks.entity.User;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Scope("prototype")
@Component
public class UserDAOImpl extends CrudDAOImpl<User, String> implements UserDAO {

    public UserDAOImpl(@Nullable Session session) {
        this.session = session;
    }

    @Override
    public boolean existsUserByEmailOrId(String emailOrId) {
        return findUserByIdOrEmail(emailOrId).isPresent();
    }

    @Override
    public Optional<User> findUserByIdOrEmail(String userIdOrEmail) {
        return session.createQuery("FROM User u WHERE u.id = :id OR u.email = :email", User.class)
                .setParameter("id", userIdOrEmail)
                .setParameter("email", userIdOrEmail)
                .uniqueResultOptional();
    }
}
