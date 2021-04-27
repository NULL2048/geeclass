package pers.cy.geeclass.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        /**
//         * allowCredentials为true时，allowedOrigins不能包含特殊值“*”
//         * 因为不能在“Access Control Allow Origin”响应头上设置该值。
//         * 要允许凭据访问一组源，请显式列出它们，或者考虑改用“allowedOriginPatterns”
//         *
//         * 上面这个错误是将spring boot升级为2.4.0而导致的
//         * https://blog.csdn.net/ASAS1314/article/details/110524116
//         */
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedHeaders(CorsConfiguration.ALL)
//                .allowedMethods(CorsConfiguration.ALL)
//                .allowCredentials(true)
//                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
//    }
//}

//// 老版本配置类
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders(CorsConfiguration.ALL)
//                .allowedMethods(CorsConfiguration.ALL)
//                .allowCredentials(true)
//                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
//    }
//
//}