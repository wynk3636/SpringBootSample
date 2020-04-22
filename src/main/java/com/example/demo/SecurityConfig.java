package com.example.demo;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

//セキュリティ設定
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//パスワードエンコーダーの定義
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource dataSource;
	
	// ユーザーIDとパスワードを取得するSQL文
    private static final String USER_SQL = "SELECT"
            + "    user_id,"
            + "    password,"
            + "    true"
            + " FROM"
            + "    m_user"
            + " WHERE"
            + "    user_id = ?";

    // ユーザーのロールを取得するSQL文
    private static final String ROLE_SQL = "SELECT"
            + "    user_id,"
            + "    role"
            + " FROM"
            + "    m_user"
            + " WHERE"
            + "    user_id = ?";
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		//静的リソースへのアクセスはセキュリティ適用なし
		web.ignoring().antMatchers("/webjars/**","/css/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//直リンク禁止
		http
			.authorizeRequests()
				.antMatchers("/webjars/**").permitAll()//webjarsへアクセス許可
				.antMatchers("/css/**").permitAll()//cssへアクセス許可
				.antMatchers("/login").permitAll()//loginページは直リンクOK
				.antMatchers("/signup").permitAll()//signupページは直リンクOK
				.antMatchers("/rest/**").permitAll()
				.antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/**").permitAll() //swagger
				.antMatchers("/admin").hasAuthority("ROLE_ADMIN") //URLで認可設定=>HTML内で認可も可能
				.anyRequest().authenticated();//それ以外は禁止
		
		//login処理
		http
			.formLogin()
				.loginProcessingUrl("/login")//ログイン処理のパス
				.loginPage("/login")//ログインページ
				.failureUrl("/login")//ログイン失敗時の遷移先
				.usernameParameter("userId")//ログインページのユーザID　->htmlでnameに設定
				.passwordParameter("password")//ログインページのパスワード
				.defaultSuccessUrl("/home",true);//ログイン成功時の遷移先
		
		//logout処理
		http
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login");
		
		//CSRF無効のURL
		RequestMatcher csrfMatcher = new RestMatcher("/rest/**");
		//CSRF対策（クロスサイトリクエストフォージェリ）を無効化
		//http.csrf().disable();
		//RESTのみCSRFを無効
		http.csrf().requireCsrfProtectionMatcher(csrfMatcher);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//ログイン処理時のユーザ情報をDBから取得
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(USER_SQL)
			.authoritiesByUsernameQuery(ROLE_SQL)
        	.passwordEncoder(passwordEncoder()); //パスワード暗号化設定->ユーザ登録時にも設定する必要あり
	}
}
