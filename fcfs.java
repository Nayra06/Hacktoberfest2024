import java.util.Scanner;

class Process {
    int pid;    // Process ID
    int at;     // Arrival time
    int bt;     // Burst time
    int ct;     // Completion time
    int wt;     // Waiting time
    int tat;    // Turnaround time
}

public class FCFS_22103020 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].pid = i + 1;
            System.out.print("Enter arrival time of P" + (i + 1) + ": ");
            p[i].at = sc.nextInt();
            System.out.print("Enter burst time of P" + (i + 1) + ": ");
            p[i].bt = sc.nextInt();
        }

        sortByArrivalTime(p, n);

        p[0].ct = p[0].at + p[0].bt;
        p[0].tat = p[0].ct - p[0].at;
        p[0].wt = p[0].tat - p[0].bt;

        int totalTAT = p[0].tat;
        int totalWT = p[0].wt;

        for (int i = 1; i < n; i++) {
            if (p[i].at <= p[i - 1].ct) {
                p[i].ct = p[i - 1].ct + p[i].bt;
            } else {
                p[i].ct = p[i].at + p[i].bt;
            }
            p[i].tat = p[i].ct - p[i].at;
            p[i].wt = p[i].tat - p[i].bt;

            totalTAT += p[i].tat;
            totalWT += p[i].wt;
        }

        double avgTAT = (double) totalTAT / n;
        double avgWT = (double) totalWT / n;

        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (Process proc : p) {
            System.out.println("P" + proc.pid + "\t" + proc.at + "\t" + proc.bt + "\t" +
                    proc.ct + "\t" + proc.tat + "\t" + proc.wt);
        }

        System.out.println("\nAverage Turnaround Time: " + avgTAT);
        System.out.println("Average Waiting Time: " + avgWT);

        System.out.println("\nGANTT Chart:");
        System.out.print(" ");
        for (Process proc : p) {
            System.out.print("--------");
        }
        System.out.println();
        for (Process proc : p) {
            System.out.print("|   P" + proc.pid + "  ");
        }
        System.out.println("|");
        System.out.print(" ");
        for (Process proc : p) {
            System.out.print("--------");
        }
        System.out.println();
        System.out.print(p[0].at);
        for (Process proc : p) {
            System.out.print("\t   " + proc.ct);
        }
    }

    public static void sortByArrivalTime(Process[] p, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (p[j].at > p[j + 1].at) {
                    Process temp = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = temp;
                }
            }
        }
    }
}
