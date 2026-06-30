public class TimeIntervalTest {
    public static void main(String[] args) {
       

        TimeInterval t1 = new TimeInterval();
        TimeInterval t2 = new TimeInterval(1, 45, 30, "WORK");
        TimeInterval t3 = new TimeInterval(t2);
        TimeInterval t4 = TimeInterval.create(0, 20, 0, "TRAVEL");
        System.out.println("****************************all the time intervasal***********************************");
         System.out.println();
        System.out.println("****************************Check On Intervasl 1***********************************");
        System.out.println(t1.toString());
        System.out.println("****************************Check On Intervasl 2***********************************");
        System.out.println(t2.toString());
        System.out.println("****************************Check On Intervasl 3***********************************");
        System.out.println(t3.toString());
        System.out.println("****************************Check On Intervasl 14***********************************");
        System.out.println(t4.toString());
        System.out.println();

        System.out.println("**************************** Testing Valid Mutation ****************************");
        t1.setHours(2);
        t1.setMinutes(10);
        t1.setSeconds(45);
        t1.setType("LUNCH");
        System.out.println("Updated t1: " + t1.toString());

        System.out.println("\n**************************** Testing Invalid Mutation ****************************");
        if (!t1.setMinutes(99)) {
            System.out.println("Validation Handled: Rejected minutes value 99.");
        }
        if (!t1.setSeconds(-15)) {
            System.out.println("Validation Handled: Rejected negative seconds value.");
        }
        System.out.println();

        System.out.println("**************************** Testing Duration Equivalencies ****************************");
        System.out.println("t2 vs t3 (Deep Copy Object):");
        System.out.println("isEqual: " + t2.isEqual(t3));
        System.out.println("notEqual: " + t2.notEqual(t3));
        System.out.println();

        System.out.println("**************************** Verifying Deep Copy Reference Isolation ****************************");
        t3.setType("CODING");
        System.out.println("t3 Type modified to: " + t3.getType());
        System.out.println("t2 Type value remains: " + t2.getType());
        System.out.println();

        System.out.println("**************************** Testing shiftDuration() Scenarios ****************************");

        System.out.println("Scenario 1 (Total <= 1800s):");
        TimeInterval sc1 = new TimeInterval(0, 10, 0, "BREAK");
        System.out.println("Before: " + sc1.toString());
        int sec1 = sc1.shiftDuration(300);
        System.out.println("After (+300s, New Total: " + sec1 + "s): " + sc1.toString());
        System.out.println();

        System.out.println("Scenario 2 (Between 1800s and 7200s):");
        System.out.println("Before: " + sc1.toString());
        int sec2 = sc1.shiftDuration(1500);
        System.out.println("After (+1500s, New Total: " + sec2 + "s): " + sc1.toString());
        System.out.println();

        System.out.println("Scenario 3 (Total > 7200s):");
        System.out.println("Before: " + sc1.toString());
        int sec3 = sc1.shiftDuration(6000);
        System.out.println("After (+6000s, New Total: " + sec3 + "s): " + sc1.toString());
    }
}
