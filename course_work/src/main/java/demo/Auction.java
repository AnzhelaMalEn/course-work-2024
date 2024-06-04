package demo;

import java.util.Date;

public class Auction {
    private int id;
    private String title;
    private double startingPrice;
    private double highestBid;
    private Date createdAt;
    private Date bidTime;
    private Date endTime;
    private String description;
    private String url;

    public Auction(int id, String title, double startingPrice, Date createdAt, Date endTime, String description, String url) {
        this(id, title, startingPrice, createdAt, endTime, description, startingPrice, null, url);
    }

    public Auction(int id, String title, double startingPrice, Date createdAt, Date endTime, String description, double highestBid, Date bidTime, String url) {
        this.id = id;
        this.title = title;
        this.startingPrice = startingPrice;
        this.highestBid = highestBid;
        this.createdAt = createdAt;
        this.endTime = endTime;
        this.bidTime = bidTime;
        this.description = description;
        this.url = url;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
