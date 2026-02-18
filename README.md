# Nexacro UIAdapter Jakarta Sample Project

이 프로젝트는 Nexacro N UI 제품과 호환되는 `uiadapter-jakarta` 모듈 기반의 Spring Boot 3 샘플 프로젝트입니다. Java 17 및 Jakarta EE 환경에서 Nexacro와의 데이터 연동 및 비즈니스 로직 구현 방법을 제시합니다.

## 🔗 GitLab 저장소 정보

- **Clone URL**: `https://gitlab.com/nexacron/spring-boot/jakarta/uiadapter-jakarta.git`
- **Download URL**: [Source Code (ZIP)](https://gitlab.com/nexacron/spring-boot/jakarta/uiadapter-jakarta/-/archive/master/uiadapter-jakarta-master.zip?ref_type=heads)

## ✨ 주요 특징

- **Spring Boot 3 & Java 17**: 최신 기술 스택 기반의 웹 애플리케이션 환경 제공
- **자동 데이터 바인딩**: `@ParamDataSet`, `@ParamVariable` 어노테이션을 통한 Nexacro 데이터셋-Java 객체 간 자동 변환
- **MyBatis 연동**: 커스텀 Interceptor를 통해 조회 결과 0건 시에도 메타데이터(컬럼 정보) 유지
- **통합 예외 처리**: 서버 예외를 Nexacro 표준 에러 포맷으로 자동 변환 처리
- **부가 기능 제공**: Excel Export/Import, 파일 업로드/다운로드 예제 포함

## 🛠 기술 스택

- **Platform**: Spring Boot 3.0.0
- **Language**: Java 17
- **Database**: MyBatis 2.3.1 (H2 In-memory DB 사용)
- **UI Framework**: Nexacro N
- **Adapter**: `uiadapter-jakarta-core` (1.0.24-SNAPSHOT)

## 🚀 시작하기

### 1. 사전 요구사항
- JDK 17 이상
- Eclipse IDE (또는 IntelliJ)
- Maven 3.x

### 2. Eclipse 프로젝트 세팅 가이드

#### Case A: GitLab에서 Clone 하는 경우
1. Eclipse에서 `File` > `Import...` 선택
2. `Git` > `Projects from Git (with smart import)` 선택 후 `Next`
3. `Clone URI` 선택 후 `Next`
4. **URI** 항목에 아래 주소 입력:
   `https://gitlab.com/nexacron/spring-boot/jakarta/uiadapter-jakarta.git`
5. `Next`를 진행하며 브랜치 선택(master) 및 로컬 저장소 경로 설정
6. `Import as general project`로 완료 후, 만약 Maven 프로젝트로 인식되지 않으면 프로젝트 우클릭 > `Configure` > `Convert to Maven Project` 수행

#### Case B: ZIP 파일을 다운로드하여 설정하는 경우
1. 다운로드한 `uiadapter-jakarta-master.zip` 파일의 압축을 해제합니다.
2. Eclipse에서 `File` > `Import...` 선택
3. `Maven` > `Existing Maven Projects` 선택 후 `Next`
4. **Root Directory**에 압축을 해제한 폴더 경로를 지정합니다.
5. `pom.xml`이 리스트에 나타나면 `Finish`를 눌러 임포트를 완료합니다.

### 3. 애플리케이션 실행
- `UiadapterApplication.java` 파일을 열고 `Run As` > `Java Application`으로 실행합니다.
- 서버 기본 포트: `8080` (Context Path: `/uiadapter`)

## 🔍 확인 및 테스트

서버가 정상적으로 기동된 후, 브라우저에서 아래 URL을 호출하여 샘플 화면이 작동하는지 확인합니다.

> **테스트 URL**: [http://localhost:8080/uiadapter/packageN/index.html](http://localhost:8080/uiadapter/packageN/index.html)

---

## 📂 프로젝트 구조

- `src/main/java`: Java 소스 코드 (Controller, Service, Mapper, VO 등)
- `src/main/resources`: 설정 파일 (`application.yml`, `mybatis-config.xml`) 및 매퍼 XML
- `webapp`: Nexacro UI 관련 리소스 및 index.html

---

## 📑 주요 컨트롤러 및 서비스 설명 (*.do)

### 1. BoardController (게시판 샘플)
게시판의 기본 CRUD 기능을 처리합니다.
- `select_datalist.do`: 게시판 목록 조회 (VO 기반)
- `select_datalist_map.do`: 게시판 목록 조회 (Map 기반)
- `select_data_single.do`: 게시물 단건 조회 (Map 기반)
- `update_datalist.do`: 게시판 데이터 저장 (C/U/D, VO 기반)
- `update_datalist_map.do`: 게시판 데이터 저장 (C/U/D, Map 기반)
- `select_datalist_firstrow.do`: FirstRow 방식을 이용한 대량 데이터 전송 샘플
- `test.do`: uiadapter에서 지원하는 다양한 파라미터 타입(DataSet, Variable, Map, List 등) 테스트

### 2. ExampleDateTypeController (데이터 타입 샘플)
다양한 Java 및 Nexacro 데이터 타입 간의 매핑을 확인합니다.
- `check_testDataTypeList.do`: 클라이언트에서 받은 DataSet을 VO 리스트로 변환하고 값 확인
- `select_testDataTypeList.do`: DB 데이터 조회 및 이미지(byte[]) 포함 응답 샘플
- `select_testDataTypeList_map.do`: DB 데이터 조회 (Map 기반)
- `update_testDataTypeList.do`: 데이터 저장 (VO 기반)
- `update_testDataTypeList_map.do`: 데이터 저장 (Map 기반)
- `checkArgsAnotation.do`: 다양한 어노테이션(`@ParamDataSet`, `@ParamVariable`) 사용 샘플

### 3. FileController (파일 업로드/다운로드)
파일 처리와 관련된 다양한 시나리오를 제공합니다.
- `advancedUploadFiles.do`: 멀티파트(Multipart) 파일 업로드 처리
- `advancedDownloadFile.do`: 개별 파일 다운로드
- `multiDownloadFiles.do`: 커스텀 멀티 파일 다운로드
- `advancedDownloadFiles.do`: 여러 파일을 ZIP으로 압축하여 다운로드
- `advancedDownloadList.do`: 서버의 파일 목록 조회
- `advancedDeleteFiles.do`: 서버의 파일 삭제

### 4. LargeDataController (대량 데이터 처리)
대량 데이터를 효율적으로 클라이언트에 전송하는 샘플입니다.
- `sampleLargeData.do`: `NexacroFirstRowHandler`를 사용하여 데이터를 Chunked 방식으로 끊어서 전송

### 5. StreamController (비디오 스트리밍)
멀티미디어 데이터를 스트리밍 방식으로 처리합니다.
- `streamingVideo.do`: `NexacroStreamResult`를 사용하여 비디오 파일 스트리밍 (Partial Content 지원)
- `video.do` / `movie/` : 스트리밍 테스트용 뷰(View) 매핑

---

## 💡 핵심 연동 개념

### 데이터 조회 프로세스
1. Nexacro Client에서 `PlatformData` 전송
2. `NexacroMethodArgumentResolver`가 데이터를 분석하여 Controller 파라미터(VO, Map 등)로 변환
3. Service 및 Mapper 레이어에서 비즈니스 로직 수행
4. `NexacroResult` 객체에 결과를 담아 반환
5. `NexacroHandlerMethodReturnValueHandler`가 응답을 `PlatformData` 형식으로 변환하여 클라이언트에 전송

### MyBatis 연동 (Metadata)
- `NexacroMybatisMetaDataProvider`와 `NexacroMybatisResultSetHandler` 플러그인을 사용하여 데이터가 없는 경우에도 Nexacro Grid의 컬럼 구조가 유지되도록 처리합니다.

---
© 2024 Nexacro UIAdapter Jakarta Guide.
