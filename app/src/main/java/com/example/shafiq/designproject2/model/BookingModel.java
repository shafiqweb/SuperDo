package com.example.shafiq.designproject2.model;

public class BookingModel {
    private int ownerImage;
    private String ownerName;
    private String beautyCategory;
    private String sectionHeaderName;

    public BookingModel(String sectionHeaderName){
        this.sectionHeaderName = sectionHeaderName;

    }
    public BookingModel(String ownerName,int ownerImage,String beautyCategory){
        this.ownerName = ownerName;
        this.ownerImage = ownerImage;
        this.beautyCategory = beautyCategory;
    }

    public int getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(int ownerImage) {
        this.ownerImage = ownerImage;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getBeautyCategory(){
        return beautyCategory;
    }
    public void setBeautyCategory(String beautyCategory){
        this.beautyCategory = beautyCategory;
    }

    public String getSectionHeaderName() {
        return sectionHeaderName;
    }

    public void setSectionHeaderName(String sectionHeaderName) {
        this.sectionHeaderName = sectionHeaderName;
    }
}
