package com.project.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "supplies")
public class Supplies implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String name;

//    @JoinColumn(name = "image_url")
//    private String imageUrl;

    @OneToMany(mappedBy = "supplies", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //cascade uses in the OneToMany side
    //if not mappedBy, will create a new join table
    private List<MenuItem> menuItemList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
