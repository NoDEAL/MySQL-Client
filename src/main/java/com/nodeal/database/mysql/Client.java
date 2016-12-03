package com.nodeal.database.mysql;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by 김지환 on 2016-12-03.
 */
public class Client {
    public static void main(String... args) throws IOException {
        Socket socket = new Socket("URL", 5000);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        dataOutputStream.writeUTF(makeMessage("query", "show databases;", "column1", "Database"));

        System.out.println(dataInputStream.readUTF());

        System.out.println(dataInputStream.readUTF());

        socket.close();
        dataOutputStream.close();
        dataInputStream.close();
    }

    public static String makeMessage(String... content) {
        JSONObject jsonObject = new JSONObject();
        String[] titles = new String[content.length / 2];
        String[] bodies = new String[content.length / 2];

        int j = 0;
        for (int i = 0; i < content.length; i++) {
            if (i % 2 == 0) titles[j] = content[i];
            else bodies[j++] = content[i];
        }

        for (int i = 0; i < titles.length; i++) {
            jsonObject.put(titles[i], bodies[i]);
        }

        return jsonObject.toString();
    }
}
