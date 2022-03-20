package sk.upjs.sk.springsecuritydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import javax.sql.DataSource;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityDemoApplication extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT name, password, 1 FROM account WHERE name = ?")
				.authoritiesByUsernameQuery("SELECT name, role FROM account WHERE name = ?")
				.rolePrefix("ROLE_");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

}
