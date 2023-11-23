package com.techacademy;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /** 認証・認可設定 */
    // @Bean アノテーションはDIコンテナーの登録対象にする記述
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ログイン画面のURLや、ログイン成功後のリダイレクト先などを指定
        http.formLogin(login -> login
            .loginProcessingUrl("/login")    // ユーザー名・パスワードの送信先
            .loginPage("/login")             // ログイン画面
            .defaultSuccessUrl("/user/list") // ログイン成功後のリダイレクト先
            .failureUrl("/login?error")      // ログイン失敗時のリダイレクト先
            .permitAll()                     // ログイン画面は未ログインでアクセス可
         // ログアウト後にログイン画面へ遷移
        ).logout(logout -> logout
            .logoutSuccessUrl("/login")      // ログアウト後のリダイレクト先
        // URLごとのアクセス制御を行なう「認可」の設定。cssなどのstatic配下のリソースはログインしなくてもアクセス可能にし、その他はログイン必要に設定
        ).authorizeHttpRequests(auth -> auth
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()                 // css等は未ログインでアクセス可
            .anyRequest().authenticated()    // その他はログイン必要
        );

        return http.build();
    }

    /** ハッシュ化したパスワードの比較に使用する */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
