package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.service.Contact;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	//アクセスURL　http://localhost:8080/swagger-ui.html
	
	@Bean
	public Docket swaggerSpringMvcPlugin() {
		
		return new Docket(DocumentationType.SWAGGER_2)
					.groupName("sample-api")
					.select()
					.paths(paths())
					.build()
					.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		// http://springfox.github.io/springfox/javadoc/2.6.0/index.html?springfox/documentation/service/ApiInfo.html
		
		return new ApiInfo(
				 "SampleApi",//タイトル
				 "APIの説明",//説明
				 "V1",//バージョン
				 "",// terms of service url
				 new Contact(
						 "株式会社XXXXXXX"      // APIに関する連絡先組織・団体等
						 ,"http://XXXXXXXXXXX.co.jp" // APIに関する連絡先組織・団体等のWeb Site Url
						 ,"XXXXXXXX@example.jp" // APIに関する連絡先組織・団体等のメールアドレス
						 )     
				 ,"API LICENSE" // APIのライセンス
				 ,"http://XXXXXXXXXXXX.co.jp"   // APIのライセンスURL
				 ,new ArrayList<VendorExtension>()  // 独自に拡張したいドキュメントがあればここで作成
				);
	}

	private Predicate<String> paths(){
		//仕様書生成対象のURLパスを指定
		return Predicates.or(Predicates.containsPattern("/rest")); //restで始まるURLを指定
	}
}
