public class TimeInterval {
    private int hours;
    private int minutes;
    private int seconds;
    private String type;

    public TimeInterval() {
        this.hours = 0;
        this.minutes = 15;
        this.seconds = 0;
        this.type = "BREAK";
    }

    public TimeInterval(int hours, int minutes, int seconds, String type) {
        this.hours = Math.max(0, hours);
        this.minutes = (minutes >= 0 && minutes <= 59) ? minutes : 0;
        this.seconds = (seconds >= 0 && seconds <= 59) ? seconds : 0;
        this.type = (type != null && !type.trim().isEmpty()) ? type.toUpperCase() : "WORK";
    }

    public TimeInterval(TimeInterval other) {
        if (other != null) {
            this.hours = other.getHours();
            this.minutes = other.getMinutes();
            this.seconds = other.getSeconds();
            this.type = new String(other.getType());
        } else {
            this.hours = 0;
            this.minutes = 15;
            this.seconds = 0;
            this.type = "BREAK";
        }
    }

    public int getHours() {
        return this.hours;
    }

    public boolean setHours(int hours) {
        if (hours >= 0) {
            this.hours = hours;
            return true;
        }
        return false;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public boolean setMinutes(int minutes) {
        if (minutes >= 0 && minutes <= 59) {
            this.minutes = minutes;
            return true;
        }
        return false;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public boolean setSeconds(int seconds) {
        if (seconds >= 0 && seconds <= 59) {
            this.seconds = seconds;
            return true;
        }
        return false;
    }

    public String getType() {
        return this.type;
    }

    public boolean setType(String type) {
        if (type != null && !type.trim().isEmpty()) {
            this.type = type.toUpperCase();
            return true;
        }
        return false;
    }

    public static TimeInterval create(int hours, int minutes, int seconds, String type) {
        if (hours < 0 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59 || type == null || type.trim().isEmpty()) {
            System.out.println("Validation Error: Configuration fields failed limits checking rules.");
            return null;
        }
        return new TimeInterval(hours, minutes, seconds, type);
    }

    public TimeInterval copy() {
        return new TimeInterval(this);
    }

    private int calculateTotalSeconds() {
        return (this.hours * 3600) + (this.minutes * 60) + this.seconds;
    }

    public boolean isEqual(TimeInterval other) {
        if (other == null) return false;
        return this.calculateTotalSeconds() == other.calculateTotalSeconds();
    }

    public boolean notEqual(TimeInterval other) {
        return !this.isEqual(other);
    }

    public int shiftDuration(int secondsToAdd) {
        int newTotal = this.calculateTotalSeconds() + secondsToAdd;
        if (newTotal < 0) {
            newTotal = 0;
        }
        this.hours = newTotal / 3600;
        int remainingSeconds = newTotal % 3600;
        this.minutes = remainingSeconds / 60;
        this.seconds = remainingSeconds % 60;

        if (newTotal > 7200) {
            this.type = "LONG";
        } else if (newTotal >= 1800) {
            this.type = "MEDIUM";
        }
        return newTotal;
    }

    @Override
    public String toString() {
        return "Interval: " + getHours() + "h " + getMinutes() + "m " + getSeconds() + "s, Type: " + getType();
    }
}
