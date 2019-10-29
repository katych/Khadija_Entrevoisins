package com.openclassrooms.entrevoisins.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
@SuppressLint("ParcelCreator")
public class Neighbour implements Parcelable {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    private String adresse;
    private String user_phone;
    private String facebook ;
    private String user_message;
    private boolean favorite;


    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param user_phone
     */
    public Neighbour(Integer id, String name, String avatarUrl , String adresse , String user_phone , String facebook , String user_message) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.adresse=adresse;
        this.user_phone= user_phone;
        this.facebook=facebook;
        this.user_message= user_message;
        this.favorite = false;
    }

    protected Neighbour(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        avatarUrl = in.readString();
        adresse = in.readString();
        user_phone= in.readString();
        facebook = in.readString();
        user_message= in.readString();
    }

    public static final Creator<Neighbour> CREATOR = new Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAdresse() { return adresse; }

    public void setAdresse(String adresse) { this.adresse = adresse; }


    public String getUser_phone() { return user_phone; }
    public void setUser_phone(String user_phone) { this.user_phone = user_phone; }

    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }


    public String getUser_message() { return user_message; }
    public void setUser_message(String user_message) { this.user_message = user_message; }

    public boolean isFavorite() { return favorite; }

    public void setFavorite(boolean favorite) { this.favorite = favorite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeString(adresse);
        dest.writeString(user_phone);
        dest.writeString(facebook);
        dest.writeString(user_message);
    }
}
