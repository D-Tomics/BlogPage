package utils.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import javax.swing.JTextArea;

import utils.time.Delay;
import utils.time.Time;

public class Download implements Runnable {

    private int bufferSize = 1024;
    private float fileSize;
    private float downloaded;
    private float downloadSpeed;
    private long timeTook;
    private float timeRemaining;

    private URL url;
    private File out;
    private Status status = Status.STOPPED;

    public Download() {}

    public Download(String link, File out) {
        this.out = out;
        this.setLink(link);
    }

    @Override
    public void run() {
        try {
            download();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download() throws IOException {
        BufferedInputStream in = null;
        FileOutputStream fos = null;
        try {
            if(fileSize <= 0) 
                this.status = Status.ERROR; 
            in = new BufferedInputStream(url.openStream());
            fos = new FileOutputStream(out);

            Delay delay = new Delay(1000);

            int read = 0;
            float dRead = 0;

            long start = Time.getTimeMS();
            long end = Time.getTimeMS();

            byte[] data = new byte[bufferSize];
            while(status == Status.DOWNLOADING) {
                read = in.read(data, 0, bufferSize);
                if(read == -1) break;
                downloaded += read;
                dRead += read;
                if(delay.isOver()) {//one second delay
                    downloadSpeed = dRead;
                    dRead = 0;
                }

                fos.write(data, 0, read);
                
                start = end;
                end = Time.getTimeMS();
                float delta = end - start;


                timeTook += (delta);
                if(delta != 0)
                    timeRemaining = (delta/read) * (fileSize - downloaded);
            }

            if(status == Status.DOWNLOADING){
                status = Status.STOPPED;
            } 

        } finally {
            if(fos != null) fos.close();
            if(in != null) in.close();
        }
    }
    
    private void downloadUsingnNIO() throws IOException {

        URLConnection connection = url.openConnection();
        double fileSize = connection.getContentLength();
        if (fileSize == -1)
            fileSize = ((HttpURLConnection) connection).getContentLength();

        long start = Time.getTimeMS();
        ReadableByteChannel channel = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(out);
        FileChannel writeChannel = fos.getChannel();
        writeChannel.transferFrom(channel, 0, Long.MAX_VALUE);
        long end = Time.getTimeMS();

        timeTook = end - start;
    }

    public void start() {
        this.downloaded = 0;
        this.timeRemaining = 0;
        this.timeTook = 0;
        this.status = Status.DOWNLOADING;
        (new Thread(this)).start();
    }

    public void stop() {
        this.status = Status.STOPPED;
    }

    public void setOutputDir(String dir) {
        this.out = new File(dir);
    }

    public void setLink(String link) {
        try {
            this.url = new URL(link);
            URLConnection connection = url.openConnection();
            this.fileSize = connection.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBufferSize(int size) {
        this.bufferSize = size;
    }

    public float getFileSize() {
        return fileSize;
    }

    public float getProgress() {
        return (downloaded/fileSize) * 100f;
    }

    public float getDownloadSpeed() {
        return downloadSpeed;
    }

    public long getTimeTookToDownload() {
        return timeTook;
    }

    public Status getStatus() {
        return status;
    }

    public float getTimeRemaining() {
        return status == Status.DOWNLOADING ? timeRemaining : 0;
    }
   


    // private boolean downloading = false;
    // private boolean stop = false;
    // private String link;
    // private File out;
    
    // private float size;
    // private float downloaded;

    // private JTextArea pw;

    // public Download(String link, File out) {
    //     this.link = link;
    //     this.out = out;
    // }

    // public Download() {
    // }

    // public void init(String link, File out) {
    //     this.link = link;
    //     this.out = out;
    // }

    // public void start() {
    //     stop = false;
    //     (new Thread(this)).start();
    // }

    // public void stop() {
    //     if (downloading) {
    //         downloading = false;
    //         stop = true;
    //     }
    // }

    // private String method = "IO";

    // public void downloadUsing(String method) {
    //     this.method = method;
    // }

    // @Override
    // public void run() {
    //     try {
    //         switch (method) {
    //         case "IO":
    //             downloadUsingIO();
    //             break;
    //         case "NIO":
    //             downloadUsingnNIO();
    //             break;
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // private int BUFFER_SIZE = 1024;

    // private void downloadUsingIO() throws IOException {
    //     URL url = new URL(link);
    //     URLConnection connection = url.openConnection();
    //     HttpURLConnection http = (HttpURLConnection) connection;

    //     BufferedInputStream in = new BufferedInputStream(http.getInputStream());
    //     FileOutputStream fos = new FileOutputStream(out);

    //     double downloaded = 0;
    //     int read = 0;
    //     double dread = 0;
    //     double percentage = 0;

    //     double timeRemaining = 0;
    //     double totalTime = 0;
    //     long loopStart = Time.getTimeMS();
    //     long loopEnd = Time.getTimeMS();
    //     byte[] data = new byte[BUFFER_SIZE];
    //     while ((read = in.read(data, 0, BUFFER_SIZE)) != -1) {
    //       
    //         downloaded += read;
    //         dread += read;
    //         percentage = (downloaded * 100d) / fileSize;

    //         fos.write(data, 0, read);

    //         loopStart = loopEnd;
    //         loopEnd = Time.getTimeMS();

    //         float delta = loopEnd - loopStart;
    //         totalTime += delta;
    //         if (delta != 0)
    //             timeRemaining = (delta / read) * (fileSize - downloaded);

    //         pw.setText("file Type : " + fileType + "\n" + "size : " + fileSize + "B :: " + fileSize / 1000d + "KB :: "
    //                 + fileSize / 1000000d + "MB\n" + "downloaded : " + downloaded + "B :: " + downloaded / 1000d
    //                 + "KB :: " + downloaded / 1000000d + "MB\n" + "percentage : " + percentage + "%\n"
    //                 + "time remaining : " + Time.getFormat((long) timeRemaining, Time.FORMAT.MM_SS) + "s\n" + "speed : "
    //                 + downloadSpeed + "\n" + "time took : " + totalTime / 1000 + "s -- "
    //                 + Time.getFormat((long) totalTime, Time.FORMAT.MM_SS));
    //     }

    //     fos.close();
    //     in.close();
    // }




    // public void set(JTextArea out) {
    //     this.pw = out;
    // }

    // public void setBufferSize(int bufferSize) {
    //     this.BUFFER_SIZE = bufferSize;
    // }

}