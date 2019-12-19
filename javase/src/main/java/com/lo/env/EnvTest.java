package com.lo.env;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EnvTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> command = new LinkedList<>();
        command.add("java");
        command.add("EchoEnv");
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        // 设置环境变量
        Map<String, String> environment = processBuilder.environment();
        String currentPid = getCurrentPid();
        System.out.println(String.format("父进程环境变量，pid %s，ppid %s",currentPid,getPPID(currentPid)));
        environment.put("HADOOP_USER_NAME", "mario");
        for (Map.Entry<String, String> stringStringEntry : environment.entrySet()) {
            System.out.println(String.format("%s-%s",stringStringEntry.getKey(),stringStringEntry.getValue()));
        }
        Process process = processBuilder.start();
        pipeLog(process);
        process.waitFor();
        Thread.sleep(5000);

    }

    private static void pipeLog(Process process){
        StringBuilder logSb=new StringBuilder("echo env\n");
        try (
                InputStream inputStream = process.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logSb.append(line+"\n");
            }


        } catch (Throwable throwable) {
        }finally {
            System.out.println(logSb.toString());
        }
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
