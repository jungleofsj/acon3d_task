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

## User Controller

[신규 유저 생성](https://www.notion.so/843a2abab50e4750aeec4cff1bf1d07c)
