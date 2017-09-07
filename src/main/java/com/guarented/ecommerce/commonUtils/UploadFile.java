package com.guarented.ecommerce.commonUtils;

import java.io.IOException;

public class UploadFile {

	public static void uploadFile1(String filePath) {
        String[] cmd = {"./src/test/resources/executables/uploadFile.exe", filePath};
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
