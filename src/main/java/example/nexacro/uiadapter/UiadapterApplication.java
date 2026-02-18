package example.nexacro.uiadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 일반 실행 형식
public class UiadapterApplication {

// War 파일로 배포형식
//public class UiadapterApplication extends SpringBootServletInitializer{

	/**
	 * War 파일로 배포일때 추가
	 * @param args
	 * @return
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UiadapterApplication.class);
	}
	 */

	public static void main(String[] args) {
		SpringApplication.run(UiadapterApplication.class, args);
		// 2025.03.12 procedure 테스트
		//InitProcedure.runProcedure();
	}
}