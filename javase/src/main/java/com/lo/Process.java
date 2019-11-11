package com.lo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Process {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println("currentId:" + getCurrentPid());
        // String pid = args[0];
        String pid = "428";
        System.out.println(String.format("get PPID: pid:%s ppid:%s", pid, getPPID(pid)));

        //Thread.sleep(1000 * 10);
    }

    /**
     * 只适用于linux操作系统，不适用于mac和windows系统
     * @param pid
     * @return
     */
    public static String getPPID(String pid) {
        try {
            Path path = new File(String.format("/proc/%s/status", pid)).toPath();
            byte[] bytes = Files.readAllBytes(path);
            List<String> strings = Files.readAllLines(path);
            String ppidEntry = "";
            if (strings.size() < 6 || !(ppidEntry = strings.get(5)).startsWith("PPid:\t")
                    || ppidEntry.length() <= 6) {
                System.out.println("error ppidEntry:" + ppidEntry);
                return null;
            }
            System.out.println("ppidEntry:" + ppidEntry);
            return ppidEntry.substring(6);
        } catch (Exception e) {
            return null;
        }
    }
}
