package vdm.appinstall.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"vdm.aop"})
@Import({MVCConfig.class, SecurityConfig.class})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppConfig {
}
