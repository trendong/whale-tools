package com.whale.admin.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author krspace-00507
 */
public class FfmpegTest {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
//        File file = new File("/Users/krspace-00507/Downloads/ffmpeg1/");
        File file = new File("/Users/krspace-00507/Downloads/ffmpeg1/258a82c843a248d7a1ac11c3bd90b6cf/");
        Map<Long, Object> mapFile = new TreeMap<>();
        getFiles(file, mapFile);
        List<Map<Long, Object>> maps = mapChunk(mapFile, 100);
        for (int i = 0; i < maps.size(); i++) {
            generateEndAudio(maps.get(i), i+1);
            System.out.println("success：" + maps.get(i).size());
//            查看切割后的maps是否是按照key的时间戳排序
            /*System.out.println("============" + i + "==============");
            Map<Long, Object> map = maps.get(i);
            map.entrySet().forEach(raw -> {
                String fileGeneTime = sdf.format(new Date(raw.getKey()));
                System.out.println(fileGeneTime);
            });*/
        }
    }

    public static void getFiles(File file, Map<Long, Object> mapFile) {
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            String fileName = tempList[i].getName();
            if (tempList[i].isDirectory()) {
//                System.out.println("文件夹：" + tempList[i]);
                getFiles(tempList[i], mapFile);
            }
            if (tempList[i].isFile()) {
                if (!fileName.contains(".wav")) {
//                if (!fileName.contains(".wav") && !fileName.contains(".g722")) {
                    continue;
                }
                String endStr = fileName.substring(0, fileName.indexOf("."));
                if (StringUtils.isBlank(endStr) || endStr.length() < 32) { continue; }
                String str_1 = endStr.substring(0, 32);
                if (StringUtils.isBlank(str_1)) { continue; }
                String str_2 = endStr.substring(32);
                mapFile.put(Long.valueOf(str_2), tempList[i].getAbsolutePath());
            }
        }
    }

    public static <k, v> List<Map<k, v>> mapChunk(Map<k, v> chunkMap, int chunkNum) {
        if (chunkMap == null || chunkNum <= 0) {
            List<Map<k, v>> list = new ArrayList<>();
            list.add(chunkMap);
            return list;
        }
        Set<k> keySet = chunkMap.keySet();
        Iterator<k> iterator = keySet.iterator();
        int i = 1;
        List<Map<k, v>> total = new ArrayList<>();
        Map<k, v> tem = new TreeMap<>();
        while (iterator.hasNext()) {
            k next = iterator.next();
            tem.put(next, chunkMap.get(next));
            if (i == chunkNum) {
                total.add(tem);
                tem = new TreeMap<>();
                i = 0;
            }
            i++;
        }
        if(!CollectionUtils.isEmpty(tem)){
            total.add(tem);
        }
        return total;
    }

    private static void generateEndAudio(Map<Long, Object> mapFile, int index) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("ffmpeg");
        getFilePaths(sb, mapFile);
        sb.append(" -filter_complex ");
        getAudioFilter(sb, mapFile.size());
        sb.append("concat=n=");
        sb.append(mapFile.size());
        sb.append(":v=0:a=1[out] -map [out]");
        sb.append(" /Users/krspace-00507/Downloads/ffmpeg2/mergeAudio");
        sb.append(index);
        sb.append(".mp3");
        System.out.println(sb);
        Process process = Runtime.getRuntime().exec(sb.toString());
        readProcessOutput(process);
        process.waitFor();
    }

    private static void getFilePaths(StringBuilder sb, Map<Long, Object> mapFile) {
        mapFile.forEach((k, v) -> {
//            String fileGeneTime = sdf.format(new Date(k));
            sb.append(" -i ");
            sb.append(v);
        });
    }

    private static void getAudioFilter(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append("[");
            sb.append(i);
            sb.append(":");
            sb.append(0);
            sb.append("]");
        }
    }

    private static void readProcessOutput(final Process process) {
        // 将进程的正常输出在 System.out 中打印，进程的错误输出在 System.err 中打印
        read(process.getInputStream(), System.out);
        read(process.getErrorStream(), System.err);
    }

    // 读取输入流
    private static void read(InputStream inputStream, PrintStream out) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
