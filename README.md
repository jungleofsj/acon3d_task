---

- 카펜스트리트 Task 전형 제출물
- 작성자 : 이동원
- 개요 : 상품 및 유저 관리 API

---

# TECH Stack  &  Environment

 - JAVA 11

 - Spring Boot

 - JDBCTemplate

 - H2 DB / POSTMAN 기반 테스트 진행

 - OS : Windows10

 - IDE : IntelliJ

 - REST 기반 API 

---

## Domain Class

- Class User

```java
public class User {

    public static final Long USER_TYPE_CREATOR = 0L;
    public static final Long USER_TYPE_EDITOR = 1L;
    public static final Long USER_TYPE_CUSTOMER = 2L;

    private Long id;
    private String name;

    /*
		getters & setters
		*/
}
```

- Item Class

```java
public class Item {
    private Long Id;
    private String nameKor;
    private String nameEng;
    private String nameChn;

    private String textKor;
    private String textEng;
    private String textChn;

    private String creator;
    private String editor;

    private Double price;
    private Double commissionPct;
    private Boolean approved = false;
		
		/*
		getters & setters
		*/
}
```

- Currency Class

```java
public class Currency {
    private String country;
    private Double rate;

		/*
		getters & setters
		*/
}
```

---

## User Controller

- 신규 유저 생성

 name, type을 입력하여 신규 유저 생성

 - URI : POST /users/new

 - parameter : @RequestBody User user

 - In Success : return User, OK '200'

 - In Error : return null,  BAD REQUEST '400'

- 유저 로그인

 User.name, User.type이 일치하면 로그인

 - URI : POST /user/login

 - parameter : @RequestBody User user

 - In Sucess : return User.type, OK '200'

 - In Error : return null,  BAD REQUEST '400'

- 유저 리스트

 유저의 전체 목록 조회

 - URI : GET /users/all

 - parameter : 

 - return : List<User>, HttpStatus.OK

## User Controller

- 신규 상품 생성

 Authorization 값의 USER_TYPE_CREATOR와의 일치여부로 권한 확인, 상품 정보 입력하여 생성

 - URI : POST /items/new

 - parameter : @RequestHeader("Autorization") @RequestBody Item item

 - In Success : return User, OK '200'

 - In Error : return null, Unauthorized '401'

- 신규 상품 생성

 Authorization 값의 USER_TYPE_EDITOR와의 일치여부로 권한 확인, 상품 정보 수정

 - URI : POST /items/update

 - parameter : @RequestHeader("Autorization") @RequestBody Item item, @PathVariable("id") Long id

 - In Success : return User, OK '200'

 - In Authorization Error : return null, Unauthorized '401'

 - In itemId Error : return "UnExist productID", BAD REQUEST '400'

- 승인 또는 미승인 상품 조회

권한에 따라 승인여부가 다른 상품을 조회

 - URI : POST /items/approved

 - parameter : @RequestHeader("Autorization") @RequestParam("isApproved") Boolean isApproved

 - In Success : return List<User>, OK '200'

 - In Authorization Error : return "열람권한이 없습니다.", Unauthorized '401'

- 상품 구매

상품 구매 기록을 DB에 INSERT

 - URI : POST /items/purchase

 - parameter : @RequestHeader("Autorization") @RequestParam("isApproved") Boolean isApproved

 - In Success : return User.id, OK '200'

 - In Exististance Error : return "없는 상품입니다.", BAD REQUEST '400'
