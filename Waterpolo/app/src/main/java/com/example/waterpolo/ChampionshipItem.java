package com.example.waterpolo;

public class ChampionshipItem {
    private String id;
    private String name;
    private String info;
    private int imageResource;
    private int cartedCount;

    public ChampionshipItem(String name, String info, int imageResource, int cartedCount) {
        this.name = name;
        this.info = info;
        this.imageResource = imageResource;
        this.cartedCount = cartedCount;
    }

    public ChampionshipItem() {
    }

    public int getCartedCount() {
        return cartedCount;
    }

    public String getName() {
        return name;
    }
    public String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String _getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}