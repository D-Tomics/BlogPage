package utils.downloader;

public enum Status {
    
    DOWNLOADING("DOWNLOADING"),
    STOPPED("STOPPED"), 
    DOWNLOADED("DOWNLOADED"),
    ERROR("ERROR");

    private String value;
    private Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}