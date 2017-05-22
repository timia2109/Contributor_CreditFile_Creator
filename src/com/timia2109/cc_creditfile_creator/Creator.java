package com.timia2109.cc_creditfile_creator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Creator {

    /**
     * Returns a String which list all constributors
     * @param userRepo GitHubUser/RepoName
     * @return String list with Constributors
     */
    public static String getConstributors(String userRepo) throws Exception {
        System.out.println("Start download...");
        String jsonString = getConstributorsJSON(userRepo);
        System.out.println("Download finsihed");

        JSONArray json = new JSONArray(jsonString);

        List<Constributor> constList = new LinkedList<>();

        JSONObject current;
        Constributor currentConst;
        for (int i=0; i<json.length(); i++) {
            current = json.getJSONObject(i);

            if (current.has("login")) {
                currentConst = new Constributor(current.get("login").toString());
                constList.add(currentConst);
            }
        }

        Collections.sort(constList);

        StringBuilder sb = new StringBuilder();
        for (Constributor s : constList) {
            sb.append("- ").append(s).append('\n');
        }

        return sb.toString();
    }

    /**
     * Download the JSON from the GitHub API
     * @param userRepo GitHubUser/RepoName
     * @return The JSON Result
     * @throws Exception if the Request fails
     */
    private static String getConstributorsJSON(String userRepo) throws Exception {
        String stringUrl = "https://api.github.com/repos/"+userRepo+"/contributors";
        URL url = new URL(stringUrl);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();

        return Utils.getFile( is );
    }

    /**
     * Prints usage
     * @throws Exception Error reading file
     */
    private static void printUsage() throws Exception {
        System.out.println( Utils.getFile( Creator.class.getResourceAsStream("usage.txt") ) );

        System.exit(0);
    }

    public static void main(String[] args) throws Exception{
        if (args.length < 2) {
            printUsage();
        }

        File preFile = null,
                postFile = null,
                outFile = new File(args[1]);
        String repo = args[0];

        if (args.length >= 3) {
            preFile = new File(args[2]);

            if (!preFile.exists())
                throw new IOException("File "+preFile+" don't exists!");
        }

        if (args.length >= 4) {
            postFile = new File(args[3]);

            if (!postFile.exists())
                throw new IOException("File "+preFile+" don't exists!");
        }

        StringBuilder sbOut = new StringBuilder();

        if (preFile != null) {
            sbOut.append( Utils.getFile(preFile) );
        }

        sbOut.append( getConstributors( repo ) );

        if (postFile != null) {
            sbOut.append( Utils.getFile( postFile ) );
        }

        Utils.writeFile(sbOut.toString(), outFile);

        System.out.println("List created: " + outFile);
    }
}
