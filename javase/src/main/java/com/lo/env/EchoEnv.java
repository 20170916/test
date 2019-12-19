package com.lo.env;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class EchoEnv {
    public static void main(String[] args) throws InterruptedException {
        String currentPid = getCurrentPid();
        System.out.println(String.format("子进程环境变量，pid %s，ppid %s",currentPid,getPPID(currentPid)));
        Map<String, String> environment = System.getenv();
        for (Map.Entry<String, String> stringStringEntry : environment.entrySet()) {
            String key = stringStringEntry.getKey();
            System.out.println(String.format("%s-%s", key,stringStringEntry.getValue()));
            if(key.equals("HADOOP_USER_NAME")) {
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            }
        }
        Thread.sleep(10000);
    }


    public static String getCurrentPid(){
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        System.out.println(name);
        return name.substring(0, name.indexOf("@"));
    }

    public static String getPPID(String pid) {
        try {
            Path path = new File(String.format("/proc/%s/status", pid)).toPath();
            byte[] bytes = Files.readAllBytes(path);
            List<String> strings = Files.readAllLines(path);
            String ppidEntry ="";
            if(strings.size()<6 || !(ppidEntry = strings.get(5)).startsWith("PPid:\t")
                    || ppidEntry.length()<=6){
                System.out.println("error ppidEntry:"+ppidEntry);
                return null;
            }
            System.out.println("ppidEntry:"+ppidEntry);
            return ppidEntry.substring(6);
        } catch (NoSuchFileException noFileException){
            System.out.println(pid+" 进程不存在");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
