import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring Properties of text editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit ;
    //file menu item
    JMenuItem newFile , openFile ,saveFile;
    //edit menu item
    JMenuItem cut , copy, paste, selectAll, close;
    JTextArea textArea;


    TextEditor(){
       // initialize a frame
      frame = new JFrame();
      // initialize text area
        textArea = new JTextArea();

      //initialize a menubar
      menuBar = new JMenuBar();

      //initializing menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //add action listner to file menu items
        newFile.addActionListener(this); // this key word means new file of this text Editor object
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // adding action listner to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //adding  items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //add menu Bar to frame
        frame.setJMenuBar(menuBar);

        //add text area to frame
        //frame.add(textArea);
        //Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(3,3,3,3));
        panel.setLayout(new BorderLayout(0,0));
        // add the text area to the panel
        panel.add(textArea,BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
      // set dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setTitle("Text Editor");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){

//edit menu item
        if(actionEvent.getSource() == cut ){
            textArea.cut(); // inbuilt function on JTextArea
        }
        if(actionEvent.getSource() == copy ){
            textArea.copy(); // inbuilt function on JTextArea
        }
        if(actionEvent.getSource() ==paste ){
            textArea.paste(); // inbuilt function on JTextArea
        }
        if(actionEvent.getSource() == selectAll  ){
            textArea.selectAll(); // inbuilt function on JTextArea
        }
        if(actionEvent.getSource() == close ){
         // we just need to exit from code
            System.exit(0);
        }
        //file menu item

        // open file

if(actionEvent.getSource() == openFile ){
//open a file chooser
    JFileChooser chooseFile = new JFileChooser("C:");
    int chooseOption = chooseFile.showOpenDialog(null);
    //if we have clicked on open button
    if(chooseOption == JFileChooser.APPROVE_OPTION ){
       File file = chooseFile.getSelectedFile(); // select File
       String filePath = file.getPath(); // get the file path
        String fileNameToShow = file.getName();
        frame.setTitle(fileNameToShow);
        try{
            //initialize file reader
            FileReader fileReader = new FileReader(filePath);
            //initialize Buffered Reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String intermediate = "" ; // contains current line
            String output = ""; // contains complete text
            //read content of file line by line
            while((intermediate = bufferedReader.readLine()) != null){
                output += intermediate + "\n";
            }
            //set output String to text Area
            textArea.setText(output);
        }
        catch(IOException fileNotFoundException){
         fileNotFoundException.printStackTrace();
        }
    }

}

// save file

if(actionEvent.getSource() == saveFile ){
JFileChooser fileChooser = new JFileChooser("C:"); // location as passing val
int chooseOption =  fileChooser.showSaveDialog(null);
// check if we clicked on save buutton
if(chooseOption == JFileChooser.APPROVE_OPTION){
    //create a new file with chosen directory path and file name
    File file = new File(fileChooser.getSelectedFile().getAbsolutePath() +".txt");
try{ // save
//we have to write here
    FileWriter fileWriter = new FileWriter(file);
    //INITIALIZE BUFFERED writer instead buffered reader
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    // write contents of text area to file
    textArea.write(bufferedWriter);
    bufferedWriter.close();
}
catch(IOException ioException){ // exception
ioException.printStackTrace();
}
}
}

// new file

if(actionEvent.getSource() == newFile ){
TextEditor newTextEditor = new TextEditor();
}

    }
    public static void main(String[] args) {
   TextEditor textEditor = new TextEditor();
    }
}