package com.beshoy.controller.Response;

import com.beshoy.Beans.PhotoResponseObject;

import java.io.Serializable;


/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */
public class GetPhotosResponse  {

private PhotoResponseObject photos;
private String stat;
    public PhotoResponseObject getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoResponseObject photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}

