
import java.util.Scanner;

public class Ip {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter IP Address (xxx.xxx.xxx.xxx): ");
        String[] ipParts = input.next().split("\\.");

        System.out.print("Enter Subnet Mask (xxx.xxx.xxx.xxx) or CIDR (xx): ");
        String subnetMask = input.next();

        int[] ip = new int[4];
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(ipParts[i]);
        }

        int[] subnet;
        if (subnetMask.contains("/")) {
            subnet = cidrToSubnetMask(Integer.parseInt(subnetMask.split("/")[1]));
        } else {
            subnet = new int[4];
            String[] subnetParts = subnetMask.split("\\.");
            for (int i = 0; i < 4; i++) {
                subnet[i] = Integer.parseInt(subnetParts[i]);
            }
        }

        System.out.println("Results:");
        System.out.println("Network ID: " + getNetworkId(ip, subnet));
        System.out.println("Broadcast ID: " + getBroadcastId(ip, subnet));
        System.out.println("Default Gateway: " + getDefaultGateway(ip, subnet));
        System.out.println("Total Number of Hosts: " + getTotalHosts(subnet));
        System.out.println("Number of Usable Hosts: " + getUsableHosts(getTotalHosts(subnet)));
    }

    private static int[] cidrToSubnetMask(int cidr) {
        int[] subnet = new int[4];
        int bits = 32 - cidr;
        int mask = (1 << bits) - 1;

        subnet[0] = (mask >> 24) & 255;
        subnet[1] = (mask >> 16) & 255;
        subnet[2] = (mask >> 8) & 255;
        subnet[3] = mask & 255;

        return subnet;
    }

    private static String getNetworkId(int[] ip, int[] subnet) {
        return (ip[0] & subnet[0]) + "." +
               (ip[1] & subnet[1]) + "." +
               (ip[2] & subnet[2]) + "." +
               (ip[3] & subnet[3]);
    }

    private static String getBroadcastId(int[] ip, int[] subnet) {
        return (ip[0] | ~subnet[0]) + "." +
               (ip[1] | ~subnet[1]) + "." +
               (ip[2] | ~subnet[2]) + "." +
               (ip[3] | ~subnet[3]);
    }

    private static String getDefaultGateway(int[] ip, int[] subnet) {
        return ip[0] + "." + ip[1] + "." + ip[2] + "." + 1;
    }

    private static int getTotalHosts(int[] subnet) {
        int hosts = 0;
        for (int i = 0; i < 4; i++) {
            hosts += Integer.bitCount(~subnet[i]);
        }
        return (1 << hosts);
    }

    private static int getUsableHosts(int totalHosts) {
        return totalHosts - 2;
    }
}

