public class PatientRecordTest {
    public static void main(String[] args) {
       

        PatientRecord p1 = new PatientRecord();
        PatientRecord p2 = new PatientRecord("Zain Ali", "B+", "2026-03-12");
        PatientRecord p3 = new PatientRecord("Ayesha Khan", "O-", "2026-05-20");
        PatientRecord p4 = new PatientRecord(p2);
         System.out.println("********************patient 1************************");
        System.out.println(p1.toString());
         System.out.println("********************patient 2************************");
        System.out.println(p2.toString());
        System.out.println();
         System.out.println("********************patient 3************************");
        System.out.println(p3.toString());
        System.out.println();
         System.out.println("********************patient 4************************");
        System.out.println(p4.toString());
        System.out.println();
         System.out.println("********************patient 5************************");
        System.out.println();

        System.out.println("********************Setters Testing patient 1 ************************");
        p1.setPatientName("John Doe");
        p1.setBloodGroup("AB+");
        p1.setLastVisitDate("2026-06-01");
        System.out.println("Updated p1: " + p1.toString());
         System.out.println();
        System.out.println("\n******************** Testing Invalid Setters ********************");
        if (!p1.setBloodGroup("X-")) {
            System.out.println("Validation Handled: Rejected invalid blood type 'X-'");
        }
        if (!p1.setLastVisitDate("")) {
            System.out.println("Validation Handled: Rejected empty visit date.");
        }
        System.out.println();

        System.out.println("--- Testing Static Factory create() ---");
        PatientRecord p5 = PatientRecord.create("Hamza Butt", "A+", "2026-06-28");
        if (p5 != null) {
            System.out.println("Created p5: " + p5.toString());
        }

        System.out.println("\n--- Testing Static Factory with Invalid Values ---");
        PatientRecord invalidRecord = PatientRecord.create("", "B-", "2026-01-01");
        System.out.println();

        System.out.println("********************  Testing Comparison Methods ******************** ");
        System.out.println("***************patient 2  vs  patient 4 (Copy Constructor Object)***************:");
        System.out.println("isEqual: " + p2.isEqual(p4));
        System.out.println("notEqual: " + p2.notEqual(p4));
        
        System.out.println("\npatient 2 vs patient 3:");
        System.out.println("isEqual: " + p2.isEqual(p3));
        System.out.println("notEqual: " + p2.notEqual(p3));
        System.out.println();

        System.out.println("******************** Testing copy() Method ********************");
        PatientRecord instanceCopy = p3.copy();
        System.out.println("Original p3: " + p3.toString());
        System.out.println("Instance Copy: " + instanceCopy.toString());
        System.out.println();

        System.out.println("******************** Testing mergeHistory() Cases ********************");
        PatientRecord matchingHistory = new PatientRecord("Zain Ali", "B+", "2026-06-30");
         System.out.println();
        System.out.println("Case 1 (Failure Mismatch):");
        boolean failStatus = p2.mergeHistory(p3);
        System.out.println("Merge Status: " + failStatus);
 System.out.println();
        System.out.println("\nCase 2 (Success Match):");
        System.out.println("Before Merge p2: " + p2.toString());
        boolean successStatus = p2.mergeHistory(matchingHistory);
        System.out.println("Merge Status: " + successStatus);
        System.out.println("After Merge p2: " + p2.toString());
    }
}
