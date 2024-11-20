package com.hc.nettygame.common.scanner;


/**
 * @author C172
 * 消息扫描器
 */
public class ClassScanner {
    public String[] scannerPackage(String namespace, String ext) throws Exception {
        String[] files = new PackageScaner().scanNamespaceFiles(namespace, ext, false, true);
        return files;
    }

}
