package CanTuna.acon3dtask;


import CanTuna.acon3dtask.repository.ItemRepository;
import CanTuna.acon3dtask.repository.JdbcTempItemRepository;
import CanTuna.acon3dtask.repository.JdbcTempUserRepository;
import CanTuna.acon3dtask.repository.UserRepository;
import CanTuna.acon3dtask.service.ItemService;
import CanTuna.acon3dtask.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource_user;
    private final DataSource dataSource_item;

    public SpringConfig(DataSource dataSource_user, DataSource dataSource_item) {
        this.dataSource_user = dataSource_user;
        this.dataSource_item = dataSource_item;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new JdbcTempUserRepository(dataSource_user);
    }

    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JdbcTempItemRepository(dataSource_item);
    }

    /*@Bean
    public ExRateRepository exRateRepository(){
        return new JdbcTempExRateRepository(dataSource_rate);
    }*/

}
