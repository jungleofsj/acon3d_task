package CanTuna.acon3dtask.domain;

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getNameChn() {
        return nameChn;
    }

    public void setNameChn(String nameChn) {
        this.nameChn = nameChn;
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

    public String getTextChn() {
        return textChn;
    }

    public void setTextChn(String textChn) {
        this.textChn = textChn;
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

    public Double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }






}
