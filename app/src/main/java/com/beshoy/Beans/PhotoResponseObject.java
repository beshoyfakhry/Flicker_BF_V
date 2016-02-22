package com.beshoy.Beans;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */

public class PhotoResponseObject {
 
    private int  page;

    private String  pages;
    private int perpage;
    private String total;
    private ArrayList<PhotoObject>photo;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<PhotoObject> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<PhotoObject> photo) {
        this.photo = photo;
    }
}
