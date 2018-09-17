package springbootskeleton.datasource.repository.master;

import org.springframework.data.repository.CrudRepository;
import springbootskeleton.datasource.model.User;

public interface UserMasterRepository extends CrudRepository<User, Long> {

    User findByMobileNo(Long mobileNo);

}
