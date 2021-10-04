package CanTuna.acon3dtask;


import CanTuna.acon3dtask.repository.JdbcTempUserRepository;
import CanTuna.acon3dtask.repository.UserRepository;
import CanTuna.acon3dtask.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource_user;

    public SpringConfig(DataSource dataSource_user) {
        this.dataSource_user = dataSource_user;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new JdbcTempUserRepository(dataSource_user);
    }

}
