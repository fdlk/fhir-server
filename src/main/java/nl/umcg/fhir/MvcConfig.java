package nl.umcg.fhir;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public ObjectMapper jacksonObjectMapper() {
	    ObjectMapper result = new ObjectMapper();
	    result.setSerializationInclusion(Include.NON_EMPTY);
	    return result;
	}
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(createJsonConverter());
		converters.add(createXmlConverter());
	}

	private Jaxb2RootElementHttpMessageConverter createXmlConverter() {
		return new Jaxb2RootElementHttpMessageConverter();
	}

	private MappingJackson2HttpMessageConverter createJsonConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setPrettyPrint(true);
		jsonConverter.setObjectMapper(jacksonObjectMapper());
		return jsonConverter;
	}

}
