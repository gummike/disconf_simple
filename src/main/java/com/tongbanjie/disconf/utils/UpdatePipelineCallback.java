package com.tongbanjie.disconf.utils;

import com.baidu.disconf.client.common.update.IDisconfUpdatePipeline;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class UpdatePipelineCallback implements IDisconfUpdatePipeline {

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void reloadDisconfFile(String key, String filePath) throws Exception {

        System.out.println(key + " : " + filePath);

        System.out.println(readFile(filePath, Charset.defaultCharset()));
    }

    public void reloadDisconfItem(String key, Object content) throws Exception {
        System.out.println(key + " : " + content);
    }
}