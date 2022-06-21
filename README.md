# callbuslab

## 과제 구상도
![image](https://user-images.githubusercontent.com/81105748/173882201-3185a63f-2c30-44d3-af4c-0ac719c025a6.png)

&nbsp;
### :computer: 사용 프레임워크 및 라이브러리

* Spring Boot 2.7.0  
* Lombok AnnotationProcessor  
* Spring Validation
* Spring Boot Stater Test
* Spring Data JPA  
* H2 DataBase

&nbsp;
### :earth_asia: 실행

* H2 Database 설치 https://www.h2database.com/html/main.html
  * jdbc:h2:tcp://localhost/~/callbus 로 URL 설정 
  * username : sa
  * password : 공백

* Eclipse 구동
  * GitHub Repository 이용 프로젝트 Import
  * Import 완료 후 프로젝트 우클릭 -> Gradle -> Refresh Gradle Project
  * 프로젝트 우클릭 -> Run As -> Spring Boot App

&nbsp;
### :dart: 검증

* __Post Man 검증 (외부 유틸리티)__ : 
  * https://www.postman.com/ 응용 프로그램 혹은 웹 로그인 
  * BoardController에 해당하는 URL ex) GetMethod http://localhost/boards
  * Authorization이 필요한경우 Headers탭에 __Authorization : REALTOR 1__ 같은 데이터 입력
  * Body가 필요한 경우 ex) BoardRequestDTO Body탭에 __raw선택 -> text를 json으로 변경 후__ 데이터 입력     

 &nbsp;

* __테스트 코드 검증__ : &nbsp;  
  src/test/java 패키지에서 com.callbus.zaritalk.board 패키지 내의 Controller , Service , Repository Class __우클릭 후 Run As -> Junit5 Test 가동__

&nbsp;
### :memo: API Documentation
&nbsp;  

 <img src="https://img.shields.io/badge/GET-getList-green">&nbsp;
  * URL : __```http://localhost/boards```__
  * Function : 게시글 목록 조회 
  * Header : Authorization - <span style="color:gray">__필수아님__</span>
  * __Return__ : BoardList
  
&nbsp;   
 <img src="https://img.shields.io/badge/GET-getOne-green">&nbsp;  
  * URL : __```http://localhost/boards/1 ```__
  * Function : 게시글 상세 조회
  * __Return__ : BoardEntity

&nbsp;

<img src="https://img.shields.io/badge/POST-insert-blue">&nbsp;  
  * URL : __```http://localhost/boards```__
  * Function : 게시글 입력
  * Header : Authorization - <span style="color:red">__필수__</span>
  * Body : JSON DATA &nbsp;  
       {&nbsp;  
       &nbsp;&nbsp;"title" : "테스트 제목" , &nbsp;  
       &nbsp;&nbsp;"content" : "테스트 내용"&nbsp;  
        }
  * __Return__ : BoardEntity
  
 &nbsp; 
<img src="https://img.shields.io/badge/PUT-update-important">&nbsp;  
  * URL : __```http://localhost/boards/1```__
  * Function : 게시글 수정
  * Header : Authorization - <span style="color:red">__필수__</span>
  * Body : JSON DATA &nbsp;  
       {&nbsp;  
       &nbsp;&nbsp;"title" : "테스트 제목" , &nbsp;  
       &nbsp;&nbsp;"content" : "테스트 내용"&nbsp;  
        }
  * __Return__ : TRUE     

&nbsp;
<img src="https://img.shields.io/badge/DELETE-delete-red">&nbsp;  
  * URL : __```http://localhost/boards/1```__
  * Function : 게시글 삭제
  * Header : Authorization - <span style="color:red">__필수__</span>
  * __Return__ : TRUE     

&nbsp;  
<img src="https://img.shields.io/badge/POST-like-blue">&nbsp;     
  * URL : __```http://localhost/boards/1/likes```__
  * Function : 좋아요 (게시글) - 한번 요청시에는 취소됨
  * Header : Authorization - <span style="color:red">__필수__</span>
  * __Return__ : TRUE     

&nbsp;
### :point_up: 추가 요구사항
1. 좋아요를 중복 요청시 해당 좋아요를 취소한다.
2. 작성자Id(기본키)와 Authorization에 요청된 숫자가 동일해야 수정, 삭제 가능하다.
 
