import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utils.downloader.Download;
import utils.downloader.Status;
import utils.time.Time;

public class Test extends JFrame{

    private GridBagConstraints gbc;

    private Test() {

        GridBagLayout layout = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(layout);
        
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        

        JLabel link_labe = new JLabel("link");
        JLabel dir_labe = new JLabel("output");
        JLabel bufSize_labe = new JLabel("buffer size");
        JLabel out = new JLabel("",SwingConstants.LEFT);

        JButton start = new JButton("download");
        JButton stop = new JButton("stop Download");
        
        JTextField link = new JTextField(20);
        JTextField dir = new JTextField("./text.txt",20);
        JTextField bufferSize = new JTextField("1024",20);

        this.addComponent(link_labe, false);
        this.addComponent(link, false);
        this.addComponent(dir_labe, true);
        this.addComponent(dir, false);
        this.addComponent(bufSize_labe, true);
        this.addComponent(bufferSize, false);
        this.addComponent(start, true,0,2);
        this.addComponent(stop, true,0,2);
        this.addComponent(out, true,0,2);

        Download download = new Download();
        start.addActionListener(e->{
            download.setBufferSize(Integer.parseInt(bufferSize.getText()));
            download.setLink(link.getText());
            download.setOutputDir(dir.getText());
            download.start();
            (new Thread(()->{
                while(download.getStatus() != Status.STOPPED) {
                    out.setText(
                        "<html>"+
                        "fileSize : "+download.getFileSize()/1000f+"KB <br/>"+
                        "progress : "+download.getProgress()+"%<br/>"+
                        "download speed :"+download.getDownloadSpeed()/1000f+"KB/S <br/>"+
                        "               :"+download.getDownloadSpeed()/1000000f+"MB/S<br/>"+
                        "time elapsed : "+download.getTimeTookToDownload()/1000d+"S<br/>"+
                        "status : "+download.getStatus()+
                        "</html>"
                        );
                }
                out.setText(
                    "<html>"+
                    "fileSize : "+download.getFileSize()/1000f+"KB <br/>"+
                    "progress : "+download.getProgress()+"%<br/>"+
                    "download speed :"+download.getDownloadSpeed()/1000f+"KB/S <br/>"+
                    "               :"+download.getDownloadSpeed()/1000000f+"MB/S<br/>"+
                    "time elapsed : "+download.getTimeTookToDownload()/1000d+"S<br/>"+
                    "status : "+download.getStatus()+
                    "</html>"
                    );
            })).start();
        });

        stop.addActionListener(e->{
            if(download != null)
                download.stop();
        });
        
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Test();
    }

    private static int gx = -1, gy = 0;
    private void addComponent(JComponent component, boolean newLine) {
        gx++;
        if(newLine) {
            gx = 0; 
            gy++;
        }
        gbc.gridx = gx;
        gbc.gridy = gy;
        this.add(component,gbc);
    }

    private void addComponent(JComponent component, boolean newLine, int rowSpan, int colSpan) {
        if(colSpan != 0) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = colSpan;
        }
        if(rowSpan != 0){
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.gridheight = rowSpan;
        }
        addComponent(component, newLine);
    }

}