package ci.spring;

import ci.spring.domain.Roles;
import ci.spring.domain.Users;
import ci.spring.repository.RolesRepository;
import ci.spring.repository.UsersRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan()
public class SpringWebApplication implements CommandLineRunner {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Set<Roles> rolesUser = new HashSet<>();
        rolesUser.add(new Roles("USER"));

        Set<Roles> rolesAdmin = new HashSet<>();
        rolesAdmin.add(new Roles("ADMIN"));
//        rolesAdmin.add(new Roles("USER"));

        String passUser = this.passwordEncoder().encode("user") ;
        System.out.println("*********** password 'user' encoder is : " + passUser + " *************");
        Users user = new Users("user", passUser,  true, rolesUser);
        this.usersRepository.save(user);

        String passAdmin = this.passwordEncoder().encode("admin");
        System.out.println("*********** password 'admin' encoder is : " + passAdmin + " *************");
        Users admin = new Users("admin", passAdmin, true, rolesAdmin);
        this.usersRepository.save(admin);
    }
}
