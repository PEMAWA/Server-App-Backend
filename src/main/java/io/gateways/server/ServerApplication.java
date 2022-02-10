package io.gateways.server;

import io.gateways.server.Model.Server;
import io.gateways.server.enumeration.Status;
import io.gateways.server.repo.ServerRepo;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
@Configuration
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null,"192.168.5.158", "Ubuntu Linux","16 GB" , "Personal PC", "http://localhost:88/server/image/server2.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.45.115", "Windows","32 GB" , "Web server", "http://localhost:88/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.11.35", "Karli Linux","168 GB" , "Main Frame", "http://localhost:88/server/image/server3.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null,"192.168.10.178", "Mac Os","15 GB" , "Departmental Server", "http://localhost:88/server/image/server.png", Status.SERVER_UP));
		};
	}
	//@Bean

	//public CorsFilter corsFilter (){

		// UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		//CorsConfiguration corsConfiguration = new CorsConfiguration();
	//	corsConfiguration.setAllowCredentials(true);
	//	corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000" , "http://localhost:4200"));
		//corsConfiguration.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
				//"Accept","Jwt-Token","Authorization","Origin,Accept","X-Requested-Width",
			//	"Access-Control-Request-Method","Access-Control-Request-Headers"));
		//corsConfiguration.setExposedHeaders(Arrays.asList("Origin" ,"Content-Type" ,"Accept" ,"Jwt-Token","Authorization",
			//	"Access-Control-Allow-Origin","Access-Control-Allow-Origin","Access-Control-Allow-Credentials","Filename"));
		//corsConfiguration.setExposedHeaders(Arrays.asList("*"));
		//corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST" ,"PUT" ,"PATCH" ,"DELETE", "OPTIONS"));
		//urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
		//return  new  CorsFilter() ;
	//}
@Bean
public WebMvcConfigurer corsConfigurer(){
		return  new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping(("/**"))
						.allowedMethods("GET","POST","DELETE")
						.allowedHeaders("*")
						.allowedOrigins("http://localhost:4200");
			}
		};
}
}
