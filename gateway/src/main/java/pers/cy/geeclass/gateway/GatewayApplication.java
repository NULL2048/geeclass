package pers.cy.geeclass.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {
	// 使用slf4j日志框架  真正使用的是log4j
	private static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！！");
		LOG.info("Gateway地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
	}

	/**
	 * 配置跨域
	 * 因为使用了路由，所以跨域配置就不要写到业务模块server了，直接在网关配置就可以了
	 * 一般用spring cloud就在网关配置跨域，如果是spring boot单体项目，那么直接在业务模块添加跨域配置类（CorsConfig）就可以了
	 * @return
	 */
	@Bean
	public CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(Boolean.TRUE);
		config.addAllowedMethod("*");

		// 需要用addAllowedOriginPattern方法
		config.addAllowedOriginPattern("*");

		config.addAllowedHeader("*");
		config.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}

}
