package com.whale.admin.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.checkerframework.checker.units.qual.K;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.hutool.poi.excel.sax.ElementName.v;

/**
 * @author krspace-00507
 */
public class FfmpegTest1 {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String startTime = "";
    private static String endTime = "";

    public static void main(String[] args) throws Exception {
//        File file = new File("/Users/krspace-00507/Downloads/ffmpeg1/");
        File file = new File("/Users/krspace-00507/Downloads/ffmpeg1/60df2755436e402095505715a4242de2/");
        Map<Long, Object> mapFileWav = new TreeMap<>();
        Map<Long, Object> mapFileG722 = new TreeMap<>();
        getFiles(file, mapFileWav, mapFileG722);
//        Map.Entry<Long, Object> head = mapFileWav.entrySet().iterator().next();
//        System.out.println("head：" + sdf.format(new Date(head.getKey())));
//        Map.Entry<Long, Object> bottom = mapFileWavDesc.entrySet().iterator().next();
//        System.out.println("bottom：" + sdf.format(new Date(bottom.getKey())));
        /*List<Map<Long, Object>> g722Maps = mapChunk(mapFileG722, 100);
        for (int i = 0; i < g722Maps.size(); i++) {
            audioFormatConvers(g722Maps.get(i));
        }
        System.out.println("success-mapFileWav：" + mapFileWav.size());
        System.out.println("success-mapFileG722：" + mapFileG722.size());*/

        List<Map<Long, Object>> maps = mapChunk(mapFileWav, 100);
        for (int i = 0; i < maps.size(); i++) {
            generateEndAudio(maps.get(i), i+1, maps.size());
//            查看切割后的maps是否是按照key的时间戳排序
            /*System.out.println("============" + i + "==============");
            Map<Long, Object> map = maps.get(i);
            map.entrySet().forEach(raw -> {
                String fileGeneTime = sdf.format(new Date(raw.getKey()));
                System.out.println(fileGeneTime);
            });*/
        }
        System.out.println("count -> mapFileWav：" + mapFileWav.size());
        System.out.println("开始时间：" + startTime);
        System.out.println("结束时间：" + endTime);
    }

    public static void getFiles(File file, Map<Long, Object> mapFileWav, Map<Long, Object> mapFileG722) {
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            String fileName = tempList[i].getName();
            if (tempList[i].isDirectory()) {
//                System.out.println("文件夹：" + tempList[i]);
                getFiles(tempList[i], mapFileWav, mapFileG722);
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
                Long str_2 = Long.valueOf(endStr.substring(32));
                if (fileName.contains(".wav")) {
                    mapFileWav.put(str_2, tempList[i].getAbsolutePath());
                } else {
                    mapFileG722.put(str_2, tempList[i].getAbsolutePath());
                }
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

    private static void audioFormatConvers(Map<Long, Object> g722Map) throws Exception {
        g722Map.entrySet().forEach(raw -> {
            StringBuilder sb = new StringBuilder();
            String path = String.valueOf(raw.getValue());
            sb.append("ffmpeg -y -i ");
            sb.append(path);
            sb.append(" ");
            sb.append(path.replace(".g722", ".wav"));
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(sb.toString());
                readProcessOutput(process);
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void generateEndAudio(Map<Long, Object> mapFile, int index, int mapCount) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("ffmpeg -y");
        getFilePaths(sb, mapFile, index, mapCount);
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

    private static void getFilePaths(StringBuilder sb, Map<Long, Object> mapFile, int index, int mapCount) {
        int i = 0;
        for (Map.Entry<Long, Object> entry : mapFile.entrySet()) {
            i++;
            if (index == 1 && StringUtils.isBlank(startTime)) {
                startTime = sdf.format(new Date(entry.getKey()));
            }
            if (index == mapCount && i == mapFile.size()) {
                endTime = sdf.format(new Date(entry.getKey()));
            }
            sb.append(" -i ");
            sb.append(entry.getValue());

        }
        /*mapFile.forEach((k, v) -> {
            sb.append(" -i ");
            sb.append(v);
        });*/
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
