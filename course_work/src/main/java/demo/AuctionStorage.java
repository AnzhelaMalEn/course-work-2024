package demo;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AuctionStorage {
    private List<Auction> auctions;
    private final String filePath = "auction_data.txt";

    public AuctionStorage() {
        this.auctions = new ArrayList<>();
        loadAuctionsFromFile();
    }

    public List<Auction> getAllAuctions() {
        return auctions.stream()
                .filter(a -> a.getEndTime().after(new Date()))
                .collect(Collectors.toList());
    }

    public Auction getAuctionById(int id) {
        for (Auction auction : auctions) {
            if (auction.getId() == id) {
                return auction;
            }
        }
        return null;
    }

    public boolean placeBid(int auctionId, double bidAmount, Date bidTime) {
        Auction auction = getAuctionById(auctionId);
        if (auction != null && bidAmount > auction.getHighestBid() && auction.getEndTime().after(new Date())) {
            auction.setHighestBid(bidAmount);
            auction.setBidTime(bidTime);
            saveAuctionsToFile();
            return true;
        }
        return false;
    }

    public void addAuction(String title, double startingPrice, Date endTime, String description) {
        int newId = auctions.size() + 1;
        String url = "" + newId;
        Auction newAuction = new Auction(newId, title, startingPrice, new Date(), endTime, description, url);
        auctions.add(newAuction);
        saveAuctionsToFile();
    }

    public void updateAuction(int id, String title, double startingPrice, String description) {
        Auction auction = getAuctionById(id);
        if (auction != null) {
            auction.setTitle(title);
            auction.setStartingPrice(startingPrice);
            auction.setDescription(description);
            saveAuctionsToFile();
        }
    }

    public List<Auction> searchAuctions(String keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return new ArrayList<>();
        }
        String lowerCaseKeywords = keywords.toLowerCase();
        return auctions.stream()
                .filter(auction -> auction.getTitle().toLowerCase().contains(lowerCaseKeywords) ||
                        auction.getDescription().toLowerCase().contains(lowerCaseKeywords))
                .collect(Collectors.toList());
    }

    public List<Auction> getAdminAuctions() {
        return auctions;
    }

    private void loadAuctionsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 7) {
                        try {
                            int id = Integer.parseInt(parts[0]);
                            String title = parts[1];
                            double startingPrice = Double.parseDouble(parts[2]);
                            Date createdAt = new Date(Long.parseLong(parts[3]));
                            Date endTime = new Date(Long.parseLong(parts[4]));
                            String description = parts[5];
                            String url = parts[6];
                            auctions.add(new Auction(id, title, startingPrice, createdAt, endTime, description, url));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("Incorrect format in line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAuctionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Auction auction : auctions) {
                writer.write(auction.getId() + "," + auction.getTitle() + "," + auction.getStartingPrice() + "," +
                        auction.getCreatedAt().getTime() + "," + auction.getEndTime().getTime() + "," + auction.getDescription() + "," + auction.getUrl());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuction(int id) {
        auctions.removeIf(auction -> auction.getId() == id);
        saveAuctionsToFile();
    }

    public void checkAndCloseAuctions() {
        Date now = new Date();
        auctions.removeIf(auction -> auction.getEndTime().before(now));
        saveAuctionsToFile();
    }
}
