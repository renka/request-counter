package com.assignment.interactions.demo;

import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;

import java.io.IOException;
import java.nio.ByteBuffer;

class GoogleStorageUtils {

    private String PROJECT_ID = "request-counter";
    private String BUCKET_URL = "request-counter.appspot.com";
    private String FILE_NAME = "storage";

    Integer read() {
        Integer i;
        try {
            i = Integer.parseInt((new String(getStorage().get(BUCKET_URL, FILE_NAME).getContent())).replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            i = 0;
        }
        return i;
    }

    Integer write() {
        Storage storage = getStorage();
        Integer i = read() + 1;

        BlobId blobId = BlobId.of(BUCKET_URL, FILE_NAME);
        byte[] content = i.toString().getBytes();
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();

        try (WriteChannel writer = storage.writer(blobInfo)) {
            try {
                writer.write(ByteBuffer.wrap(content, 0, content.length));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return read();
    }

    private Storage getStorage() {
        return StorageOptions.newBuilder()
                .setProjectId(PROJECT_ID)
                .build()
                .getService();
     }


}


