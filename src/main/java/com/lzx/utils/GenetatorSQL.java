//package com.lzx.utils;
//
//
//import org.apache.ibatis.io.Resources;
//import org.junit.Test;
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.InvalidConfigurationException;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.*;
//public class GenetatorSQL {
//
//    @Test
//    public void init() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        String resource = "xbg.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = (Configuration) cp.parseConfiguration(inputStream);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
//    }
//}
