public class Contact {

    private String fullName;
    private String phoneNumber;
    private String isFavorite;

    public Contact(String fullName, String phoneNumber, String isFavorite) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.isFavorite = isFavorite;
    }



    //accessors

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String isFavorite() {
        return isFavorite;
    }

    public String getFavorite() {
        return isFavorite;
    }

    public void setFavorite(String favorite) {
        isFavorite = favorite;
    }
}
