package CanTuna.acon3dtask.controller;

public class ItemForm {
    private long id;
    private String nameKor;
    private String nameEng;
    private String nameCHN;

    private String textKor;
    private String textEng;
    private String textCHN;

    private String creator;
    private String editor;
    private Double price;

    private Double commissonPct;

    private Boolean approved;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameKor() {
        return nameKor;
    }

    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameCHN() {
        return nameCHN;
    }

    public void setNameCHN(String nameCHN) {
        this.nameCHN = nameCHN;
    }

    public String getTextKor() {
        return textKor;
    }

    public void setTextKor(String textKor) {
        this.textKor = textKor;
    }

    public String getTextEng() {
        return textEng;
    }

    public void setTextEng(String textEng) {
        this.textEng = textEng;
    }

    public String getTextCHN() {
        return textCHN;
    }

    public void setTextCHN(String textCHN) {
        this.textCHN = textCHN;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCommissonPct() {
        return commissonPct;
    }

    public void setCommissonPct(Double commissonPct) {
        this.commissonPct = commissonPct;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }


}
