package CanTuna.acon3dtask.domain;

public class User {

    public static final Long USER_TYPE_CREATOR = 0L;
    public static final Long USER_TYPE_EDITOR = 1L;
    public static final Long USER_TYPE_CUSTOMER = 2L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    private Long type;


}
