public class PatientRecord {
    private String patientID;
    private String patientName;
    private String bloodGroup;
    private String lastVisitDate;
    private static int idCounter = 1001;

    public PatientRecord() {
        this.patientName = "Unknown";
        this.bloodGroup = "O+";
        this.lastVisitDate = "2000-01-01";
        this.patientID = "P0000";
    }

    public PatientRecord(String patientName, String bloodGroup, String lastVisitDate) {
        this.patientID = "P" + (idCounter++);
        this.patientName = (patientName == null || patientName.trim().isEmpty()) ? "Unknown" : patientName;
        if (!setBloodGroup(bloodGroup)) {
            this.bloodGroup = "O+";
        }
        if (!setLastVisitDate(lastVisitDate)) {
            this.lastVisitDate = "2000-01-01";
        }
    }

    public PatientRecord(PatientRecord other) {
        if (other != null) {
            this.patientID = other.getPatientID() + "_COPY";
            this.patientName = other.getPatientName();
            this.bloodGroup = other.getBloodGroup();
            this.lastVisitDate = other.getLastVisitDate();
        } else {
            this.patientName = "Unknown";
            this.bloodGroup = "O+";
            this.lastVisitDate = "2000-01-01";
            this.patientID = "P0000";
        }
    }

    public String getPatientID() {
        return this.patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        if (patientName != null && !patientName.trim().isEmpty()) {
            this.patientName = patientName;
        }
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public boolean setBloodGroup(String bloodGroup) {
        if (bloodGroup == null) return false;
        String bg = bloodGroup.trim().toUpperCase();
        if (bg.equals("A+") || bg.equals("A-") || bg.equals("B+") || bg.equals("B-") ||
            bg.equals("AB+") || bg.equals("AB-") || bg.equals("O+") || bg.equals("O-")) {
            this.bloodGroup = bg;
            return true;
        }
        return false;
    }

    public String getLastVisitDate() {
        return this.lastVisitDate;
    }

    public boolean setLastVisitDate(String lastVisitDate) {
        if (lastVisitDate != null && !lastVisitDate.trim().isEmpty()) {
            this.lastVisitDate = lastVisitDate;
            return true;
        }
        return false;
    }

    public static PatientRecord create(String patientName, String bloodGroup, String lastVisitDate) {
        if (patientName == null || patientName.trim().isEmpty()) {
            System.out.println("Validation Error: Patient name cannot be empty.");
            return null;
        }
        String bg = (bloodGroup != null) ? bloodGroup.trim().toUpperCase() : "";
        if (!bg.equals("A+") && !bg.equals("A-") && !bg.equals("B+") && !bg.equals("B-") &&
            !bg.equals("AB+") && !bg.equals("AB-") && !bg.equals("O+") && !bg.equals("O-")) {
            System.out.println("Validation Error: Invalid Blood Group: " + bloodGroup);
            return null;
        }
        if (lastVisitDate == null || lastVisitDate.trim().isEmpty()) {
            System.out.println("Validation Error: Last visit date cannot be empty.");
            return null;
        }
        return new PatientRecord(patientName, bg, lastVisitDate);
    }

    public PatientRecord copy() {
        return new PatientRecord(this);
    }

    public boolean isEqual(PatientRecord other) {
        if (other == null) return false;
        return this.getPatientID().equals(other.getPatientID()) && 
               this.getPatientName().equalsIgnoreCase(other.getPatientName());
    }

    public boolean notEqual(PatientRecord other) {
        return !this.isEqual(other);
    }

    public boolean mergeHistory(PatientRecord other) {
        if (other == null) {
            System.out.println("Merge Error: Target record is null.");
            return false;
        }
        if (!this.getPatientName().equalsIgnoreCase(other.getPatientName())) {
            System.out.println("Merging Failed: Patient Name mismatch (" + this.getPatientName() + " vs " + other.getPatientName() + ").");
            return false;
        }
        if (!this.getBloodGroup().equals(other.getBloodGroup())) {
            System.out.println("Merging Failed: Blood Group mismatch (" + this.getBloodGroup() + " vs " + other.getBloodGroup() + ").");
            return false;
        }
        this.setLastVisitDate(other.getLastVisitDate());
        this.setPatientID(this.getPatientID() + "+" + other.getPatientID());
        return true;
    }

    @Override
    public String toString() {
        return "Patient: " + getPatientName() + ", ID: " + getPatientID() + 
               ", Blood Group: " + getBloodGroup() + ", Last Visit: " + getLastVisitDate();
    }
}
