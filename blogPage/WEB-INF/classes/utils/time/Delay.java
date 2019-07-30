package utils.time;

public class Delay {
    private int length; // in ms
    private long endTime;
    private boolean started;

    public Delay(int length) {
        this.length = length;
        this.started = false;
    }

    public boolean isOver() {
        start();
        if(System.currentTimeMillis() >= endTime) {
            reset();
            return true;
        }

        return false;
    }

    private void start() {
        if(started) return;
        started = true;
        endTime = Time.getTimeMS() + length;
    }

    private void reset() {
        if(!started) return;
        started = false;
    }
}